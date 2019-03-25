
 package centraleOperativa.Launcher;
 
import javax.jms.JMSException;

import centraleOperativa.ProxyComunicazioneAsincrona.*;

public class launcher {

	public static void main(String argv[]) throws JMSException{
		//MessageConsumer consAllarmi = null,consKeep = null;
		System.out.println("*****CENTRALE****");
		proxysetup proxyAsincrona = new proxysetup();	//proxy che fa in modo di ricevere le segnalazioni d'allarme
		proxyAsincrona.setup();
		

		TimerProxy tp1 = new TimerProxy(0,proxyAsincrona.getConsumerAllarmi());	//proxy che riceve i messaggi di tipo keep alive
		TimerProxy tp2 = new TimerProxy(1,proxyAsincrona.getConsumerKeep());	//proxy che riceve i messaggi di tipo keep alive
		tp1.start();
		tp2.start();
//		gestisci la chiusura della connessione!
		//si è scelto di utilizzato (lato admin) una chiamata RMI a proxyAsincrona.chiudi().
//		proxyAsincrona.chiudi();
		}
}

