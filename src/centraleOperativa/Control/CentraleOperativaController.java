package centraleOperativa.Control;

import java.util.ArrayList;
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
//		System.out.println("CENTRALE_GESTISCISEGNALAZIONE");
//		System.out.println("idRobot:" + idrobot);
//		System.out.println("idsensore:" + idsensore);
//		System.out.println("valore:" + valore);
//		System.out.println("dara-ora:" + dataora);
		//Verifica Dati
		if(checkMess.VerificaCoerenzaDati()){
			System.out.println("La segnalazione d'allarme proviene dal robot <"+idrobot+"> REGISTRATO ed ON ");
			
			SegnalazioneManager segnManag=new SegnalazioneManager(idrobot,idsensore,valore,dataora);			//gestisci segnalazione
			segnManag.trattaSegnalazione();
			String ids=segnManag.getIdSegnalazione();
			if(ids.compareTo("error")!=0) { //se è stata creata una nuova segnalazione (in tal caso si modifica il valore iniziale di "idsegnalazione")
			//MESSAGGIO AL PROPRIETARIO: idsegnalazione; idsensore; dataora;
				String messaggioProprietario =(ids+";"+idsensore+";"+valore+";"+dataora+";"+segnManag.getTipologia()+";");
				ComunicazioneManager cm= new ComunicazioneManager(messaggioProprietario,idrobot);
				String recapito=cm.recuperaRecapito();

				if(recapito!=null) {
				//	System.out.println("[CENTRALE OPERATIVA CONTROLLER] ecco il recapito    "+recapito);
					ServizioDiComunicazioneInterface sc = new ServizioDiComunicazioneInterface(); //si inizializza a null in quanto ci si affida ad un Servizio esterno che va ad implementare questa funzione!
					sc.contattaProprietario(messaggioProprietario, recapito);
					segnManag.setAttesa(); 													//setta la segnalazione a IN ATTESA
					ThreadNotifica t=new ThreadNotifica(idrobot,ids,segnManag.getIdgestore()); //aspetta 2 minuti, verifica notifica dal cliente, (se necessario) contatta gestore esterno
					t.start();
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

	
	
	//metodo che quando richiamato, deve cotrollare se nella tabella dei keep alive ce ne sono alcuni che non aggiornati  
	//da più di tot. minuti.
	//nel caso che ci siano robot ON da cui non si ricevono keep da più di "minuti" 
	//allora si contatta il prorpietario e si avvisa che quel robot è fuori Servizio
	//NB in realtà si controlla che tale intervallo di tempo si a minuti < intervallo <= 2*minuti)
	public void gestisciMalfunzionamenti(Date d,int minuti_in_secondi) {
		System.out.println("Controllo maldunzionamenti alle ora: "+d);
		MalfunzionamentiManager mm= new MalfunzionamentiManager();
		ArrayList<String> robotFuoriUso = new ArrayList<String>();
		robotFuoriUso=mm.ControllaMalfunzionamenti(d,minuti_in_secondi); 				
		for(int i=0; i<robotFuoriUso.size();i++) {
			if(mm.checkRobotON(robotFuoriUso.get(i))) {
				String messaggioProprietario =(robotFuoriUso.get(i)+" provvisoriamente non funzionante");
				ComunicazioneManager cm= new ComunicazioneManager(messaggioProprietario,robotFuoriUso.get(i));
				String recapito=cm.recuperaRecapito();
				if(recapito!=null) {
					ServizioDiComunicazioneInterface sc = new ServizioDiComunicazioneInterface(); //si inizializza a null in quanto ci si affida ad un Servizio esterno che va ad implementare questa funzione!
					sc.contattaProprietario(messaggioProprietario, recapito);
					mm.setMalfunzionamento(robotFuoriUso.get(i)); 	//idRobot
					}
				else {
					System.out.println("E' stato individuato un robot non funzionanete, ma non è presente il recapito del proprietario");
				}
			}
			else {
				System.out.println("il robot <"+robotFuoriUso.get(i)+" non manda un keep da tanto tempo, ma e' spento!");
			}
			
		}
	}
}
