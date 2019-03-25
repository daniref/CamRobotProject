package centraleOperativa.Entity;

import centraleOperativa.Entity.*;
import centraleOperativa.DB.KeepAlive;
import centraleOperativa.DB.KeepAliveDAO;
import centraleOperativa.DB.Robot;
import centraleOperativa.DB.RobotDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import org.orm.PersistentException;

public class registroKeepAlive_Entity {
	
	//creazione del SINGLETON
	private static registroKeepAlive_Entity registro=null;
	
	//lista di tutti i robot appartenenti all'area
	private static ArrayList<keepAlive_Entity> listaKeep;
	
	//costruttore privato
	private registroKeepAlive_Entity() throws PersistentException{

		try {
			this.listaKeep=new ArrayList<keepAlive_Entity>();
        		ArrayList<KeepAlive> keepList = new ArrayList<KeepAlive>();
        		keepList=KeepAliveDAO.getKeepAliveList();
        		TimeZone.getDefault();
			TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        		for(KeepAlive k : keepList) {
        			keepAlive_Entity new_keep = new keepAlive_Entity();
        			java.util.Date date=convertFromSQLDateToJAVADate(k.getData());
        			java.util.Date dateTime= new Date(date.getTime()+k.getOra().getTime());
        			new_keep.setId(k.getId());
        			new_keep.setDataTime(dateTime);
        			new_keep.setIdRobot(k.getRobot().getId());
        			this.listaKeep.add(new_keep);
	        	}
		}
        	catch(Exception e) {
        		e.printStackTrace();
        		throw new PersistentException(e);
        	}
		
	}
	
	//-------metodo usato per l'accesso alla classe singleton
	public static synchronized registroKeepAlive_Entity getInstance() throws PersistentException{
		if(registro==null) {
			try {
				registro=new registroKeepAlive_Entity();
			}
			catch(Exception e) {
				e.printStackTrace();
				throw new PersistentException(e);
			}
		}
		return registro;
	}
	
	
	public static java.util.Date convertFromSQLDateToJAVADate(java.sql.Date sqlDate) {
		
		java.util.Date javaDate = null;
		if (sqlDate != null) {
			javaDate = new Date(sqlDate.getTime());
		}
		return javaDate;
	
	}
	
	public void addKeep(keepAlive_Entity keep) throws PersistentException{
		try {
			listaKeep.add(keep);
			keep.addKeep();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void updateKeep(keepAlive_Entity keep) throws PersistentException{
		
		try {
			keepAlive_Entity old_keep=getKeepByIdRobot(keep.getIdRobot());
			listaKeep.set(listaKeep.indexOf(old_keep),keep);
			old_keep.deleteKeep();
			keep.addKeep();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static ArrayList<keepAlive_Entity> getListaKeepAlive() {
		return listaKeep;
	}

	public static keepAlive_Entity getKeepByIdRobot(String idRobot) {

		ArrayList<keepAlive_Entity> keepList = getListaKeepAlive();
		keepAlive_Entity k = new keepAlive_Entity();
		int i=0;
		boolean trovato=false;
		while(i<keepList.size() && !trovato) {
			k = keepList.get(i);
			if(k.getIdRobot().compareTo(idRobot)==0) {
				trovato=true;
			}
			else {
				i++;
			}
		}
		return k;
	}

}
