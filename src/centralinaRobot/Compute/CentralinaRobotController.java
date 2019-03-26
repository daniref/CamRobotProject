package centralinaRobot.Compute;


import centralinaRobot.Control.*;
import centralinaRobot.Sense.*;

import java.util.ArrayList;
import javax.jms.JMSException;



public class CentralinaRobotController {
	private final static String idR="rb0001"; //id robot univoco per ogni controller!
	
	private final static ArrayList<SensoreInterface> Sensori= new ArrayList<SensoreInterface>();
	private static ArrayList<Float> SensoriSoglie= new ArrayList<Float>();	

	public static ArrayList<Float> getSensoriSoglie() {
		return SensoriSoglie;
	}
	public static void setSensoriSoglie(ArrayList<Float> sensoriSoglie) {
		SensoriSoglie = sensoriSoglie;
	}

	private static MonitoraggioManager ManagerMon;
	private static String IDRobot;

	//CREAZIONE DEL 'SINGLETON'
	private static CentralinaRobotController centralinaRobot=null;
	//costruttore privato---------------------------------
	private  CentralinaRobotController() {
	}
	//metodo usato per l'accesso alla classe singleton
	public static synchronized CentralinaRobotController getCentralinaRobot(){
			if(centralinaRobot==null){
				centralinaRobot= new CentralinaRobotController();
			}
			return centralinaRobot;
		}
	public ArrayList<SensoreInterface> getSensori(){
		return Sensori;
	}

	public String getID() {
		return IDRobot;
	}

	//dati ottenuti dal server
	public void configuration(){
		IDRobot=idR; 
		//si va a fare una richiesta con RMI in cui si caricano i Sensori Interface con i valori che ci sono sul db!
		ArrayList<String> datisensoriDB= new ArrayList<String>();
		SetupManager sm=new SetupManager();
		datisensoriDB=sm.loadDataDB(idR);
		String [] splitdati =new String[3];
		for(int i=0;i<datisensoriDB.size();i++) {
			splitdati=datisensoriDB.get(i).split(";");	
			Sensori.add(new SensoreInterface(splitdati[0],splitdati[1]));
			SensoriSoglie.add(Float.valueOf(splitdati[2]));
		}
//		SensoreInterface  s=new SensoreInterface("s030","T");
 //       SensoreInterface s1=new SensoreInterface("s031","F");
       // SensoreInterface s2=new SensoreInterface("s032","P");
        //SensoreInterface s3=new SensoreInterface("s033","P");
        //SensoreInterface s4=new SensoreInterface("s034","P");
   //     Sensori.add(s);
   //     Sensori.add(s1);
        //Sensori.add(s2);
        //Sensori.add(s3);
        //Sensori.add(s4);
    //    configurationSoglie();
	//	System.out.println("[Controller]configurazione completata");
}

	public void configurationSoglie() {
		for(int i=0;i<Sensori.size();i++) {
		switch(Sensori.get(i).getTipo()) {
			case "F":
				SensoriSoglie.add((float)80.0);
				break;
			case "T":
				SensoriSoglie.add((float)30.0);
				break;
			case "P":
				SensoriSoglie.add((float)50);
				break;	
			default: 
				SensoriSoglie.add((float)20);
				break;									
			}
		}
	}

	
	public void Misura(Display d) throws JMSException{
	//	System.out.println("[Controller]crea manager segnalazioni con idrobot "+ getID() + "Sensori e Soglie");
        ManagerMon=new MonitoraggioManager(getID(),Sensori, SensoriSoglie); //carica soglie!
		ManagerMon.Monitora(d);
	}
	
	
	public ArrayList<String> MonitoraggioRemoto(){
		System.out.println("[Controller]crea manager MONITORAGGIO-REMOTO con idrobot "+ getID() + "Sensori e Soglie");
		MonitoraggioManager MonMan=new MonitoraggioManager(getID(),Sensori, SensoriSoglie);
		return MonMan.MonitoraggioRemoto();
		
	}
	
	public void ControllaFunz(Display d) throws JMSException{
		System.out.println("[Controller]crea un nuovo manager con idrobot="+ getID());
		FunzionamentoManager mf=new FunzionamentoManager(getID());
		mf.CheckFunzionamento(d);
	}
	
}
