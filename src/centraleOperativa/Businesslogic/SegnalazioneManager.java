package centraleOperativa.Businesslogic;

import java.util.Date;

import centraleOperativa.Businesslogic.*;

public class SegnalazioneManager {
	private String idRobot;
	private String idsensore;
	private float valore;
	private Date data_ora;
	//private ArrayList <Segnalazione> segnalazioni;
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
	
	public boolean verificaSegnalazionePendente() {
		
	String tipo=this.leggiTipologia();
	String idg=this.tipoSensoreToGestore(tipo);
	//ArrayList<Segnalazioni> segn=richiestaListaSegnalazioni(idg);
	//cerca se condizione su questa lista!(id_sensore)
	//	  3) vai dal gestore corrispondente e fatti dare la lista delle segnalazioni che sta "gestendo"
	//	  4) verifica se è presente in questa lista una segnalazione precedente
		return true;
	}
	
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

	
public boolean cercasegnalazionependente() {
	//  se esiste una segnalazione nella lista che è stat
	//	seleziona tutte le segnalazioni di quel sensore nella lista associata a quel gestore
	//	per ognuno vai a calcolare la differenza 
	//	Date orario_corrente=new Date();
	//	for(Segnalazione s: segnalazioni_sensore) {
	//		if(orario_corrente.getTime()-s.Data.getTime()<1800000) return false;
	//		}
		return true;
	} 
	
/*
// metodo che a partire dall'id di un gestore determina tutte le segnalazini non chiuse che sta gestendo!
  	public void richiestaListaSegnalazioni(String idgestore){
		setSegnalazioni (getListaSegnalazioni(idgestore));
	}
 */
	
	public void creaSegnalazione() {
		//deve essere creato un nuovo oggetto di tipo Segnalazione!
/*		campi di Segnalazione D'Allarme
			id segnalazione
			stato=Aperto
			valore allarme
			data
			ora
			Id Gestore
			Id Sensore
	*/
		//Segnalazione s=new Segnalazione("APERTO",this.getValore(),data,ora,idgestore,idsensore);
		
		}



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

/*	public void setSegnalazioni(ArrayList<Segnalazione> ss) {
		this.segnalazioni=ss;
	}

	public ArrayList<Segnalazione> getSegnalazioni(){
		return segnalazioni;
	}
	*/
}
