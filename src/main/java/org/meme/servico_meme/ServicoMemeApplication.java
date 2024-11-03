package org.meme.servico_meme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicoMemeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicoMemeApplication.class, args);
	}

}
