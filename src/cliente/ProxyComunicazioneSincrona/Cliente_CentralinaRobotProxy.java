package cliente.ProxyComunicazioneSincrona;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import RMI.Interfaces.*;


public class Cliente_CentralinaRobotProxy {
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

	
}


