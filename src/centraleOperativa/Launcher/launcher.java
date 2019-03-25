
 package centraleOperativa.Launcher;
 
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.jms.JMSException;

import centraleOperativa.ProxyComunicazioneAsincrona.*;
import centraleOperativa.ProxyComunicazioneSincrona.Cliente_CentraleOperativaProxy;
import centralinaRobot.ProxyComunicazioneSincrona.Cliente_CentralinaRobotProxy;

public class launcher {

	public static void main(String argv[]) throws JMSException{
		//MessageConsumer consAllarmi = null,consKeep = null;
		   try {
			   //la centralina riceve i messaggi dal cliente sulla porta 4000!
			   Cliente_CentraleOperativaProxy proxyc1 = new Cliente_CentraleOperativaProxy();		
			   Registry registro_centrale_cliente=LocateRegistry.createRegistry(4001);
			   Naming.rebind("rmi://localhost:4001/cl", proxyc1);
			   System.out.println("Centrale pronta a ricever richieste dal Cliente.");
			   }catch (Exception e) {
				   System.out.println("Centrale Server/Cliente error: " + e);
				}
		   
		   
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

