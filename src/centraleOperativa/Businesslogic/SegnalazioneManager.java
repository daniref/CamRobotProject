package centraleOperativa.Businesslogic;


import java.util.ArrayList;
import java.util.Date;


public class SegnalazioneManager {
	private String idRobot;
	private String idsensore;
	private float valore;
	private Date data_ora;
	private String idSegnalazione;

/*
  1) leggi tipologia del sensore che ha causato l'allarme
  2) Traduci la tipologia in gestore
  3) vai dal gestore corrispondente e fatti dare la lista delle segnalazioni che sta "gestendo"
  4) verifica se è presente in questa lista una segnalazione precedente
*/
	
	
	public SegnalazioneManager(String idr,String ids,float v, Date dataora) {
		this.idRobot=idr;
		this.idsensore=ids;
		this.valore=v;
		this.data_ora= dataora;
	}
	
	
	public void trattaSegnalazione() {
		String tipo=this.leggiTipologia();
		String idg=this.tipoSensoreToGestore(tipo);
/*		ArrayList<SegnalazioneTest> s=getUltimaSegnalazione(idg); //viene restituita l'ultima segnalazione provocata da un determinato sensore
		if (s.size()>0) {
						if (!verificaCondizione(s.get(1))) {
							SegnalazioneTest newSeg = new SegnalazioneTest("s01","APERTO",this.getValore(),this.getData_ora(),idg, this.getIdsensore());
							setIdSegnalazione(newSeg.getId()); //setta id segnalazione che è stato restituito dall'entity
							}
						}
	*/}
	
	
	public String getIdSegnalazione() {
		return this.idSegnalazione;
	}

	public void setIdSegnalazione(String idseg) {
		this.idSegnalazione=idseg;
	}

	//deve essere creato un nuovo oggetto di tipo Segnalazione!
/*
 		campi di Segnalazione D'Allarme
		id segnalazione
		stato=Aperto
		valore allarme
		data
		ora
		Id Gestore
		Id Sensore
*/
	//Segnalazione s=new Segnalazione(getIdrobot(),getIdrensore(),idgestore, getValore(), "APERTO",data,ora);
	
	
	
	//Metodo in cui si permette di ricavare la tipologia di allarme che è stato generato
	public String leggiTipologia() {
		//return getTipologia(ths.idsensore)
		return "T";
	}
	
//Metodo utile ad associare ad ogni sensore(tipologia di sensore) un gestore corrispondente a cui inviare la segnalazione!
	public String tipoSensoreToGestore(String tipo_sensore) {
		switch(tipo_sensore) {
		case "T": return "gs0001"; //per un allarme dovuto ad na soglia superata dal Termometro vai al gestore con id gs0001
		case "F": return "gs0002"; //per un allarme dovuto ad na soglia superata dal SensoreDiFumo vai al gestore con id gs0002
		case "P": return "gs0003"; //per un allarme dovuto ad na soglia superata dal sensore di Prossimità vai al gestore con id gs0001
		default: return "gs0004";  //per un allarme provocato da un sensore generico vai al gestore gs0004
		}
	}

// metodo con cui si permette di chiedere al gestore con un certo id di restituire l'ultima segnalazione che ha gestito per quel sensore!
/*
 public ArrayList <SegnalazioneTest> getUltimaSegnalazione(String idgestore){
 		ArrayList <SegnalazioneTest> lista = new ArrayList<SegnalazioneTest>();
			//lista= getUltimaSegnalazione(idgestore, getIdensore()));
		return lista;
	}
	
public boolean verificaCondizione(SegnalazioneTest s) {
	//  se esiste una segnalazione nella lista che è stat
	//	seleziona tutte le segnalazioni di quel sensore nella lista associata a quel gestore
	//	per ognuno vai a calcolare la differenza 
	//	Date orario_corrente=new Date();
	//	for(Segnalazione s: segnalazioni_sensore) {
	//		if(orario_corrente.getTime()-s.Data.getTime()<1800000) return true;
	//		}
		return false;
	} 
*/	






	//set and get
	public String getIdRobot() {
		return idRobot;
	}

	public String getIdsensore() {
		return idsensore;
	}

	public float getValore() {
		return valore;
	}

	public Date getData_ora() {
		return data_ora;
	}

	public void setIdRobot(String idRobot) {
		this.idRobot = idRobot;
	}

	public void setIdsensore(String idsensore) {
		this.idsensore = idsensore;
	}

	public void setValore(float valore) {
		this.valore = valore;
	}

	public void setData_ora(Date data_ora) {
		this.data_ora = data_ora;
	}

	
/*	public class ControlloStato extends Thread{
		SegnalazioneTest s;
		
		public ControlloStato(SegnalazioneTest s) {
			this.s=s;
		}
		
		
	}*/


}
