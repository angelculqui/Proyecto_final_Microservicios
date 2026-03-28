package com.bank.credit_service.infrastructure.controller;

import com.bank.credit_service.application.service.CreditCardService;
import com.bank.credit_service.domain.dto.CreateCreditCardRequest;
import com.bank.credit_service.domain.dto.ConsumeCreditCardRequest;
import com.bank.credit_service.domain.model.CreditCard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/credit-cards")
@RequiredArgsConstructor
public class CreditCardController {

    private final CreditCardService creditCardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CreditCard> createCreditCard(@RequestBody CreateCreditCardRequest request) {
        return creditCardService.createCreditCard(request);
    }

    @GetMapping
    public Flux<CreditCard> getAllCreditCards() {
        return creditCardService.getAllCreditCards();
    }

    @GetMapping("/{id}")
    public Mono<CreditCard> getCreditCardById(@PathVariable String id) {
        return creditCardService.getCreditCardById(id);
    }

    @PutMapping("/{id}")
    public Mono<CreditCard> updateCreditCard(@PathVariable String id, @RequestBody CreditCard creditCard) {
        creditCard.setId(id);
        return creditCardService.updateCreditCard(creditCard);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCreditCard(@PathVariable String id) {
        return creditCardService.deleteCreditCard(id);
    }

    @GetMapping("/client/{clientId}")
    public Flux<CreditCard> getCreditCardsByClient(@PathVariable String clientId) {
        return creditCardService.getCreditCardsByClient(clientId);
    }

    // 🔥 NUEVO: Consumo de tarjeta
    @PostMapping("/consume")
    public Mono<CreditCard> consume(@RequestBody ConsumeCreditCardRequest request) {
        return creditCardService.consume(request);
    }

    // 🔥 NUEVO: Consulta de límite disponible
    @GetMapping("/{id}/available-limit")
    public Mono<Double> getAvailableLimit(@PathVariable String id) {
        return creditCardService.getAvailableLimit(id);
    }
}

