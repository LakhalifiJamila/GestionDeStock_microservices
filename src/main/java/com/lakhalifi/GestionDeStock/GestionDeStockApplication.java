package com.lakhalifi.GestionDeStock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaAuditing //pour que @CreatedDate & @LastModifiedDate marchent
/*
Lorsque vous ajoutez l'annotation @EnableJpaAuditing à votre configuration Spring,
cela active la prise en charge de l'audit au niveau de JPA, et vous pouvez utiliser
des annotations telles que @CreatedDate, @LastModifiedDate, et @CreatedBy pour marquer
les champs de vos entités en fonction de l'audit souhaité.
*/
public class GestionDeStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionDeStockApplication.class, args);
	}

}
