package centraleOperativa.Entity;

import java.util.Date;
import java.util.TimeZone;

import org.orm.*;

import centraleOperativa.DB.Cliente;
import centraleOperativa.DB.ClienteDAO;

public class cliente_Entity {
	
	private String id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String recapito;
	private Date data_di_nascita;
	private String luogo_di_nascita;
		
	public cliente_Entity() {
	}
		
	public cliente_Entity(String nome, String cognome, String username, String password, String recapito,
			Date data_di_nascita, String luogo_di_nascita) throws PersistentException{
		try {
			this.id = ClienteDAO.getNextId();
			this.nome = nome;
			this.cognome = cognome;
			if(ClienteDAO.searchUsername(username)) {
				this.username = username;
				this.password = password;
				this.recapito = recapito;
				this.data_di_nascita = data_di_nascita;
				this.luogo_di_nascita = luogo_di_nascita;
			}
			else {
				throw new PersistentException("Username già esistente");
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	public String addCliente() throws PersistentException{
		
		try {
			java.sql.Date new_date = new java.sql.Date(this.data_di_nascita.getTime());
			
			
			centraleOperativa.DB.Cliente cliente = centraleOperativa.DB.ClienteDAO.createCliente(this.id,
											this.nome,
											this.cognome,
											this.username,
											this.password,
											this.recapito,
											new_date,
											this.luogo_di_nascita);
			centraleOperativa.DB.ClienteDAO.save(cliente);
			System.out.println("Aggiunto nuovo cliente!");
			return this.id;
		}
		catch(Exception e) {
			System.out.println("Errore nell'inserimento del nuovo cliente!");
			e.printStackTrace();
			throw new PersistentException(e);
		}		
	}
	
	public String getRecapitoById(String id) throws PersistentException{
		
		try {
			return ClienteDAO.getRecapitobyId(id);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}		
	}
	
	public Cliente getClienteById(String id) throws PersistentException{
		
		try {
			TimeZone.getDefault();
			TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
			Cliente new_cliente=new Cliente();
			new_cliente=ClienteDAO.getClienteById(id);
			this.id=new_cliente.getId();
			this.nome=new_cliente.getNome();
			this.cognome=new_cliente.getCognome();
			this.username=new_cliente.getUsername();
			this.password=new_cliente.getPassword();
			this.recapito=new_cliente.getRecapito();
			this.data_di_nascita=convertFromSQLDateToJAVADate(new_cliente.getData_di_nascita());
			this.luogo_di_nascita=new_cliente.getLuogo_di_nascita();
			return new_cliente;
			
		}
		catch(Exception e) {
			System.out.println("Cliente non presente!");
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRecapito() {
		return recapito;
	}

	public void setRecapito(String recapito) {
		this.recapito = recapito;
	}

	public Date getData_di_nascita() {
		return data_di_nascita;
	}

	public void setData_di_nascita(Date data_di_nascita) {
		this.data_di_nascita = data_di_nascita;
	}

	public String getLuogo_di_nascita() {
		return luogo_di_nascita;
	}

	public void setLuogo_di_nascita(String luogo_di_nascita) {
		this.luogo_di_nascita = luogo_di_nascita;
	}

}
