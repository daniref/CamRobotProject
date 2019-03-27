package centralinaRobot.Compute;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import centralinaRobot.ProxyComunicazioneSincrona.CentralinaCentraleProxy;

public class SetupManager {
	String idRobot;
	public SetupManager() {}



	public ArrayList<String> loadDataDB(String idrobot){
		
		ArrayList<String> BufferString= new ArrayList<String>();
		 CentralinaCentraleProxy ccp= new CentralinaCentraleProxy();
		 try {
			BufferString=ccp.LoadDataDB(idrobot);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return BufferString;
		
	}
	

}
