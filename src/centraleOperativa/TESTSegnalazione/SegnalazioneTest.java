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
	
	
	
	
	public SegnalazioneTest(String id, String stato, float valoreAllarme, Date dataora, String idgestore,
			String idsensore) {
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
	
	public void ControlloNotifica() {
		Thread ControlloNotifica=new Thread()
		{
			public void run() {
					try {
						sleep(120000); //si attende 2 minuti
						//se dopo 2 minuti lo stato della segnalazione è ancora in attesa
						//allora significa che non è arrivata alcuna notifica dall'utente!
						if(getStato()=="IN ATTESA") {
											//demanda la segnalazione ad un gestore esterno!!!!!!
											setStato("GESTORE ESTERNO");
											System.out.println("E' stata notficata una richiesta ad un GESTORE ESTERNO");
											}
						
						}
					catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			
		};
		ControlloNotifica.start();
	}
	
}
