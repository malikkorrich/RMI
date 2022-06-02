package RMI;

/**
 * 
 * @author malik
 * La clase  SQLQueries tiene  lista de constantes static de tipo String  que son las queries 
 * las queries  INSERT_EMPLEADO,UPDATE_EMPLEADO,DELETE_EMPLEADO reciben datos como parametros 
 */
public class SQLQueries {

	
	public static final String SELECT_EMPLEADO = "SELECT * FROM EMPLEADO";
	public static final String INSERT_EMPLEADO = "INSERT INTO EMPLEADO (NOMBRE,APELLIDOS,EDAD,CARGOEMPRESA) VALUES (?,?,?,?)";
	public static final String UPDATE_EMPLEADO = "UPDATE EMPLEADO SET NOMBRE=?,APELLIDOS=?,EDAD=?,CARGOEMPRESA=? WHERE ID =?";
 
	public static final String DELETE_EMPLEADO = "DELETE FROM EMPLEADO WHERE ID =?";
	
	
	 
}
