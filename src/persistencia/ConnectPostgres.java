package persistencia;

import java.sql.*;

public class ConnectPostgres {
    
    /**
     * Método para establecer la conexión a la base de datos <a>localhost:5432/pap_culturarte</a>
     * 
     */
	public void connectDatabase() {
        try {
            // Registramos el driver de PostgresSQL
            try { 
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            // Conectamos con la base de datos
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/pap_culturarte",
                    "postgres", "postgres");
 
            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error: " + sqle);
        }
    }
	
    /**
     * Método para establecer la conexión a la base de datos mediante el paso de parámetros.
     * 
     * @param host <code>String</code> Nombre del host o ip.
     * @param port <code>String</code> Puerto en el que escucha la base de datos.
     * @param database <code>String</code> Nombre de la base de datos para la conexión.
     * @param user <code>String</code> Nombre de usuario.
     * @param password  <code>String</code> Password del usuario.
     */
    public void connectDatabase(String host, String port, String database,
            String user, String password) {
        String url = "";
        try {
            // We register the PostgreSQL driver
            // Registramos el driver de PostgresSQL
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
            // Database connect
            // Conectamos con la base de datos
            connection = DriverManager.getConnection(
                    url,
                    user, password);           
            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
        } catch (java.sql.SQLException sqle) { 
            System.out.println("Error al conectar con la base de datos de PostgreSQL (" + url + "): " + sqle);
        }
    }
 
	
	public static void main(String[] args) {
		ConnectPostgres javaPostgreSQLBasic = new ConnectPostgres();
        javaPostgreSQLBasic.connectDatabase();
        javaPostgreSQLBasic.connectDatabase("localhost", "5432", "pap_culturarte", "postgres", "postgres");

	}

}
