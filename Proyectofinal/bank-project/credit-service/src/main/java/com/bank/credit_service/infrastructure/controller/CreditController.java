package com.bank.credit_service.infrastructure.controller;

import com.bank.credit_service.application.service.CreditService;
import com.bank.credit_service.domain.dto.CreateCreditRequest;
import com.bank.credit_service.domain.model.Credit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/credits")
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Credit> createCredit(@RequestBody CreateCreditRequest request) {
        return creditService.createCredit(request);
    }

    @GetMapping
    public Flux<Credit> getAllCredits() {
        return creditService.getAllCredits();
    }

    @GetMapping("/{id}")
    public Mono<Credit> getCreditById(@PathVariable String id) {
        return creditService.getCreditById(id);
    }

    @PutMapping("/{id}")
    public Mono<Credit> updateCredit(@PathVariable String id, @RequestBody Credit credit) {
        credit.setId(id);
        return creditService.updateCredit(credit);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCredit(@PathVariable String id) {
        return creditService.deleteCredit(id);
    }

    @GetMapping("/client/{clientId}")
    public Flux<Credit> getCreditsByClient(@PathVariable String clientId) {
        return creditService.getCreditsByClient(clientId);
    }
}
