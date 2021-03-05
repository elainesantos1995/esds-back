package com.esds.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.esds.security.jwt.JwtService;
import com.esds.servico.impl.FuncionarioServiceImpl;

/**
 * Classe de configuração do Spring Security
 *  
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{	
	
	@Autowired
	private FuncionarioServiceImpl funcionarioService;
	
	@Autowired
	private JwtService jwtService;
	
	/**
	 * Método que vai encriptar e descriptar as senhas informada
	 */
	@Bean
	public PasswordEncoder passwordEncoder () {		
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * Intercepta as requisições delegando a tarefa de setar o usuário no contexto da aplicaçao para o JWTFilter
	 */
	@Bean
	public OncePerRequestFilter jwtFilter() {
		return new JwtAuthFilter(jwtService, funcionarioService);
	}
	
	/**
	 * Carrega o objeto que faz a autenticação dos usuários
	 * e adiciona esses usuários no contexto do Security
	 * para realizar a autenticação de fato
	 * 
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(funcionarioService)
			.passwordEncoder(passwordEncoder());
	}
	
	/**
	 * Configuração da autorização aos end points verificando as autorizações
	 * que os usuários autenticados possuem para acessar cada verbo
	 * 
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/api/funcionarios/auth")
			.permitAll()
			.antMatchers(HttpMethod.GET, "/api/funcionarios/**")
			.permitAll()
			.antMatchers(HttpMethod.POST, "/api/funcionarios/**")
			.permitAll()
			.antMatchers(HttpMethod.DELETE, "/api/funcionarios/**")
			.permitAll()
			.antMatchers(HttpMethod.PUT, "/api/entrevistador/**")
			.permitAll()
			.antMatchers(HttpMethod.POST, "/api/entrevistador/**")
			.permitAll()
			.antMatchers(HttpMethod.POST, "api/assistente-social/**")
			.permitAll()
			.antMatchers(HttpMethod.POST, "api/facilitador/**")
			.permitAll()
			.antMatchers(HttpMethod.POST, "api/beneficiarios/**")
			.permitAll()
			.antMatchers(HttpMethod.POST, "api/enderecos/**")
			.permitAll()
			.antMatchers(HttpMethod.GET, "api/enderecos/**")
			.permitAll()
			.antMatchers(HttpMethod.PUT, "api/enderecos/**")
			.permitAll()
			.antMatchers(HttpMethod.POST, "api/programas-e-beneficios/**")
			.permitAll()
			.antMatchers(HttpMethod.GET, "api/programas-e-beneficios/**")
			.permitAll()
			.antMatchers(HttpMethod.PUT, "api/programas-e-beneficios/**")
			.permitAll()
			.antMatchers(HttpMethod.DELETE, "api/programas-e-beneficios/**")
			.permitAll()
			.antMatchers(HttpMethod.POST, "api/beneficios/**")
			.permitAll()
			.antMatchers(HttpMethod.GET, "api/beneficios/**")
			.permitAll()
			.antMatchers(HttpMethod.PUT, "api/beneficios/**")
			.permitAll()
			.antMatchers(HttpMethod.DELETE, "api/beneficios/**")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class); 
	}
	
}
