package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocalServiceTest {
	private LocacaoService service;

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() {
		service = new LocacaoService();
	}

	@Test
	public void deveAlugarFilme() throws Exception {

		// cenario

		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5d));

		// ação
		Locacao locacao = service.alugarFilme(usuario, filmes);

		// verificação
		error.checkThat(locacao.getValor(), is(equalTo(5d)));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
				is(true));

	}

	@Test(expected = FilmeSemEstoqueException.class)
	public void deveLancarExcecaoAoAlugarFilmeSemEstoque() throws Exception {
		// cenario
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 4d));

		// ação
		service.alugarFilme(usuario, filmes);
	}

	@Test
	public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException {
		// cenário
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5d));

		// ação
		try {
			service.alugarFilme(null, filmes);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuário vazio"));
		}
	}

	@Test
	public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException {
		// cenario
		Usuario usuario = new Usuario("Usuario 1");

		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		// ação
		service.alugarFilme(usuario, null);

	}

	@Test
	public void devePagar75PctNoFilme3() throws FilmeSemEstoqueException, LocadoraException {
		// cenario
		Usuario usuario = new Usuario();
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 4d), new Filme("Filme 2", 1, 4d),
				new Filme("Filme 3", 1, 4d));

		// ação
		Locacao resultado = service.alugarFilme(usuario, filmes);

		// verificação
		Assert.assertThat(resultado.getValor(), is(11d));
	}

	@Test
	public void devePagar50PctNoFilme4() throws FilmeSemEstoqueException, LocadoraException {
		// cenario
		Usuario usuario = new Usuario();
		List<Filme> filmes = Arrays.asList(
				new Filme("Filme 1", 1, 4d),
				new Filme("Filme 2", 1, 4d),
				new Filme("Filme 3", 1, 4d),
				new Filme("Filme 4", 1, 4d));

		// ação
		Locacao resultado = service.alugarFilme(usuario, filmes);

		// verificação
		Assert.assertThat(resultado.getValor(), is(13d));
	}
	@Test
	public void devePagar25PctNoFilme5() throws FilmeSemEstoqueException, LocadoraException {
		// cenario
		Usuario usuario = new Usuario();
		List<Filme> filmes = Arrays.asList(
				new Filme("Filme 1", 1, 4d),
				new Filme("Filme 2", 1, 4d),
				new Filme("Filme 3", 1, 4d),
				new Filme("Filme 4", 1, 4d),
				new Filme("Filme 5", 1, 4d));

		// ação
		Locacao resultado = service.alugarFilme(usuario, filmes);

		// verificação
		Assert.assertThat(resultado.getValor(), is(14d));
	}
	@Test
	public void devePagar0PctNoFilme6() throws FilmeSemEstoqueException, LocadoraException {
		// cenario
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme(
				"Filme 1", 1, 10d),
				new Filme("Filme 2", 1, 10d),
				new Filme("Filme 3", 1, 10d),
				new Filme("Filme 4", 1, 10d),
				new Filme("Filme 5", 1, 10d),
				new Filme("Filme 6", 1, 10d));

		// ação
		service.alugarFilme(usuario, filmes);

		// verificação
		Assert.assertEquals(6, filmes.size(), 0.1);
		Assert.assertEquals(35, service.alugarFilme(usuario, filmes).getValor(), 0.01);

	}
}
