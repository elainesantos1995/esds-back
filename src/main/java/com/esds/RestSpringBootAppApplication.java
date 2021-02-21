package com.esds;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.esds.modelo.Entrevistador;
import com.esds.modelo.Funcionario;
import com.esds.repositorio.Funcionarios;
import com.esds.security.jwt.JwtService;

@SpringBootApplication
public class RestSpringBootAppApplication {
	
	@Autowired
	private PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(RestSpringBootAppApplication.class, args);
	}
	
//	@Bean
//	public CommandLineRunner insertSuperUser(Funcionarios repo) {
//		return args -> {
//			Optional<Funcionario> superUsuario = repo.findByLogin("admin");
//			if(!superUsuario.isPresent()) {
//				Funcionario funcionario = new Entrevistador();
//				funcionario.setAdmin(true);
//				funcionario.setNome("Administrador");
//				funcionario.setCpf("99999999999");
//				funcionario.setMatricula("0000000000");
//				funcionario.setRg("00000999999");
//				funcionario.setRgDataEmissao("");
//				funcionario.setRgDataEmissao("ADMIN");
//				funcionario.setDataNascimento(new Date());
//				funcionario.setLogin("admin");
//				funcionario.setSenha(encoder.encode("123"));
//				repo.save(funcionario);
//			}
//		};
//	}

}
