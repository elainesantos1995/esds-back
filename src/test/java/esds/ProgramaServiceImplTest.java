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
import com.esds.repositorio.Programas;
import com.esds.servico.impl.BeneficioServiceImpl;
import com.esds.servico.impl.ProgramaSocialServiceImpl;

@RunWith(SpringRunner.class)
public class ProgramaServiceImplTest {

	@InjectMocks
	private ProgramaSocialServiceImpl programaSocialServiceImpl;
	
	@InjectMocks
	private BeneficioServiceImpl beneficioServiceImpl;
	
	@Mock
	private Programas programas;

	ProgramaSocial programa;
	List <Beneficio> beneficiosDoPrograma;
	Beneficio beneficio;

	@Before
	public void setup() {
		programa = new ProgramaSocial();
		programa.setId(1);
		programa.setNome("Programa 1");
		programa.setDescricao("Programa destinado a...");
		programa.setVigenciaInicio(new Date());
		programa.setVigenciaTermino(new Date());
		beneficiosDoPrograma = new ArrayList<>();

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

		beneficiosDoPrograma.add(beneficio);
		programa.setBeneficios(beneficiosDoPrograma);

		
	}
	
	@Test
	public void testeSalvarProgramaComSucesso() {		
		ProgramaSocial programaSocial = programaSocialServiceImpl.salvar(programa);		
		assertThatObject(programaSocial);
	}
	
	@Test
	public void testeSalvarProgramaComErro() {		
		ProgramaSocial programaSocial = programaSocialServiceImpl.salvar(programa);		
		assertEquals(null, programaSocial);
	}
		
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeAtualizarProgramaComSucesso() {
		programa.setNome("Programa Nome Atualizado");		
		programaSocialServiceImpl.atualizar(1, programa);	
		
		when(programas.findById(Mockito.anyInt())).thenReturn(Optional.of(programa)).thenReturn(Optional.of(programa));
		ProgramaSocial programaRetornado = programaSocialServiceImpl.buscarPorId(1);
		Assert.assertEquals(programaRetornado.getNome(), "Programa Nome Atualizado");
		assertEquals(programaRetornado.getId(), 1);
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeAtualizarProgramaComErroNomeNaoPersistido() {
		programa.setNome("Nome Atualizado");		
		programaSocialServiceImpl.atualizar(1, programa);	
		
		when(programas.findById(Mockito.anyInt())).thenReturn(Optional.of(programa)).thenReturn(Optional.of(programa));
		ProgramaSocial programaSocialRetornado = programaSocialServiceImpl.buscarPorId(1);
		Assert.assertNotEquals(programaSocialRetornado.getNome(), "Programa Nome Atualizado");
	}	
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeAtualizarProgramaComErroAlteracaoId() {
		programa.setNome("Nome Atualizado");		
		programaSocialServiceImpl.atualizar(1, programa);	
		
		when(programas.findById(Mockito.anyInt())).thenReturn(Optional.of(programa)).thenReturn(Optional.of(programa));
		ProgramaSocial programaSocialRetornado = programaSocialServiceImpl.buscarPorId(1);
		assertNotEquals(programaSocialRetornado.getId(), 1);
	}	
	
	@Test
	public void testeBuscarProgramaPorIdComSucesso() {
		
		when(programas.findById(Mockito.anyInt())).thenReturn(Optional.of(programa)).thenReturn(Optional.of(programa));
		ProgramaSocial programaRetornado = programaSocialServiceImpl.buscarPorId(1);
		assertEquals(programaRetornado.getId(), 1);
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeBuscarProgramaPorIdNaoEncontrado() {

		when(programas.findById(Mockito.anyInt())).thenReturn(Optional.empty());		
		ProgramaSocial retornado = programaSocialServiceImpl.buscarPorId(0);
		Assert.assertNull(retornado);		
	}
	
	@Test
	public void testeCarregarTodosOsProgramasPorAnoComSucesso() {

		List<ProgramaSocial> retornados = programaSocialServiceImpl.findByAnoProgramaFetchBeneficio(2021);
		Assert.assertNotNull(retornados);
	}
	
	@Test
	public void testeCarregarTodosOsProgramasPorAnoComErroAnoInexistente() {

		List<ProgramaSocial> retornados = programaSocialServiceImpl.findByAnoProgramaFetchBeneficio(2015);
		Assert.assertTrue(retornados.size() == 0);
	}
	
	@Test
	public void testeCarregarTodosOsProgramasComSucesso() {

		List<ProgramaSocial> retornados = programaSocialServiceImpl.buscarTodos();
		Assert.assertNotNull(retornados);
	}
	
	@Test
	public void testeRemoveProgramaComSucesso() {

		when(programas.findById(Mockito.anyInt())).thenReturn(Optional.of(programa)).thenReturn(Optional.empty());
		programaSocialServiceImpl.remover(1);
		
	}
	
	@Test
	public void testeRemoverProgramaNaoEncontrado() {

		when(programas.findById(Mockito.anyInt())).thenReturn(Optional.of(programa)).thenReturn(Optional.empty());
		programaSocialServiceImpl.remover(0);
		
	}
	
}