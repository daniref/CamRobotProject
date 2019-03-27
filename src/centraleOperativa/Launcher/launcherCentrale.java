
 package centraleOperativa.Launcher;
 
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.jms.JMSException;

import centraleOperativa.ProxyComunicazioneSincrona.*;

public class launcherCentrale {

	public static void main(String argv[]) throws JMSException{
		final int PortaCentraleAmministratore=6100;
		final String urlCentraleAmministratore= "rmi://localhost:"+PortaCentraleAmministratore+"/AM";

		final int PortaCentraleCliente=4001;
		final String urlCentraleCliente= "rmi://localhost:"+PortaCentraleCliente+"/cl";

		final int PortaCentraleCentralina=7000;
		final String urlCentraleCentralina= "rmi://localhost:"+PortaCentraleCentralina+"/CC";
		
		
		System.out.println("*****CENTRALE****");
		try {
			   //la centrale apre un registro sulla porta 4001 per una comunicazione sincrona con il cliente
			   Cliente_CentraleOperativaProxy proxyc1 = new Cliente_CentraleOperativaProxy();		
			   Registry registro_centrale_cliente=LocateRegistry.createRegistry(PortaCentraleCliente);
			   Naming.rebind(urlCentraleCliente, proxyc1);
			   System.out.println("Centrale pronta a ricevere richieste dal Cliente.");
			   }catch (Exception e) {
				   System.out.println("Centrale Server/Cliente error: " + e);
				}

		   try {
			   //la centrale apre un registro sulla porta 6100 per una comunicazione sincrona con l'amministratore
			   Amministratore_CentraleOperativaProxy proxyc2 = new Amministratore_CentraleOperativaProxy();
			   Registry registro_centrale_cliente=LocateRegistry.createRegistry(PortaCentraleAmministratore);
			   Naming.rebind(urlCentraleAmministratore, proxyc2);
			   System.out.println("Centrale pronta a ricevere richieste dall'amministratore.");
			   }catch (Exception e) {
				   System.out.println("Centrale Server/Amministratore error: " + e);
				}
		   try {
			   //la centrale apre un registro sulla porta 7000 per una comunicazione sincrona con la centralina
			   CentralinaCentraleProxy proxyc3 = new CentralinaCentraleProxy();
			   Registry registro_centrale_centralina=LocateRegistry.createRegistry(PortaCentraleCentralina);
			   Naming.rebind(urlCentraleCentralina, proxyc3);
			   System.out.println("Centrale pronta a ricevere richieste dalla centralina.");
			   }catch (Exception e) {
				   System.out.println("Centrale Server/Centralina error: " + e);
				}
		   
		   
		   
		}
}

