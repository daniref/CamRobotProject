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

public class Keep_AliveDAO {
	public static Keep_Alive loadKeep_AliveByORMID(String id) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return loadKeep_AliveByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Keep_Alive getKeep_AliveByORMID(String id) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return getKeep_AliveByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Keep_Alive loadKeep_AliveByORMID(String id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return loadKeep_AliveByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Keep_Alive getKeep_AliveByORMID(String id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return getKeep_AliveByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Keep_Alive loadKeep_AliveByORMID(PersistentSession session, String id) throws PersistentException {
		try {
			return (Keep_Alive) session.load(Keep_Alive.class, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Keep_Alive getKeep_AliveByORMID(PersistentSession session, String id) throws PersistentException {
		try {
			return (Keep_Alive) session.get(Keep_Alive.class, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Keep_Alive loadKeep_AliveByORMID(PersistentSession session, String id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Keep_Alive) session.load(Keep_Alive.class, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Keep_Alive getKeep_AliveByORMID(PersistentSession session, String id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Keep_Alive) session.get(Keep_Alive.class, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryKeep_Alive(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return queryKeep_Alive(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryKeep_Alive(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return queryKeep_Alive(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Keep_Alive[] listKeep_AliveByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return listKeep_AliveByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Keep_Alive[] listKeep_AliveByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return listKeep_AliveByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryKeep_Alive(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From Keep_Alive as Keep_Alive");
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
	
	public static List queryKeep_Alive(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From Keep_Alive as Keep_Alive");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Keep_Alive", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Keep_Alive[] listKeep_AliveByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryKeep_Alive(session, condition, orderBy);
			return (Keep_Alive[]) list.toArray(new Keep_Alive[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Keep_Alive[] listKeep_AliveByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryKeep_Alive(session, condition, orderBy, lockMode);
			return (Keep_Alive[]) list.toArray(new Keep_Alive[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Keep_Alive loadKeep_AliveByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return loadKeep_AliveByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Keep_Alive loadKeep_AliveByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return loadKeep_AliveByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Keep_Alive loadKeep_AliveByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Keep_Alive[] keep_Alives = listKeep_AliveByQuery(session, condition, orderBy);
		if (keep_Alives != null && keep_Alives.length > 0)
			return keep_Alives[0];
		else
			return null;
	}
	
	public static Keep_Alive loadKeep_AliveByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Keep_Alive[] keep_Alives = listKeep_AliveByQuery(session, condition, orderBy, lockMode);
		if (keep_Alives != null && keep_Alives.length > 0)
			return keep_Alives[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateKeep_AliveByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return iterateKeep_AliveByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateKeep_AliveByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return iterateKeep_AliveByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateKeep_AliveByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From Keep_Alive as Keep_Alive");
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
	
	public static java.util.Iterator iterateKeep_AliveByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From Keep_Alive as Keep_Alive");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Keep_Alive", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Keep_Alive createKeep_Alive() {
		return new Keep_Alive();
	}
	
	public static boolean save(Keep_Alive keep_Alive) throws PersistentException {
		try {
			CamRobotPersistentManager.instance().saveObject(keep_Alive);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(Keep_Alive keep_Alive) throws PersistentException {
		try {
			CamRobotPersistentManager.instance().deleteObject(keep_Alive);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	

	

	
	public static boolean refresh(Keep_Alive keep_Alive) throws PersistentException {
		try {
			CamRobotPersistentManager.instance().getSession().refresh(keep_Alive);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(Keep_Alive keep_Alive) throws PersistentException {
		try {
			CamRobotPersistentManager.instance().getSession().evict(keep_Alive);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	

}
