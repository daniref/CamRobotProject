package centraleOperativa.Entity;

import org.orm.PersistentException;

import centraleOperativa.DB.ClienteDAO;
import centraleOperativa.DB.Contratto;
import centraleOperativa.DB.ContrattoDAO;
import centraleOperativa.DB.RobotDAO;
import centraleOperativa.DB.Sensore;
import centraleOperativa.DB.SensoreDAO;

public class sensore_Entity {
	
	//attributi privati della classe
	private String id;
	private float soglia;
	private String tipologia;
	private String idRobot;
	
	//costruttore vuoto
	public sensore_Entity() {
		
	}
	
	//costruttore con parametri
	public sensore_Entity(float soglia, String tipologia, 
			String idRobot) throws PersistentException {
		
		try {
			this.id=SensoreDAO.getNextId();
			this.soglia=soglia;
			this.tipologia=tipologia;
			this.idRobot=idRobot;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//metodo per l'aggiunta di un sensore al db
	public String addSensore() throws PersistentException{
		
		try {
			Sensore sensore = SensoreDAO.createSensore(this.id,
								this.soglia,
								this.tipologia,
								RobotDAO.getRobotById(this.idRobot));
			SensoreDAO.save(sensore);
			System.out.println("Aggiunto nuovo sensore!");
			return this.id;
		}
		catch(Exception e) {
			System.out.println("Errore nell'inserimento del nuovo sensore!");
			e.printStackTrace();
			throw new PersistentException(e);
		}		
		
	}
	
	//metodo che restituisce un sensore in base al proprio id
	public void getSensoreById(String id) throws PersistentException{
		
		try {
			Sensore new_sensore=new Sensore();
			new_sensore=SensoreDAO.getSensoreById(id);
			this.id=new_sensore.getId();
			this.soglia=new_sensore.getSoglia();
			this.tipologia=new_sensore.getTipologia();
			this.idRobot=new_sensore.getRobot().getId();
		}
		catch(Exception e) {
			System.out.println("Contratto non presente!");
			throw new PersistentException(e);
			
		}
		
	}
	
	//metodo che restituisce la tipologia di un sensore in base al proprio id
	public String getTipologiaById(String id) throws PersistentException{
		
		try {
			return SensoreDAO.getTipologiaById(id);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}	
		
	}
	
	//metodi di get e set per gli attributi privati della classe

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getSoglia() {
		return soglia;
	}

	public void setSoglia(float soglia) {
		this.soglia = soglia;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getIdRobot() {
		return idRobot;
	}

	public void setIdRobot(String idRobot) {
		this.idRobot = idRobot;
	}
	
	
}
