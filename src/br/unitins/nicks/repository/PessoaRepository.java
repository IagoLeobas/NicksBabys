package br.unitins.nicks.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.nicks.application.RepositoryException;
import br.unitins.nicks.model.Pessoa;

public class PessoaRepository extends Repository<Pessoa> {
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> findByNome(String nome) throws RepositoryException {
		try { 
			EntityManager em = getEntityManager();
			//JPQL
			Query query = em.createQuery("SELECT p FROM Pessoa p WHERE upper(p.nome) LIKE upper(:nome)");
			query.setParameter("nome", "%" + nome + "%");
			
			return query.getResultList();
		} catch (Exception e) {
			// mandando pro console o exception gerado
			e.printStackTrace();
			// repassando a excecao para quem vai executar o metodo
			throw new RepositoryException("Problema ao pesquisar pessoas.");
		}
	}

}