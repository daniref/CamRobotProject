package centraleOperativa.Entity;

import java.util.Date;
import java.util.TimeZone;
import org.orm.*;
import centraleOperativa.DB.*;

public class keepAlive_Entity {
	
	//attributi privati della classe
	private String id;
	private Date dataTime;
	private String idRobot;
	
	//costruttore vuoto
	public keepAlive_Entity(){
		
	}
	
	//costruttore con parametri
	public keepAlive_Entity(Date dataTime, String idRobot) throws PersistentException {
		
		try {
			this.id=KeepAliveDAO.getNextId();
			this.dataTime=dataTime;
			this.idRobot=idRobot;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//metodo per l'aggiunta di un keepAlive al db
	public String addKeep() throws PersistentException{
		
		try {
			java.sql.Date new_date = new java.sql.Date(this.dataTime.getTime());
			java.sql.Time new_time = new java.sql.Time(this.dataTime.getTime());
			KeepAlive keep = KeepAliveDAO.createKeep(this.id,new_date,new_time,RobotDAO.getRobotById(this.idRobot));
			KeepAliveDAO.save(keep);
			return this.id;
		}
		catch(Exception e) {
			System.out.println("Errore nell'inserimento del nuovo keep alive!");
			e.printStackTrace();
			throw new PersistentException(e);
		}		
		
	}
	
	//metodo per l'aggiornamento del keep alive nel db con l'orario del keep in question
	//ma con lo stesso id keepAlive
	public void updateKeep() throws PersistentException{

		try {
			KeepAlive tobeupdate_keep=new KeepAlive();
			tobeupdate_keep=KeepAliveDAO.getKeepAliveByIdRobot(this.idRobot);
			java.sql.Date updated_date = new java.sql.Date(this.dataTime.getTime());
			java.sql.Time updated_time = new java.sql.Time(this.dataTime.getTime());
			tobeupdate_keep.setData(updated_date);
			tobeupdate_keep.setOra(updated_time);
			KeepAliveDAO.save(tobeupdate_keep);
		}
		catch(Exception e) {
			System.out.println("Errore nell'aggiornamento del keep alive!");
			e.printStackTrace();
		}	
		
	}
	
	//metodo per la cancellazione di un keep alive dal db
	public boolean deleteKeep() throws PersistentException{
		
		try {
			java.sql.Date new_date = new java.sql.Date(this.dataTime.getTime());
			java.sql.Time new_time = new java.sql.Time(this.dataTime.getTime());	
			KeepAlive keep = KeepAliveDAO.createKeep(this.id,new_date,new_time,RobotDAO.getRobotById(this.idRobot));
			KeepAliveDAO.delete(keep);
			return true;
		}
		catch(Exception e){
			System.out.println("Errore nella cancellazione del keep alive!");
			e.printStackTrace();
			throw new PersistentException(e);
		}
		
	}
	
	// metodo per ricercare un keepAlive all'interno del db passando l'id del keep
	public void getKeepById(String id) throws PersistentException{
		
		try {
			TimeZone.getDefault();
			TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
			KeepAlive new_keep=new KeepAlive();
			new_keep=KeepAliveDAO.getKeepAliveByIdKeep(id);
			java.util.Date date=convertFromSQLDateToJAVADate(new_keep.getData());
			java.util.Date dateTime= new Date(date.getTime()+new_keep.getOra().getTime());
			this.id=new_keep.getId();
			this.dataTime=dateTime;
			this.idRobot=new_keep.getRobot().getId();
		}
		catch(Exception e) {
			System.out.println("Keep non presente!");
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
	
	//metodi di set e get della classe
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDataTime() {
		return dataTime;
	}

	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}

	public String getIdRobot() {
		return idRobot;
	}

	public void setIdRobot(String idRobot) {
		this.idRobot = idRobot;
	}

}
