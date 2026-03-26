package com.bank.credit_service.infrastructure.controller;

import com.bank.credit_service.application.service.CreditCardService;
import com.bank.credit_service.domain.model.CreditCard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controlador REST para la gestión de tarjetas de crédito.
 * Expone endpoints CRUD y de consulta por cliente.
 */
@RestController
@RequestMapping("/api/credit-cards")
@RequiredArgsConstructor
public class CreditCardController {

    private final CreditCardService creditCardService;

    /**
     * Crear una nueva tarjeta de crédito
     * POST /api/credit-cards
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CreditCard> createCreditCard(@RequestBody CreditCard creditCard) {
        return creditCardService.createCreditCard(creditCard);
    }

    /**
     * Obtener todas las tarjetas
     * GET /api/credit-cards
     */
    @GetMapping
    public Flux<CreditCard> getAllCreditCards() {
        return creditCardService.getAllCreditCards();
    }

    /**
     * Obtener tarjeta por ID
     * GET /api/credit-cards/{id}
     */
    @GetMapping("/{id}")
    public Mono<CreditCard> getCreditCardById(@PathVariable String id) {
        return creditCardService.getCreditCardById(id);
    }

    /**
     * Actualizar tarjeta de crédito
     * PUT /api/credit-cards/{id}
     */
    @PutMapping("/{id}")
    public Mono<CreditCard> updateCreditCard(@PathVariable String id, @RequestBody CreditCard creditCard) {
        creditCard.setId(id);
        return creditCardService.updateCreditCard(creditCard);
    }

    /**
     * Eliminar tarjeta de crédito
     * DELETE /api/credit-cards/{id}
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCreditCard(@PathVariable String id) {
        return creditCardService.deleteCreditCard(id);
    }

    /**
     * Obtener tarjetas por cliente
     * GET /api/credit-cards/client/{clientId}
     */
    @GetMapping("/client/{clientId}")
    public Flux<CreditCard> getCreditCardsByClient(@PathVariable String clientId) {
        return creditCardService.getCreditCardsByClient(clientId);
    }
}