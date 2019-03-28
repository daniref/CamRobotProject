package centraleOperativa.Entity;

import centraleOperativa.DB.CamRobotPersistentManager;
import centraleOperativa.DB.KeepAlive;
import centraleOperativa.DB.KeepAliveDAO;
import centraleOperativa.DB.Robot;
import centraleOperativa.DB.RobotDAO;
import centraleOperativa.Entity.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import org.orm.*;

public class registroKeepAlive_Entity {
	
	//creazione del SINGLETON
	private static registroKeepAlive_Entity registro=null;	
	
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
	
	//metodo usato per la conversione da sql.date a util.date
	public static java.util.Date convertFromSQLDateToJAVADate(java.sql.Date sqlDate) {
		
		java.util.Date javaDate = null;
		if (sqlDate != null) {
			javaDate = new Date(sqlDate.getTime());
		}
		return javaDate;
	
	}
	
	//metodo per aggiungere un keep  alive alla lista e al db
	public void addKeep(keepAlive_Entity keep) throws PersistentException{

		try {
			listaKeep.add(keep);
			keep.addKeep();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//metodo per aggiornare un keep alive nella lista e nel db
	public void updateKeep(keepAlive_Entity keep) throws PersistentException{
		
		try {
			keepAlive_Entity old_keep=getKeepByIdRobot(keep.getIdRobot());
			keep.setId(old_keep.getId());
			listaKeep.set(listaKeep.indexOf(old_keep),keep);
			keepAlive_Entity tobeupdate_keep = new keepAlive_Entity();
			tobeupdate_keep=keep;
			tobeupdate_keep.updateKeep();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	//metodo che restituisce la lista dei keep alive presenti nel registro
	public static ArrayList<keepAlive_Entity> getListaKeepAlive() {
		return listaKeep;
	}

	//metodo che resituisce un keep alive nella lista cercandolo in base al robot a cui è associato
	public static keepAlive_Entity getKeepByIdRobot(String idRobot) {

		ArrayList<keepAlive_Entity> keepList = getListaKeepAlive();
		keepAlive_Entity returnedKeep_Entity = new keepAlive_Entity();
		keepAlive_Entity k = new keepAlive_Entity();
		int i=0;
		boolean trovato=false;
		while(i<keepList.size() && !trovato) {
			k = keepList.get(i);
			if(k.getIdRobot().compareTo(idRobot)==0) {
				trovato=true;
				returnedKeep_Entity=k;
			}
			else {
				i++;
			}
		}
		return returnedKeep_Entity;
		
	}

}
