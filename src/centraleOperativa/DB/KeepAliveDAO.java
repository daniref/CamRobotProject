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

public class KeepAliveDAO {
	public static KeepAlive loadKeepAliveByORMID(String id) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return loadKeepAliveByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KeepAlive getKeepAliveByORMID(String id) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return getKeepAliveByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KeepAlive loadKeepAliveByORMID(String id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return loadKeepAliveByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KeepAlive getKeepAliveByORMID(String id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return getKeepAliveByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KeepAlive loadKeepAliveByORMID(PersistentSession session, String id) throws PersistentException {
		try {
			return (KeepAlive) session.load(KeepAlive.class, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KeepAlive getKeepAliveByORMID(PersistentSession session, String id) throws PersistentException {
		try {
			return (KeepAlive) session.get(KeepAlive.class, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KeepAlive loadKeepAliveByORMID(PersistentSession session, String id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (KeepAlive) session.load(KeepAlive.class, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KeepAlive getKeepAliveByORMID(PersistentSession session, String id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (KeepAlive) session.get(KeepAlive.class, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryKeepAlive(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return queryKeepAlive(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryKeepAlive(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return queryKeepAlive(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KeepAlive[] listKeepAliveByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return listKeepAliveByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KeepAlive[] listKeepAliveByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return listKeepAliveByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryKeepAlive(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From KeepAlive as KeepAlive");
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
	
	public static List queryKeepAlive(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From KeepAlive as KeepAlive");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("KeepAlive", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KeepAlive[] listKeepAliveByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryKeepAlive(session, condition, orderBy);
			return (KeepAlive[]) list.toArray(new KeepAlive[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KeepAlive[] listKeepAliveByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryKeepAlive(session, condition, orderBy, lockMode);
			return (KeepAlive[]) list.toArray(new KeepAlive[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KeepAlive loadKeepAliveByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return loadKeepAliveByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KeepAlive loadKeepAliveByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return loadKeepAliveByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KeepAlive loadKeepAliveByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		KeepAlive[] keepAlives = listKeepAliveByQuery(session, condition, orderBy);
		if (keepAlives != null && keepAlives.length > 0)
			return keepAlives[0];
		else
			return null;
	}
	
	public static KeepAlive loadKeepAliveByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		KeepAlive[] keepAlives = listKeepAliveByQuery(session, condition, orderBy, lockMode);
		if (keepAlives != null && keepAlives.length > 0)
			return keepAlives[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateKeepAliveByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return iterateKeepAliveByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateKeepAliveByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return iterateKeepAliveByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateKeepAliveByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From KeepAlive as KeepAlive");
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
	
	public static java.util.Iterator iterateKeepAliveByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From KeepAlive as KeepAlive");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("KeepAlive", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static KeepAlive createKeepAlive() {
		return new KeepAlive();
	}
	
	public static boolean save(KeepAlive keepAlive) throws PersistentException {
		try {
			CamRobotPersistentManager.instance().saveObject(keepAlive);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(KeepAlive keepAlive) throws PersistentException {
		try {
			CamRobotPersistentManager.instance().deleteObject(keepAlive);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(KeepAlive keepAlive)throws PersistentException {
		try {
			if (keepAlive.getRobot() != null) {
				keepAlive.getRobot().setKeepAlive(null);
			}
			
			return delete(keepAlive);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(KeepAlive keepAlive, org.orm.PersistentSession session)throws PersistentException {
		try {
			if (keepAlive.getRobot() != null) {
				keepAlive.getRobot().setKeepAlive(null);
			}
			
			try {
				session.delete(keepAlive);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(KeepAlive keepAlive) throws PersistentException {
		try {
			CamRobotPersistentManager.instance().getSession().refresh(keepAlive);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(KeepAlive keepAlive) throws PersistentException {
		try {
			CamRobotPersistentManager.instance().getSession().evict(keepAlive);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
}
