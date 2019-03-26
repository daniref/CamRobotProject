/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: 
 * License Type: Evaluation
 */
package centraleOperativa.DB;

import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;

import java.sql.Date;
import java.util.List;

public class ClienteDAO {

	//-------------Crea un nuovo Cliente-------------
	public static Cliente createCliente(String id, String nome, String cognome, String username, String password, String recapito,
			Date data_di_nascita, String luogo_di_nascita) {

		return new Cliente(id,nome,cognome,username,password,recapito,data_di_nascita,luogo_di_nascita);
		
	}
	
	//--------------Salva un nuovo cliente nel database
	public static boolean save(centraleOperativa.DB.Cliente cliente) throws PersistentException {

		PersistentSession session = centraleOperativa.DB.CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		try {
			CamRobotPersistentManager.instance().saveObject(cliente);
			transaction.commit();
			return true;
		}
		catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw new PersistentException(e);
		}
		finally {
			session.close();
		}
		
	}
	
	//----------Calcola l'id più grande salvato nel db
	public static String getMaxId() throws PersistentException {
		
		String max="cl0000";
		PersistentSession session = centraleOperativa.DB.CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		try {
			String hql = "SELECT max(C.id) FROM Cliente C";
			Query query = session.createQuery(hql);
			if(query.list().get(0)!=null) {
				max=(String)query.list().get(0);
			}
			transaction.commit();
			return max;
		}
		catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw new PersistentException(e);
		}
		finally {
		    session.close();
		}
		
	}
	
	//----------Calcola l'id univoco da associare al nuovo cliente
	public static String getNextId () throws PersistentException{

		String nextMaxIdString="000";
		try {
			String currentMaxIdString = getMaxId();
			int currentMaxIdInt = Integer.parseInt(currentMaxIdString.substring(2));
			currentMaxIdInt++;
			String nextMaxIdTemp = String.format("%04d", currentMaxIdInt);
			nextMaxIdString="cl"+nextMaxIdTemp;
			return nextMaxIdString;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	
	}
	
	//----------Cerca un username nel database-----------
	public static boolean searchUsername(String username) throws PersistentException{
		
		PersistentSession session = centraleOperativa.DB.CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		try {
			String hql = "FROM Cliente C WHERE C.username='"+username+"'";
			Query query = session.createQuery(hql);
			transaction.commit();
			return(query.list().isEmpty());

		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
		finally {
			    session.close();
		}
				
	}
	
	//----------Cerca un cliente nel database fornendo l'id-----------
	public static Cliente getClienteById(String id) throws PersistentException{
		
		PersistentSession session = centraleOperativa.DB.CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		Cliente returnedCliente = new Cliente();
		try {
			String hql = "FROM Cliente C WHERE C.id='"+id+"'";
			Query query = session.createQuery(hql);
			transaction.commit();
			if(!query.list().isEmpty()) {
				returnedCliente=(Cliente)query.list().get(0);
			}
			return returnedCliente;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
		finally {
			    session.close();
		}
				
	}
	
	//------------------Restituisce il recapito del cliente avente un certo id----
	public static String getRecapitobyId (String id) throws PersistentException{
		
		try {
			Cliente new_cliente = new Cliente();
			new_cliente=getClienteById(id);
			return new_cliente.getRecapito();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
		
	}

}
