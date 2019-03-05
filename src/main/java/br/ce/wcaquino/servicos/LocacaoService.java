package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;
import java.util.List;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException {

		if (usuario == null) {
			throw new LocadoraException("Usu·rio vazio");
		}

		if (filmes == null || filmes.isEmpty()) {
			throw new LocadoraException("Filme vazio");
		}
		for (Filme filme : filmes) {
			if (filme.getEstoque() == 0) {
				throw new FilmeSemEstoqueException();
			}
		}

		Locacao locacao = new Locacao();
		locacao.setFilmes(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		double valorTotal = 0d;
		for (Filme filme : filmes) {
			valorTotal += filme.getPrecoLocacao();
			if (filmes.size() == 3) {
				double desconto25 = filmes.get(2).getPrecoLocacao() - (filmes.get(2).getPrecoLocacao() * 0.25);
				double total = desconto25;
				locacao.setValor(total);
			}
			if (filmes.size() == 4) {
				double desconto25 = filmes.get(2).getPrecoLocacao() - (filmes.get(2).getPrecoLocacao() * 0.25);
				double desconto50 = filmes.get(3).getPrecoLocacao() - (filmes.get(3).getPrecoLocacao() * 0.5);
				double total = desconto25 + desconto50;
				locacao.setValor(total);

			}
			if (filmes.size() == 5) {
				double desconto25 = filmes.get(2).getPrecoLocacao() - (filmes.get(2).getPrecoLocacao() * 0.25);
				double desconto50 = filmes.get(3).getPrecoLocacao() - (filmes.get(3).getPrecoLocacao() * 0.5);
				double desconto75 = filmes.get(4).getPrecoLocacao() - (filmes.get(4).getPrecoLocacao() * 0.75);
				double total = desconto25 + desconto50 + desconto75;
				locacao.setValor(total);

			}
			if (filmes.size() == 6) {
				double locacao1 = filmes.get(0).getPrecoLocacao();
				double locacao2 = filmes.get(1).getPrecoLocacao();
				double desconto25 = filmes.get(2).getPrecoLocacao() - (filmes.get(2).getPrecoLocacao() * 0.25);
				double desconto50 = filmes.get(3).getPrecoLocacao() - (filmes.get(3).getPrecoLocacao() * 0.5);
				double desconto75 = filmes.get(4).getPrecoLocacao() - (filmes.get(4).getPrecoLocacao() * 0.75);
				double desconto100 = filmes.get(5).getPrecoLocacao() - (filmes.get(5).getPrecoLocacao());
				double total = locacao1 + locacao2 + desconto25 + desconto50 + desconto75 + desconto100;
				locacao.setValor(total);


			}
		}
		

		// Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);

		// Salvando a locacao...
		// TODO adicionar m√©todo para salvar

		return locacao;
	}

}