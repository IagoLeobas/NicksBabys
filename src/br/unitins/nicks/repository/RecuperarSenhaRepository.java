package br.unitins.nicks.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.nicks.application.RepositoryException;
import br.unitins.nicks.model.RecuperarSenha;

public class RecuperarSenhaRepository extends Repository<RecuperarSenha> {
	
	public static List<RecuperarSenha> buscarCodigo(String codigo) throws RepositoryException {
		try { 
			EntityManager em = getEntityManager();
			Query query = em.createQuery("SELECT r FROM Recuperarsenha r WHERE r.id_usuario = 9 AND r.utilizado = false AND r.codigo = :codigo ORDER BY r.datalimite DESC LIMIT 1");
			query.setParameter("codigo", codigo);
			
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			// mandando pro console o exception gerado
			e.printStackTrace();
			// repassando a excecao para quem vai executar o metodo
			throw new RepositoryException("Problema ao pesquisar codigo.");
		}
		
	}
	
}