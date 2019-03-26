package cliente.ProxyComunicazioneSincrona;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import RMI.Interfaces.ProxyClienteCentrale;

public class Cliente_CentraleOperativaProxy {
	final int PortaCliente=4001;
	final String urlCliente= "rmi://localhost:"+PortaCliente+"/cl";
	
	public Cliente_CentraleOperativaProxy() {};
	public boolean NotificaLetturaSegnalazione(String idsegnalazione) throws MalformedURLException, RemoteException, NotBoundException {
		
		try{
			ProxyClienteCentrale proxy;
			proxy = (ProxyClienteCentrale)Naming.lookup(urlCliente);
			return proxy.Notifica(idsegnalazione);
			}
		catch (Exception e1) {
			System.out.println("Eccezione RMI Notifica Segnalazione: " + e1);
			e1.printStackTrace();
			return false;
		}
	} 

}
