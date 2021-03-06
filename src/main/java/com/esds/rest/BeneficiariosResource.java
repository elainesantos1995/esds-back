package com.esds.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.esds.dto.BeneficiarioEnderecoDTO;
import com.esds.enumeracoes.EstadoCivil;
import com.esds.enumeracoes.Sexo;
import com.esds.modelo.Beneficiario;
import com.esds.modelo.Endereco;
import com.esds.modelo.Imagem;
import com.esds.repositorio.Imagens;
import com.esds.servico.impl.BeneficiarioServiceImpl;
import com.esds.servico.impl.EnderecoServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/beneficiarios")
public class BeneficiariosResource{
	
	@Autowired
	private BeneficiarioServiceImpl beneficiarios;
	
	@Autowired
	private EnderecoServiceImpl enderecos;
	
	@Autowired
	private Imagens imagens;
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Beneficiario salvar(@RequestBody BeneficiarioEnderecoDTO beneficiarioEnderecoDTO) {
			
		Endereco endereco = new Endereco();
		endereco.setLogradouro(beneficiarioEnderecoDTO.getLogradouro());
		endereco.setNumero(beneficiarioEnderecoDTO.getNumero());
		endereco.setBairro(beneficiarioEnderecoDTO.getBairro());
		endereco.setCidade(beneficiarioEnderecoDTO.getCidade());
		endereco.setCep(beneficiarioEnderecoDTO.getCep());
		endereco.setComplemento(beneficiarioEnderecoDTO.getComplemento());
		endereco.setPontoDeReferencia(beneficiarioEnderecoDTO.getPontoDeReferencia());
		
		Beneficiario beneficiario = new Beneficiario();
		beneficiario.setNome(beneficiarioEnderecoDTO.getNome());
		beneficiario.setSobrenome(beneficiarioEnderecoDTO.getSobrenome());
		beneficiario.setRg(beneficiarioEnderecoDTO.getRg());
		beneficiario.setRgDataEmissao(beneficiarioEnderecoDTO.getRgDataEmissao());
		beneficiario.setRgOrgaoEmissor(beneficiarioEnderecoDTO.getRgOrgaoEmissor());
		beneficiario.setCpf(beneficiarioEnderecoDTO.getCpf());
		beneficiario.setTelefone1(beneficiarioEnderecoDTO.getTelefone1());
		beneficiario.setTelefone2(beneficiarioEnderecoDTO.getTelefone2());
		beneficiario.setEmail(beneficiarioEnderecoDTO.getEmail());
		beneficiario.setDataNascimento(beneficiarioEnderecoDTO.getDataNascimento());
		
		Sexo sexo = Sexo.valueOf(beneficiarioEnderecoDTO.getSexo());
		beneficiario.setSexo(sexo);
		
		EstadoCivil estadoCivil = EstadoCivil.valueOf(beneficiarioEnderecoDTO.getEstadoCivil());
		beneficiario.setEstadoCivil(estadoCivil);
		
		Optional<Imagem> img = null;
		Imagem imagem = new Imagem();
		try {
			img = imagens.retornarImagemPorUltimoId();
			imagem.setId(img.get().getId());
			imagem.setName(img.get().getName());
			imagem.setType(img.get().getType());
			imagem.setPicByte(decompressBytes(img.get().getPicByte()));
			beneficiario.setImagem(imagem);			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		enderecos.salvar(endereco);
		Endereco ultimo = enderecos.retornarEnderecoPorUltimoId();
		
		beneficiario.setEndereco(ultimo);		
		
		return beneficiarios.salvar(beneficiario);

	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody BeneficiarioEnderecoDTO beneficiarioEnderecoDTO) {
		
		Endereco endereco = new Endereco();		
		
		endereco.setLogradouro(beneficiarioEnderecoDTO.getLogradouro());
		endereco.setNumero(beneficiarioEnderecoDTO.getNumero());
		endereco.setBairro(beneficiarioEnderecoDTO.getBairro());
		endereco.setCidade(beneficiarioEnderecoDTO.getCidade());
		endereco.setCep(beneficiarioEnderecoDTO.getCep());
		endereco.setComplemento(beneficiarioEnderecoDTO.getComplemento());
		endereco.setPontoDeReferencia(beneficiarioEnderecoDTO.getPontoDeReferencia());
		
		Beneficiario beneficiario = new Beneficiario();
		beneficiario.setNome(beneficiarioEnderecoDTO.getNome());
		beneficiario.setSobrenome(beneficiarioEnderecoDTO.getSobrenome());
		beneficiario.setRg(beneficiarioEnderecoDTO.getRg());
		beneficiario.setRgDataEmissao(beneficiarioEnderecoDTO.getRgDataEmissao());
		beneficiario.setRgOrgaoEmissor(beneficiarioEnderecoDTO.getRgOrgaoEmissor());
		beneficiario.setCpf(beneficiarioEnderecoDTO.getCpf());
		beneficiario.setTelefone1(beneficiarioEnderecoDTO.getTelefone1());
		beneficiario.setTelefone2(beneficiarioEnderecoDTO.getTelefone2());
		beneficiario.setEmail(beneficiarioEnderecoDTO.getEmail());
		beneficiario.setDataNascimento(beneficiarioEnderecoDTO.getDataNascimento());

		Sexo sexo = Sexo.valueOf(beneficiarioEnderecoDTO.getSexo());
		beneficiario.setSexo(sexo);
		
		EstadoCivil estadoCivil = EstadoCivil.valueOf(beneficiarioEnderecoDTO.getEstadoCivil());
		beneficiario.setEstadoCivil(estadoCivil);
		
		System.out.println();
		Optional<Imagem> imagem = imagens.retornarImagemPorUltimoId();
		Imagem nova = new Imagem();
		nova = imagem.get();
				
		beneficiario.setImagem(nova);
		
		enderecos.atualizar(beneficiarioEnderecoDTO.getIdEndereco(), endereco);
		beneficiario.setEndereco(endereco);
		beneficiarios.atualizar(id, beneficiario);

	}


	@DeleteMapping("{id}")
	public void remover(@PathVariable Integer id) {
		
		Beneficiario beneficiario = beneficiarios.buscarPorId(id);
		Endereco endereco = enderecos.buscarPorId(beneficiario.getEndereco().getId());
		
		enderecos.remover(endereco.getId());
		beneficiarios.remover(id);		
	}

	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public BeneficiarioEnderecoDTO buscarPorId(@PathVariable Integer id) {
		
		List<Beneficiario> bene = beneficiarios.findByIdBeneficiarioFetchEndereco(id);
		Beneficiario beneficiario = bene.get(0);
		BeneficiarioEnderecoDTO beneficiarioEnderecoDTO = new BeneficiarioEnderecoDTO();
		
		beneficiarioEnderecoDTO.setId(beneficiario.getId());
		beneficiarioEnderecoDTO.setNome(beneficiario.getNome());
		beneficiarioEnderecoDTO.setSobrenome(beneficiario.getSobrenome());
		beneficiarioEnderecoDTO.setRg(beneficiario.getRg());
		beneficiarioEnderecoDTO.setRgDataEmissao(beneficiario.getRgDataEmissao());
		beneficiarioEnderecoDTO.setRgOrgaoEmissor(beneficiario.getRgOrgaoEmissor());
		beneficiarioEnderecoDTO.setCpf(beneficiario.getCpf());
		beneficiarioEnderecoDTO.setTelefone1(beneficiario.getTelefone1());
		beneficiarioEnderecoDTO.setTelefone2(beneficiario.getTelefone2());
		beneficiarioEnderecoDTO.setEmail(beneficiario.getEmail());
		
		beneficiarioEnderecoDTO.setDataNascimento(beneficiario.getDataNascimento());
		
		if(beneficiario.getSexo() != null) {
			String sexo = beneficiario.getSexo().getOpcao();
			beneficiarioEnderecoDTO.setSexo(sexo);			
		}
		
		if(beneficiario.getEstadoCivil() != null) {
			String estadoCivil = beneficiario.getEstadoCivil().getEstado();
			beneficiarioEnderecoDTO.setEstadoCivil(estadoCivil);			
		}
		
		
		beneficiarioEnderecoDTO.setIdEndereco(beneficiario.getEndereco().getId());
		beneficiarioEnderecoDTO.setLogradouro(beneficiario.getEndereco().getLogradouro());
		beneficiarioEnderecoDTO.setNumero(beneficiario.getEndereco().getNumero());
		beneficiarioEnderecoDTO.setBairro(beneficiario.getEndereco().getBairro());
		beneficiarioEnderecoDTO.setCidade(beneficiario.getEndereco().getCidade());
		beneficiarioEnderecoDTO.setCep(beneficiario.getEndereco().getCep());
		beneficiarioEnderecoDTO.setComplemento(beneficiario.getEndereco().getComplemento());
		beneficiarioEnderecoDTO.setPontoDeReferencia(beneficiario.getEndereco().getPontoDeReferencia());
		
		beneficiarioEnderecoDTO.setImagem(beneficiario.getImagem());
		
		return beneficiarioEnderecoDTO;
	}

	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<BeneficiarioEnderecoDTO> buscarTodos() {
		
		List<BeneficiarioEnderecoDTO> beneficiariosDTO = new ArrayList<BeneficiarioEnderecoDTO>();
		List<Beneficiario> listaDeBeneficiarios = beneficiarios.findByBeneficiarioFetchEagerEndereco();
		
		for(Beneficiario b: listaDeBeneficiarios ) {
			
			BeneficiarioEnderecoDTO beneficiario = new BeneficiarioEnderecoDTO();
			
			beneficiario.setId(b.getId());
			beneficiario.setNome(b.getNome());
			beneficiario.setSobrenome(b.getSobrenome());
			beneficiario.setRg(b.getRg());
			beneficiario.setRgDataEmissao(b.getRgDataEmissao());
			beneficiario.setRgOrgaoEmissor(b.getRgOrgaoEmissor());
			beneficiario.setCpf(b.getCpf());
			beneficiario.setTelefone1(b.getTelefone1());
			beneficiario.setTelefone2(b.getTelefone2());
			beneficiario.setEmail(b.getEmail());
			beneficiario.setDataNascimento(b.getDataNascimento());
		
			if(b.getImagem() != null) {		
				final Optional<Imagem> retrievedImage = imagens.findById(b.getImagem().getId());
				Imagem img = new Imagem(retrievedImage.get().getName(), retrievedImage.get().getType(),
				decompressBytes(retrievedImage.get().getPicByte()));
				beneficiario.setImagem(img);
				
			}
			
			if(b.getSexo() != null) {
				String sexo = b.getSexo().getOpcao();
				beneficiario.setSexo(sexo);				
			}
			
			if(b.getEstadoCivil() != null) {
				String estadoCivil = b.getEstadoCivil().getEstado();
				beneficiario.setEstadoCivil(estadoCivil);				
			}			
			
			beneficiario.setIdEndereco(b.getEndereco().getId());
			beneficiario.setLogradouro(b.getEndereco().getLogradouro());
			beneficiario.setNumero(b.getEndereco().getNumero());
			beneficiario.setBairro(b.getEndereco().getBairro());
			beneficiario.setCidade(b.getEndereco().getCidade());
			beneficiario.setCep(b.getEndereco().getCep());
			beneficiario.setComplemento(b.getEndereco().getComplemento());
			beneficiario.setPontoDeReferencia(b.getEndereco().getPontoDeReferencia());
			
			beneficiariosDTO.add(beneficiario);
		}
		
		return beneficiariosDTO;

	}
	
	// Comprime osbytes da imagem antes de salvar no banco de dados
		public static byte[] compressBytes(byte[] data) {

			Deflater deflater = new Deflater();

			deflater.setInput(data);

			deflater.finish();

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

			byte[] buffer = new byte[1024];

			while (!deflater.finished()) {
				int count = deflater.deflate(buffer);
				outputStream.write(buffer, 0, count);
			}

			try {
				outputStream.close();
			} catch (IOException e) {
			}

			System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
			return outputStream.toByteArray();

		}

		public static byte[] decompressBytes(byte[] data) {

			Inflater inflater = new Inflater();
			inflater.setInput(data);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];

			try {
				while (!inflater.finished()) {
					int count = inflater.inflate(buffer);
					outputStream.write(buffer, 0, count);
				}
				outputStream.close();
			} catch (IOException ioe) {

			} catch (DataFormatException e) {

			}
			return outputStream.toByteArray();
		}
		
		@GetMapping("/cpf/{cpf}")
		@ResponseStatus(HttpStatus.OK)
		public Beneficiario pegarPorCPF(@PathVariable String cpf) {
			return this.beneficiarios.findByCPF(cpf);
		}
	
	
		@GetMapping("/entity/pagination")
		@ResponseStatus(HttpStatus.OK)
		public Page<Beneficiario> findAllPageable(Pageable pageable) {
		
		Pageable sortedByAnoDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),Sort.by("nome").descending());
		// return beneficiarios.findAllPageable(sortedByAnoDesc);
			return beneficiarios.findByBeneficiarioFetchEagerEndereco(sortedByAnoDesc);
		}

}
