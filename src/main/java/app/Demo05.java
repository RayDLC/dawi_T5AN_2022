package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo05 {

	public static void main(String[] args) {
		// fabrica el acceso a los datos
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		// crea el manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		em.getTransaction().begin();
		// procesos
		Usuario u = em.find(Usuario.class, 10);
		// Eliminar
		if (u==null)
			System.err.println("Usuario NO existe...");
		else {
			em.remove(u);
			System.out.println("Usuario ELIMINADO...");
		}
		// Realiza transaccion
		em.getTransaction().commit();
		// Finaliza
		em.close();
	}
}
