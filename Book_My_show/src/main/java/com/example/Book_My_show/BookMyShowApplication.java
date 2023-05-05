package com.example.Book_My_show;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class BookMyShowApplication {

	public static void main(String[] args) {

		SpringApplication.run(BookMyShowApplication.class, args);
	}

}
