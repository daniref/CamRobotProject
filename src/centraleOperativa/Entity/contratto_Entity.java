package centraleOperativa.Entity;

import java.util.Date;
import java.util.TimeZone;

import org.orm.*;

import centraleOperativa.DB.*;

public class contratto_Entity {
	
	//attributi privati della classe
	private String id;
	private String idCliente;
	private String idRobot;
	private Date data_di_inizio;
	private Date data_di_scadenza;
	private float canone;
		
	//costruttore privato
	public contratto_Entity() {
		
	}
	
	//costruttore con parametri
	public contratto_Entity(String idCliente, String idRobot, Date data_di_inizio, 
			Date data_di_scadenza, float canone) throws PersistentException {

		try {
			this.id = ContrattoDAO.getNextId();
			this.idCliente = idCliente;
			this.idRobot = idRobot;
			this.data_di_inizio=data_di_inizio;
			this.data_di_scadenza=data_di_scadenza;
			this.canone=canone;

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//metodo per l'aggiunta di un nuovo contratto al db
	public String addContratto() throws PersistentException{
		
		try {
			java.sql.Date new_date1 = new java.sql.Date(this.data_di_inizio.getTime());
			java.sql.Date new_date2 = new java.sql.Date(this.data_di_scadenza.getTime());			
			
			Contratto contratto = ContrattoDAO.createContratto(this.id,
									ClienteDAO.getClienteById(this.idCliente),
									RobotDAO.getRobotById(this.idRobot),
									new_date1,
									new_date2,
									this.canone);
			ContrattoDAO.save(contratto);
			System.out.println("Aggiunto nuovo contratto!");
			return this.id;
		}
		catch(Exception e) {
			System.out.println("Errore nell'inserimento del nuovo contratto!");
			e.printStackTrace();
			throw new PersistentException(e);
		}		
		
	}
	
	//metodo che restituisce un contratto dal db in base al suo id
	public void getContrattoById(String id) throws PersistentException{
		
		try {
			Contratto new_contratto=new Contratto();
			new_contratto=ContrattoDAO.getContrattoByIdContratto(id);
			this.id=new_contratto.getId();
			this.idCliente=new_contratto.getUtente().getId();
			this.idRobot=new_contratto.getRobot().getId();
			this.data_di_inizio=convertFromSQLDateToJAVADate(new_contratto.getData_di_inizio());
			this.data_di_scadenza=convertFromSQLDateToJAVADate(new_contratto.getData_di_scadenza());
			this.canone=new_contratto.getCanone();
		}
		catch(Exception e) {
			System.out.println("Contratto non presente!");
			throw new PersistentException(e);
		}
		
	}
	
	//metodo che restituisce l'id di un cliente proprietario di un certo robot
	//in base all'id robot passato
	public String getIdUtenteByIdRobot(String id) throws PersistentException{
		
		try {
			Contratto new_contratto=new Contratto();
			new_contratto=ContrattoDAO.getContrattoByIdRobot(id);
			Cliente new_cliente=new Cliente();
			new_cliente=new_contratto.getUtente();
			return new_cliente.getId();			
		}
		catch(Exception e) {
			System.out.println("Impossibile recuperare l'id del cliente a partire dall'id robot: "+id);
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
	
	//metodi di get e set degli attributi privati della classe

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCliente() {
		return idCliente;
	}

	public void setCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getRobot() {
		return idRobot;
	}

	public void setRobot(String idRobot) {
		this.idRobot = idRobot;
	}

	public Date getData_di_inizio() {
		return data_di_inizio;
	}

	public void setData_di_inizio(Date data_di_inizio) {
		this.data_di_inizio = data_di_inizio;
	}

	public Date getData_di_scadenza() {
		return data_di_scadenza;
	}

	public void setData_di_scadenza(Date data_di_scadenza) {
		this.data_di_scadenza = data_di_scadenza;
	}

	public float getCanone() {
		return canone;
	}

	public void setCanone(float canone) {
		this.canone = canone;
	}
	
}