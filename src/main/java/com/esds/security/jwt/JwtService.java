package com.esds.security.jwt;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.esds.RestSpringBootAppApplication;
import com.esds.modelo.Endereco;
import com.esds.modelo.Entrevistador;
import com.esds.modelo.Funcionario;
import com.esds.repositorio.Enderecos;
import com.esds.servico.impl.EnderecoServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Classe de configuração do JWT
 * 
 */
@Service
public class JwtService {
	
	@Value("${security.jwt.expiracao}")
	private String expiracao;
	
	@Value("${security.jwt.chave-assinatura}")
	private String chaveAssinatura;
	
	/**
	 * Método que gera um token
	 */
	public String gerarToken(Funcionario funcionario) {
		
		long expString = Long.valueOf(expiracao);
		
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
		
		Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
		
		Date data = Date.from(instant);
		
		return Jwts
				.builder() 
				.setSubject(funcionario.getLogin()) /*identificação para o usuário que está fazendo a requisição*/
				.setExpiration(data) 				/*tempo de expiração do token*/
				.signWith(SignatureAlgorithm.HS512, chaveAssinatura) /*assinatura do token que recebe um algoritmo que criptografa o token e uma chave de assinatura*/
				.compact(); /*geração da string do token de acordo com todas as informações*/
		
	}
	
	private Claims obterClaims(String token) throws ExpiredJwtException {
		return Jwts
				.parser()
				.setSigningKey(chaveAssinatura)
				.parseClaimsJws(token)
				.getBody();
	}
	
	public boolean tokenValido(String token) {
		try {
			Claims clains = obterClaims(token);
			Date dataExpiracao = clains.getExpiration();
			LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			return !LocalDateTime.now().isAfter(data);
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Retorna as informações que foram passadas no token
	 */
	public String obterLoginFuncionario (String token) throws ExpiredJwtException{
		return (String) obterClaims(token).getSubject();
	}
	
	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = SpringApplication.run(RestSpringBootAppApplication.class);
		JwtService service = context.getBean(JwtService.class);
		Funcionario funcionario = new Entrevistador();
		funcionario.setLogin("elaine");
		String token = service.gerarToken(funcionario);
		System.out.println(token);
		
		boolean isTokenValido = service.tokenValido(token);
		System.out.println("O token está válido? " + isTokenValido);
		
		System.out.println(service.obterLoginFuncionario(token));		
	}

}
