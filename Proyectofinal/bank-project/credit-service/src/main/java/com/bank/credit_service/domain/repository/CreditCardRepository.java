package com.bank.credit_service.domain.repository;

import com.bank.credit_service.domain.model.CreditCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interfaz que define las operaciones CRUD para las tarjetas de crédito.
 * Es la capa de dominio (puerto) y no depende de MongoDB.
 */
public interface CreditCardRepository {

    // Guardar una tarjeta de crédito
    Mono<CreditCard> save(CreditCard creditCard);

    // Buscar tarjeta por id
    Mono<CreditCard> findById(String id);

    // Obtener todas las tarjetas
    Flux<CreditCard> findAll();

    // Actualizar tarjeta
    Mono<CreditCard> update(CreditCard creditCard);

    // Eliminar tarjeta por id
    Mono<Void> deleteById(String id);

    // Buscar tarjetas de un cliente específico
    Flux<CreditCard> findByClientId(String clientId);
}