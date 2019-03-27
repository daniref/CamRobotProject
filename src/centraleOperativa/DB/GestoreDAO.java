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

public class GestoreDAO {
	
	//-------------Crea un nuovo gestore-------------
	public static Gestore createGestore(String id, String nome, String recapito) {

		return new Gestore(id,nome,recapito);
		
	}
	
	//--------------Salva un nuovo gestore nel database
	public static boolean save(Gestore gestore) throws PersistentException {

		PersistentSession session = centraleOperativa.DB.CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		try {
			CamRobotPersistentManager.instance().saveObject(gestore);
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
		
		String max="gs0000";
		PersistentSession session = centraleOperativa.DB.CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		try {
			String hql = "SELECT max(G.id) FROM Gestore G";
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
			nextMaxIdString="gs"+nextMaxIdTemp;
			return nextMaxIdString;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	
	}
	
	//----------Cerca un gestore nel database fornendo l'id-----------
	public static Gestore getGestoreById(String id) throws PersistentException{
		
		PersistentSession session = centraleOperativa.DB.CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		Gestore returnedGestore = new Gestore();
		try {
			String hql = "FROM Gestore G WHERE G.id='"+id+"'";
			Query query = session.createQuery(hql);
			transaction.commit();
			if(!query.list().isEmpty()) {
				returnedGestore=(Gestore)query.list().get(0);
			}
			return returnedGestore;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
		finally {
			    session.close();
		}
				
	}

	
}
