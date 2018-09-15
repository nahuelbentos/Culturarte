package persistencia;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexionPostgresHibernate {
	
	private static ConexionPostgresHibernate instancia = null;
	
	private static EntityManagerFactory emf;
	
	private ConexionPostgresHibernate() {
		emf = Persistence.createEntityManagerFactory("Conexion");
	}
	
	public static ConexionPostgresHibernate getInstancia() {
		if (instancia == null) 
			instancia = new ConexionPostgresHibernate();
		return instancia;
	}
	
	public EntityManagerFactory getEntityManager() {
		return emf;
	}

}
