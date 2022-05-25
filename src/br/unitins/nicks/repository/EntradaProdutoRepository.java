package br.unitins.nicks.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.nicks.application.RepositoryException;
import br.unitins.nicks.model.PessoaJuridica;

public class EntradaProdutoRepository extends Repository<PessoaJuridica> {

	@SuppressWarnings("unchecked")
	public List<PessoaJuridica> findByNome(String nome) throws RepositoryException {
		try { 
			EntityManager em = getEntityManager();
			//JPQL ou SQL
			Query query = em.createQuery("SELECT e FROM PessoaJuridica e WHERE upper(e.nome) LIKE upper(:nome)");
			query.setParameter("nome", "%" + nome + "%");
			
			return query.getResultList();
		} catch (Exception e) {
			// mandando pro console o exception gerado
			e.printStackTrace();
			// repassando a excecao para quem vai executar o metodo
			throw new RepositoryException("Problema ao pesquisar pessoas juridicas.");
		}
	}

}
