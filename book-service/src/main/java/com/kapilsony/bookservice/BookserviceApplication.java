package com.kapilsony.bookservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class BookserviceApplication implements CommandLineRunner {

	@Value("${testprop}")
	private String profile;

	public static void main(String[] args) {
		SpringApplication.run(BookserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("=====>>>"+profile);
	}
}
