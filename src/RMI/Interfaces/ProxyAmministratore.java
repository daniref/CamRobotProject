package RMI.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.jms.JMSException;

public interface ProxyAmministratore extends Remote{
	   public void startMonitoraggio() throws RemoteException;
	   public void stopMonitoraggio() throws RemoteException;

}
