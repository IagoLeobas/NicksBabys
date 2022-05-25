package br.unitins.nicks.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.nicks.application.RepositoryException;
import br.unitins.nicks.model.Categoria;
import br.unitins.nicks.model.Produto;

public class ProdutoRepository extends Repository<Produto> {

	@SuppressWarnings("unchecked")
	public List<Produto> findByDescricao(String descricao) throws RepositoryException {
		try { 
			EntityManager em = getEntityManager();
			//JPQL ou SQL
			Query query = em.createQuery("SELECT p FROM Produto p WHERE upper(p.descricao) LIKE upper(:descricao)");
			query.setParameter("descricao", "%" + descricao + "%");
			
			return query.getResultList();
		} catch (Exception e) {
			// mandando pro console o exception gerado
			e.printStackTrace();
			// repassando a excecao para quem vai executar o metodo
			throw new RepositoryException("Problema ao pesquisar produtos.");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> findByCategoria(Categoria categoria) throws RepositoryException {
		try { 
			EntityManager em = getEntityManager();
			//JPQL ou SQL
			Query query = em.createQuery("SELECT p FROM Produto p WHERE p.categoria = :categoria");
			query.setParameter("categoria", categoria);
			
			return query.getResultList();
		} catch (Exception e) {
			// mandando pro console o exception gerado
			e.printStackTrace();
			// repassando a excecao para quem vai executar o metodo
			throw new RepositoryException("Problema ao pesquisar produtos.");
		}
	}

}
