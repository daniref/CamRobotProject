package centraleOperativa.ProxyComunicazioneSincrona;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.jms.JMSException;

import RMI.Interfaces.ProxyAmministratore;
import centraleOperativa.Control.AmministratoreController;


public class Amministratore_CentraleOperativaProxy extends UnicastRemoteObject
implements ProxyAmministratore{

	public Amministratore_CentraleOperativaProxy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void startMonitoraggio() throws RemoteException {
		System.out.println("[Amministratore_CentraleOperativaProxy-CENTRALE] ");
		AmministratoreController ac=AmministratoreController.getIstance();
		try {
			System.out.println("[Amministratore_CentraleOperativaProxy-CENTRALE] chiama gestisci start");
			ac.gestisciStart();
			System.out.println("[Amministratore_CentraleOperativaProxy-CENTRALE] torna gestisci start");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void stopMonitoraggio() throws RemoteException {
		// TODO Auto-generated method stub
		AmministratoreController ac=AmministratoreController.getIstance();
		try {
			ac.gestisciStop();
		} catch (JMSException e) {
			e.printStackTrace();
		}		
	}

}


