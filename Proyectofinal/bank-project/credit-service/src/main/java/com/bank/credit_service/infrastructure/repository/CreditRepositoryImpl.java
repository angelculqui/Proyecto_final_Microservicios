package com.bank.credit_service.infrastructure.repository;

import com.bank.credit_service.domain.model.Credit;
import com.bank.credit_service.domain.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Implementación de la interfaz CreditRepository usando MongoDB.
 * Actúa como adaptador entre la lógica de negocio y la base de datos.
 */
@Repository
@RequiredArgsConstructor
public class CreditRepositoryImpl implements CreditRepository {

    private final CreditMongoRepository creditMongoRepository;

    @Override
    public Mono<Credit> save(Credit credit) {
        return creditMongoRepository.save(credit);
    }

    @Override
    public Mono<Credit> findById(String id) {
        return creditMongoRepository.findById(id);
    }

    @Override
    public Flux<Credit> findAll() {
        return creditMongoRepository.findAll();
    }

    @Override
    public Mono<Credit> update(Credit credit) {
        return creditMongoRepository.save(credit);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return creditMongoRepository.deleteById(id);
    }

    @Override
    public Flux<Credit> findByClientId(String clientId) {
        return creditMongoRepository.findByClientId(clientId);
    }
}