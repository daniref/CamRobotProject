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

public class ContrattoDAO {
	
	//-------------Crea un nuovo Contratto-------------
	public static Contratto createContratto(String id, Cliente utente, Robot robot, Date data_di_inizio, Date data_di_scadenza,
			float canone) {

		return new Contratto(id,utente,robot,data_di_inizio,data_di_scadenza,canone);
	
	}

	//--------------Salva un nuovo contratto nel database
	public static boolean save(Contratto contratto) throws PersistentException {

		PersistentSession session = centraleOperativa.DB.CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		try {
			CamRobotPersistentManager.instance().saveObject(contratto);
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
		
		String max="cn0000";
		PersistentSession session = centraleOperativa.DB.CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		try {
			String hql = "SELECT max(C.id) FROM Contratto C";
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
	
	//----------Calcola l'id univoco da associare al nuovo contratto
	public static String getNextId () throws PersistentException{

		String nextMaxIdString="000";
		try {
			String currentMaxIdString = getMaxId();
			int currentMaxIdInt = Integer.parseInt(currentMaxIdString.substring(2));
			currentMaxIdInt++;
			String nextMaxIdTemp = String.format("%04d", currentMaxIdInt);
			nextMaxIdString="cn"+nextMaxIdTemp;
			return nextMaxIdString;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	
	}
	
	//----------Cerca un contratto nel database fornendo l'id del contratto----------
	public static Contratto getContrattoByIdContratto(String id) throws PersistentException{
		
		PersistentSession session = centraleOperativa.DB.CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		Contratto returnedContratto = new Contratto();
		try {
			String hql = "FROM Contratto C WHERE C.id='"+id+"'";
			Query query = session.createQuery(hql);
			transaction.commit();
			if(!query.list().isEmpty()) {
				returnedContratto=(Contratto)query.list().get(0);
			}
			return returnedContratto;
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
	
	//----------Cerca un contratto nel database fornendo l'id del robot----------
	public static Contratto getContrattoByIdRobot(String id) throws PersistentException{
		
		PersistentSession session = centraleOperativa.DB.CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		Contratto returnedContratto = new Contratto();
		try {
			String hql = "FROM Contratto C WHERE C.robot='"+id+"'";
			Query query = session.createQuery(hql);
			transaction.commit();
			if(!query.list().isEmpty()) {
				returnedContratto=(Contratto)query.list().get(0);
			}
			return returnedContratto;
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
