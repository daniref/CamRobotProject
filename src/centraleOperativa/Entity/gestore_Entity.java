package centraleOperativa.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import org.orm.PersistentException;

import centraleOperativa.DB.Gestore;
import centraleOperativa.DB.GestoreDAO;
import centraleOperativa.DB.Segnalazione;
import centraleOperativa.DB.SegnalazioneDAO;

public class gestore_Entity {
	
	//creazione del SINGLETON
	private static gestore_Entity gestore=null;
	
	
	//metodo usato per l'accesso alla classe singleton
/*	public static synchronized gestore_Entity getInstance(String idGestore) throws PersistentException{

		if(gestore==null) {
			System.out.println("Prima costruzione di gestore "+idGestore);
			gestore=new gestore_Entity(idGestore);
		}
		return gestore;
		}
	*/

	
	
	//attributi privati della classe
	private String id;					
	private String nome;					
	private String recapito;					

	//lista di liste delle segnalazioni appartenenti al gestore
	private ArrayList<segnalazione_Entity> listaSegnalazioni=new ArrayList<segnalazione_Entity>();;
	

	//costruttore privato
	public gestore_Entity(String idGestore) throws PersistentException{
		//while(listaSegnalazioni.size()>0)listaSegnalazioni.remove(0);
		try {
			//riempimento lista di segnalazioni
        		ArrayList<Segnalazione> segnalazioneList = new ArrayList<Segnalazione>();
        		SegnalazioneDAO seg=new SegnalazioneDAO();
        		segnalazioneList=seg.getSegnalazioniListByIdGestore(idGestore);
        		for(Segnalazione s : segnalazioneList) {
        			segnalazione_Entity new_segnalazione = new segnalazione_Entity();
        			new_segnalazione.setId(s.getId());
        			new_segnalazione.setStato(s.getStato());
        			new_segnalazione.setValore_allarme(s.getValore_allarme());
        			java.util.Date date=convertFromSQLDateToJAVADate(s.getData());
        			java.util.Date dateTime= new Date(date.getTime()+s.getOra().getTime());
        			new_segnalazione.setDataTime(dateTime);
        			new_segnalazione.setIdGestore(s.getGestore().getId());
        			new_segnalazione.setIdRobot(s.getRobot().getId());
        			new_segnalazione.setIdSensore(s.getSensore().getId());
        			listaSegnalazioni.add(new_segnalazione);
        			}
        		
        		getGestoreById(idGestore);
        		System.out.println("E' stata caricata la lista di "+segnalazioneList.size()+" segnalazioni dal db relativa al gestore "+ idGestore);
        		System.out.println("Ripeto, ci sono  "+listaSegnalazioni.size()+" segnalazioni caricate in locale per il gestore "+ idGestore);
        		
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
	
	//metodo che restituisce un gestore attraverso il suo id
	public void getGestoreById(String idGestore) throws PersistentException{
		
		try {
			Gestore new_gestore=new Gestore();
			new_gestore=GestoreDAO.getGestoreById(idGestore);
			this.id=new_gestore.getId();
			this.nome= new_gestore.getNome();
			this.recapito= new_gestore.getRecapito();			
		}
		catch(Exception e) {
			System.out.println("Cliente non presente!");
			throw new PersistentException(e);
		}		
	
	}
	
	//------------inserisce nuova segnalazione nella lista e nel database
	public String addSegnalazione(segnalazione_Entity new_segnalazione) throws PersistentException {

		try {
			
			//listaSegnalazioni.get(i).add(new_segnalazione);
			listaSegnalazioni.add(new_segnalazione);
			new_segnalazione.addSegnalazione();
			return new_segnalazione.getId();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
		
	}
	
	//------------aggiorna una segnalazione nella lista e nel database
	public boolean updateSegnalazione(segnalazione_Entity segnalazione) throws PersistentException {

		try {
			listaSegnalazioni.set(listaSegnalazioni.indexOf(getSegnalazioneById(segnalazione.getId())),segnalazione);
			segnalazione.addSegnalazione();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
		
	}
	
	//metodo che restituisce la lista di robot dell'area
	public ArrayList<segnalazione_Entity> getListaSegnalazioni() {
		return listaSegnalazioni;
	}
	
	
	//metodo che cerca nella lista dell'area un robot attraverso il suo id e lo restituisce
	public segnalazione_Entity getSegnalazioneById(String id) {
		
		segnalazione_Entity s = new segnalazione_Entity();
		segnalazione_Entity returnedSegnalazione = new segnalazione_Entity();
		int i=0;
		boolean trovato=false;
		while(i<listaSegnalazioni.size() && !trovato) {
			s = listaSegnalazioni.get(i);
			if(s.getId().compareTo(id)==0) {
				trovato=true;
				returnedSegnalazione=s;
			}
			else {
				i++;
			}
		}
		return returnedSegnalazione;
		
	}
	
	//metodo per la ricerca dell'ultima segnalazione associata ad un sensore
	public synchronized segnalazione_Entity getUltimaSegnalazioneByIdSensore(String id) {
		segnalazione_Entity s = new segnalazione_Entity();
		segnalazione_Entity returnedSegnalazione = new segnalazione_Entity();
		int i=0;
		while(i<listaSegnalazioni.size()) {
					s = listaSegnalazioni.get(i);
			if(s.getIdSensore().compareTo(id)==0) {

				if(returnedSegnalazione.getId()!=null) {
					if(returnedSegnalazione.getDataTime().compareTo(s.getDataTime())<0) {
						returnedSegnalazione=s;
					}
				}
				else {
					returnedSegnalazione=s;
				}
			}
		i++;
		}		
		return returnedSegnalazione;
		
	}

	//metodi get e set per gli attributi privati della classe
	
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

	public String getRecapito() {
		return recapito;
	}

	public void setRecapito(String recapito) {
		this.recapito = recapito;
	}

	public void StampaLista() {
		System.out.println("*****LISTA DEL GESTORE "+id+" **************");
		for(int i=0;i<listaSegnalazioni.size();i++) {
				System.out.println(listaSegnalazioni.get(i));
		}
	}
	
}
