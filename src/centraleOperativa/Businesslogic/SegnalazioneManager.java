package centraleOperativa.Businesslogic;


import java.util.ArrayList;
import java.util.Date;

import org.orm.PersistentException;

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
		System.out.println("DEBUG(SegnalazioneManager)(trattasegnalazione)          -6        ");

		this.tipologia=this.leggiTipologia();
		System.out.println("DEBUG(SegnalazioneManager)(trattasegnalazione)          -5        ");
		this.idgestore=this.tipoSensoreToGestore(tipologia);
		System.out.println("DEBUG(SegnalazioneManager)(trattasegnalazione)          -4        ");
		try {
			System.out.println("DEBUG(SegnalazioneManager)(trattasegnalazione)          -3        ");
			gestore_Entity gest= gestore_Entity.getInstance(this.idgestore);
			System.out.println("DEBUG(SegnalazioneManager)(trattasegnalazione)          -2        ");
			segnalazione_Entity s = new segnalazione_Entity();
			System.out.println("DEBUG(SegnalazioneManager)(trattasegnalazione)          -1        "+s.getId());
			s=gest.getUltimaSegnalazioneByIdSensore(this.idsensore);
			System.out.println("DEBUG(SegnalazioneManager)(trattasegnalazione)          0        "+s.getId());
			if (s.getId()!=null) {
				System.out.println("DEBUG(SegnalazioneManager)(trattasegnalazione)       1   c'è già una segnalazione per quel sensore      ");

				if (!verificaCondizione(s,4)) { //dimmi se sono passati più di 4 minuti dalla precedente segnalazione con quell'id!
					System.out.println("DEBUG(SegnalazioneManager)(trattasegnalazione)    2   quela segnalazione è stata generata poiù di 3 minuti  fa        ");
					try {
						segnalazione_Entity newSeg = new segnalazione_Entity(this.valore,this.data_ora,this.idgestore,this.idsensore,this.idRobot);
						System.out.println("DEBUG(SegnalazioneManager)(trattasegnalazione)    3   creo una nuova segnalazione        ");
						this.idSegnalazione=gest.addSegnalazione(newSeg);
						System.out.println("DEBUG(SegnalazioneManager)(trattasegnalazione)    4  agggiungo questa segnalazione al gestore corrispondente        ");
						
					} catch (PersistentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else {
				System.out.println("DEBUG(SegnalazioneManager)(trattasegnalazione)       1.1   non c'è alcuna segnalazione per quel sensore      ");
				try {
					segnalazione_Entity newSeg = new segnalazione_Entity(this.valore,this.data_ora,this.idgestore,this.idsensore,this.idRobot);
					System.out.println("DEBUG(SegnalazioneManager)(trattasegnalazione)    2.1   creo una nuova segnalazione        ");
				this.idSegnalazione=gest.addSegnalazione(newSeg);
				System.out.println("DEBUG(SegnalazioneManager)(trattasegnalazione)    3.1  agggiungo questa segnalazione al gestore corrispondente        ");
				
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
		System.out.println("[VERIFICA CONDIZIONE] orario della segnalazione: "+s.getDataTime());
		System.out.println("[VERIFICA CONDIZIONE] orario corrente: "+orario_corrente);
		System.out.println("[VERIFICA CONDIZIONE] differenza: "+ (orario_corrente.getTime()-s.getDataTime().getTime()));
		if(orario_corrente.getTime()-s.getDataTime().getTime()<(minuti*60*1000)) return true;
		//getTime restituisce il numero di millisecondi trascorsi dal 01/01/1970 00:00:00.
		//dunque la differenza tra queste due date è espressa in millisecondi.
		return false;
}


public void setAttesa(){
		 gestore_Entity ge;
		try {
			ge = gestore_Entity.getInstance(idgestore);
			 segnalazione_Entity se= new segnalazione_Entity();
			 se= gestore_Entity.getSegnalazioneById(idSegnalazione); 
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

