package com.bank.credit_service.application.service;

import com.bank.credit_service.client.CustomerClient;
import com.bank.credit_service.domain.dto.CreateCreditRequest;
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

    public Flux<Credit> getAllCredits() {
        return creditRepository.findAll();
    }

    public Mono<Credit> getCreditById(String id) {
        return creditRepository.findById(id);
    }

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

    public Mono<Void> deleteCredit(String id) {
        return creditRepository.deleteById(id);
    }

    public Flux<Credit> getCreditsByClient(String clientId) {
        return creditRepository.findByClientId(clientId);
    }
}


