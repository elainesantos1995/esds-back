package esds;

import static org.assertj.core.api.Assertions.assertThatObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

import com.esds.excecoes.RegraDeNegocioException;
import com.esds.modelo.Beneficio;
import com.esds.modelo.Inscricao;
import com.esds.modelo.UsoDeBeneficio;
import com.esds.repositorio.Beneficios;
import com.esds.repositorio.Inscricoes;
import com.esds.repositorio.UsosDeBeneficios;
import com.esds.servico.impl.BeneficioServiceImpl;
import com.esds.servico.impl.InscricaoServiceImpl;
import com.esds.servico.impl.UsoDeBeneficioServiceImpl;


@RunWith(SpringRunner.class)
public class UsoDeBeneficioServiceTest {
	
	@InjectMocks
	private UsoDeBeneficioServiceImpl usoService;
	
	@InjectMocks
	private BeneficioServiceImpl beneficioService;
	
	@InjectMocks
	private InscricaoServiceImpl inscricaoService;	
	
	@Mock
	private UsosDeBeneficios usos;
	
	@Mock
	private Beneficios beneficios;
	
	@Mock
	private Inscricoes inscricoes;
	
	UsoDeBeneficio usoDeBeneficio;
	
	Inscricao inscricao;
	Beneficio beneficio;
	
	@Before
	public void setup() {
		
//		inscricao = inscricaoService.buscarPorId(2);
//		beneficio = beneficioService.buscarPorId(1);
		
		usoDeBeneficio = new UsoDeBeneficio();
		usoDeBeneficio.setId(1);
		Date data = new Date();
		usoDeBeneficio.setDataDoUso(data);
		usoDeBeneficio.setControleBiometria(false);
		usoDeBeneficio.setControleCarteirinha(false);
		usoDeBeneficio.setControleDocumento(true);		
		
		usoDeBeneficio.setInscricao(inscricao);
		usoDeBeneficio.setBeneficio(beneficio);	
	}
	
	@Test
	public void testeSalvarUsoComSucesso() {		
		usoService.salvar(usoDeBeneficio);		
		assertThatObject(usoDeBeneficio);
	}
	
	@Test
	public void testeSalvarUsoComErro() {		
		
		UsoDeBeneficio usoNovo = usoService.salvar(usoDeBeneficio);		
		assertEquals(null, usoNovo);
	}
		
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeAtualizarUsoComSucesso() {
		Date data = new Date(2021, 04, 19, 14, 00);
		usoDeBeneficio.setDataDoUso(data);
		usoService.atualizar(1, usoDeBeneficio);	
		
		when(usos.findById(Mockito.anyInt())).thenReturn(Optional.of(usoDeBeneficio)).thenReturn(Optional.of(usoDeBeneficio));
		UsoDeBeneficio usoRetornado = usoService.buscarPorId(1);
		Assert.assertEquals(usoDeBeneficio.getDataDoUso(), data);
		assertEquals(usoRetornado.getId(), 1);
	}	
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeAtualizarUsoComErroAlteracaoId() {
	
		usoService.atualizar(1, usoDeBeneficio);	
		
		when(usos.findById(Mockito.anyInt())).thenReturn(Optional.of(usoDeBeneficio)).thenReturn(Optional.of(usoDeBeneficio));
		UsoDeBeneficio usoRetornado = usoService.buscarPorId(1);
		assertNotEquals(usoRetornado.getId(), 1);
	}
	
	@Test
	public void testeBuscarUsoPorIdComSucesso() {
		
		when(usos.findById(Mockito.anyInt())).thenReturn(Optional.of(usoDeBeneficio)).thenReturn(Optional.of(usoDeBeneficio));
		UsoDeBeneficio usoRetornado = usoService.buscarPorId(1);
		assertEquals(usoRetornado.getId(), 1);
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeBuscarUsoPorIdNaoEncontrado() {
		
		when(usos.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		UsoDeBeneficio usoRetornado = usoService.buscarPorId(1);
		assertNotEquals(usoRetornado.getId(), 1);
	}
	
	@Test
	public void testeCarregarTodosOsUsosComSucesso() {

		List<UsoDeBeneficio> usosRetornados = usoService.buscarTodos();
		Assert.assertNotNull(usosRetornados);

	}
	
	@Test
	public void testeCarregarUsosDeUmBeneficiarioComSucesso() {

		List<UsoDeBeneficio> usosBeneficiarioRetornados = usoService.buscarUsoDeUmBeneficiario(21);
		Assert.assertTrue(usosBeneficiarioRetornados!= null);

	}
	
	@Test
	public void testeCarregarUsosDeUmBeneficiarioComErroBeneficiarioNaoContemplado() {

		List<UsoDeBeneficio> usosBeneficiarioRetornados = usoService.buscarUsoDeUmBeneficiario(23);
		Assert.assertTrue(usosBeneficiarioRetornados.size() == 0);

	}
	
	@Test
	public void testeCarregarUsosDeUmBeneficioComSucesso() {

		List<UsoDeBeneficio> usosBeneficioRetornados = usoService.buscarUsoDeUmBeneficio(1);
		Assert.assertTrue(usosBeneficioRetornados != null);

	}
	
	@Test
	public void testeCarregarUsosDeUmBeneficioComErroBeneficioNaoEncontrado() {

		List<UsoDeBeneficio> usosBeneficioRetornados = usoService.buscarUsoDeUmBeneficio(11);
		Assert.assertTrue(usosBeneficioRetornados.size() == 0);

	}
	
	@Test
	public void testeRemoveUsoComSucesso() {

		when(usos.findById(Mockito.anyInt())).thenReturn(Optional.of(usoDeBeneficio)).thenReturn(Optional.empty());
		usoService.remover(1);
		
	}
	
	@Test
	public void testeRemoverUsoNaoEncontrado() {

		when(usos.findById(Mockito.anyInt())).thenReturn(Optional.of(usoDeBeneficio)).thenReturn(Optional.empty());
		usoService.remover(0);		
	}
	
}
