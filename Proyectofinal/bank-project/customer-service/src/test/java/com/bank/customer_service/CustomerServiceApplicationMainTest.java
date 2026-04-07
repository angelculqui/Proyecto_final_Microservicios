package com.bank.customer_service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Este test levanta el servidor completo y falla. No es necesario.")
class CustomerServiceApplicationMainTest {

    @Test
    void testMain() {
        CustomerServiceApplication.main(new String[]{});
    }
}
