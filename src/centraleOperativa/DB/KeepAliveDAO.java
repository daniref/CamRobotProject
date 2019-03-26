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
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class KeepAliveDAO {
	
	//-------------Crea un nuovo Keep Alive-------------
	public static KeepAlive createKeep(String id, Date data, Time ora, Robot robot) {

		return new KeepAlive(id,data,ora,robot);
		
	}
	
	//--------------Salva un nuovo cliente nel database
	public static boolean save(KeepAlive keep) throws PersistentException {
		
		PersistentSession session = CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		try {
			CamRobotPersistentManager.instance().saveObject(keep);
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
	
	//----------Cancella un keep alive dal database
	public static boolean delete(centraleOperativa.DB.KeepAlive keep) throws PersistentException {

		PersistentSession session = CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		try {
			session.delete(keep);
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
		
		String max="ka0000";
		PersistentSession session = CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		try {
			String hql = "SELECT max(K.id) FROM KeepAlive K";
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
	
	//----------Calcola l'id univoco da associare al nuovo keep alive
	public static String getNextId () throws PersistentException{
		
		String nextMaxIdString="000";
		try {
			String currentMaxIdString = getMaxId();
			int currentMaxIdInt = Integer.parseInt(currentMaxIdString.substring(2));
			currentMaxIdInt++;
			String nextMaxIdTemp = String.format("%04d", currentMaxIdInt);
			nextMaxIdString="ka"+nextMaxIdTemp;
			return nextMaxIdString;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	
	}
	
	//----------Ottieni lista keep alive nel database----------
	public static ArrayList<KeepAlive> getKeepAliveList() throws PersistentException{
		
		PersistentSession session = CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		ArrayList<KeepAlive> keepAliveList = new ArrayList<KeepAlive>();
		try {
			String hql = "FROM KeepAlive";
			Query query = session.createQuery(hql);
			transaction.commit();
			if(!query.list().isEmpty()) {
				keepAliveList=(ArrayList<KeepAlive>)query.list();
			}
			return keepAliveList;
		}
		catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw new PersistentException(e);
		}
		finally {
			session.close();
		}
				
	}
			
	//----------Cerca un keep alive nel database fornendo l'id del keep alive----------
	public static KeepAlive getKeepAliveByIdKeep(String id) throws PersistentException{
		
		PersistentSession session = CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		KeepAlive returnedKeep = new KeepAlive();
		try {
			String hql = "FROM KeepAlive K WHERE K.id='"+id+"'";
			Query query = session.createQuery(hql);
			transaction.commit();
			if(!query.list().isEmpty()) {
				returnedKeep=(KeepAlive)query.list().get(0);
			}
			return returnedKeep;
		}
		catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw new PersistentException(e);
		}
		finally {
			session.close();
		}
				
	}
	
	//----------Cerca un keep alive nel database fornendo l'id del robot----------
	public static KeepAlive getKeepAliveByIdRobot(String id) throws PersistentException{
		
		PersistentSession session = CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		KeepAlive returnedKeep = new KeepAlive();
		try {
			String hql = "FROM KeepAlive K WHERE K.robot='"+id+"'";
			Query query = session.createQuery(hql);
			transaction.commit();
			if(!query.list().isEmpty()) {
				returnedKeep=(KeepAlive)query.list().get(0);
			}
			return returnedKeep;
		}
		catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
			throw new PersistentException(e);
		}
		finally {
			session.close();
		}
				
	}
		
}
