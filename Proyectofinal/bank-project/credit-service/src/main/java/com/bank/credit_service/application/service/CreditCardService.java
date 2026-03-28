package com.bank.credit_service.application.service;

import com.bank.credit_service.client.CustomerClient;
import com.bank.credit_service.domain.dto.CreateCreditCardRequest;
import com.bank.credit_service.domain.dto.ConsumeCreditCardRequest;
import com.bank.credit_service.domain.model.CreditCard;
import com.bank.credit_service.domain.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final CustomerClient customerClient;

    // ============================
    // CREATE CREDIT CARD
    // ============================
    public Mono<CreditCard> createCreditCard(CreateCreditCardRequest request) {

        if (!request.getCardType().equals("PERSONAL") &&
                !request.getCardType().equals("BUSINESS")) {
            return Mono.error(new RuntimeException("Invalid card type"));
        }

        if (request.getCreditLimit() <= 0) {
            return Mono.error(new RuntimeException("Credit limit must be positive"));
        }

        CreditCard card = new CreditCard();
        card.setClientId(request.getClientId());
        card.setCardType(request.getCardType());
        card.setCardNumber(request.getCardNumber());
        card.setCreditLimit(BigDecimal.valueOf(request.getCreditLimit()));
        card.setCurrentBalance(BigDecimal.ZERO);
        card.setExpirationDate(LocalDate.parse(request.getExpirationDate()));
        card.setActive(true);

        return customerClient.getCustomerById(request.getClientId())
                .flatMap(customer -> creditCardRepository.save(card));
    }

    // ============================
    // GET ALL
    // ============================
    public Flux<CreditCard> getAllCreditCards() {
        return creditCardRepository.findAll();
    }

    // ============================
    // GET BY ID
    // ============================
    public Mono<CreditCard> getCreditCardById(String id) {
        return creditCardRepository.findById(id);
    }

    // ============================
    // UPDATE
    // ============================
    public Mono<CreditCard> updateCreditCard(CreditCard card) {
        return creditCardRepository.findById(card.getId())
                .switchIfEmpty(Mono.error(new RuntimeException("Card not found")))
                .flatMap(existing -> {

                    if (!existing.isActive()) {
                        return Mono.error(new RuntimeException("Card is inactive"));
                    }

                    existing.setCreditLimit(card.getCreditLimit());
                    existing.setExpirationDate(card.getExpirationDate());

                    return creditCardRepository.update(existing);
                });
    }

    // ============================
    // DELETE
    // ============================
    public Mono<Void> deleteCreditCard(String id) {
        return creditCardRepository.deleteById(id);
    }

    // ============================
    // GET BY CLIENT
    // ============================
    public Flux<CreditCard> getCreditCardsByClient(String clientId) {
        return creditCardRepository.findByClientId(clientId);
    }

    // ============================================================
    // 🔥 NUEVO: CONSUMO DE TARJETA
    // ============================================================
    public Mono<CreditCard> consume(ConsumeCreditCardRequest request) {

        if (request.getAmount() <= 0) {
            return Mono.error(new RuntimeException("Amount must be positive"));
        }

        return creditCardRepository.findById(request.getCardId())
                .switchIfEmpty(Mono.error(new RuntimeException("Credit card not found")))
                .flatMap(card -> {

                    double available = card.getCreditLimit().doubleValue() - card.getCurrentBalance().doubleValue();

                    if (request.getAmount() > available) {
                        return Mono.error(new RuntimeException("Insufficient credit limit"));
                    }

                    double newBalance = card.getCurrentBalance().doubleValue() + request.getAmount();
                    card.setCurrentBalance(BigDecimal.valueOf(newBalance));

                    return creditCardRepository.update(card);
                });
    }

    // ============================================================
    // 🔥 NUEVO: CONSULTA DE LÍMITE DISPONIBLE
    // ============================================================
    public Mono<Double> getAvailableLimit(String id) {
        return creditCardRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Credit card not found")))
                .map(card -> card.getCreditLimit().doubleValue() - card.getCurrentBalance().doubleValue());
    }
}


