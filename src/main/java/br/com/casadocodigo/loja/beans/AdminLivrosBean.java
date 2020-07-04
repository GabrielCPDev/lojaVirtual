package br.com.casadocodigo.loja.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.Livro;

@Named
@RequestScoped
public class AdminLivrosBean {
	
	@Inject
	private LivroDao dao;

	private Livro livro = new Livro();
	
	@Transactional
	public void salvar() {
		dao.salvar(livro);
		System.out.println("Livro cadastrado: "+ livro);
		System.out.println("Livro salvo com sucesso");
	}
	
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		 this.livro = livro;
	}
}
