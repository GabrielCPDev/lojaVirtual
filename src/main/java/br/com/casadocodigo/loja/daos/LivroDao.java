package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import br.com.casadocodigo.loja.models.Livro;

@Stateful
public class LivroDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager manager;
	
	public void salvar(Livro livro) {
		manager.persist(livro);
	}

	public List<Livro> listar() {
		String jpql = "select distinct(l)  from livro l" + " join fetch l.autores";
		return manager.createQuery(jpql, Livro.class).getResultList();
	}

	public List<Livro> ultimosLancamentos() {
		String jpql = "select l from livro l order by l.dataPublicacao desc";
		return manager.createQuery(jpql, Livro.class)
				.setMaxResults(5)
				.getResultList();
	}

	public List<Livro> demailLivros() {
		String jpql = "select l from livro l order by l.id desc";
		
		return manager.createQuery(jpql).setFirstResult(6).getResultList();
	}

	public Livro buscarPorId(Integer id) {
	
		String jpql = "select l from livro l join fetch l.autores" + "where l.id = :id";
		return manager.createQuery(jpql, Livro.class).setParameter("id", id).getSingleResult();
	}
}
