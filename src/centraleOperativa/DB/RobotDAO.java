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

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class RobotDAO {
	
	public RobotDAO() {
		
	}

	//-------------Crea un nuovo Robot-------------
	public static Robot createRobot(String id, String stato, String condizione, String funzionamento, String indirizzo, String areaId) {
				return new Robot(id,stato,condizione,funzionamento,indirizzo,areaId);
		}
	
	//--------------Salva un nuovo Robot nel database
	public static boolean save(centraleOperativa.DB.Robot robot) throws PersistentException {
		PersistentSession session = centraleOperativa.DB.CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		try {
			CamRobotPersistentManager.instance().saveObject(robot);
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
		
		String max="rb0000";
		PersistentSession session = centraleOperativa.DB.CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		try {
			String hql = "SELECT max(R.id) FROM Robot R";
			Query query = session.createQuery(hql);
			max=(String)query.list().get(0);
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
			nextMaxIdString="rb"+nextMaxIdTemp;
			return nextMaxIdString;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	
	}

	
	//----------Cerca un robot nel database fornendo l'id-----------
	public static Robot getRobotById(String id) throws PersistentException{
		
		PersistentSession session = centraleOperativa.DB.CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		try {
			String hql = "FROM Robot R WHERE R.id='"+id+"'";
			Query query = session.createQuery(hql);
			transaction.commit();
			return((Robot)query.list().get(0));

		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
		finally {
			    session.close();
			}
				
	}
	
	//---------Restituisci tutti i robot dell'area---------
	public static ArrayList<Robot> getRobotListByIdArea(String idArea) throws PersistentException{
		
		
		PersistentSession session = centraleOperativa.DB.CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		try {
			String hql = "FROM Robot R WHERE R.areaId='"+idArea+"'";
			Query query = session.createQuery(hql);
			transaction.commit();
			ArrayList<Robot> tmp = new ArrayList<Robot>(query.list());
			return tmp;
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
