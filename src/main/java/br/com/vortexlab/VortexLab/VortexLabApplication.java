package br.com.vortexlab.VortexLab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VortexLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(VortexLabApplication.class, args);
	}

}
