package com.bank.credit_service.domain.repository;

import com.bank.credit_service.domain.model.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interfaz que define las operaciones CRUD para los créditos.
 * Esta es la capa de dominio (puerto) y no depende de MongoDB.
 */
public interface CreditRepository {

    // Guardar un crédito
    Mono<Credit> save(Credit credit);

    // Buscar un crédito por su id
    Mono<Credit> findById(String id);

    // Obtener todos los créditos
    Flux<Credit> findAll();

    // Actualizar un crédito
    Mono<Credit> update(Credit credit);

    // Eliminar un crédito por id
    Mono<Void> deleteById(String id);

    // Buscar créditos de un cliente específico
    Flux<Credit> findByClientId(String clientId);
}