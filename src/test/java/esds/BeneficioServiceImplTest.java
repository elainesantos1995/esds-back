package esds;

import static org.assertj.core.api.Assertions.assertThatObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import com.esds.enumeracoes.Periodicidade;
import com.esds.excecoes.RegraDeNegocioException;
import com.esds.modelo.Beneficio;
import com.esds.modelo.ProgramaSocial;
import com.esds.repositorio.Beneficios;
import com.esds.repositorio.Programas;
import com.esds.servico.impl.BeneficioServiceImpl;
import com.esds.servico.impl.ProgramaSocialServiceImpl;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
public class BeneficioServiceImplTest {

	@InjectMocks
	private ProgramaSocialServiceImpl programaSocialServiceImpl;
	
	@InjectMocks
	private BeneficioServiceImpl beneficioServiceImpl;
	
	@Mock
	private Programas programas;
	
	@Mock
	private Beneficios beneficios;

	ProgramaSocial programa;
	List <Beneficio> beneficiosDoPrograma;
	Beneficio beneficio;

	@Before
	public void setup() {
		beneficio = new Beneficio();
		beneficio.setId(1);
		beneficio.setNome("Beneficio 1");
		beneficio.setJustificativa("Justifictiva do benefício 1");
		beneficio.setToleranciaUsosCancelado(3);
		beneficio.setToleranciaUsosInadimplente(1);
		beneficio.setTotalRecursosAportados(10000000);
		beneficio.setControleBiometria(false);
		beneficio.setControleCarteirinha(true);
		beneficio.setControleDocumento(true);
		beneficio.setLimiteVagas(50);
		beneficio.setPeriodicidade(Periodicidade.MENSAL);
		
		
		programa = new ProgramaSocial();
		programa.setId(1);
		programa.setNome("Programa 1");
		programa.setDescricao("Programa destinado a...");
		programa.setVigenciaInicio(new Date());
		programa.setVigenciaTermino(new Date());
		beneficiosDoPrograma = new ArrayList<>();


		beneficiosDoPrograma.add(beneficio);
		programa.setBeneficios(beneficiosDoPrograma);
		beneficio.setPrograma(programa);
		
	}
	
	@Test
	public void testeSalvarBeneficioComSucesso() {		
		beneficioServiceImpl.salvar(beneficio);		
		assertThatObject(beneficio);
	}
	
	@Test
	public void testeSalvarBeneficioComErro() {		
		
		Beneficio beneficioNovo = beneficioServiceImpl.salvar(beneficio);		
		assertEquals(null, beneficioNovo);
	}
		
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeAtualizarBeneficioComSucesso() {
		beneficio.setNome("Beneficio Atualizado");		
		beneficioServiceImpl.atualizar(1, beneficio);	
		
		when(beneficios.findById(Mockito.anyInt())).thenReturn(Optional.of(beneficio)).thenReturn(Optional.of(beneficio));
		Beneficio beneficioRetornado = beneficioServiceImpl.buscarPorId(1);
		Assert.assertEquals(beneficioRetornado.getNome(), "Beneficio Atualizado");
		assertEquals(beneficioRetornado.getId(), 1);
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeAtualizarBeneficioComErroNomeNaoPersistido() {
		beneficio.setNome("Nome Atualizado");		
		beneficioServiceImpl.atualizar(1, beneficio);	
		
		when(beneficios.findById(Mockito.anyInt())).thenReturn(Optional.of(beneficio)).thenReturn(Optional.of(beneficio));
		Beneficio beneficioRetornado = beneficioServiceImpl.buscarPorId(1);
		Assert.assertNotEquals(beneficioRetornado.getNome(), "Beneficio Nome Atualizado");
	}	
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeAtualizarBeneficioComErroAlteracaoId() {
		beneficio.setNome("Nome Atualizado");		
		beneficioServiceImpl.atualizar(1, beneficio);	
		
		when(beneficios.findById(Mockito.anyInt())).thenReturn(Optional.of(beneficio)).thenReturn(Optional.of(beneficio));
		Beneficio beneficioRetornado = beneficioServiceImpl.buscarPorId(1);
		assertNotEquals(beneficioRetornado.getId(), 1);
	}
	
	@Test
	public void testeCarregaBeneficioComSucesso() {

		when(beneficios.findById(Mockito.anyInt())).thenReturn(Optional.of(beneficio));
		
		Beneficio beneficioRetornado = beneficioServiceImpl.buscarPorId(beneficio.getId());
		TestCase.assertEquals(beneficioRetornado.getId(), beneficio.getId());
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeCarregaBeneficioNaoEncontrado() {

		when(beneficios.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		
		beneficioServiceImpl.buscarPorId(0);
	}
	
	@Test
	public void testeBuscarBeneficioPorIdComSucesso() {
		
		when(beneficios.findById(Mockito.anyInt())).thenReturn(Optional.of(beneficio)).thenReturn(Optional.of(beneficio));
		Beneficio beneficioRetornado = beneficioServiceImpl.buscarPorId(1);
		assertEquals(beneficioRetornado.getId(), 1);
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeBuscarBeneficioPorIdNaoEncontrado() {
		
		when(beneficios.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		Beneficio beneficioRetornado = beneficioServiceImpl.buscarPorId(1);
		assertNotEquals(beneficioRetornado.getId(), 1);
	}
	
	@Test
	public void testeRemoveBeneficioComSucesso() {

		when(beneficios.findById(Mockito.anyInt())).thenReturn(Optional.of(beneficio)).thenReturn(Optional.empty());
		beneficioServiceImpl.remover(1);
		
	}
	
	@Test
	public void testeRemoverBeneficioNaoEncontrado() {

		when(beneficios.findById(Mockito.anyInt())).thenReturn(Optional.of(beneficio)).thenReturn(Optional.empty());
		beneficioServiceImpl.remover(0);
		
	}
	
}