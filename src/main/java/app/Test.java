package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Producto;

public class Test {
	
	public static void main(String[] args) {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		em.getTransaction().begin();
		
		Producto u = new Producto();
		u.setIdProducto("P0022");
		
		try {
			em.remove(em.contains(u) ? u : em.merge(u));
			em.getTransaction().commit();
			System.out.println("Producto Eliminado con EXITO");
		} catch(Exception a) {
			System.out.println("Producto NO Eliminado-->ERROR: " + a.getMessage());
			//em.getTransaction().rollback();
		}
		em.close();
	}

}
