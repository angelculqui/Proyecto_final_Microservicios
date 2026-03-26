package com.bank.credit_service.infrastructure.controller;

import com.bank.credit_service.application.service.CreditService;
import com.bank.credit_service.domain.model.Credit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controlador REST para la gestión de créditos.
 * Expone endpoints CRUD y de consulta por cliente.
 */
@RestController
@RequestMapping("/api/credits")
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;

    /**
     * Crear un nuevo crédito
     * POST /api/credits
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Credit> createCredit(@RequestBody Credit credit) {
        return creditService.createCredit(credit);
    }

    /**
     * Obtener todos los créditos
     * GET /api/credits
     */
    @GetMapping
    public Flux<Credit> getAllCredits() {
        return creditService.getAllCredits();
    }

    /**
     * Obtener crédito por ID
     * GET /api/credits/{id}
     */
    @GetMapping("/{id}")
    public Mono<Credit> getCreditById(@PathVariable String id) {
        return creditService.getCreditById(id);
    }

    /**
     * Actualizar un crédito existente
     * PUT /api/credits/{id}
     */
    @PutMapping("/{id}")
    public Mono<Credit> updateCredit(@PathVariable String id, @RequestBody Credit credit) {
        credit.setId(id);
        return creditService.updateCredit(credit);
    }

    /**
     * Eliminar un crédito por ID
     * DELETE /api/credits/{id}
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCredit(@PathVariable String id) {
        return creditService.deleteCredit(id);
    }

    /**
     * Obtener créditos por cliente
     * GET /api/credits/client/{clientId}
     */
    @GetMapping("/client/{clientId}")
    public Flux<Credit> getCreditsByClient(@PathVariable String clientId) {
        return creditService.getCreditsByClient(clientId);
    }
}