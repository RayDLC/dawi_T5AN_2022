package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Usuario u = new  Usuario();
		u.setCodigo(10);
		u.setNombre("Sergio");
		u.setApellido("Pepito");
		u.setUsuario("Serpi");
		u.setClave("12345");
		u.setFchnacim("2001/01/01");
		u.setTipo(1);		
		u.setEstado(1);
		
		// 1. fabrica el acceso a los datos
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		// 2. crea el manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		
		try {
			em.getTransaction().begin();
			// 4. procesos
			em.persist(u);
			// 5. confirmar la transacción
			em.getTransaction().commit();
		} catch (Exception e){
			System.err.println("Error al registrar..." + e.getMessage());
			//em.getTransaction().rollback();
		}
		// 3. empezar mi transacción
		em.close();
		System.out.println("Termino...");
	}

}
