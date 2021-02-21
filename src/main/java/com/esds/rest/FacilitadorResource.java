package com.esds.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.esds.dto.FuncionarioEnderecoDTO;
import com.esds.enumeracoes.Sexo;
import com.esds.modelo.Endereco;
import com.esds.modelo.Facilitador;
import com.esds.modelo.Funcionario;
import com.esds.servico.impl.EnderecoServiceImpl;
import com.esds.servico.impl.FacilitadorServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/facilitador")
public class FacilitadorResource {
	
	@Autowired
	private FacilitadorServiceImpl facilitadores;	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EnderecoServiceImpl enderecos;
	
	@PostMapping()	
	@ResponseStatus(HttpStatus.CREATED)
	public Funcionario salvar(@RequestBody FuncionarioEnderecoDTO funcionarioEnderecoDTO) {

		Endereco endereco = new Endereco();
		endereco.setLogradouro(funcionarioEnderecoDTO.getLogradouro());
		endereco.setNumero(funcionarioEnderecoDTO.getNumero());
		endereco.setBairro(funcionarioEnderecoDTO.getBairro());
		endereco.setCidade(funcionarioEnderecoDTO.getCidade());
		endereco.setCep(funcionarioEnderecoDTO.getCep());
		endereco.setComplemento(funcionarioEnderecoDTO.getComplemento());
		endereco.setPontoDeReferencia(funcionarioEnderecoDTO.getPontoDeReferencia());
		
		Facilitador funcionario = new Facilitador();
		String senhaCriptografada = passwordEncoder.encode(funcionarioEnderecoDTO.getSenha());
		funcionario.setSenha(senhaCriptografada);
		funcionario.setNome(funcionarioEnderecoDTO.getNome());
		funcionario.setRg(funcionarioEnderecoDTO.getRg());
		funcionario.setRgDataEmissao(funcionarioEnderecoDTO.getRgDataEmissao());
		funcionario.setRgOrgaoEmissor(funcionarioEnderecoDTO.getRgOrgaoEmissor());
		funcionario.setCpf(funcionarioEnderecoDTO.getCpf());
		funcionario.setTelefone1(funcionarioEnderecoDTO.getTelefone1());
		funcionario.setTelefone2(funcionarioEnderecoDTO.getTelefone2());
		funcionario.setLogin(funcionarioEnderecoDTO.getLogin());
		funcionario.setEmail(funcionarioEnderecoDTO.getEmail());
		funcionario.setTipo(funcionarioEnderecoDTO.getTipo());
		funcionario.setAdmin(funcionarioEnderecoDTO.isAdmin());
		funcionario.setDataNascimento(funcionarioEnderecoDTO.getDataNascimento());

		Sexo sexo = Sexo.valueOf(funcionarioEnderecoDTO.getSexo());
		funcionario.setSexo(sexo);	
		
		enderecos.salvar(endereco);
		Endereco ultimo = enderecos.retornarEnderecoPorUltimoId();
		funcionario.setEndereco(ultimo);		
		return facilitadores.salvar(funcionario);

	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody FuncionarioEnderecoDTO funcionarioEnderecoDTO) {
		
		Endereco endereco = new Endereco();
		endereco.setLogradouro(funcionarioEnderecoDTO.getLogradouro());
		endereco.setNumero(funcionarioEnderecoDTO.getNumero());
		endereco.setBairro(funcionarioEnderecoDTO.getBairro());
		endereco.setCidade(funcionarioEnderecoDTO.getCidade());
		endereco.setCep(funcionarioEnderecoDTO.getCep());
		endereco.setComplemento(funcionarioEnderecoDTO.getComplemento());
		endereco.setPontoDeReferencia(funcionarioEnderecoDTO.getPontoDeReferencia());
		
		Facilitador funcionario = new Facilitador();
		String senhaCriptografada = passwordEncoder.encode(funcionarioEnderecoDTO.getSenha());
		funcionario.setSenha(senhaCriptografada);
		funcionario.setNome(funcionarioEnderecoDTO.getNome());
		funcionario.setRg(funcionarioEnderecoDTO.getRg());
		funcionario.setRgDataEmissao(funcionarioEnderecoDTO.getRgDataEmissao());
		funcionario.setRgOrgaoEmissor(funcionarioEnderecoDTO.getRgOrgaoEmissor());
		funcionario.setCpf(funcionarioEnderecoDTO.getCpf());
		funcionario.setTelefone1(funcionarioEnderecoDTO.getTelefone1());
		funcionario.setTelefone2(funcionarioEnderecoDTO.getTelefone2());
		funcionario.setLogin(funcionarioEnderecoDTO.getLogin());
		funcionario.setEmail(funcionarioEnderecoDTO.getEmail());
		funcionario.setTipo(funcionarioEnderecoDTO.getTipo());
		funcionario.setAdmin(funcionarioEnderecoDTO.isAdmin());
		funcionario.setDataNascimento(funcionarioEnderecoDTO.getDataNascimento());
			
		Sexo sexo = Sexo.valueOf(funcionarioEnderecoDTO.getSexo());
		funcionario.setSexo(sexo);	
		
		enderecos.atualizar(funcionarioEnderecoDTO.getIdEndereco(), endereco);
	//	funcionario.setEndereco(endereco);
		facilitadores.atualizar(id, funcionario);

	}

}
