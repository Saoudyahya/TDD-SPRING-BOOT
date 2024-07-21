package com.example.tdd_DDD_OUTH2_;

import org.springframework.boot.SpringApplication;

public class TestTddDddOuth2Application {

	public static void main(String[] args) {
		SpringApplication.from(TddDddOuth2Application::main).with(TestcontainersConfiguration.class).run(args);
	}

}
