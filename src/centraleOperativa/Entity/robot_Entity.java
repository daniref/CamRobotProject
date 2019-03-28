package centraleOperativa.Entity;

import java.sql.Date;

import org.orm.PersistentException;

import centraleOperativa.DB.*;
import centraleOperativa.Entity.sensore_Entity;
import java.util.ArrayList;


public class robot_Entity {
	
	// attributi privati della classe
	private String id;
	private String stato;
	private String condizione;
	private String funzionamento;
	private String indirizzo;
	private String areaId;
	
	private ArrayList<sensore_Entity> listaSensori;
	
	//costruttore vuoto
	public robot_Entity() {
		
	} 

	//costruttore con parametri
	public robot_Entity(String stato,String condizione, String funzionamento, 
			String indirizzo, String areaId) {

		try {
			this.id=RobotDAO.getNextId();
			this.stato=stato;
			this.condizione=condizione;
			this.funzionamento=funzionamento;
			this.indirizzo=indirizzo;
			this.areaId=areaId;	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//metodo per aggiungere un robot al db
	public String addRobot() throws PersistentException{
		
		try {
			Robot robot = RobotDAO.createRobot(this.id,
							this.stato,
							this.condizione,
							this.funzionamento,
							this.indirizzo,
							this.areaId);
			RobotDAO.save(robot);
			System.out.println("Aggiunto nuovo robot!");
			return this.id;
		}
		catch(Exception e) {
			System.out.println("Errore nell'inserimento del nuovo robot!");
			e.printStackTrace();
			throw new PersistentException(e);
		}
		
	}
	
	//metodo per cancellare un robot dal db
	public boolean deleteRobot() throws PersistentException{
		
		try {
			Robot robot = RobotDAO.createRobot(this.id,this.stato,
							this.condizione,this.funzionamento,
							this.indirizzo,this.areaId);
			RobotDAO.delete(robot);
			return true;
		}
		catch(Exception e){
			System.out.println("Errore nella cancellazione del robot!");
			e.printStackTrace();
			throw new PersistentException(e);
		}
		
	}
	
	// metodo per ricercare un robot all'interno del db passando l'id del robot
	public void getRobotById(String id) throws PersistentException{
		
		try {
			Robot new_robot=new Robot();
			new_robot=RobotDAO.getRobotById(id);
			this.id=new_robot.getId();
			this.stato=new_robot.getStato();
			this.condizione=new_robot.getCondizione();
			this.funzionamento=new_robot.getFunzionamento();
			this.indirizzo=new_robot.getIndirizzo();
			this.areaId=new_robot.getAreaId();
		}
		catch(Exception e) {
			System.out.println("Robot non presente!");
			throw new PersistentException(e);
			
		}
	}
	
	//metodo per riempire la lista di sensori associata al robot in questione
	public void fillSensoriList() {
		try {
			listaSensori=new ArrayList<sensore_Entity>();
        		ArrayList<Sensore> sensoriList= new ArrayList<Sensore>();
        		SensoreDAO sen=new SensoreDAO();
        		sensoriList=sen.getSensoriListByIdRobot(this.id);
        		for(Sensore s : sensoriList) {
        			sensore_Entity new_sensore = new sensore_Entity();
        			new_sensore.setId(s.getId());
        			new_sensore.setSoglia(s.getSoglia());
        			new_sensore.setTipologia(s.getTipologia());
        			new_sensore.setIdRobot(s.getRobot().getId());
        			listaSensori.add(new_sensore);
        		}	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//metodo che restituisce la lista di sensori privata della classe
	public ArrayList<sensore_Entity> getSensoriList(){
		return listaSensori;
	}
	
	//metodi di get e set del robot
	public String getStatoById(String id) throws PersistentException{
		
		try {
			Robot new_robot=new Robot();
			new_robot=RobotDAO.getRobotById(id);
			return new_robot.getStato();			
			
		}
		catch(Exception e) {
			System.out.println("Impossibile recuperare lo stato del robot "+id);
			throw new PersistentException(e);
		}	
	}
		
	public String getCondizioneById(String id) throws PersistentException{
		
		try {
			Robot new_robot=new Robot();
			new_robot=RobotDAO.getRobotById(id);
			return new_robot.getCondizione();				
		}
		catch(Exception e) {
			System.out.println("Impossibile recuperare la condizione del robot "+id);
			throw new PersistentException(e);
		}		
	}
	
	public String getFunzionamentoById(String id) throws PersistentException{
		
		try {
			Robot new_robot=new Robot();
			new_robot=RobotDAO.getRobotById(id);
			return new_robot.getFunzionamento();				
		}
		catch(Exception e) {
			System.out.println("Impossibile recuperare il funzionamento del robot "+id);
			throw new PersistentException(e);
		}		
	}	
	
	public void setStatoById(String id, String stato) throws PersistentException{
		
		try {
			Robot new_robot=new Robot();
			new_robot=RobotDAO.getRobotById(id);
			new_robot.setStato(stato);
			RobotDAO.save(new_robot);
		}
		catch(Exception e) {
			System.out.println("Impossibile settare lo stato del robot "+id);
			throw new PersistentException(e);
		}	
	}
	
	public void setCondizioneById(String id, String condizione) throws PersistentException{
		
		try {
			Robot new_robot=new Robot();
			new_robot=RobotDAO.getRobotById(id);
			new_robot.setCondizione(condizione);
			RobotDAO.save(new_robot);
		}
		catch(Exception e) {
			System.out.println("Impossibile settare la condizione del robot "+id);
			throw new PersistentException(e);
		}		
	}
	
	public void setFunzionamentoById(String id, String funzionamento) throws PersistentException{
		
		try {
			Robot new_robot=new Robot();
			new_robot=RobotDAO.getRobotById(id);
			new_robot.setFunzionamento(funzionamento);
			RobotDAO.save(new_robot);
		}
		catch(Exception e) {
			System.out.println("Impossibile settare il funzionamento del robot "+id);
			throw new PersistentException(e);
		}		
	}
	
	public String getIndirizzoId(String id) throws PersistentException{
		
		try {
			Robot new_robot=new Robot();
			new_robot=RobotDAO.getRobotById(id);
			return new_robot.getIndirizzo();				
		}
		catch(Exception e) {
			System.out.println("Impossibile recuperare l'indirizzo del robot "+id);
			throw new PersistentException(e);
		}		
	}
	
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

	public String getCondizione() {
		return condizione;
	}

	public void setCondizione(String condizione) {
		this.condizione = condizione;
	}

	public String getFunzionamento() {
		return funzionamento;
	}

	public void setFunzionamento(String funzionamento) {
		this.funzionamento = funzionamento;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
		
}
