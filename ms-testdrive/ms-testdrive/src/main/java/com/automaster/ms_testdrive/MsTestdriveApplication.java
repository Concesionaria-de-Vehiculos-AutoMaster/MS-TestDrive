package com.automaster.ms_testdrive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsTestdriveApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTestdriveApplication.class, args);
	}
}