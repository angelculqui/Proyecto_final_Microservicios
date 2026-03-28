package com.bank.credit_service.application.service;

import com.bank.credit_service.client.CustomerClient;
import com.bank.credit_service.domain.dto.CreateCreditRequest;
import com.bank.credit_service.domain.dto.PayCreditRequest;
import com.bank.credit_service.domain.model.Credit;
import com.bank.credit_service.domain.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final CreditRepository creditRepository;
    private final CustomerClient customerClient;

    // ============================
    // CREATE CREDIT
    // ============================
    public Mono<Credit> createCredit(CreateCreditRequest request) {

        if (request.getAmount() <= 0) {
            return Mono.error(new RuntimeException("Credit amount must be positive"));
        }

        if (!request.getCreditType().equals("PERSONAL") &&
                !request.getCreditType().equals("BUSINESS")) {
            return Mono.error(new RuntimeException("Invalid credit type"));
        }

        Credit credit = new Credit();
        credit.setClientId(request.getClientId());
        credit.setCreditType(request.getCreditType());
        credit.setAmount(BigDecimal.valueOf(request.getAmount()));
        credit.setBalance(BigDecimal.valueOf(request.getAmount()));
        credit.setInterestRate(request.getInterestRate());
        credit.setStartDate(LocalDate.now());
        credit.setDueDate(LocalDate.now().plusMonths(12));
        credit.setPaidOff(false);

        return customerClient.getCustomerById(request.getClientId())
                .flatMap(customer -> creditRepository.save(credit));
    }

    // ============================
    // GET ALL
    // ============================
    public Flux<Credit> getAllCredits() {
        return creditRepository.findAll();
    }

    // ============================
    // GET BY ID
    // ============================
    public Mono<Credit> getCreditById(String id) {
        return creditRepository.findById(id);
    }

    // ============================
    // UPDATE CREDIT
    // ============================
    public Mono<Credit> updateCredit(Credit credit) {
        return creditRepository.findById(credit.getId())
                .switchIfEmpty(Mono.error(new RuntimeException("Credit not found")))
                .flatMap(existing -> {

                    if (existing.isPaidOff()) {
                        return Mono.error(new RuntimeException("Credit already paid off"));
                    }

                    existing.setDueDate(credit.getDueDate());
                    existing.setInterestRate(credit.getInterestRate());

                    return creditRepository.update(existing);
                });
    }

    // ============================
    // DELETE CREDIT
    // ============================
    public Mono<Void> deleteCredit(String id) {
        return creditRepository.deleteById(id);
    }

    // ============================
    // GET CREDITS BY CLIENT
    // ============================
    public Flux<Credit> getCreditsByClient(String clientId) {
        return creditRepository.findByClientId(clientId);
    }

    // ============================================================
    // 🔥 NUEVO: PAGO DE CRÉDITO
    // ============================================================
    public Mono<Credit> payCredit(PayCreditRequest request) {

        if (request.getAmount() <= 0) {
            return Mono.error(new RuntimeException("Payment amount must be positive"));
        }

        return creditRepository.findById(request.getCreditId())
                .switchIfEmpty(Mono.error(new RuntimeException("Credit not found")))
                .flatMap(credit -> {

                    if (credit.isPaidOff()) {
                        return Mono.error(new RuntimeException("Credit already paid off"));
                    }

                    double newBalance = credit.getBalance().doubleValue() - request.getAmount();

                    if (newBalance < 0) {
                        return Mono.error(new RuntimeException("Payment exceeds remaining balance"));
                    }

                    credit.setBalance(BigDecimal.valueOf(newBalance));

                    if (newBalance == 0) {
                        credit.setPaidOff(true);
                    }

                    return creditRepository.update(credit);
                });
    }

    // ============================================================
    // 🔥 NUEVO: CONSULTA DE SALDO PENDIENTE
    // ============================================================
    public Mono<Double> getRemainingBalance(String id) {
        return creditRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Credit not found")))
                .map(c -> c.getBalance().doubleValue());
    }
}



