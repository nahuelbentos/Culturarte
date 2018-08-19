package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexion {

	public void realizaConexion(){
	            Connection conn = null;
	            String urlDatabase =  "jdbc:postgresql://localhost:0000/midatabase"; // cambiar datos 
	            try {
	                Class.forName("org.postgresql.Driver");
	                conn = DriverManager.getConnection(urlDatabase,  "usuario", "password");
	            } catch (Exception e) {
	                System.out.println("Ocurrio un error : "+e.getMessage());
	            }
	            System.out.println("La conexión se realizo sin problemas! =) ");
	}
}
