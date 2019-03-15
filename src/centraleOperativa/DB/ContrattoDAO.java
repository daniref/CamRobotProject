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

public class ContrattoDAO {
	public static Contratto loadContrattoByORMID(String id) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return loadContrattoByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Contratto getContrattoByORMID(String id) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return getContrattoByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Contratto loadContrattoByORMID(String id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return loadContrattoByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Contratto getContrattoByORMID(String id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return getContrattoByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Contratto loadContrattoByORMID(PersistentSession session, String id) throws PersistentException {
		try {
			return (Contratto) session.load(Contratto.class, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Contratto getContrattoByORMID(PersistentSession session, String id) throws PersistentException {
		try {
			return (Contratto) session.get(Contratto.class, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Contratto loadContrattoByORMID(PersistentSession session, String id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Contratto) session.load(Contratto.class, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Contratto getContrattoByORMID(PersistentSession session, String id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Contratto) session.get(Contratto.class, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryContratto(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return queryContratto(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryContratto(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return queryContratto(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Contratto[] listContrattoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return listContrattoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Contratto[] listContrattoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return listContrattoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryContratto(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From Contratto as Contratto");
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
	
	public static List queryContratto(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From Contratto as Contratto");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Contratto", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Contratto[] listContrattoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryContratto(session, condition, orderBy);
			return (Contratto[]) list.toArray(new Contratto[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Contratto[] listContrattoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryContratto(session, condition, orderBy, lockMode);
			return (Contratto[]) list.toArray(new Contratto[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Contratto loadContrattoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return loadContrattoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Contratto loadContrattoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return loadContrattoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Contratto loadContrattoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Contratto[] contrattos = listContrattoByQuery(session, condition, orderBy);
		if (contrattos != null && contrattos.length > 0)
			return contrattos[0];
		else
			return null;
	}
	
	public static Contratto loadContrattoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Contratto[] contrattos = listContrattoByQuery(session, condition, orderBy, lockMode);
		if (contrattos != null && contrattos.length > 0)
			return contrattos[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateContrattoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return iterateContrattoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateContrattoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return iterateContrattoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateContrattoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From Contratto as Contratto");
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
	
	public static java.util.Iterator iterateContrattoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From Contratto as Contratto");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Contratto", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Contratto createContratto() {
		return new Contratto();
	}
	
	public static boolean save(Contratto contratto) throws PersistentException {
		try {
			CamRobotPersistentManager.instance().saveObject(contratto);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(Contratto contratto) throws PersistentException {
		try {
			CamRobotPersistentManager.instance().deleteObject(contratto);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	

	

	
	public static boolean refresh(Contratto contratto) throws PersistentException {
		try {
			CamRobotPersistentManager.instance().getSession().refresh(contratto);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(Contratto contratto) throws PersistentException {
		try {
			CamRobotPersistentManager.instance().getSession().evict(contratto);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	

}
