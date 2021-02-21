package com.esds.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.header.writers.frameoptions.RegExpAllowFromStrategy;
import org.springframework.web.filter.OncePerRequestFilter;

import com.esds.security.jwt.JwtService;
import com.esds.servico.impl.FuncionarioServiceImpl;

/**
 * Classe que faz a integração entre o o JWT Service e o Spring Security
 * 
 */
public class JwtAuthFilter extends OncePerRequestFilter{
	
	private JwtService jwtService;
	private FuncionarioServiceImpl funcionarioService;

	public JwtAuthFilter(JwtService jwtService, FuncionarioServiceImpl funcionarioService) {
		this.jwtService = jwtService;
		this.funcionarioService = funcionarioService;
	}
	
	/**
	 * Método que intercepta uma requisição, recupera as informações
	 * e adiciona um usuário, se for válido, dentro da sessão do Security
	 * 
	 */
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain) throws ServletException, IOException {
		
		String authorization = request.getHeader("Authorization"); 
		
		if(authorization != null && authorization.startsWith("Bearer")) {
			
			String token = authorization.split(" ") [1];
			
			boolean isValido = jwtService.tokenValido(token);
			
			if(isValido) {
				String loginFuncionario = jwtService.obterLoginFuncionario(token);
				UserDetails usuario = funcionarioService.loadUserByUsername(loginFuncionario);
				
				UsernamePasswordAuthenticationToken user = 
						new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
				
				user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(user);
			}
		}
		
		filterChain.doFilter(request, response);		
	}

}
