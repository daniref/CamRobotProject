package centraleOperativa.Businesslogic;

import java.util.ArrayList;

import org.orm.PersistentException;

import centraleOperativa.Entity.managerGestori_Entity;
import centraleOperativa.Entity.gestore_Entity;
import centraleOperativa.Entity.segnalazione_Entity;

public class NotificheManager {
	public NotificheManager() {
		super();
	}

	public boolean NotificaLetturaSegnalazione(String id,String tipo) {
		//istanza g singleton gestore
		gestore_Entity ge;
			synchronized(this) { 
				try {
					managerGestori_Entity g= managerGestori_Entity.getInstance();
					ge=g.getGestore(tipoSensoreToGestore(tipo));
//					ge = gestore_Entity.getInstance(tipoSensoreToGestore(tipo));
					ArrayList<segnalazione_Entity> kk=ge.getListaSegnalazioni();
					for(int i=0;i<kk.size();i++) {
						System.out.print(kk.get(i).getId());
					}
					segnalazione_Entity se= new segnalazione_Entity();
					se=ge.getSegnalazioneById(id);
					if(se.getStato().compareTo("IN ATTESA")==0) {
						se.setStato("RISOLTA");
						ge.updateSegnalazione(se);
						return true;
					}
				} catch (PersistentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();		
				}
				
			}
			return false;

			
	
	}
	
	
	public String tipoSensoreToGestore(String tipo_sensore) {
		switch(tipo_sensore) {
		case "T": return "gs0001"; //per un allarme dovuto ad na soglia superata dal Termometro vai al gestore con id gs0001 (Pronto Soccorso)
		case "F": return "gs0002"; //per un allarme dovuto ad na soglia superata dal SensoreDiFumo vai al gestore con id gs0002 (Vigili Del Fuoco)
		case "P": return "gs0003"; //per un allarme dovuto ad na soglia superata dal sensore di Prossimit� vai al gestore con id gs0001 (Polizia)
		default: return "gs0004";  //per un allarme provocato da un sensore generico vai al gestore gs0004 (Security Agency)
		}
	}
		
}
