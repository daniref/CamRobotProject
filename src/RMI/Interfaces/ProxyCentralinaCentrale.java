package RMI.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ProxyCentralinaCentrale  extends Remote{
	public ArrayList<String> loadData(String idr) throws RemoteException;
}