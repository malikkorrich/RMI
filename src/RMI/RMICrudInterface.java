package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @author malik
 * La interface RMICrudInterface  extiende del interface java.rmi.Remote que permite que los metodos CRUD en esta interface sean invocado 
 * desde cualquier máquina virtual de Java .los metodo lanzan RemoteException y  la interface tiene que ser public 
 */
public interface RMICrudInterface  extends Remote {
	
	
	
	
	/**
	 * El metodo insertEmp permite insertar los datos del empleado en la base de datos SQLLITE
	 * @param nombre String el nombre de empleado 
	 * @param apellidos String apellido de empleado 
	 * @param edad int edad de empleado 
	 * @param CargoEmp String el cargo de empleado 
	 * @throws RemoteException  RemoteException
	 */
	
	public int insertEmp(String nombre,String apellidos , int edad , String CargoEmp) throws RemoteException;
	
	
	
	
	
	/**
	 * El metodo updateEmp permite modifcar los datos del empleado mediante su id 
	 * @param id int el id de empleado 
	 * @param nombre  string  nombre de empleado 
	 * @param apellidos string apellido de empleado 
	 * @param edad int edad de empleado 
	 * @param CargoEmp string  cargo de empleado 
	 * @throws RemoteException RemoteException
	 */
 
	public int updateEmp(int id,String nombre, String apellidos, int edad, String CargoEmp) throws RemoteException;
	
	
	
	
	/**
	 * El metodo selectEmp permite de mostrar todos los empleados  en la tabla empleado 
	 * @throws RemoteException RemoteException
	 */
	public ArrayList<Empleado> selectEmp() throws RemoteException;
	
	
	
	
	
	
	/**
	 * El deleteEmp permite eliminar un empleado mediante su id 
	 * @param idEmp int
	 * @throws RemoteException RemoteException
	 */
	
	public int deleteEmp(int idEmp) throws RemoteException;
	
	
	
	
	
	
	
	
	

}
