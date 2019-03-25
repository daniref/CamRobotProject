package centraleOperativa.ProxyComunicazioneSincrona;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import RMI.Interfaces.ProxyClienteCentrale;
import centraleOperativa.Control.CentraleOperativaController;
import centraleOperativa.Businesslogic.*;

public class Cliente_CentraleOperativaProxy extends UnicastRemoteObject
	implements ProxyClienteCentrale{
	public Cliente_CentraleOperativaProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Notifica(String s) throws RemoteException {
		// TODO Auto-generated method stub
		CentraleOperativaController cpc=CentraleOperativaController.getIstance();
		cpc.gestisciNotifica(s);
	}
}
/*
 * import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

import RMI.Interfaces.*;
import centralinaRobot.Compute.*;

public class Cliente_CentralinaRobotProxy extends UnicastRemoteObject
	implements ProxyClienteCentralina{

	public Cliente_CentralinaRobotProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> MonitoraggioRemoto() throws RemoteException {
		CentralinaRobotController cgc= CentralinaRobotController.getCentralinaRobot();
		MonitoraggioManager mm =new MonitoraggioManager(cgc.getID(), cgc.getSensori(), cgc.getSensoriSoglie());
		ArrayList<String> buff= new ArrayList<String>();
		buff=mm.MonitoraggioRemoto();
		return buff;
	}*/
