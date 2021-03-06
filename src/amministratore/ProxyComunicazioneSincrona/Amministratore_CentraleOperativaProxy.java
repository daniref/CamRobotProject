package amministratore.ProxyComunicazioneSincrona;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import RMI.Interfaces.ProxyAmministratore;

public class Amministratore_CentraleOperativaProxy {

	final int PortaCentraleAmministratore=6100;
	final String urlCentraleAmministratore= "rmi://localhost:"+PortaCentraleAmministratore+"/AM";

	public Amministratore_CentraleOperativaProxy() {}
	
	public void start()  throws MalformedURLException, RemoteException, NotBoundException{

		try {
			ProxyAmministratore pr;
			pr=(ProxyAmministratore)Naming.lookup(urlCentraleAmministratore);
			pr.startMonitoraggio();
			}
		catch(Exception e) {
			System.out.println("Eccezione RMI Start funzionamento centrale da amministratore");
			e.printStackTrace();

		}
	}
	
	
	public void stop()  throws MalformedURLException, RemoteException, NotBoundException{
		try {
			ProxyAmministratore pr1;
			pr1=(ProxyAmministratore)Naming.lookup(urlCentraleAmministratore);
			pr1.stopMonitoraggio();
			}
		catch(Exception e) {
			System.out.println("Eccezione RMI STOP funzionamento centrale da amministratore");
			e.printStackTrace();

		}
	}
	
}
