package centraleOperativa.Businesslogic;

public class NotificheManager {
	public NotificheManager() {
		super();
	}

	public boolean NotificaLetturaSegnalazione(String id) {
		//istanza g singleton gestore
		if(id.compareTo("sg0001")==0) return true;
		else return false;
		//WAIT
		//g.cercasegnalazione(id); //cerco nella 5 lista  e se esiste ELIMINA
		//NOTIFY
		//settare stato = RISOLTA
		//g.aggiungi a lista gestore corrispondente
	}

}
