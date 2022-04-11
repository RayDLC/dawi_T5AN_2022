package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo04 {
	//Objetivo: encontrar y devolver los datos de un 
	//usuario segun su codigo
	public static void main(String[] args) {
	// 1. fabrica el acceso a los datos
	EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
	// 2. crea el manejador de entidades
	EntityManager em = fabrica.createEntityManager();
	Usuario u = em.find(Usuario.class, 10);
	if (u==null)
		System.err.println("Usuario NO existe...");
	else
		System.out.println(u);
	// 3. empezar mi transacción
	em.close();
	}
}
