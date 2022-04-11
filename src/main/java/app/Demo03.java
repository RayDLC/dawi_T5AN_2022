package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo03 {
	public static void main(String[] args) {
		
		// 1. fabrica el acceso a los datos
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		// 2. crea el manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		em.getTransaction().begin();
		// 4. procesos
		Usuario u = new Usuario();
		u.setCodigo(10);

		// Eliminar
		em.remove(u);
		// 5. confirmar la transacci�n
		em.getTransaction().commit();
		// 3. empezar mi transacci�n
		em.close();
	}
}
