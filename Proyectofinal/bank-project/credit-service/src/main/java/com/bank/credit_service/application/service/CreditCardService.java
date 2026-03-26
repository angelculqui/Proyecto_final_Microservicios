package com.bank.credit_service.application.service;

import com.bank.credit_service.domain.model.CreditCard;
import com.bank.credit_service.domain.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Servicio de negocio para tarjetas de crédito.
 * Permite crear, actualizar, consultar y eliminar tarjetas de crédito.
 */
@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    /**
     * Crear una nueva tarjeta de crédito
     *
     * @param creditCard Objeto tarjeta de crédito a guardar
     * @return Mono con la tarjeta guardada
     */
    public Mono<CreditCard> createCreditCard(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    /**
     * Obtener todas las tarjetas
     *
     * @return Flux con todas las tarjetas
     */
    public Flux<CreditCard> getAllCreditCards() {
        return creditCardRepository.findAll();
    }

    /**
     * Obtener tarjeta por ID
     *
     * @param id ID de la tarjeta
     * @return Mono con la tarjeta si existe
     */
    public Mono<CreditCard> getCreditCardById(String id) {
        return creditCardRepository.findById(id);
    }

    /**
     * Actualizar tarjeta de crédito
     *
     * @param creditCard Objeto con datos actualizados
     * @return Mono con la tarjeta actualizada
     */
    public Mono<CreditCard> updateCreditCard(CreditCard creditCard) {
        return creditCardRepository.update(creditCard);
    }

    /**
     * Eliminar tarjeta de crédito
     *
     * @param id ID de la tarjeta
     * @return Mono vacío al completar
     */
    public Mono<Void> deleteCreditCard(String id) {
        return creditCardRepository.deleteById(id);
    }

    /**
     * Obtener tarjetas de un cliente
     *
     * @param clientId ID del cliente
     * @return Flux con las tarjetas del cliente
     */
    public Flux<CreditCard> getCreditCardsByClient(String clientId) {
        return creditCardRepository.findByClientId(clientId);
    }
}