package com.magatella.arrayprocessservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@OpenAPIDefinition(
        info = @Info(
                title = "RESTfull API Relex",
                version = "0.1",
                description = "RESTfull API service для обработки последовательности чисел",
                contact = @Contact(url = "https://t.me/Magatella", name = "Magomed Muradkhanov", email = "magamuradkhanov@gmail.com")
        )
)

@SpringBootApplication
@EnableCaching
public class ArrayProcessServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArrayProcessServiceApplication.class, args);
    }
}
