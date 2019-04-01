package RMI.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ProxyClienteCentralina extends Remote {
	   public ArrayList<String> MonitoraggioRemoto() throws RemoteException;
	   public boolean refresh() throws RemoteException;
}
