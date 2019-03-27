package centraleOperativa.Control;

import javax.jms.JMSException;

import centraleOperativa.ProxyComunicazioneAsincrona.TimerProxy;
import centraleOperativa.ProxyComunicazioneAsincrona.CentraleOperativaProxy;

public class AmministratoreController {
	static TimerProxy tp1;
	static TimerProxy tp2;
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
		tp1 = new TimerProxy(0,proxyAsincrona.getConsumerAllarmi());
		tp2 = new TimerProxy(1,proxyAsincrona.getConsumerKeep());
		tp1.start();
		tp2.start();
	}
	
	public void gestisciStop() throws JMSException {
		tp1.stoppa();
		tp2.stoppa();
		CentraleOperativaProxy proxyAsincrona= CentraleOperativaProxy.getIstance();
		proxyAsincrona.chiudi();
	}
	
}
