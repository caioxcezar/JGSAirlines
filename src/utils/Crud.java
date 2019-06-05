package utils;

import java.util.List;

import javax.persistence.criteria.*;

import org.hibernate.*;
import org.hibernate.query.Query;

import models.*;

public class Crud {
	
	public static void salvar(Object obj) throws HibernateException {
		Session sessao = null;
		try {
			sessao = DBConnector.getSessionFactory().openSession();
			sessao.beginTransaction();
			sessao.save(obj);
			sessao.getTransaction().commit();
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de inserção. Mensagem: " + e.getMessage());
			}
		}
	}

	public static void atualizar(Object obj) {
		Session sessao = null;
		Transaction transacao = null;
		try {
			sessao = DBConnector.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.update(obj);
			transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar o ContatoAnnotations. Erro:" + e.getMessage());
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de atualização.Mensagem: " + e.getMessage());
			}
		}
	}

	public static void excluir(Object obj) {
		Session sessao = null;
		Transaction transacao = null;
		try {
			sessao = DBConnector.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			sessao.delete(obj);
			transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir o ContatoAnnotations. Erro: " + e.getMessage());
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de exclusão. Mensagem: " + e.getMessage());
			}
		}
	}

	public static Object buscar(int valor, Class<?> cs) throws HibernateException {
		Object obj = null;
		Session sessao = null;
		Transaction transacao = null;
		try {
			sessao = DBConnector.getSessionFactory().openSession();
			transacao = sessao.beginTransaction();
			obj = sessao.get(cs, valor);
			transacao.commit();
			return obj;
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de buscar. Mensagem: " + e.getMessage());
			}
		}
	}

	public static List<Aviao> listarAviao() throws HibernateException {
		Session sessao = null;
		try {
			sessao = DBConnector.getSessionFactory().openSession();
			sessao.beginTransaction();
			List<Aviao> lista = sessao.createQuery("from Aviao", Aviao.class).list();
			sessao.getTransaction().commit();
			return lista;
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de consulta. Mensagem: " + e.getMessage());
			}
		}
	}
	
	public static List<Cliente> listarCliente() throws HibernateException {
		Session sessao = null;
		try {
			sessao = DBConnector.getSessionFactory().openSession();
			sessao.beginTransaction();
			List<Cliente> lista = sessao.createQuery("from Cliente", Cliente.class).list();
			sessao.getTransaction().commit();
			return lista;
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de consulta. Mensagem: " + e.getMessage());
			}
		}
	}
	
	public static List<Funcionario> listarFuncionario() throws HibernateException {
		Session sessao = null;
		try {
			sessao = DBConnector.getSessionFactory().openSession();
			sessao.beginTransaction();
			List<Funcionario> lista = sessao.createQuery("from Funcionario", Funcionario.class).list();
			sessao.getTransaction().commit();
			return lista;
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de consulta. Mensagem: " + e.getMessage());
			}
		}
	}
	
	public static List<Passagem> listarPassagem() throws HibernateException {
		Session sessao = null;
		try {
			sessao = DBConnector.getSessionFactory().openSession();
			sessao.beginTransaction();
			List<Passagem> lista = sessao.createQuery("from Passagem", Passagem.class).list();
			sessao.getTransaction().commit();
			return lista;
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de consulta. Mensagem: " + e.getMessage());
			}
		}
	}
	
	public static List<Voo> listarVoo() throws HibernateException {
		Session sessao = null;
		try {
			sessao = DBConnector.getSessionFactory().openSession();
//			sessao.beginTransaction();
//			List<Voo> lista = sessao.createQuery("from Voo", Voo.class).list();
//			sessao.getTransaction().commit();
//			return lista;
			CriteriaBuilder cb = sessao.getCriteriaBuilder();
		    CriteriaQuery<Voo> cq = cb.createQuery(Voo.class);
		    Root<Voo> rootEntry = cq.from(Voo.class);
		    CriteriaQuery<Voo> all = cq.select(rootEntry);
		 
		    Query<Voo> allQuery = sessao.createQuery(all);
		    return allQuery.getResultList();
		} finally {
			try {
				sessao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de consulta. Mensagem: " + e.getMessage());
			}
		}
	}
}
