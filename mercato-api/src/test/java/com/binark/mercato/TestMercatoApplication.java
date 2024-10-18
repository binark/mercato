package com.binark.mercato;

import org.springframework.boot.SpringApplication;

public class TestMercatoApplication {

    public static void main(String[] args) {
        SpringApplication.from(MercatoApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
