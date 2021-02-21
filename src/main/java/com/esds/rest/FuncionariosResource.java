package com.esds.rest;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.esds.dto.CredenciaisDTO;
import com.esds.dto.FuncionarioEnderecoDTO;
import com.esds.dto.TokenDTO;
import com.esds.excecoes.SenhaInvalidaException;
import com.esds.modelo.AssistenteSocial;
import com.esds.modelo.Endereco;
import com.esds.modelo.Entrevistador;
import com.esds.modelo.Facilitador;
import com.esds.modelo.Funcionario;
import com.esds.security.jwt.JwtService;
import com.esds.servico.impl.EnderecoServiceImpl;
import com.esds.servico.impl.FuncionarioServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/funcionarios")
public class FuncionariosResource {
	
	@Autowired
	private FuncionarioServiceImpl funcionarioService;
	
	@Autowired
	private EnderecoServiceImpl enderecos;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/auth")
	public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) {
		
		try {			
			Funcionario funcionario = new Funcionario();
			funcionario.setLogin(credenciais.getLogin());
			funcionario.setSenha(credenciais.getSenha());
			
			funcionarioService.autenticar(funcionario);
			
			String token = jwtService.gerarToken(funcionario);
			
			return new TokenDTO(funcionario.getLogin(), token);
			
		} catch (UsernameNotFoundException | SenhaInvalidaException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}		
	}
		
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public FuncionarioEnderecoDTO buscarPorIdEndereco(@PathVariable Integer id) {
		
		FuncionarioEnderecoDTO funcionarioEnderecoDTO = new FuncionarioEnderecoDTO();

		List<Funcionario> funcionarios = funcionarioService.findByIdFuncionarioFetchEndereco(id);
		Funcionario funcionario = funcionarios.get(0);
		Funcionario funcionarioRetornado = null;
		
		if(funcionario instanceof AssistenteSocial) {
			funcionarioRetornado = new AssistenteSocial();
		}else if(funcionario instanceof Facilitador) {
			funcionarioRetornado = new Facilitador();
		}else {
			funcionarioRetornado = new Entrevistador();
		}
		
		funcionarioRetornado = funcionario;
		
		funcionarioEnderecoDTO.setSenha(funcionarioRetornado.getSenha());
		funcionarioEnderecoDTO.setNome(funcionarioRetornado.getNome());
		funcionarioEnderecoDTO.setRg(funcionarioRetornado.getRg());
		funcionarioEnderecoDTO.setRgDataEmissao(funcionarioRetornado.getRgDataEmissao());
		funcionarioEnderecoDTO.setRgOrgaoEmissor(funcionarioRetornado.getRgOrgaoEmissor());
		funcionarioEnderecoDTO.setCpf(funcionarioRetornado.getCpf());
		funcionarioEnderecoDTO.setTelefone1(funcionarioRetornado.getTelefone1());
		funcionarioEnderecoDTO.setTelefone2(funcionarioRetornado.getTelefone2());
		funcionarioEnderecoDTO.setMatricula(funcionarioRetornado.getMatricula());
		funcionarioEnderecoDTO.setLogin(funcionarioRetornado.getLogin());
		funcionarioEnderecoDTO.setEmail(funcionarioRetornado.getEmail());
		
		//Passar a data de nascimento sem a hora
		funcionarioEnderecoDTO.setDataNascimento(funcionarioRetornado.getDataNascimento());
		
		if(funcionarioRetornado.getSexo() != null) {
			String sexo = funcionarioRetornado.getSexo().getOpcao();
			funcionarioEnderecoDTO.setSexo(sexo);			
		}
		
		funcionarioEnderecoDTO.setIdEndereco(funcionarioRetornado.getEndereco().getId());
		funcionarioEnderecoDTO.setLogradouro(funcionarioRetornado.getEndereco().getLogradouro());
		funcionarioEnderecoDTO.setNumero(funcionarioRetornado.getEndereco().getNumero());
		funcionarioEnderecoDTO.setBairro(funcionarioRetornado.getEndereco().getBairro());
		funcionarioEnderecoDTO.setCidade(funcionarioRetornado.getEndereco().getCidade());
		funcionarioEnderecoDTO.setCep(funcionarioRetornado.getEndereco().getCep());
		funcionarioEnderecoDTO.setComplemento(funcionarioRetornado.getEndereco().getComplemento());
		funcionarioEnderecoDTO.setPontoDeReferencia(funcionarioRetornado.getEndereco().getPontoDeReferencia());
		
		return funcionarioEnderecoDTO;		

	}
	
	@DeleteMapping("{id}")
	public void remover(@PathVariable Integer id) {
		
		Funcionario funcionario = funcionarioService.buscarPorId(id);
		Endereco endereco = enderecos.buscarPorId(funcionario.getEndereco().getId());
		
		enderecos.remover(endereco.getId());		
		funcionarioService.remover(id);
	}
	
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<FuncionarioEnderecoDTO> buscarTodos() {
		
		List<FuncionarioEnderecoDTO> funcionariosDTO = new ArrayList<FuncionarioEnderecoDTO>();
		List<Funcionario> listaDeFuncionarios = funcionarioService.findByFuncionarioFetchEagerEndereco();
		
		for(Funcionario f: listaDeFuncionarios ) {
			FuncionarioEnderecoDTO funcionarioDTO = new FuncionarioEnderecoDTO();			
			
			funcionarioDTO.setId(f.getId());
			funcionarioDTO.setNome(f.getNome());
			funcionarioDTO.setRg(f.getRg());
			funcionarioDTO.setRgDataEmissao(f.getRgDataEmissao());
			funcionarioDTO.setRgOrgaoEmissor(f.getRgOrgaoEmissor());
			funcionarioDTO.setCpf(f.getCpf());
			funcionarioDTO.setTelefone1(f.getTelefone1());
			funcionarioDTO.setTelefone2(f.getTelefone2());
			funcionarioDTO.setSenha(f.getSenha());
			funcionarioDTO.setMatricula(f.getMatricula());
			funcionarioDTO.setLogin(f.getLogin());
			funcionarioDTO.setEmail(f.getEmail());
			
			//Passar a data de nascimento sem a hora
			funcionarioDTO.setDataNascimento(f.getDataNascimento());
			
			if(funcionarioDTO.getSexo() != null) {
				String sexo = f.getSexo().getOpcao();
				funcionarioDTO.setSexo(sexo);			
			}
			
			funcionarioDTO.setIdEndereco(f.getEndereco().getId());
			funcionarioDTO.setLogradouro(f.getEndereco().getLogradouro());
			funcionarioDTO.setNumero(f.getEndereco().getNumero());
			funcionarioDTO.setBairro(f.getEndereco().getBairro());
			funcionarioDTO.setCidade(f.getEndereco().getCidade());
			funcionarioDTO.setCep(f.getEndereco().getCep());
			funcionarioDTO.setComplemento(f.getEndereco().getComplemento());
			funcionarioDTO.setPontoDeReferencia(f.getEndereco().getPontoDeReferencia());
			
			funcionariosDTO.add(funcionarioDTO);
		}
		System.out.println();
		return funcionariosDTO;

	}

}
