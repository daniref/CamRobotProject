package RMI.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ProxyClienteCentrale extends Remote{
	public boolean Notifica(String ids, String tipo) throws RemoteException;
}
