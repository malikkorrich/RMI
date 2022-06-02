package RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author malik
 * La clase RMIClient nos permite establezer conexion y obtener informacion sobre registry con el servidor en el puerto especificado 
 *  usando el metodo getRegistry() de la clase  LocateRegistry psandole el host y el puertoy buscar el objeto remoto en RMI Registry el metodo lookup() 
 *  por el nombre que se ha definido en el servidor  usando el metodo una vez obtenido se invoca los metodos remotos implementados en el servidor 
 * La clase cliente debe importar la interface  RMICrudInterface  
 *
 */
public class RMIClient {

	public static void main(String[] args) {
		/**
		 * aqui se crea un objeto de la interface  RMICrudInterface
		 */
		RMICrudInterface  crud = null;
		ArrayList<Empleado> lista;	
		int filas_affec;
		try {
			/**
			 * aqui se estableze la conexion con el servior en el puerto especificado  y se obtiene la infromacion sobre el registry mediante el metodo getRegistry()
			 */
			Registry registry = LocateRegistry.getRegistry("localhost",55442);
			
			try {
				/**
				 * aqui se obtiene el objeto remoto 
				 */
				crud = (RMICrudInterface) registry.lookup("CurdObj");
			
				
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			/**
			 * se comprueba si el objeto no es null se invocada los metodos 
			 */
			if (crud != null) {
			 
				System.out.println("*****Insert Empleados****");
				filas_affec  = crud.insertEmp("pepe", "gil", 36, "jefa");
				System.out.println("Insert filas afectadas: "+ filas_affec);
				
				filas_affec  = crud.insertEmp("sara ", "martinez", 36, "jefa");
				System.out.println("Insert filas afectadas: "+ filas_affec);
				
				
				System.out.println("\n*****Select Empleados****");
			lista = crud.selectEmp();
			
			for (Empleado e : lista) {
				System.out.println(e.toString());
			}
			
			
			 
			System.out.println("\n*****Update Empleado id=5 ****");
			filas_affec = crud.updateEmp(5, "jauan", "carlos", 30, "empleado");
			System.out.println("Update filas affectadas : "+ filas_affec);
			
			
			System.out.println("\n*****Delete Empleado id 7 ****");
			filas_affec = crud.deleteEmp(9);	
			System.out.println("Delete filas affectadas : "+ filas_affec); 
			
			
			
			
			System.out.println("\n*****Select Empleados****");
			lista = crud.selectEmp();
			
			for (Empleado e : lista) {
				System.out.println(e.toString());
			}
			
			
			}
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
