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


/*public class Cliente_CentralinaRobotProxy {
	final int PortaCentralinaCliente=4000;
	final String urlCentralinaCliente= "rmi://localhost:"+PortaCentralinaCliente+"/BC";
	
	public Cliente_CentralinaRobotProxy() {};
	public ArrayList<String> MonitoraggioRemoto() throws MalformedURLException, RemoteException, NotBoundException {
		
		try{
			ProxyClienteCentralina proxy;
			proxy = (ProxyClienteCentralina)Naming.lookup(urlCentralinaCliente);
			ArrayList<String> buff= new ArrayList<String>();
			buff=proxy.MonitoraggioRemoto();
			return buff;
			}
		catch (Exception e1) {
			System.out.println("Eccezione RMI Monitoraggio Remoto: " + e1);
			e1.printStackTrace();
		}
	return null;
	} 

	
}*/