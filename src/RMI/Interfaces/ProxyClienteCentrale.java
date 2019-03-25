package RMI.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ProxyClienteCentrale extends Remote{
	public void Notifica() throws RemoteException;
}
