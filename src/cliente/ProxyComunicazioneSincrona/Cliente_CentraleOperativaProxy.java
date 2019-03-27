package cliente.ProxyComunicazioneSincrona;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import RMI.Interfaces.ProxyClienteCentrale;

public class Cliente_CentraleOperativaProxy {
	final int PortaCentraleCliente=4001;
	final String urlCentraleCliente= "rmi://localhost:"+PortaCentraleCliente+"/cl";
	
	public Cliente_CentraleOperativaProxy() {};
	public boolean NotificaLetturaSegnalazione(String idsegnalazione,String tipologia) throws MalformedURLException, RemoteException, NotBoundException {
		
		try{
			ProxyClienteCentrale proxy;
			proxy = (ProxyClienteCentrale)Naming.lookup(urlCentraleCliente);
			return proxy.Notifica(idsegnalazione,tipologia);
			}
		catch (Exception e1) {
			System.out.println("Eccezione RMI Notifica Segnalazione: " + e1);
			e1.printStackTrace();
			return false;
		}
	} 

}
