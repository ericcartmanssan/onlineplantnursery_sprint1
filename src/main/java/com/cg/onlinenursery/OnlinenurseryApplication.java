package com.cg.onlinenursery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@ComponentScan(basePackages= {"com.cg.onlinenursery"})
public class OnlinenurseryApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinenurseryApplication.class, args);
	}

}
