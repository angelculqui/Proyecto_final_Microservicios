package com.bank.credit_service.infrastructure.repository;

import com.bank.credit_service.domain.model.CreditCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * Repositorio reactivo de MongoDB para tarjetas de crédito.
 */
public interface CreditCardMongoRepository extends ReactiveMongoRepository<CreditCard, String> {

    // Buscar todas las tarjetas de un cliente
    Flux<CreditCard> findByClientId(String clientId);
}