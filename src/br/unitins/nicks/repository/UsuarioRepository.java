package br.unitins.nicks.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.nicks.application.JPAUtil;
import br.unitins.nicks.application.RepositoryException;
import br.unitins.nicks.application.Util;
import br.unitins.nicks.model.PessoaFisica;
import br.unitins.nicks.model.Usuario;

public class UsuarioRepository extends Repository<Usuario> {

	public Usuario validarLogin(Usuario usuario) throws RepositoryException {
		try {
			EntityManager em = getEntityManager();
			// JPQL ou SQL
			Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha");
			query.setParameter("login", usuario.getLogin());
			query.setParameter("senha", Util.hash(usuario.getSenha()));

			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			// mandando pro console o exception gerado
			e.printStackTrace();
			// repassando a excecao para quem vai executar o metodo
			throw new RepositoryException("Problema ao pesquisar usuários.");
		}

	}

	@SuppressWarnings("unchecked")
	public Usuario findByEmail(String login) throws RepositoryException {
		try {
			// JPQL ou SQL
			Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login ");
			query.setParameter("login", login);

			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			// mandando pro console o exception gerado
			e.printStackTrace();
			// repassando a excecao para quem vai executar o metodo
			throw new RepositoryException("Problema ao pesquisar usuários.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> findByNome(String nome) throws RepositoryException {
		try {
			EntityManager em = getEntityManager();
			// JPQL ou SQL
			Query query = em.createQuery("SELECT u FROM Usuario u WHERE upper(u.nome) LIKE upper(:nome)");
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();
		} catch (Exception e) {
			// mandando pro console o exception gerado
			e.printStackTrace();
			// repassando a excecao para quem vai executar o metodo
			throw new RepositoryException("Problema ao pesquisar usuários.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> findByLogin(String login) throws RepositoryException {
		try {
			EntityManager em = getEntityManager();
			// JPQL ou SQL
			Query query = em.createQuery("SELECT p FROM Usuario p WHERE upper(p.login) LIKE upper(:login)");
			query.setParameter("login", "%" + login + "%");

			return query.getResultList();
		} catch (Exception e) {
			// mandando pro console o exception gerado
			e.printStackTrace();
			// repassando a excecao para quem vai executar o metodo
			throw new RepositoryException("Problema ao pesquisar produtos.");
		}
	}

	@SuppressWarnings("unchecked")
	public Usuario findByPessoaFisica(PessoaFisica pessoaFisica) throws RepositoryException {
		try {
			EntityManager em = getEntityManager();
			// JPQL ou SQL
			Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.pessoaFisica.id = :idPessoaFisica");
			query.setParameter("idPessoaFisica", pessoaFisica.getId());

			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			// mandando pro console o exception gerado
			e.printStackTrace();
			// repassando a excecao para quem vai executar o metodo
			throw new RepositoryException("Problema ao pesquisar usuários.");
		}
	}

	@SuppressWarnings("unchecked")
	public Usuario getUsuario(String login, String senha) {

		Query query = getEntityManager().createQuery("Select " + "  u " + "From " + "  Usuario u " + "WHERE "
				+ "  u.login = :login AND " + "  u.senha = :senha ");

		query.setParameter("login", login);
		query.setParameter("senha", senha);

		List<Usuario> lista = query.getResultList();

		if (lista == null || lista.isEmpty())
			return null;

		return lista.get(0);
	}

	public Usuario adicionar(Usuario obj) throws RepositoryException {
		try {
			obj.setSenha(Util.hash(obj.getSenha()));
			em.getTransaction().begin();
			Usuario t = em.merge(obj);
			em.getTransaction().commit();
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao adicionar!");
		}

	}

}
