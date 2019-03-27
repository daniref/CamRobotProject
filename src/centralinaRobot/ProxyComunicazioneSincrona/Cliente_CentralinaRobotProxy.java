package centralinaRobot.ProxyComunicazioneSincrona;

import java.rmi.*;
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
	}
	
}