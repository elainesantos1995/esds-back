package esds;

import static org.assertj.core.api.Assertions.assertThatObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import com.esds.enumeracoes.Sexo;
import com.esds.modelo.AssistenteSocial;
import com.esds.modelo.Endereco;
import com.esds.modelo.Funcionario;
import com.esds.repositorio.Funcionarios;
import com.esds.servico.impl.AssistenteSocialServiceImpl;
import com.esds.servico.impl.FuncionarioServiceImpl;

@RunWith(SpringRunner.class)
public class AssistenteSocialServiceImplTest {

	@InjectMocks
	private AssistenteSocialServiceImpl assistenteSocialServiceImpl;
	
	@InjectMocks
	private FuncionarioServiceImpl funcionarioServiceImpl;
	
	@Mock
	private Funcionarios funcionarios;
	
	@Mock
	private PasswordEncoder encoder;

	AssistenteSocial funcionario;
	Endereco endereco;

	UserDetails usuario;
	String[] roles = new String[] { "ADMIN", "USER" };

	@Before
	public void setup() {
		funcionario = new AssistenteSocial();
		funcionario.setId(1);
		funcionario.setAdmin(true);
		funcionario.setSenha("123");
		funcionario.setMatricula("12345");
		funcionario.setNome("FuncionarioTeste");
		funcionario.setDataNascimento(new Date());
		funcionario.setCpf("12345678910");
		funcionario.setRg("987654321");
		funcionario.setRgDataEmissao("2020-01-01");
		funcionario.setRgOrgaoEmissor("SDS PE");
		funcionario.setLogin("teste");
		funcionario.setTipo("Assistente Social");
		funcionario.setTelefone1("1234-4321");
		funcionario.setTelefone2("5678-8765");
		funcionario.setSexo(Sexo.MASCULINO);
		funcionario.setEmail("teste@teste.com");
		funcionario.setMatriculaCFESS("1234");

		endereco = new Endereco();
		endereco.setId(1);
		endereco.setLogradouro("Rua Teste");
		endereco.setNumero("1");
		endereco.setComplemento("Apto Teste");
		endereco.setBairro("Bairro Teste");
		endereco.setCidade("Cidade Teste");
		endereco.setCep("12.345-678");
		endereco.setPontoDeReferencia("Referencia Teste");

		funcionario.setEndereco(endereco);

		usuario = User.builder()
				.username(funcionario.getLogin())
				.password(funcionario.getSenha())
				.roles(roles).build();
	}
	
	@Test
	public void testeSalvarFuncionarioComSucesso() {		
		Funcionario funcionarioSalvo = assistenteSocialServiceImpl.salvar(funcionario);		
		assertThatObject(funcionarioSalvo);
	}
	
	@Test
	public void testeSalvarFuncionarioComErro() {		
		Funcionario funcionarioSalvo = assistenteSocialServiceImpl.salvar(funcionario);		
		assertEquals(null, funcionarioSalvo);
	}
		
	
	@Test(expected = ResponseStatusException.class)
	public void testeAtualizarFuncionarioComSucesso() {
		funcionario.setNome("Nome Atualizado");		
		assistenteSocialServiceImpl.atualizar(1, funcionario);	
		
		when(funcionarios.findById(Mockito.anyInt())).thenReturn(Optional.of(funcionario)).thenReturn(Optional.of(funcionario));
		Funcionario funcionarioRetornado = funcionarioServiceImpl.buscarPorId(1);
		Assert.assertEquals(funcionarioRetornado.getNome(), "Nome Atualizado");
		Assert.assertEquals(funcionarioRetornado.getId(), 1);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void testeAtualizarFuncionarioComErro() {
		funcionario.setNome("Nome Atualizado");		
		assistenteSocialServiceImpl.atualizar(1, funcionario);	
		
		when(funcionarios.findById(Mockito.anyInt())).thenReturn(Optional.of(funcionario)).thenReturn(Optional.of(funcionario));
		Funcionario funcionarioRetornado = funcionarioServiceImpl.buscarPorId(1);
		Assert.assertNotEquals(funcionarioRetornado.getId(), funcionario.getId());
	}	
	
}