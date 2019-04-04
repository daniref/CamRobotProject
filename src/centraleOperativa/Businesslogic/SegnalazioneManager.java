package centraleOperativa.Businesslogic;


import java.util.ArrayList;
import java.util.Date;

import org.orm.PersistentException;

import centraleOperativa.Entity.GestoreManager;
import centraleOperativa.Entity.gestore_Entity;
import centraleOperativa.Entity.segnalazione_Entity;
import centraleOperativa.Entity.sensore_Entity;
import centraleOperativa.Boundary.ServizioDiComunicazioneInterface;
import centraleOperativa.Businesslogic.ComunicazioneManager;


public class SegnalazioneManager {
	private String idRobot;
	private String idsensore;
	private float valore;
	private Date data_ora;
	private String idSegnalazione;
	private String tipologia;
	private String idgestore;
	
	public SegnalazioneManager(String idr,String ids,float v, Date dataora) {
		this.idRobot=idr;
		this.idsensore=ids;
		this.valore=v;
		this.data_ora= dataora;
		this.idSegnalazione="error";
	}
	
	public void trattaSegnalazione() {
		this.tipologia=this.leggiTipologia();
		this.idgestore=this.tipoSensoreToGestore(tipologia);
		try {
			GestoreManager g= GestoreManager.getInstance();
			gestore_Entity gest=g.getGestore(this.idgestore);
			segnalazione_Entity s = new segnalazione_Entity();
			s=gest.getUltimaSegnalazioneByIdSensore(this.idsensore);
			if (s.getId()!=null) {

				if (!verificaCondizione(s,4)) { //dimmi se sono passati più di 4 minuti dalla precedente segnalazione con quell'id!
					try {
						segnalazione_Entity newSeg = new segnalazione_Entity(this.valore,this.data_ora,this.idgestore,this.idsensore,this.idRobot);
						this.idSegnalazione=gest.addSegnalazione(newSeg);
					} catch (PersistentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else {
				try {
					segnalazione_Entity newSeg = new segnalazione_Entity(this.valore,this.data_ora,this.idgestore,this.idsensore,this.idRobot);
					this.idSegnalazione=gest.addSegnalazione(newSeg);
				} catch (PersistentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (PersistentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	


	
	
	//Metodo in cui si permette di ricavare la tipologia di allarme che è stato generato
	public String leggiTipologia() {
		sensore_Entity se= new sensore_Entity();
		try {
			return se.getTipologiaById(getIdsensore());
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
//Metodo utile ad associare ad ogni sensore(tipologia di sensore) un gestore corrispondente a cui inviare la segnalazione!
	public String tipoSensoreToGestore(String tipo_sensore) {
		switch(tipo_sensore) {
		case "T": return "gs0001"; //per un allarme dovuto ad na soglia superata dal Termometro vai al gestore con id gs0001 (Pronto Soccorso)
		case "F": return "gs0002"; //per un allarme dovuto ad na soglia superata dal SensoreDiFumo vai al gestore con id gs0002 (Vigili Del Fuoco)
		case "P": return "gs0003"; //per un allarme dovuto ad na soglia superata dal sensore di Prossimità vai al gestore con id gs0001 (Polizia)
		default: return "gs0004";  //per un allarme provocato da un sensore generico vai al gestore gs0004 (Security Agency)
		}
	}

// metodo con cui si permette di chiedere al gestore con un certo id di restituire l'ultima segnalazione che ha gestito per quel sensore!
/*
 public ArrayList <SegnalazioneTest> getUltimaSegnalazione(String idgestore){
 		ArrayList <SegnalazioneTest> lista = new ArrayList<SegnalazioneTest>();
			//lista= getUltimaSegnalazione(idgestore, getIdensore()));
		return lista;
	}
*/
public boolean verificaCondizione(segnalazione_Entity s,int minuti) {
		Date orario_corrente=new Date();
		if(orario_corrente.getTime()-s.getDataTime().getTime()<(minuti*60*1000)) return true;
		return false;
}


public void setAttesa(){
		 gestore_Entity ge;
		try {
			GestoreManager g= GestoreManager.getInstance();
			ge=g.getGestore(idgestore);
			 segnalazione_Entity se= new segnalazione_Entity();
			 se= ge.getSegnalazioneById(idSegnalazione); 
			 se.setStato("IN ATTESA");
			 ge.updateSegnalazione(se);
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}				




	//set and get
	public String getIdRobot() {
		return idRobot;
	}

	public String getIdsensore() {
		return idsensore;
	}

	public String getTipologia() {
		return tipologia;
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

	public void setTipologia(String tip) {
		this.tipologia = tip;
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
	
	public String getIdSegnalazione() {
		return this.idSegnalazione;
	}

	public void setIdSegnalazione(String idseg) {
		this.idSegnalazione=idseg;
	}

	
	public String getIdgestore() {
		return idgestore;
	}

}

