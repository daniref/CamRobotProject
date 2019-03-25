package RMI.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ProxyClienteCentralina extends Remote {
	   public String MonitoraggioRemoto() throws RemoteException;
}
