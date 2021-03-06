package com.esds.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.esds.excecoes.RegraDeNegocioException;
import com.esds.excecoes.SenhaInvalidaException;
import com.esds.modelo.Funcionario;
import com.esds.repositorio.Funcionarios;
import com.esds.servico.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService, UserDetailsService{
	
	@Autowired
	private Funcionarios funcionarios;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Funcionario funcionario = funcionarios.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados!"));
		
		String[] roles = funcionario.isAdmin() ? 
				new String []{"ADMIN", "USER"} 
				: new String[]{"ADMIN"};
		
		return User
				.builder()
				.username(funcionario.getLogin())
				.password(funcionario.getSenha())
				.roles(roles)
				.build();
	}
	
	public UserDetails autenticar(Funcionario funcionario) {
		
		UserDetails usuario = loadUserByUsername(funcionario.getLogin());
		boolean senhasBatem = encoder.matches(funcionario.getSenha(), usuario.getPassword());
		
		if(senhasBatem) {
			return usuario;
		}
		throw new SenhaInvalidaException();
		
	}	

	@Override
	public void remover(Integer id) {

		funcionarios.findById(id).map(funcionario -> {
			funcionarios.delete(funcionario);
			return funcionario;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado"));

	}

	@Override
	public Funcionario buscarPorId(Integer id) {
		
		return funcionarios
				.findById(id)
				.orElseThrow(() -> 
				new RegraDeNegocioException("Não foi possível localizar funcionário com o ID informado!"));	
	}

	@Override
	public List<Funcionario> buscarTodos() {
		return funcionarios.findAll();
	}
	
	@Transactional(readOnly = true)
	public Page<Funcionario> findAllPageable(Pageable pageable) { 
		return funcionarios.findAll(pageable); 
	}

	public FuncionarioServiceImpl(Funcionarios funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public List <Funcionario> findByFuncionarioFetchEagerEndereco(){
		return funcionarios.findByFuncionarioFetchEagerEndereco();
	}
	
	public List<Funcionario> findByIdFuncionarioFetchEndereco(Integer id){
		return funcionarios.findByIdFuncionarioFetchEndereco(id);
	}
	
	public Funcionario verificarDisponibilidadeLogin(String login) {
		
		List<Funcionario> lista = funcionarios.verificarDisponibilidadeLogin(login);
				
		if(lista.size() > 0) {
			return lista.get(0);
		}if (lista.size() == 0) {
			return null;			
		}
		return null;
	}
	
	public Funcionario findByCPF(String cpf) {
		return funcionarios.findByCPF(cpf);
	}

}
