package RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author malik
 * la clase RMIServer  implementa la interface RMICrudInterface con los metodos remoto  (selectEmp,deleteEmp...) permite crear un servidor que va estar escuchando 
 * en un puerto usando la libreria de java.rmi nos permite   Crear y exporta un objeto remoto
 */
public class RMIServer implements RMICrudInterface {
	
	private static String URL;
	private static Connection conn ;
	private static  PreparedStatement ps;
	private static  int  filas_affec;
 
	
	/**
	 * En el Constructor constructor se crear una conexion con la base de datos SQLITE  con el  metodo getConnection(URL) 
	 * pasandole URL de conxion 
	 */
	RMIServer(){
		
		/**
		 * hay que cambiar la ruta del base de datos G:\\...... 
		 */
		String URL = "jdbc:sqlite:G:\\FCL\\FCL Programacion de Serivicios y procesos\\PspWorkspace\\UT4_RMI_Korrich_Malik\\EmpleadosDB.db" ;
		try {
			Class.forName("org.sqlite.JDBC");
			  conn = DriverManager.getConnection(URL);
			
		} catch (ClassNotFoundException | SQLException e) {
		 
			e.printStackTrace();
		}
	}
	

	
	/**
	 * En el metodo main se crea una instancia de registro que acepta solicitudes en el puerto especificado luego se crea
	 * el objeto remoto que va ser el objeto server luego se inscrebe ese objeto en el registry que proporciona los metodos que van a server invocados 
	 * desde el cliente usando el metodo exportObject() de la clase UnicastRemoteObject
	 * tenemos al metodo rebind() que recibe un nombre y objeto  reemplazar objeto escrtito en el RMI registry con el nombre pasado por parametro 
	 * para hacerlo disponible al cliente es decir el cliente lo busque por este nombre 
	 * @param args String
	 */

	public static void main(String[] args)  {
	 
		
		 Registry reg = null;
		 
		 /**
		  * El metodo createRegistry(port) Crea y exporta una instancia de registro que acepta solicitudes en el puerto especificado.
		  */
	 try {
		reg = LocateRegistry.createRegistry(55442);
		
		
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		System.out.println("No se ha podido crear el registro:"+e.getLocalizedMessage());

	
	}
	 
	 /**
	  * aqui se crea objeto remoto 
	  */

	 RMIServer serverObj = new RMIServer();
	 
	 /**
	  * aqui se inscrebe el objeto en serverObj  que proporciona los metodos que van a server invocados 
	 * desde el cliente el registry usando el metodo exportObject() de la clase UnicastRemoteObject
	  */
	 try {
		RMICrudInterface stub = (RMICrudInterface) UnicastRemoteObject.exportObject(serverObj,0);
			reg.rebind("CurdObj", stub);
			
			System.out.println("Servidor Iniciado");
	 
	 
	 
	 
	 } catch (RemoteException e) {
		// TODO Auto-generated catch block
		System.out.println("No se ha podido inscribir el objeto servidor");
	}
		 
		
	 
	}


	
	
	/**
	 * El metodo insertEmp permite insertar los datos del empleado en la base de datos SQLLITE
	 */

	@Override
	public int insertEmp(String nombre, String apellidos, int edad, String CargoEmp) throws RemoteException {
		try {
			
			ps = conn.prepareStatement(SQLQueries.INSERT_EMPLEADO);
			ps.setString(1,nombre);
			ps.setString(2, apellidos);
			ps.setInt(3, edad);
			ps.setString(4, CargoEmp);
			
			  filas_affec = ps.executeUpdate();
			
			 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filas_affec;
	}


	/**
	 * El metodo updateEmp permite modifcar los datos del empleado mediante su id 
	 */

	@Override
	public int updateEmp(int id, String nombre, String apellidos, int edad, String CargoEmp) throws RemoteException {
		try {
			ps = conn.prepareStatement(SQLQueries.UPDATE_EMPLEADO);
			ps.setString(1,nombre);
			ps.setString(2, apellidos);
			ps.setInt(3, edad);
			ps.setString(4, CargoEmp);
			ps.setString(4, CargoEmp);
			ps.setInt(5, id);
			
			
			  filas_affec = ps.executeUpdate();
				
			  
			  
			  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filas_affec;
		
	}

	/**
	 * El metodo selectEmp permite de mostrar todos los empleados  en la tabla empleado 
	 */


	@Override
	public ArrayList<Empleado> selectEmp() throws RemoteException {
		ArrayList<Empleado> lista = new ArrayList<>();
		try {
			ps = conn.prepareStatement(SQLQueries.SELECT_EMPLEADO);
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nombre = resultSet.getString("nombre");
				String apellidos = resultSet.getString("apellidos");
				int edad = resultSet.getInt("edad");
				String cargoEmp = resultSet.getString("cargoEmpresa");
				
				//System.out.println(id +" " + nombre + " " + apellidos + " " + edad + " " + cargoEmp);
				
			
				lista.add(new Empleado(id,nombre,apellidos,edad,cargoEmp));
				
				 
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;		
	}


	/**
	 * El deleteEmp permite eliminar un empleado mediante su id 
	 */

	@Override
	public int deleteEmp(int idEmp) throws RemoteException {
		try {
			ps = conn.prepareStatement(SQLQueries.DELETE_EMPLEADO);
			
			ps.setInt(1,idEmp);
			  filas_affec = ps.executeUpdate();
				 
				
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return filas_affec;
	}
	
	


}
