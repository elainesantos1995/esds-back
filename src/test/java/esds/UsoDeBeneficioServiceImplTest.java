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
import com.esds.excecoes.RegraDeNegocioException;
import com.esds.modelo.Beneficio;
import com.esds.modelo.Inscricao;
import com.esds.modelo.UsoDeBeneficio;
import com.esds.repositorio.UsosDeBeneficios;
import com.esds.servico.impl.UsoDeBeneficioServiceImpl;

@RunWith(SpringRunner.class)
public class UsoDeBeneficioServiceImplTest {
	
	@InjectMocks
	private UsoDeBeneficioServiceImpl usoService;

	@Mock
	private UsosDeBeneficios usos;
	
	UsoDeBeneficio uso;
	Inscricao inscricao;
	Beneficio beneficio;
	
	@Before
	public void setup() {
		
		uso = new UsoDeBeneficio();
		uso.setId(1);
		uso.setDataDoUso(new Date());
		uso.setControleBiometria(false);
		uso.setControleCarteirinha(false);
		uso.setControleDocumento(true);
		
		inscricao = new Inscricao();
		inscricao.setId(1);
		beneficio = new Beneficio();
		beneficio.setId(1);
		
		uso.setInscricao(inscricao);
		uso.setBeneficio(beneficio);
	}
	
	@Test
	public void testeSalvarUsoComSucesso() {		
		UsoDeBeneficio usoSalvo = usoService.salvar(uso);		
		assertThatObject(usoSalvo);
	}
	
	@Test
	public void testeSalvarUsoComErro() {		
		UsoDeBeneficio usoSalvo = usoService.salvar(uso);		
		assertEquals(null, usoSalvo);
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeAtualizarUsoComSucesso() {
		uso.setControleCarteirinha(true);		
		usoService.atualizar(1, uso);	
		
		when(usos.findById(Mockito.anyInt())).thenReturn(Optional.of(uso)).thenReturn(Optional.of(uso));
		UsoDeBeneficio usoRetornado = usoService.buscarPorId(1);
		assertEquals(usoRetornado.getId(), 1);
		Assert.assertEquals(usoRetornado.isControleCarteirinha(), true);
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeAtualizarUsoComErro() {
		uso.setControleCarteirinha(true);		
		usoService.atualizar(1, uso);	
		
		when(usos.findById(Mockito.anyInt())).thenReturn(Optional.of(uso)).thenReturn(Optional.of(uso));
		uso.setControleCarteirinha(true);
		UsoDeBeneficio usoRetornado = usoService.buscarPorId(1);
		Assert.assertNotEquals(usoRetornado.getId(), uso.getId());
	}	
	
	@Test
	public void testeRemoveUsoComSucesso() {

		when(usos.findById(Mockito.anyInt())).thenReturn(Optional.of(uso)).thenReturn(Optional.empty());
		usoService.remover(1);
		assertThatObject(null);

	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeRemoveUsoNaoEncontrado() {

		when(usos.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		usoService.remover(1);
	}
	
	@Test
	public void testeBuscarUsoPorIdComSucesso() {
		
		when(usos.findById(Mockito.anyInt())).thenReturn(Optional.of(uso)).thenReturn(Optional.of(uso));
		UsoDeBeneficio usoRetornado = usoService.buscarPorId(1);
		assertEquals(usoRetornado.getId(), 1);
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeBuscarUsoPorIdNaoEncontrado() {
		
		when(usos.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		usoService.buscarPorId(1);
	}
	
	@Test
	public void testeBuscarUsos() {
		List<UsoDeBeneficio> listaDeUsos = usoService.buscarTodos();
		assertThatObject(listaDeUsos);
	}

	

}
