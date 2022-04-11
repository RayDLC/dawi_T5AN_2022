package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo07 {

	public static void main(String[] args) {
		// fabrica el acceso a los datos
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		// Listar
		TypedQuery<Usuario> consulta =  em.createQuery("select u from Usuario u where tipo = :xtipo ", Usuario.class);
		
		consulta.setParameter("xtipo", 1);
		
		List<Usuario> listadoUsuario = consulta.getResultList();
		
		for (Usuario u : listadoUsuario) {
			System.out.println(u);
		}
		em.close();
	}
}
