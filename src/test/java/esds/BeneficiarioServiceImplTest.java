package esds;

import static org.assertj.core.api.Assertions.assertThatObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import com.esds.enumeracoes.Sexo;
import com.esds.excecoes.RegraDeNegocioException;
import com.esds.modelo.Beneficiario;
import com.esds.modelo.Endereco;
import com.esds.modelo.Imagem;
import com.esds.repositorio.Beneficiarios;
import com.esds.servico.impl.BeneficiarioServiceImpl;

import junit.framework.TestCase;
import org.junit.Assert;

@RunWith(SpringRunner.class)
public class BeneficiarioServiceImplTest {

	@InjectMocks
	private BeneficiarioServiceImpl beneficiarioServiceImpl;

	@Mock
	private Beneficiarios beneficiarios;

	Beneficiario beneficiario;
	Endereco endereco;
	Imagem imagem;

	@Before
	public void setup() {
		beneficiario = new Beneficiario();
		beneficiario.setId(1);
		beneficiario.setNome("BeneficiarioTeste");
		beneficiario.setDataNascimento(new Date());
		beneficiario.setCpf("12345678910");
		beneficiario.setRg("987654321");
		beneficiario.setRgDataEmissao("2020-01-01");
		beneficiario.setRgOrgaoEmissor("SDS PE");
		beneficiario.setTelefone1("1234-4321");
		beneficiario.setTelefone2("5678-8765");
		beneficiario.setSexo(Sexo.MASCULINO);
		beneficiario.setEmail("teste@teste.com");

		endereco = new Endereco();
		endereco.setId(1);
		endereco.setLogradouro("Rua Teste");
		endereco.setNumero("1");
		endereco.setComplemento("Apto Teste");
		endereco.setBairro("Bairro Teste");
		endereco.setCidade("Cidade Teste");
		endereco.setCep("12.345-678");
		endereco.setPontoDeReferencia("Referencia Teste");

		beneficiario.setEndereco(endereco);
		
		imagem = new Imagem();
		imagem.setId(1);
		
	}
	
	@Test
	public void testeSalvarBeneficiarioComSucesso() {		
		Beneficiario beneficiarioSalvo = beneficiarioServiceImpl.salvar(beneficiario);		
		assertThatObject(beneficiarioSalvo);
	}
	
	@Test
	public void testeSalvarBeneficiarioComErro() {		
		Beneficiario beneficiarioSalvo = beneficiarioServiceImpl.salvar(beneficiario);		
		assertEquals(null, beneficiarioSalvo);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void testeAtualizarBeneficiarioComSucesso() {
		beneficiario.setNome("Nome Atualizado");		
		beneficiarioServiceImpl.atualizar(1, beneficiario);	
		
		when(beneficiarios.findById(Mockito.anyInt())).thenReturn(Optional.of(beneficiario)).thenReturn(Optional.of(beneficiario));
		Beneficiario beneficiarioRetornado = beneficiarioServiceImpl.buscarPorId(1);
		Assert.assertEquals(beneficiarioRetornado.getId(), 1);
		Assert.assertEquals(beneficiarioRetornado.getNome(), "Nome Atualizado");
	}
	
	@Test(expected = ResponseStatusException.class)
	public void testeAtualizarBeneficiarioComErro() {
		beneficiario.setNome("Nome Atualizado");		
		beneficiarioServiceImpl.atualizar(1, beneficiario);	
		
		when(beneficiarios.findById(Mockito.anyInt())).thenReturn(Optional.of(beneficiario)).thenReturn(Optional.of(beneficiario));
		Beneficiario beneficiarioRetornado = beneficiarioServiceImpl.buscarPorId(1);
		Assert.assertNotEquals(beneficiarioRetornado.getId(), beneficiario.getId());
	}	
	
	@Test
	public void testeRemoveBeneficiarioComSucesso() {

		when(beneficiarios.findById(Mockito.anyInt())).thenReturn(Optional.of(beneficiario)).thenReturn(Optional.empty());
		beneficiarioServiceImpl.remover(1);
		
	}
	
	@Test(expected = ResponseStatusException.class)
	public void testeRemoveBeneficiarioNaoEncontrado() {

		when(beneficiarios.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		beneficiarioServiceImpl.remover(1);
	}
	
	@Test
	public void testeBuscarBeneficiarioPorIdComSucesso() {
		
		when(beneficiarios.findById(Mockito.anyInt())).thenReturn(Optional.of(beneficiario)).thenReturn(Optional.of(beneficiario));
		Beneficiario beneficiarioRetornado = beneficiarioServiceImpl.buscarPorId(1);
		TestCase.assertEquals(beneficiarioRetornado.getId(), 1);
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeBuscarBeneficiarioPorIdNaoEncontrado() {
		
		when(beneficiarios.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		beneficiarioServiceImpl.buscarPorId(1);
	}
	
	@Test
	public void testeBuscarBeneficiarios() {
		List<Beneficiario> beneficiarios = beneficiarioServiceImpl.buscarTodos();
		assertThatObject(beneficiarios);
	}
	
	public void atualizarImgBeneficiario() {
		
	}
	
}