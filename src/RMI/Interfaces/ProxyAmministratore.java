package RMI.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ProxyAmministratore extends Remote{
	   public void startMonitoraggio() throws RemoteException;
	   public void stopMonitoraggio() throws RemoteException;

}
