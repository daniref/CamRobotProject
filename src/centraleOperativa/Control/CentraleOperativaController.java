package centraleOperativa.Control;

import java.util.Date;

import centraleOperativa.Businesslogic.*;

public class CentraleOperativaController {

	//CREAZIONE DEL 'SINGLETON'
	private static CentraleOperativaController centrale=null;
	
	//costruttore privato---------------------------------
	private  CentraleOperativaController() {
		
		//costruisci manager!
		
		
	}
	//metodo usato per l'accesso alla classe singleton
	public static synchronized CentraleOperativaController getIstance(){
		if(centrale==null){
			centrale= new CentraleOperativaController();
		}
		return centrale;
	}

	
	public void gestisciSegnalazione(String idrobot, String idsensore,float valore, Date dataora) {
		CoerenzaMessaggioManager checkMess=new CoerenzaMessaggioManager(idrobot);  //datacheck
		//String notificaGestore=null;
		System.out.println("CENTRALE_GESTISCISEGNALAZIONE");
		System.out.println("idRobot:" + idrobot);
		System.out.println("idsensore:" + idsensore);
		System.out.println("valore:" + valore);
		System.out.println("dara-ora:" + dataora);
		//Verifica Dati
		if(checkMess.VerificaCoerenzaDati()){
			System.out.println("Verifica Superata");
			
			SegnalazioneManager segnManag=new SegnalazioneManager(idrobot,idsensore,valore,dataora);			//gestisci segnalazione
			segnManag.trattaSegnalazione();
			String ids=segnManag.getIdSegnalazione();
			//MESSAGGIO AL PROPRIETARIO: idsegnalazione; idsensore; dataora;
			String messaggioProprietario =(ids+";"+idsensore+";"+valore+";"+dataora+";");
			ComunicazioneManager cm= new ComunicazioneManager(messaggioProprietario,idrobot);
			cm.ContattaProprietario();
		}
		else {
			System.out.println("\n\nErrore! Dati ricevuti non corretti. Il messaggio ricevuto da '"+idrobot+"' e' stato ignorato!\n\n");
			}
	}
		

	public void gestisciNotifica(String idSegnalazione){
		NotificheManager mm =new NotificheManager();
		mm.NotificaLetturaSegnalazione(idSegnalazione);
	}
	
	
	public void gestisciKeep(String idrobot, Date dataora) {
		System.out.println("CENTRALE_GESTISCI-KEEP");

	}
	
}
