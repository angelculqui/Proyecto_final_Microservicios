package com.bank.credit_service.infrastructure.repository;

import com.bank.credit_service.domain.model.CreditCard;
import com.bank.credit_service.domain.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Implementación de CreditCardRepository usando MongoDB.
 * Permite a la capa de aplicación manipular tarjetas de crédito.
 */
@Repository
@RequiredArgsConstructor
public class CreditCardRepositoryImpl implements CreditCardRepository {

    private final CreditCardMongoRepository creditCardMongoRepository;

    @Override
    public Mono<CreditCard> save(CreditCard creditCard) {
        return creditCardMongoRepository.save(creditCard);
    }

    @Override
    public Mono<CreditCard> findById(String id) {
        return creditCardMongoRepository.findById(id);
    }

    @Override
    public Flux<CreditCard> findAll() {
        return creditCardMongoRepository.findAll();
    }

    @Override
    public Mono<CreditCard> update(CreditCard creditCard) {
        return creditCardMongoRepository.save(creditCard);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return creditCardMongoRepository.deleteById(id);
    }

    @Override
    public Flux<CreditCard> findByClientId(String clientId) {
        return creditCardMongoRepository.findByClientId(clientId);
    }
}