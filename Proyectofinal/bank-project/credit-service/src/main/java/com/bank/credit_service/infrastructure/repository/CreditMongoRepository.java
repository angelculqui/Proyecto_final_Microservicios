package com.bank.credit_service.infrastructure.repository;

import com.bank.credit_service.domain.model.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * Repositorio reactivo de MongoDB para créditos.
 * Este es el repositorio que Spring Data MongoDB implementa automáticamente.
 */
public interface CreditMongoRepository extends ReactiveMongoRepository<Credit, String> {

    // Método para buscar todos los créditos de un cliente
    Flux<Credit> findByClientId(String clientId);
}