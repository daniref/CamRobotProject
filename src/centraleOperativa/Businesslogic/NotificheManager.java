package centraleOperativa.Businesslogic;

public class NotificheManager {
	public NotificheManager() {
		super();
	}

	public void NotificaLetturaSegnalazione(String id) {
		//istanza g singleton gestore
		
		//WAIT
		//g.cercasegnalazione(id); //cerco nella 5 lista  e se esiste ELIMINA
		//NOTIFY
		//settare stato = RISOLTA
		//g.aggiungi a lista gestore corrispondente
		System.out.print("E' stata notificata la lettura per id: "+ id);
	}

}
