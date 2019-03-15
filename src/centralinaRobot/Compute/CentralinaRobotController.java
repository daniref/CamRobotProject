package centralinaRobot.Compute;


import centralinaRobot.Control.*;
import centralinaRobot.Sense.*;

import java.util.ArrayList;
import javax.jms.JMSException;



public class CentralinaRobotController {
	private final static String idR="rb0001"; //id robot univoco per ogni controller!
	
	private final static ArrayList<SensoreInterface> Sensori= new ArrayList<SensoreInterface>();
	private static MonitoraggioManager ManagerMon;
	private static FunzionamentoManager ManagerFunz;

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
		SensoreInterface  s=new SensoreInterface("s030","T");
        SensoreInterface s1=new SensoreInterface("s031","F");
        SensoreInterface s2=new SensoreInterface("s032","P");
        SensoreInterface s3=new SensoreInterface("s033","P");
        SensoreInterface s4=new SensoreInterface("s034","P");
        Sensori.add(s);
        Sensori.add(s1);
        Sensori.add(s2);
        Sensori.add(s3);
        Sensori.add(s4);
		ManagerMon=new MonitoraggioManager(Sensori);
		ManagerFunz=new FunzionamentoManager();
		ManagerMon.configurationSoglie();
		System.out.println("[Controller] e manager configurazione completata");
}

	public void Misura(Display d) throws JMSException{
		System.out.println("[Controller]chiama funzione manager");
		ManagerMon.Monitora(getID(), d);
	}
	
	public void ControllaFunz(Display d) throws JMSException{
		System.out.println("[Controller]chiama funzione manager");
		ManagerFunz.CheckFunzionamento(getID(),d);
	}
	
}
