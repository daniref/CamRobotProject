package centralinaRobot.ProxyComunicazioneSincrona;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import RMI.Interfaces.ProxyCentralinaCentrale;

public class CentralinaCentraleProxy {

	final int PortaCentraleCentralina=7000;
	final String urlCentraleCentralina= "rmi://localhost:"+PortaCentraleCentralina+"/CC";
	
	
	public CentralinaCentraleProxy() {};

	
	public ArrayList<String> LoadDataDB(String idr) throws MalformedURLException, RemoteException, NotBoundException {

		try{

			ProxyCentralinaCentrale proxy;
			proxy = (ProxyCentralinaCentrale)Naming.lookup(urlCentraleCentralina);
			ArrayList<String> buffer= new ArrayList<String>();
			buffer=proxy.loadData(idr);
			return buffer;
			}
		catch (Exception e) {
			System.out.println("Eccezione RMI Setup Centralina from Centrale: " + e);
			e.printStackTrace();
		}
	return null;
}
}
