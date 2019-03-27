package centraleOperativa.TESTSegnalazione;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SegnalazioneTest {
	private String id;		//id allarme
	private String stato; 	//APERTA , "IN ATTESA", "RISOLTA", "GESTORE ESTERNO" 
	private float valoreAllarme;
	Date dataora;
	String idgestore;
	String idsensore;
	
	public SegnalazioneTest(String id, String stato, float valoreAllarme, Date dataora, String idgestore, String idsensore) {
		super();
		this.id = id;
		this.stato = stato;
		this.valoreAllarme = valoreAllarme;
		this.dataora = dataora;
		this.idgestore = idgestore;
		this.idsensore = idsensore;
	}
	@Override
	public String toString() {
		return "SegnalazioneTest [id=" + id + ", stato=" + stato + ", valoreAllarme=" + valoreAllarme + ", dataora="
				+ dataora + ", idgestore=" + idgestore + ", idsensore=" + idsensore + "]";
	}
	public String getId() {
		return id;
	}
	public String getStato() {
		return stato;
	}
	public float getValoreAllarme() {
		return valoreAllarme;
	}
	public Date getDataora() {
		return dataora;
	}
	public String getIdgestore() {
		return idgestore;
	}
	public String getIdsensore() {
		return idsensore;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public void setValoreAllarme(float valoreAllarme) {
		this.valoreAllarme = valoreAllarme;
	}
	public void setDataora(Date dataora) {
		this.dataora = dataora;
	}
	public void setIdgestore(String idgestore) {
		this.idgestore = idgestore;
	}
	public void setIdsensore(String idsensore) {
		this.idsensore = idsensore;
	}
	
	public void NOTIFICA() {
		setStato("RISOLTA");
	}
	
/*	
	//da portare in gestore_entity
	public void ControlloNotifica(String id_segnalazione) {
		Thread ControlloNotifica=new Thread()
		{
			public void run() {
					try {
						sleep(120000);															// attende 2 minuti
						for(int i=0;i<this.ListaSegnalazioni.size();i++){						//CERCA LA SEGNALAZIONE CON QUELL'ID
							if(this.ListaSegnalazioni.get(i).getId() == id_segnalazione){		
					        	if(ListaSegnalazioni.get(i).getStato()=="IN ATTESA") {			//presa la segnalazione con quell'id controlla se è IN ATTESA
									setStato("GESTORE ESTERNO");								//in tal caso demanda la segnalazione ad un gestore esterno!!!!!!
									System.out.println("E' stata notficata una richiesta ad un GESTORE ESTERNO");
									}
					        	}
					    	break;		//esci dal ciclo se hai trovato la segnalazione con quell'ID 
					    	}
						}
					catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			
		};
		ControlloNotifica.start();
	}
	*/
	
}


