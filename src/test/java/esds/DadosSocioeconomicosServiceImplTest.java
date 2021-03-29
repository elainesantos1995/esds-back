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

import com.esds.enumeracoes.ComposicaoDomicilio;
import com.esds.enumeracoes.CondicaoDaResidencia;
import com.esds.enumeracoes.LocalidadeDomicilio;
import com.esds.enumeracoes.QuanMembrosIdosos;
import com.esds.enumeracoes.QuantComodos;
import com.esds.enumeracoes.QuantMembrosCrianca;
import com.esds.enumeracoes.QuantMembrosFamilia;
import com.esds.enumeracoes.RendaPerCapita;
import com.esds.enumeracoes.TipoDeTrabalho;
import com.esds.excecoes.RegraDeNegocioException;
import com.esds.modelo.Beneficiario;
import com.esds.modelo.DadosSocioEconomicos;
import com.esds.repositorio.DadosSocioEconomicosRepository;
import com.esds.servico.impl.DadosSocioEconomicosServiceImpl;

@RunWith(SpringRunner.class)
public class DadosSocioeconomicosServiceImplTest {
	
	@InjectMocks
	private DadosSocioEconomicosServiceImpl dadosServiceImpl;

	@Mock
	private DadosSocioEconomicosRepository dadosRepository;
	
	DadosSocioEconomicos dados;
	Beneficiario beneficiario;
	
	@Before
	public void setup() {
		
		dados = new DadosSocioEconomicos();
		dados.setId(1);
		dados.setTemBanheiro(true);
		dados.setAguaEncanada(true);
		dados.setEnergiaEletrica(true);
		dados.setColetaEsgoto(false);
		dados.setFamiliaIndigena(false);
		dados.setFamiliaQuilombola(true);
		dados.setMembroEmpregado(false);
		dados.setDoencaCronica(true);
		dados.setMembroDeficiente(true);
		dados.setComposicaoDomicilio(ComposicaoDomicilio.ALVENARIA);
		dados.setLocalidade(LocalidadeDomicilio.URBANA);
		dados.setCondicaoResidencia(CondicaoDaResidencia.ALUGADA);
		dados.setQuantComodos(QuantComodos.ENTRE_SEIS_E_DEZ);
		dados.setQuantMembrosFamilia(QuantMembrosFamilia.ENTRE_CINCO_SETE);
		dados.setQuantMembrosIdosos(QuanMembrosIdosos.UM);
		dados.setTipoDeTrabalho(TipoDeTrabalho.AUTONOMO);
		dados.setRendaPerCapita(RendaPerCapita.MENOR_UM_SM);
		dados.setQuantMembrosCriancas(QuantMembrosCrianca.DOIS);
		dados.setRendaFamiliar(1500);
		dados.setQuantMembroDeficiente(1);
		dados.setTipoDeficiencia("Física");
		dados.setDataPreenchimento(new Date());
		dados.setDataUltimaAtualizacao(null);
		dados.setPontuacao(0);
	}
	
	@Test
	public void testeSalvarDadosSocioEconomicosComSucesso() {		
		DadosSocioEconomicos dadosSalvo = dadosServiceImpl.salvar(dados);		
		assertThatObject(dadosSalvo);
	}
	
	@Test
	public void testeSalvarDadosSocioEconomicosComErro() {		
		DadosSocioEconomicos dadosSalvo = dadosServiceImpl.salvar(dados);		
		assertEquals(null, dadosSalvo);
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeAtualizarDadosSocioEconomicosComSucesso() {
		dados.setPontuacao(115);		
		dadosServiceImpl.atualizar(1, dados);	
		
		when(dadosRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(dados)).thenReturn(Optional.of(dados));
		DadosSocioEconomicos dadosRetornado = dadosServiceImpl.buscarPorId(1);
		assertEquals(dadosRetornado.getId(), 1);
		assertEquals(dadosRetornado.getPontuacao(), 115);
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeAtualizarDadosSocioEconomicosComErro() {
		dados.setPontuacao(123);		
		dadosServiceImpl.atualizar(1, dados);	
		
		when(dadosRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(dados)).thenReturn(Optional.of(dados));
		DadosSocioEconomicos dadosRetornado = dadosServiceImpl.buscarPorId(1);
		Assert.assertNotEquals(dadosRetornado.getId(), dados.getId());
	}	
	
	@Test
	public void testeRemoveDadosSocioEconomicosComSucesso() {

		when(dadosRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(dados)).thenReturn(Optional.empty());
		dadosServiceImpl.remover(1);
		
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeRemoveDadosSocioEconomicosNaoEncontrado() {

		when(dadosRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		dadosServiceImpl.remover(1);
	}
	
	@Test
	public void testeBuscarDadosSocioEconomicosPorIdComSucesso() {
		
		when(dadosRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(dados)).thenReturn(Optional.of(dados));
		DadosSocioEconomicos dadosRetornado = dadosServiceImpl.buscarPorId(1);
		assertEquals(dadosRetornado.getId(), 1);
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void testeBuscarDadosSocioEconomicosPorIdNaoEncontrado() {
		
		when(dadosRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		dadosServiceImpl.buscarPorId(1);
	}
	
	@Test
	public void testeBuscarDadosSocioEconomicos() {
		List<DadosSocioEconomicos> listaDeDados = dadosServiceImpl.buscarTodos();
		assertThatObject(listaDeDados);
	}

	
}
