
 package centraleOperativa.Launcher;
 
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.jms.JMSException;

import centraleOperativa.ProxyComunicazioneAsincrona.*;
import centraleOperativa.ProxyComunicazioneSincrona.*;

public class launcher {

	public static void main(String argv[]) throws JMSException{
		final int PortaAmministratore=6100;
		final String urlAmministratore= "rmi://localhost:"+PortaAmministratore+"/AM";

		final int PortaCliente=4001;
		final String urlCliente= "rmi://localhost:"+PortaCliente+"/cl";

		
		System.out.println("*****CENTRALE****");
		try {
			   //la centralina riceve i messaggi dal cliente sulla porta 4000!
			   Cliente_CentraleOperativaProxy proxyc1 = new Cliente_CentraleOperativaProxy();		
			   Registry registro_centrale_cliente=LocateRegistry.createRegistry(PortaCliente);
			   Naming.rebind(urlCliente, proxyc1);
			   System.out.println("Centrale pronta a ricever richieste dal Cliente.");
			   }catch (Exception e) {
				   System.out.println("Centrale Server/Cliente error: " + e);
				}

		   try {
			   //la centralina riceve i messaggi dall'amministratore sulla porta 6100!
			   Amministratore_CentraleOperativaProxy proxyc2 = new Amministratore_CentraleOperativaProxy();
			   Registry registro_centrale_cliente=LocateRegistry.createRegistry(PortaAmministratore);
			   Naming.rebind(urlAmministratore, proxyc2);
			   System.out.println("Centrale pronta a ricever richieste dall'amministratore.");
			   }catch (Exception e) {
				   System.out.println("Centrale Server/Amministratore error: " + e);
				}
		   
		   
		   

//		proxysetup proxyAsincrona = new proxysetup();	//proxy che fa in modo di ricevere le segnalazioni d'allarme
//		proxyAsincrona.setup();
//		
//
//		TimerProxy tp1 = new TimerProxy(0,proxyAsincrona.getConsumerAllarmi());	//proxy che riceve i messaggi di tipo keep alive
//		TimerProxy tp2 = new TimerProxy(1,proxyAsincrona.getConsumerKeep());	//proxy che riceve i messaggi di tipo keep alive
//		tp1.start();
//		tp2.start();
//		gestisci la chiusura della connessione!
		//si è scelto di utilizzato (lato admin) una chiamata RMI a proxyAsincrona.chiudi().
//		proxyAsincrona.chiudi();
		}
}

