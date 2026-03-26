package com.bank.credit_service.application.service;

import com.bank.credit_service.domain.model.Credit;
import com.bank.credit_service.domain.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Servicio de negocio para créditos.
 * Contiene la lógica de creación, actualización, consulta y eliminación de créditos.
 */
@Service
@RequiredArgsConstructor
public class CreditService {

    private final CreditRepository creditRepository;

    /**
     * Crear un nuevo crédito
     *
     * @param credit Objeto crédito a guardar
     * @return Mono con el crédito guardado
     */
    public Mono<Credit> createCredit(Credit credit) {
        return creditRepository.save(credit);
    }

    /**
     * Obtener todos los créditos
     *
     * @return Flux con todos los créditos
     */
    public Flux<Credit> getAllCredits() {
        return creditRepository.findAll();
    }

    /**
     * Obtener crédito por ID
     *
     * @param id ID del crédito
     * @return Mono con el crédito si existe
     */
    public Mono<Credit> getCreditById(String id) {
        return creditRepository.findById(id);
    }

    /**
     * Actualizar un crédito existente
     *
     * @param credit Objeto crédito con datos actualizados
     * @return Mono con el crédito actualizado
     */
    public Mono<Credit> updateCredit(Credit credit) {
        return creditRepository.update(credit);
    }

    /**
     * Eliminar un crédito por ID
     *
     * @param id ID del crédito
     * @return Mono vacío al completar la eliminación
     */
    public Mono<Void> deleteCredit(String id) {
        return creditRepository.deleteById(id);
    }

    /**
     * Obtener créditos por cliente
     *
     * @param clientId ID del cliente
     * @return Flux con todos los créditos del cliente
     */
    public Flux<Credit> getCreditsByClient(String clientId) {
        return creditRepository.findByClientId(clientId);
    }
}