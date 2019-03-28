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
		
		this.tipologia=this.leggiTipologia();
		this.idgestore=this.tipoSensoreToGestore(tipologia);
		try {
			gestore_Entity gest= gestore_Entity.getInstance(this.idgestore);
			segnalazione_Entity s = new segnalazione_Entity();
			System.out.println("DEBUG          -1        "+s.getId());
			s=gest.getUltimaSegnalazioneByIdSensore(this.idsensore);
			System.out.println("DEBUG          0        "+s.getId());
			if (s.getId()!=null) {
				if (!verificaCondizione(s)) {
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
//		s=getUltimaSegnalazione(idg);  //viene restituita l'ultima segnalazione provocata da un determinato sensor

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
public boolean verificaCondizione(segnalazione_Entity s) {
		Date orario_corrente=new Date();
		System.out.println("[VERIFICA CONDIZIONE] orario della segnalazione: "+s.getDataTime());
		System.out.println("[VERIFICA CONDIZIONE] orario corrente: "+orario_corrente);
		System.out.println("[VERIFICA CONDIZIONE] differenza: "+orario_corrente.compareTo(s.getDataTime()));
		if(orario_corrente.compareTo(s.getDataTime())<1800000) return true;
		return false;
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

	
	//da portare in gestore_entity
	/**
	 * 
	 */
	public void ControlloNotifica() {

		System.out.println("FUNZIONE CONTROLLO NOTIFICA           INIZIO");

		Thread ThreadNotifica=new Thread(){
			public void run() {
/*=======
		Thread ThreadNotifica=new Thread()
			{
				public void run() {
						try {
							gestore_Entity ge=gestore_Entity.getInstance(idgestore);
							wait();
							segnalazione_Entity s= ge.getSegnalazioneById(idSegnalazione);		//ho già creato una segnalazione (che ha lo stato iniziale a "APERTA")
							s.setStato("IN ATTESA");											//si setta lo stato di quella segnalazione a "IN ATTESA"
							ge.updateSegnalazione(s);											//update in gestore -> e db
							notifyAll();
							sleep(120000);														//si attendono 2 minuti
							wait();
							s= gestore_Entity.getSegnalazioneById(idSegnalazione); 				//wait - action- notify (semaphore)
							if(s.getStato().compareTo("IN ATTESA")==0) {
								s.setStato("GESTORE ESTERNO");
								ge.updateSegnalazione(s);
								notifyAll();
								ComunicazioneManager cM = new ComunicazioneManager("",idRobot,idgestore);
								String indi=cM.recuperaIndirizzo();
								String telEm=cM.recuperaNumeroEmergenza();
								ServizioDiComunicazioneInterface sci=null;
								String msg="Indirizzo da raggiungere: "+indi+"; Allarme scattato: "+tipologia+"; Valore rilevato: "+valore+"; Orario: "+data_ora;
								sci.contattaProprietario(msg, telEm);
								System.out.println("E' stata inoltrato il seguente messaggio: <"+msg+"> al numero: <"+telEm+">");
							}
							else {
								notifyAll();
								//System.out.println("La segnalazione è stata chiusa attraverso la notifica del Cliente");
							}
							
							}
						catch(InterruptedException | PersistentException e){
							e.printStackTrace();
						}
					}
>>>>>>> branch 'master' of https://github.com/daniref/CamRobotProject.git
*/				
				boolean comunica=false;
				
				try {
					gestore_Entity ge=gestore_Entity.getInstance(idgestore);
					synchronized(this) {
					segnalazione_Entity se= gestore_Entity.getSegnalazioneById(idSegnalazione); 
					se.setStato("IN ATTESA");
					ge.updateSegnalazione(se);
					notifyAll();
					}
					System.out.println("THREAD. GET NAME           PRE-ATTESA"+Thread.currentThread().getName());
					sleep(10000);										// attende 2 minuti
					System.out.println("THREAD. GET NAME           POST-ATTESA"+Thread.currentThread().getName());
					synchronized(this) {
						segnalazione_Entity se= new segnalazione_Entity();
						se=gestore_Entity.getSegnalazioneById(idSegnalazione); 		// lettura fatta col semaforo!
						if(se.getStato().compareTo("IN ATTESA")==0) {
							se.setStato("GESTORE ESTERNO");
							ge.updateSegnalazione(se);
							comunica=true;
						}
						else {
							//System.out.println("La segnalazione è stata chiusa attraverso la notifica del Cliente");
						}
					notifyAll();
					}
					if(comunica) {
						ComunicazioneManager cM = new ComunicazioneManager("",idRobot,idgestore);
						String indi=cM.recuperaIndirizzo();
						String telEm=cM.recuperaNumeroEmergenza();
						ServizioDiComunicazioneInterface sci=null;
						String msg="Indirizzo da raggiungere: "+indi+"; Allarme scattato: "+tipologia+"; Valore rilevato: "+valore+"; Orario: "+data_ora;
						sci.contattaProprietario(msg, telEm);
						System.out.println("E' stata inoltrato il seguente messaggio: <"+msg+"> al numero: <"+telEm+">");
					}
				}
				catch(InterruptedException | PersistentException e){
					e.printStackTrace();
				}
			}
				
		};
			ThreadNotifica.start();
	}
}

