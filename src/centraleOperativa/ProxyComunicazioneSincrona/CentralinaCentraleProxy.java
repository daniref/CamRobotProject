package centraleOperativa.ProxyComunicazioneSincrona;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import RMI.Interfaces.ProxyCentralinaCentrale;
import centraleOperativa.Control.CentralinaController;


public class CentralinaCentraleProxy extends UnicastRemoteObject
implements ProxyCentralinaCentrale{

	public CentralinaCentraleProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> loadData(String idrobot) throws RemoteException {
		CentralinaController cc= new CentralinaController();
		ArrayList<String> buffer= new ArrayList<String>();
		buffer=cc.gestisciLoad(idrobot);
		return buffer;
	}

}
