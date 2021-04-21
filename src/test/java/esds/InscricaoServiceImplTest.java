package esds;

import static org.assertj.core.api.Assertions.assertThatObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.esds.enumeracoes.StatusInscricao;
import com.esds.excecoes.RegraDeNegocioException;
import com.esds.modelo.Beneficiario;
import com.esds.modelo.Beneficio;
import com.esds.modelo.Inscricao;
import com.esds.repositorio.Inscricoes;
import com.esds.servico.impl.InscricaoServiceImpl;

@RunWith(SpringRunner.class)
public class InscricaoServiceImplTest {
	
	@InjectMocks
	private InscricaoServiceImpl inscricaoService;	

	@Mock
	private Inscricoes inscricoes;
	
	Inscricao inscricao;
	Beneficiario beneficiario;
	Beneficio beneficio;
	
	@Before
	public void setup() {
		
		beneficiario = new Beneficiario();
		beneficiario.setId(1);
		beneficio = new Beneficio();
		beneficio.setId(1);
		
		inscricao = new Inscricao();
		inscricao.setId(1);
		inscricao.setDataInscricao(new Date());
		inscricao.setParecer("Parecer 1");
		inscricao.setStatus(StatusInscricao.EM_ANALISE);
		inscricao.setBeneficiario(beneficiario);
		inscricao.setBeneficiario(beneficiario);
		inscricao.setQuantBeneficiosASeremRetiradados(0);
		inscricao.setQuantBeneficiosRetirados(0);
	
	}
	
	@Test
	public void testeSalvarInscricaoComSucesso() {		
		Inscricao inscricaoSalva = inscricaoService.salvar(inscricao);		
		assertThatObject(inscricaoSalva);
	}
	
	@Test
	public void testeSalvarInscricaoComErro() {		
		Inscricao inscricaoSalva = inscricaoService.salvar(inscricao);		
		assertEquals(null, inscricaoSalva);
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeAtualizarInscricaoComSucesso() {
		inscricao.setParecer("Parecer Atualizado");		
		inscricaoService.atualizar(1, inscricao);	
		
		when(inscricoes.findById(Mockito.anyInt())).thenReturn(Optional.of(inscricao)).thenReturn(Optional.of(inscricao));
		Inscricao inscricaoRetornada = inscricaoService.buscarPorId(1);
		assertEquals(inscricaoRetornada.getId(), 1);
		Assert.assertEquals(inscricaoRetornada.getParecer(), "Parecer Atualizado");
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeAtualizarInscricaoComErro() {
		inscricao.setParecer("Parecer Atualizado");		
		inscricaoService.atualizar(1, inscricao);	
		
		when(inscricoes.findById(Mockito.anyInt())).thenReturn(Optional.of(inscricao)).thenReturn(Optional.of(inscricao));
		Inscricao inscricaoRetornada = inscricaoService.buscarPorId(1);
		Assert.assertNotEquals(inscricaoRetornada.getId(), inscricao.getId());
	}	
	
	@Test
	public void testeBuscarInscricaoPorIdComSucesso() {
		
		when(inscricoes.findById(Mockito.anyInt())).thenReturn(Optional.of(inscricao)).thenReturn(Optional.of(inscricao));
		Inscricao inscricaoRetornada = inscricaoService.buscarPorId(1);
		assertEquals(inscricaoRetornada.getId(), 1);
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeBuscarInscricaoPorIdNaoEncontrado() {
		
		when(inscricoes.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		inscricaoService.buscarPorId(1);
	}
	
	@Test
	public void testeRemoveInscricaoComSucesso() {

		when(inscricoes.findById(Mockito.anyInt())).thenReturn(Optional.of(inscricao)).thenReturn(Optional.empty());
		inscricaoService.remover(1);		
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeRemoveInscricaoNaoEncontrado() {

		when(inscricoes.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		inscricaoService.remover(1);
	}
	
	@Test
	public void testeBuscarInscricoes() {
		List<Inscricao> inscricoes = inscricaoService.buscarTodos();
		Assert.assertNotNull(inscricoes);
	}	
	
	@Test
	public void testeBuscarInscricoesSelecionadasPorIdComSucesso() {
		List<Inscricao> inscricoes = inscricaoService.buscarInscricoesSelecionadas(1);
		Assert.assertNotNull(inscricoes);
	}	
	
	@Test
	public void testeBuscarInscricoesDeUmBeneficiarioPorIdComSucesso() {
		List<Inscricao> inscricoes = inscricaoService.buscarInscricoesDeUmBeneficiario(21);
		Assert.assertNotNull(inscricoes);
	}	
	
	@Test
	public void testeBuscarInscricoesDeUmBeneficioPorIdComSucesso() {
		List<Inscricao> inscricoes = inscricaoService.buscarInscricoesEmUmBeneficio(1);
		Assert.assertNotNull(inscricoes);
	}	
	
//	@Test
//	public void testeBuscarInscricoesSelecionadasPorIdComErro() {
//		List<Inscricao> inscricoes = inscricaoService.buscarInscricoesSelecionadas(0);
//		Assert.assertNull(inscricoes);
//	}	
//	
//	@Test
//	public void testeBuscarInscricoesDeUmBeneficiarioPorIdComErro() {
//		List<Inscricao> inscricoes = inscricaoService.buscarInscricoesDeUmBeneficiario(0);
//		Assert.assertNull(inscricoes);
//	}	
//	
//	@Test
//	public void testeBuscarInscricoesDeUmBeneficioPorIdComErro() {
//		List<Inscricao> inscricoes = inscricaoService.buscarInscricoesEmUmBeneficio(0);
//		Assert.assertNull(inscricoes);
//	}	



}
