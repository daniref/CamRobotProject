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

import java.util.ArrayList;
import java.util.List;

public class SensoreDAO {
	
	public SensoreDAO() {
		
	}

	//-------------Crea un nuovo Sensore-------------
	public static Sensore createSensore(String id, float soglia, String tipologia, Robot robot) {

		return new Sensore(id,soglia,tipologia,robot);
	
	}
	
	//--------------Salva un nuovo sensore nel database
	public static boolean save(Sensore sensore) throws PersistentException {

		PersistentSession session = centraleOperativa.DB.CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		try {
			CamRobotPersistentManager.instance().saveObject(sensore);
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
		
		String max="sn0000";
		PersistentSession session = centraleOperativa.DB.CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		try {
			String hql = "SELECT max(S.id) FROM Sensore S";
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
	
	//----------Calcola l'id univoco da associare al nuovo sensore
	public static String getNextId () throws PersistentException{

		String nextMaxIdString="000";
		try {
			String currentMaxIdString = getMaxId();
			int currentMaxIdInt = Integer.parseInt(currentMaxIdString.substring(2));
			currentMaxIdInt++;
			String nextMaxIdTemp = String.format("%04d", currentMaxIdInt);
			nextMaxIdString="sn"+nextMaxIdTemp;
			return nextMaxIdString;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	
	}
	
	//----------Cerca un sensore nel database fornendo l'id-----------
	public static Sensore getSensoreById(String id) throws PersistentException{
		
		PersistentSession session = centraleOperativa.DB.CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		Sensore returnedSensore = new Sensore();
		try {
			String hql = "FROM Sensore S WHERE S.id='"+id+"'";
			Query query = session.createQuery(hql);
			transaction.commit();
			if(!query.list().isEmpty()) {
				returnedSensore=(Sensore)query.list().get(0);
			}
			return returnedSensore;
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
	
	//---------Restituisci tutti i sensori associati ad un idRobot---------
	public static ArrayList<Sensore> getSensoriListByIdRobot(String idRobot) throws PersistentException{
		
		PersistentSession session = centraleOperativa.DB.CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		ArrayList<Sensore> sensoreList = new ArrayList<Sensore>();
		try {
			String hql = "FROM Sensore S WHERE S.robot='"+idRobot+"'";
			Query query = session.createQuery(hql);
			transaction.commit();
			if(!query.list().isEmpty()) {
				sensoreList=(ArrayList<Sensore>)query.list();
			}
			return sensoreList;
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
	
	//------------------Restituisce la tipologia di un sensore----
	public static String getTipologiaById (String id) throws PersistentException{
		
		try {
			Sensore new_sensore = new Sensore();
			new_sensore=getSensoreById(id);
			return new_sensore.getTipologia();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
		
	}

}
