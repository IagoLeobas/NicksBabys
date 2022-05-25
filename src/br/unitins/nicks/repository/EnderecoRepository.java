package br.unitins.nicks.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.unitins.nicks.application.RepositoryException;
import br.unitins.nicks.model.Endereco;

public class EnderecoRepository extends Repository<Endereco> {

	@SuppressWarnings("unchecked")
	public List<Endereco> findByCep(String cep) throws RepositoryException {
		try { 
			EntityManager em = getEntityManager();
			//JPQL ou SQL
			Query query = em.createQuery("SELECT e FROM Endereco e WHERE upper(e.cep) LIKE upper(:cep)");
			query.setParameter("cep", "%" + cep + "%");
			
			return query.getResultList();
		} catch (Exception e) {
			// mandando pro console o exception gerado
			e.printStackTrace();
			// repassando a excecao para quem vai executar o metodo
			throw new RepositoryException("Problema ao pesquisar endereços.");
		}
	}

}
