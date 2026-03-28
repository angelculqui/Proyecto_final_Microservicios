package com.bank.credit_service.application.service;

import com.bank.credit_service.client.CustomerClient;
import com.bank.credit_service.domain.dto.CreateCreditCardRequest;
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

    public Flux<CreditCard> getAllCreditCards() {
        return creditCardRepository.findAll();
    }

    public Mono<CreditCard> getCreditCardById(String id) {
        return creditCardRepository.findById(id);
    }

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

    public Mono<Void> deleteCreditCard(String id) {
        return creditCardRepository.deleteById(id);
    }

    public Flux<CreditCard> getCreditCardsByClient(String clientId) {
        return creditCardRepository.findByClientId(clientId);
    }
}


