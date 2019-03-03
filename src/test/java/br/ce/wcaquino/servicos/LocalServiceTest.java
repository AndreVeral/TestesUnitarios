package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocalServiceTest {
	@Test
	public void test() {
		// cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 2, 5d);

		// ação
		Locacao locacao = service.alugarFilme(usuario, filme);

		assertThat(locacao.getValor(), is(equalTo(5d)));
		assertThat(locacao.getValor(), is(not(6d)));
		assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
	}
}
