package com.binark.mercato;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class MercatoApplicationTests {

    @Test
    void contextLoads() {
    }

}
