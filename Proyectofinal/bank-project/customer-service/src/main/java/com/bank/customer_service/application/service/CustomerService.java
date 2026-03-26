package com.bank.customer_service.application.service;

import com.bank.customer_service.domain.model.Customer;
import com.bank.customer_service.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    // Obtener todos los clientes
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Obtener cliente por ID
    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    // Crear cliente
    public Customer createCustomer(Customer customer) {

        // Validación: evitar duplicados por documentNumber
        customerRepository.findByDocumentNumber(customer.getDocumentNumber())
                .ifPresent(c -> {
                    throw new RuntimeException("Customer with this document already exists");
                });

        return customerRepository.save(customer);
    }

    // Actualizar cliente
    public Customer updateCustomer(String id, Customer customer) {

        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        existing.setName(customer.getName());
        existing.setDocumentNumber(customer.getDocumentNumber());
        existing.setType(customer.getType());

        return customerRepository.save(existing);
    }

    // Eliminar cliente
    public void deleteCustomer(String id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customerRepository.delete(id);
    }
}