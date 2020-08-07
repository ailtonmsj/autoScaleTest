package br.com.amsj.infrastructure.autoscale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class AutoScaleTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoScaleTestApplication.class, args);
	}

}
