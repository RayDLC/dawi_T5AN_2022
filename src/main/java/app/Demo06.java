package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Categoria;
import model.Usuario;

public class Demo06 {
	public static void main(String[] args) {
		/*
		// fabrica el acceso a los datos
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		// Listar
		TypedQuery<Usuario> consulta =  em.createQuery("select u from Usuario u ", Usuario.class);
		
		List<Usuario> listadoUsuario = consulta.getResultList();
		
		for (Usuario u : listadoUsuario) {
			System.out.println(u);
		}
		em.close();
		
		*/
		
		// fabrica el acceso a los datos
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		// Listar
		TypedQuery<Categoria> consulta =  em.createQuery("select c from Categoria c", Categoria.class);
		
		List<Categoria> listadoCategoria = consulta.getResultList();
		
		for (Categoria u : listadoCategoria) {
			System.out.println(u);
		}
		em.close();
	}

}
