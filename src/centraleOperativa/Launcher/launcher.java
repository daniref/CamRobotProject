
 package centraleOperativa.Launcher;
 
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.jms.JMSException;

import centraleOperativa.ProxyComunicazioneSincrona.*;

public class launcher {

	public static void main(String argv[]) throws JMSException{
		final int PortaCentraleAmministratore=6100;
		final String urlCentraleAmministratore= "rmi://localhost:"+PortaCentraleAmministratore+"/AM";

		final int PortaCentraleCliente=4001;
		final String urlCentraleCliente= "rmi://localhost:"+PortaCentraleCliente+"/cl";

		
		System.out.println("*****CENTRALE****");
		try {
			   //la centralina riceve i messaggi dal cliente sulla porta 4000!
			   Cliente_CentraleOperativaProxy proxyc1 = new Cliente_CentraleOperativaProxy();		
			   Registry registro_centrale_cliente=LocateRegistry.createRegistry(PortaCentraleCliente);
			   Naming.rebind(urlCentraleCliente, proxyc1);
			   System.out.println("Centrale pronta a ricever richieste dal Cliente.");
			   }catch (Exception e) {
				   System.out.println("Centrale Server/Cliente error: " + e);
				}

		   try {
			   //la centralina riceve i messaggi dall'amministratore sulla porta 6100!
			   Amministratore_CentraleOperativaProxy proxyc2 = new Amministratore_CentraleOperativaProxy();
			   Registry registro_centrale_cliente=LocateRegistry.createRegistry(PortaCentraleAmministratore);
			   Naming.rebind(urlCentraleAmministratore, proxyc2);
			   System.out.println("Centrale pronta a ricever richieste dall'amministratore.");
			   }catch (Exception e) {
				   System.out.println("Centrale Server/Amministratore error: " + e);
				}
		   
		   
		   
		}
}

