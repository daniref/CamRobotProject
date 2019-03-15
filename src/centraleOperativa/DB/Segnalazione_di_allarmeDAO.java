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
import java.util.List;

public class Segnalazione_di_allarmeDAO {
	public static Segnalazione_di_allarme loadSegnalazione_di_allarmeByORMID(String id) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return loadSegnalazione_di_allarmeByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Segnalazione_di_allarme getSegnalazione_di_allarmeByORMID(String id) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return getSegnalazione_di_allarmeByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Segnalazione_di_allarme loadSegnalazione_di_allarmeByORMID(String id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return loadSegnalazione_di_allarmeByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Segnalazione_di_allarme getSegnalazione_di_allarmeByORMID(String id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return getSegnalazione_di_allarmeByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Segnalazione_di_allarme loadSegnalazione_di_allarmeByORMID(PersistentSession session, String id) throws PersistentException {
		try {
			return (Segnalazione_di_allarme) session.load(Segnalazione_di_allarme.class, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Segnalazione_di_allarme getSegnalazione_di_allarmeByORMID(PersistentSession session, String id) throws PersistentException {
		try {
			return (Segnalazione_di_allarme) session.get(Segnalazione_di_allarme.class, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Segnalazione_di_allarme loadSegnalazione_di_allarmeByORMID(PersistentSession session, String id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Segnalazione_di_allarme) session.load(Segnalazione_di_allarme.class, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Segnalazione_di_allarme getSegnalazione_di_allarmeByORMID(PersistentSession session, String id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Segnalazione_di_allarme) session.get(Segnalazione_di_allarme.class, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List querySegnalazione_di_allarme(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return querySegnalazione_di_allarme(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List querySegnalazione_di_allarme(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return querySegnalazione_di_allarme(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Segnalazione_di_allarme[] listSegnalazione_di_allarmeByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return listSegnalazione_di_allarmeByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Segnalazione_di_allarme[] listSegnalazione_di_allarmeByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return listSegnalazione_di_allarmeByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List querySegnalazione_di_allarme(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From Segnalazione_di_allarme as Segnalazione_di_allarme");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List querySegnalazione_di_allarme(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From Segnalazione_di_allarme as Segnalazione_di_allarme");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Segnalazione_di_allarme", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Segnalazione_di_allarme[] listSegnalazione_di_allarmeByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = querySegnalazione_di_allarme(session, condition, orderBy);
			return (Segnalazione_di_allarme[]) list.toArray(new Segnalazione_di_allarme[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Segnalazione_di_allarme[] listSegnalazione_di_allarmeByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = querySegnalazione_di_allarme(session, condition, orderBy, lockMode);
			return (Segnalazione_di_allarme[]) list.toArray(new Segnalazione_di_allarme[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Segnalazione_di_allarme loadSegnalazione_di_allarmeByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return loadSegnalazione_di_allarmeByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Segnalazione_di_allarme loadSegnalazione_di_allarmeByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return loadSegnalazione_di_allarmeByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Segnalazione_di_allarme loadSegnalazione_di_allarmeByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Segnalazione_di_allarme[] segnalazione_di_allarmes = listSegnalazione_di_allarmeByQuery(session, condition, orderBy);
		if (segnalazione_di_allarmes != null && segnalazione_di_allarmes.length > 0)
			return segnalazione_di_allarmes[0];
		else
			return null;
	}
	
	public static Segnalazione_di_allarme loadSegnalazione_di_allarmeByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Segnalazione_di_allarme[] segnalazione_di_allarmes = listSegnalazione_di_allarmeByQuery(session, condition, orderBy, lockMode);
		if (segnalazione_di_allarmes != null && segnalazione_di_allarmes.length > 0)
			return segnalazione_di_allarmes[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateSegnalazione_di_allarmeByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return iterateSegnalazione_di_allarmeByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateSegnalazione_di_allarmeByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return iterateSegnalazione_di_allarmeByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateSegnalazione_di_allarmeByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From Segnalazione_di_allarme as Segnalazione_di_allarme");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateSegnalazione_di_allarmeByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From Segnalazione_di_allarme as Segnalazione_di_allarme");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Segnalazione_di_allarme", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Segnalazione_di_allarme createSegnalazione_di_allarme() {
		return new Segnalazione_di_allarme();
	}
	
	public static boolean save(Segnalazione_di_allarme segnalazione_di_allarme) throws PersistentException {
		try {
			CamRobotPersistentManager.instance().saveObject(segnalazione_di_allarme);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(Segnalazione_di_allarme segnalazione_di_allarme) throws PersistentException {
		try {
			CamRobotPersistentManager.instance().deleteObject(segnalazione_di_allarme);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	

	

	
	public static boolean refresh(Segnalazione_di_allarme segnalazione_di_allarme) throws PersistentException {
		try {
			CamRobotPersistentManager.instance().getSession().refresh(segnalazione_di_allarme);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(Segnalazione_di_allarme segnalazione_di_allarme) throws PersistentException {
		try {
			CamRobotPersistentManager.instance().getSession().evict(segnalazione_di_allarme);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	

}
