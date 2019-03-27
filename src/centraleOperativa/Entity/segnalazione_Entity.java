package centraleOperativa.Entity;

import java.sql.Time;
import java.util.Date;
import java.util.TimeZone;
import org.orm.*;
import centraleOperativa.DB.*;

public class segnalazione_Entity {
	
	//attributi privati della classe
	private String id;
	private String stato;
	private float valore_allarme;
	private Date dataTime;
	private String idGestore;
	private String idSensore;
	private String idRobot;
	
	//costruttore vuoto
	public segnalazione_Entity() {
		
	}
	
	//costruttore con parametri
	public segnalazione_Entity(float valore_allarme,
			Date dataTime, String idGestore,
			String idSensore, String idRobot) throws PersistentException {
		
		try {
			this.id=SegnalazioneDAO.getNextId();
			this.stato="APERTA";
			this.valore_allarme=valore_allarme;
			this.dataTime=dataTime;
			this.idGestore=idGestore;
			this.idSensore=idSensore;
			this.idRobot=idRobot;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//metodo per l'aggiunta di una segnalazione al db
	public String addSegnalazione() throws PersistentException{
		
		try {
			java.sql.Date new_date = new java.sql.Date(this.dataTime.getTime());
			java.sql.Time new_time = new java.sql.Time(this.dataTime.getTime());
			Segnalazione segnalazione = SegnalazioneDAO.createSegnalazione
						(this.id,this.stato,this.valore_allarme,
						new_date,new_time,GestoreDAO.getGestoreById(this.idGestore),
						SensoreDAO.getSensoreById(this.idSensore),RobotDAO.getRobotById(this.idRobot));
			SegnalazioneDAO.save(segnalazione);
			return this.id;
		}
		catch(Exception e) {
			System.out.println("Errore nell'inserimento della nuova segnalazione!");
			e.printStackTrace();
			throw new PersistentException(e);
		}		
		
	}
	
	// metodo per ricercare una segnalazione all'interno del db passando l'id della segnalazione
	public void getSegnalazioneById(String id) throws PersistentException{
		
		try {
			TimeZone.getDefault();
			TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
			Segnalazione new_segnalazione=new Segnalazione();
			new_segnalazione=SegnalazioneDAO.getSegnalazioneById(id);
			java.util.Date date=convertFromSQLDateToJAVADate(new_segnalazione.getData());
			java.util.Date dateTime= new Date(date.getTime()+new_segnalazione.getOra().getTime());
			this.id=new_segnalazione.getId();
			this.stato=new_segnalazione.getStato();
			this.valore_allarme=new_segnalazione.getValore_allarme();
			this.dataTime=dateTime;
			this.idGestore=new_segnalazione.getGestore().getId();
			this.idSensore=new_segnalazione.getSensore().getId();
			this.idRobot=new_segnalazione.getRobot().getId();
		}
		catch(Exception e) {
			System.out.println("Segnalazione non presente!");
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
	
	//metodi di get e set per gli attributi privati della classe

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public float getValore_allarme() {
		return valore_allarme;
	}

	public void setValore_allarme(float valore_allarme) {
		this.valore_allarme = valore_allarme;
	}

	public Date getDataTime() {
		return dataTime;
	}

	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}

	public String getIdGestore() {
		return idGestore;
	}

	public void setIdGestore(String idGestore) {
		this.idGestore = idGestore;
	}

	public String getIdSensore() {
		return idSensore;
	}

	public void setIdSensore(String idSensore) {
		this.idSensore = idSensore;
	}

	public String getIdRobot() {
		return idRobot;
	}

	public void setIdRobot(String idRobot) {
		this.idRobot = idRobot;
	}

}
