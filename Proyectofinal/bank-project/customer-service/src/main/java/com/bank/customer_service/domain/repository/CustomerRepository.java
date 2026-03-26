package com.bank.customer_service.domain.repository;

import com.bank.customer_service.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    List<Customer> findAll();

    Optional<Customer> findById(String id);

    Optional<Customer> findByDocumentNumber(String documentNumber); // 🔥 nuevo

    Customer save(Customer customer);

    void delete(String id);
}