package centraleOperativa.Control;

import java.util.Date;

import centraleOperativa.Boundary.ServizioDiComunicazioneInterface;
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
			System.out.println("DEBUG 0");
			segnManag.trattaSegnalazione();
			System.out.println("DEBUG 1");
			String ids=segnManag.getIdSegnalazione();
			System.out.println("DEBUG 2");
			if(ids.compareTo("error")!=0) { //se è stata creata una nuova segnalazione (in tal caso si modifica il valore iniziale di "idsegnalazione")
			//MESSAGGIO AL PROPRIETARIO: idsegnalazione; idsensore; dataora;
				String messaggioProprietario =(ids+";"+idsensore+";"+valore+";"+dataora+";"+segnManag.getTipologia()+";");
				ComunicazioneManager cm= new ComunicazioneManager(messaggioProprietario,idrobot);
				String recapito=cm.recuperaRecapito();

				if(recapito!=null) {
					System.out.println("[CENTRALE OPERATIVA CONTROLLER] ecco il recapito    "+recapito);

					ServizioDiComunicazioneInterface sc = new ServizioDiComunicazioneInterface(); //si inizializza a null in quanto ci si affida ad un Servizio esterno che va ad implementare questa funzione!
					sc.contattaProprietario(messaggioProprietario, recapito);
					System.out.println("Ci sono arrivato                      dfsd");
					segnManag.ControlloNotifica(); 				// setta ad IN ATTESA , aspetta 2 minuti, verifica notifica dal cliente, (se necessario) contatta gestore esterno
				}
			}
			else {
				System.out.println("Non è stato creata alcuna nuova segnalazione perchè già presente (<30 min)");
			}
		}
		else {
			System.out.println("\n\nErrore! Dati ricevuti non corretti. Il messaggio ricevuto da '"+idrobot+"' e' stato ignorato!\n\n");
		}
	}
		
	
	public void gestisciKeep(String idrobot, Date dataora) {
		KeepAliveManager kam=new KeepAliveManager(idrobot,dataora);
		kam.RegistraKeep();
		kam.aggiornaFunzionamentoRobot();
		
	}
	
}
