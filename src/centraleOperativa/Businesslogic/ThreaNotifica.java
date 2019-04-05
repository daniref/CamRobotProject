package centraleOperativa.Businesslogic;


import org.orm.PersistentException;

import centraleOperativa.Boundary.ServizioDiComunicazioneInterface;
import centraleOperativa.Entity.managerGestori_Entity;
import centraleOperativa.Entity.gestore_Entity;
import centraleOperativa.Entity.segnalazione_Entity;

public class ThreaNotifica extends Thread{

	private String idsegnalazione; 
	private String idgestore;
	private String idrobot;

	public ThreaNotifica(String idr,String ids, String idg){
		this.idsegnalazione=ids;
		this.idgestore=idg;
		this.idrobot=idr;
		//System.out.println("[DEBUG-THREAD-NOTIFICA]creato nuovo thread per la segnalazione<"+ids +"> gestita da <"+idg+">");
	}


	public void run(){
		boolean comunica=false;
		   		//	System.out.println("[DEBUG-THREAD-NOTIFICA] start Monitoraggio");					
					try {
						Thread.sleep(15000);
						managerGestori_Entity g= managerGestori_Entity.getInstance();
						gestore_Entity ge=g.getGestore(idgestore);
						 segnalazione_Entity se= new segnalazione_Entity();
						 se= ge.getSegnalazioneById(idsegnalazione); 
							if(se.getStato().compareTo("IN ATTESA")==0) {
								se.setStato("GESTORE ESTERNO");
								ge.updateSegnalazione(se);
								comunica=true;
							}
							else {
								System.out.println("La segnalazione è stata chiusa attraverso la notifica del Cliente");
							}
												 

					} catch (InterruptedException | PersistentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(comunica) {
						ComunicazioneManager cM = new ComunicazioneManager("",idrobot,idgestore);
						String indi=cM.recuperaIndirizzo();
						String telEm=cM.recuperaNumeroEmergenza();
						//ServizioDiComunicazioneInterface sci=new ServizioDiComunicazioneInterface();
						String msg="Si prega di raggiungere immediatamente il seguente indirizzo: "+indi;
						ServizioDiComunicazioneInterface.contattaProprietario(msg, telEm);
						System.out.println("E' stato inviato il seguente messaggio : <"+msg+"> al numero: <"+telEm+">");
					}
		   		
			}
}


