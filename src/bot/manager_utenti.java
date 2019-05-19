package bot;

import java.util.ArrayList;

import bot.utente;

public class manager_utenti {
	
	private static manager_utenti manager=null;
	
	public static synchronized manager_utenti getInstance(){

		if(manager==null) {
			try {
				manager=new manager_utenti();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return manager;
	}
	
	private static ArrayList<utente> listautenti;
	
	//costruttore privato
	private manager_utenti() {

		try {
			listautenti=new ArrayList<utente>();
		}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
		
	}
	
	public void add_utente(utente new_utente) {
		
		try {
			listautenti.add(new_utente);
			System.out.println("E' stato aggiunto un nuovo utente alla lista");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//metodo che cerca nella lista un utente attraverso il suo id
	public static utente getUtenteById(long user_id) {
		
	//	ArrayList<robot_Entity> robotList = getListaRobot();
		utente u = new utente();
		utente returnedUtente = new utente();
		int i=0;
		boolean trovato=false;
		while(i<listautenti.size() && !trovato) {
			u = listautenti.get(i);
			if(u.getUser_id()-user_id==0) {
				trovato=true;
				returnedUtente=u;
			}
			else {
				i++;
			}
		}
		return returnedUtente;
		
	}
	
	//metodo che restituisce lo user_id una volta passato il phone number
	public static utente getUtenteByPhone(String phone) {
		
	//	ArrayList<robot_Entity> robotList = getListaRobot();
		utente u = new utente();
		utente returnedUtente = new utente();
		int i=0;
		boolean trovato=false;
		while(i<listautenti.size() && !trovato) {
			u = listautenti.get(i);
			if(u.getPhone().equals(phone)) {
				trovato=true;
				returnedUtente=u;
			}
			else {
				i++;
			}
		}
		return returnedUtente;
		
	}
	

}
