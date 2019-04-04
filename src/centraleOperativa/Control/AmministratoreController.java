package centraleOperativa.Control;

import javax.jms.JMSException;

import org.orm.PersistentException;

import centraleOperativa.ProxyComunicazioneAsincrona.TimerProxy;
import centraleOperativa.Entity.GestoreManager;
import centraleOperativa.Entity.gestore_Entity;
import centraleOperativa.ProxyComunicazioneAsincrona.CentraleOperativaProxy;

public class AmministratoreController {
	static TimerProxy tp1; //schedula i messaggi segnalazione
	static TimerProxy tp2; //schedula i messaggi keep 
	static TimerProxy tp3; //thread che va a fare controlo sui keep per verificare i malfunzionamenti

	//CREAZIONE DEL 'SINGLETON'
	private static AmministratoreController AmmCon=null;
	
	//costruttore privato---------------------------------
	private  AmministratoreController() {
		
		
	}
	//metodo usato per l'accesso alla classe singleton
	public static synchronized AmministratoreController getIstance(){
		if(AmmCon==null){
			AmmCon= new AmministratoreController();
		}
		return AmmCon;
	}
	
	

	public void gestisciStart() throws JMSException {
		CentraleOperativaProxy proxyAsincrona= CentraleOperativaProxy.getIstance();
		proxyAsincrona.setup();	//proxy che fa in modo di ricevere le segnalazioni d'allarme
		tp1 = new TimerProxy(0,proxyAsincrona.getConsumerAllarmi(),3); //ogni 2.3 secondi si controllano gli allarmi (in realtà c'è un thread sui sensori interface che aggiorna il valore ogni 6 secondi
		tp2 = new TimerProxy(1,proxyAsincrona.getConsumerKeep(),2.2);	 //ogni 7.2 secondi si controlla il funzionamento
		tp3 = new TimerProxy(3,5); 									//ogni 1 minuto parte un controllo sui Keep!
		tp1.start();
		tp2.start();
		tp3.start();
		try {
			GestoreManager g=GestoreManager.getInstance();
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void gestisciStop() throws JMSException {
		tp1.stoppa();
		tp2.stoppa();
		tp3.stoppa();
		try {
			GestoreManager g=GestoreManager.getInstance();
			g.getGestore("gs0001").StampaLista();
			g.getGestore("gs0002").StampaLista();
			g.getGestore("gs0003").StampaLista();
			g.getGestore("gs0004").StampaLista();
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CentraleOperativaProxy proxyAsincrona= CentraleOperativaProxy.getIstance();
		proxyAsincrona.chiudi();
	}
	
}
