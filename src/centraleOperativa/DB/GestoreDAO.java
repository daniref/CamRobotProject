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

public class GestoreDAO {
	public static Gestore loadGestoreByORMID(String id) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return loadGestoreByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Gestore getGestoreByORMID(String id) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return getGestoreByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Gestore loadGestoreByORMID(String id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return loadGestoreByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Gestore getGestoreByORMID(String id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return getGestoreByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Gestore loadGestoreByORMID(PersistentSession session, String id) throws PersistentException {
		try {
			return (Gestore) session.load(Gestore.class, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Gestore getGestoreByORMID(PersistentSession session, String id) throws PersistentException {
		try {
			return (Gestore) session.get(Gestore.class, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Gestore loadGestoreByORMID(PersistentSession session, String id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Gestore) session.load(Gestore.class, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Gestore getGestoreByORMID(PersistentSession session, String id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Gestore) session.get(Gestore.class, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryGestore(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return queryGestore(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryGestore(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return queryGestore(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Gestore[] listGestoreByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return listGestoreByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Gestore[] listGestoreByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return listGestoreByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryGestore(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From Gestore as Gestore");
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
	
	public static List queryGestore(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From Gestore as Gestore");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Gestore", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Gestore[] listGestoreByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryGestore(session, condition, orderBy);
			return (Gestore[]) list.toArray(new Gestore[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Gestore[] listGestoreByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryGestore(session, condition, orderBy, lockMode);
			return (Gestore[]) list.toArray(new Gestore[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Gestore loadGestoreByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return loadGestoreByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Gestore loadGestoreByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return loadGestoreByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Gestore loadGestoreByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Gestore[] gestores = listGestoreByQuery(session, condition, orderBy);
		if (gestores != null && gestores.length > 0)
			return gestores[0];
		else
			return null;
	}
	
	public static Gestore loadGestoreByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Gestore[] gestores = listGestoreByQuery(session, condition, orderBy, lockMode);
		if (gestores != null && gestores.length > 0)
			return gestores[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateGestoreByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return iterateGestoreByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateGestoreByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = CamRobotPersistentManager.instance().getSession();
			return iterateGestoreByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateGestoreByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From Gestore as Gestore");
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
	
	public static java.util.Iterator iterateGestoreByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From Gestore as Gestore");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Gestore", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Gestore createGestore() {
		return new Gestore();
	}
	
	public static boolean save(Gestore gestore) throws PersistentException {
		try {
			CamRobotPersistentManager.instance().saveObject(gestore);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(Gestore gestore) throws PersistentException {
		try {
			CamRobotPersistentManager.instance().deleteObject(gestore);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	

	

	
	public static boolean refresh(Gestore gestore) throws PersistentException {
		try {
			CamRobotPersistentManager.instance().getSession().refresh(gestore);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(Gestore gestore) throws PersistentException {
		try {
			CamRobotPersistentManager.instance().getSession().evict(gestore);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	

}
