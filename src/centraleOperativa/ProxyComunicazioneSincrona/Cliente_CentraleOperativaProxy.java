package centraleOperativa.ProxyComunicazioneSincrona;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import RMI.Interfaces.ProxyClienteCentrale;
import centraleOperativa.Control.ClienteController;
import centraleOperativa.Businesslogic.*;

public class Cliente_CentraleOperativaProxy extends UnicastRemoteObject
	implements ProxyClienteCentrale{
	public Cliente_CentraleOperativaProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean Notifica(String s,String t) throws RemoteException {
		// TODO Auto-generated method stub
		ClienteController cc=new ClienteController();
		return cc.gestisciNotifica(s,t);
	}
}
