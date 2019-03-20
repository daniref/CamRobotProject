package centraleOperativa.Entity;

import java.util.Date;
import java.util.TimeZone;

import org.orm.*;

import centraleOperativa.DB.*;

public class contratto_Entity {
	
	private String id;
	private Cliente cliente;
	private Robot robot;
	private Date data_di_inizio;
	private Date data_di_scadenza;
	private float canone;
		
	public contratto_Entity() {
		
	}
	
	public contratto_Entity(String id, Cliente cliente, Robot robot, Date data_di_inizio, 
			Date data_di_scadenza, float canone) throws PersistentException {
		try {
			this.id = ContrattoDAO.getNextId();
			this.cliente = cliente;
			this.robot = robot;
			this.data_di_inizio=data_di_inizio;
			this.data_di_scadenza=data_di_scadenza;
			this.canone=canone;

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String addContratto() throws PersistentException{
		
		try {
			java.sql.Date new_date1 = new java.sql.Date(this.data_di_inizio.getTime());
			java.sql.Date new_date2 = new java.sql.Date(this.data_di_scadenza.getTime());			
			
			centraleOperativa.DB.Contratto contratto = centraleOperativa.DB.ContrattoDAO.createContratto(this.id,
											this.cliente,
											this.robot,
											new_date1,
											new_date2,
											this.canone);
			centraleOperativa.DB.ContrattoDAO.save(contratto);
			System.out.println("Aggiunto nuovo contratto!");
			return this.id;
		}
		catch(Exception e) {
			System.out.println("Errore nell'inserimento del nuovo contratto!");
			e.printStackTrace();
			throw new PersistentException(e);
		}		
	}
	
	public void getContrattoById(String id) throws PersistentException{
		
		try {
			Contratto new_contratto=new Contratto();
			new_contratto=ContrattoDAO.getContrattoByIdContratto(id);
			this.id=new_contratto.getId();
			this.cliente=new_contratto.getUtente();
			this.robot=new_contratto.getRobot();
			this.data_di_inizio=convertFromSQLDateToJAVADate(new_contratto.getData_di_inizio());
			this.data_di_scadenza=convertFromSQLDateToJAVADate(new_contratto.getData_di_scadenza());
			this.canone=new_contratto.getCanone();
		}
		catch(Exception e) {
			System.out.println("Contratto non presente!");
			throw new PersistentException(e);
			
		}
	}
	
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
	
	public static java.util.Date convertFromSQLDateToJAVADate(
		            java.sql.Date sqlDate) {
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
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
