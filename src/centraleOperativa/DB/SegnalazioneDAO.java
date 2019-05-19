/**
w * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
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

public class SegnalazioneDAO {
	
	//-------------Crea una nuova segnalazione-------------
	public static Segnalazione createSegnalazione(String id, String stato, float valore_allarme,
			Date data,Time ora, Gestore gestore,
			Sensore sensore, Robot robot) {

		return new Segnalazione(id,stato,valore_allarme,data,ora,gestore,sensore,robot);
		
	}
	
	//--------------Salva una nuova segnalazione nel database
	public static boolean save(Segnalazione segnalazione) throws PersistentException {
		
		PersistentSession session = CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		try {
			CamRobotPersistentManager.instance().saveObject(segnalazione);
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
	
	//----------Cancella una segnalazione dal database
	public static boolean delete(Segnalazione segnalazione) throws PersistentException {

		PersistentSession session = CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		try {
			session.delete(segnalazione);
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
		
		String max="sg0000";
		PersistentSession session = CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		try {
			String hql = "SELECT max(S.id) FROM Segnalazione S";
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
			nextMaxIdString="sg"+nextMaxIdTemp;
			return nextMaxIdString;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	
	}
	
	//----------Ottieni lista segnalazioni nel database in base al tipo di gestore----------
	public static ArrayList<Segnalazione> getSegnalazioniListByIdGestore(String idGestore) throws PersistentException{
		
		PersistentSession session = CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		ArrayList<Segnalazione> segnalazioniList = new ArrayList<Segnalazione>();
		
		try {
			//String hql = "FROM Segnalazione S WHERE S.gestore.id='"+idGestore+"'";
			java.util.Date date = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());			
			String hql = "FROM Segnalazione S WHERE S.gestore.id='"+idGestore+"' and S.data='"+sqlDate+"'";
			Query query = session.createQuery(hql);
			transaction.commit();
			if(!query.list().isEmpty()) {
				segnalazioniList=(ArrayList<Segnalazione>)query.list();
			}
			return segnalazioniList;
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
	
	//----------Cerca una segnalazione nel database fornendo l'id della segnalazione----------
	public static Segnalazione getSegnalazioneById(String id) throws PersistentException{
		
		PersistentSession session = CamRobotPersistentManager.instance().getSession();
		PersistentTransaction transaction = session.beginTransaction();
		Segnalazione returnedSegnalazione = new Segnalazione();
		try {
			String hql = "FROM Segnalazione S WHERE S.id='"+id+"'";
			Query query = session.createQuery(hql);
			transaction.commit();
			if(!query.list().isEmpty()) {
				returnedSegnalazione=(Segnalazione)query.list().get(0);
			}
			return returnedSegnalazione;
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
