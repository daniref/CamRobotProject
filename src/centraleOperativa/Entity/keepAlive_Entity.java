package centraleOperativa.Entity;

import java.util.Date;
import java.util.TimeZone;
import org.orm.*;
import centraleOperativa.DB.*;

public class keepAlive_Entity {
	
	private String id;
	private Date dataTime;
	private String idRobot;
	
	public keepAlive_Entity(){
		
	}
	
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
	
	public String addKeep() throws PersistentException{
		
		try {
			java.sql.Date new_date = new java.sql.Date(this.dataTime.getTime());
			java.sql.Time new_time = new java.sql.Time(this.dataTime.getTime());
			KeepAlive keep = KeepAliveDAO.createKeep(this.id,new_date,new_time,RobotDAO.getRobotById(this.idRobot));
			KeepAliveDAO.save(keep);
		//	System.out.println("Aggiunto nuovo keep Alive!");
			return this.id;
		}
		catch(Exception e) {
			System.out.println("Errore nell'inserimento del nuovo keep alive!");
			e.printStackTrace();
			throw new PersistentException(e);
		}		
		
	}
	
	public boolean deleteKeep() throws PersistentException{
		
		try {
			java.sql.Date new_date = new java.sql.Date(this.dataTime.getTime());
			java.sql.Time new_time = new java.sql.Time(this.dataTime.getTime());	
			KeepAlive keep = KeepAliveDAO.createKeep(this.id,new_date,new_time,RobotDAO.getRobotById(this.idRobot));
			KeepAliveDAO.delete(keep);
		//	System.out.println("Keep cancellato dal db");
			return true;
		}
		catch(Exception e){
			System.out.println("Errore nella cancellazione del keep alive!");
			e.printStackTrace();
			throw new PersistentException(e);
			
		}
		
	}
	
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
			System.out.println("Cliente non presente!");
			throw new PersistentException(e);
			
		}		
	}
	
	public static java.util.Date convertFromSQLDateToJAVADate(java.sql.Date sqlDate) {
		
		java.util.Date javaDate = null;
		if (sqlDate != null) {
			javaDate = new Date(sqlDate.getTime());
		}
		return javaDate;
	
	}
	

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
