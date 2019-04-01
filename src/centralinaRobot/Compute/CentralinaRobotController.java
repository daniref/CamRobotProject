package centralinaRobot.Compute;


import centralinaRobot.Control.*;
import centralinaRobot.Sense.*;

import java.util.ArrayList;
import javax.jms.JMSException;




public class CentralinaRobotController {
	private final static String idR="rb0002"; //id robot univoco per ogni controller!
	static TimerInterface t1;
	static TimerInterface t2;
	boolean avviato;
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
		if(datisensoriDB!=null) {
			for(int i=0;i<datisensoriDB.size();i++) {
				splitdati=datisensoriDB.get(i).split(";");	
				Sensori.add(new SensoreInterface(splitdati[0],splitdati[1]));
				SensoriSoglie.add(Float.valueOf(splitdati[2]));
			}
		}
		else {
			System.out.println("Non ci sono sensori associati al robot <"+idR+">");
		}
}

	
	public void Misura() throws JMSException{
	//	System.out.println("[Controller]crea manager segnalazioni con idrobot "+ getID() + "Sensori e Soglie");
        ManagerMon=new MonitoraggioManager(getID(),Sensori, SensoriSoglie); //carica soglie!
		ManagerMon.Monitora();
	}
	
	
	public ArrayList<String> MonitoraggioRemoto(){
		System.out.println("[Controller]crea manager MONITORAGGIO-REMOTO con idrobot "+ getID() + "Sensori e Soglie");
		MonitoraggioManager MonMan=new MonitoraggioManager(getID(),Sensori, SensoriSoglie);
		return MonMan.MonitoraggioRemoto();
		
	}
	
	public void ControllaFunz() throws JMSException{
		System.out.println("[Controller]crea un nuovo manager con idrobot="+ getID());
		FunzionamentoManager mf=new FunzionamentoManager(getID());
		if(!mf.CheckFunzionamento())stop();	//se il funzionamento non è corretto, allora stoppa la generazione dei valori sul display!
	}

	//metodo utilizzato per creare e mandare in run i due threads
	public void start(){
		System.out.println("[Controller]Il robot <"+getID()+"> sta per essere riavviato!");
		for(int i=0;i<Sensori.size();i++) {
			Sensori.get(i).refreshFunzionamentoSimulazione();
		}
		t1 = new TimerInterface(0);		//timer che scatena monitoraggio
		t2 = new TimerInterface(1);		//Timer che scatena il controllo funzionamento
		t1.start();
		t2.start();
		this.avviato=true;
	}

	//metodo utilizzato per interrompere i due thread che 
	public void stop(){

		System.out.println("[Controller]Il robot <"+getID()+"> sta per essere riavviato!");
		for(int i=0;i<Sensori.size();i++) {
			Sensori.get(i).setPausaSimulazione();
			}
		t1.stoppa();			//si interrompe il run del thread che scatena monitoraggio dei sensori
		t2.stoppa();			//si interrompe il run del thread che scatena il controllo del funzionamento
		this.avviato=false;
		}
	public boolean isAvviato() {
		return avviato;
	}
	public void setAvviato(boolean avviato) {
		this.avviato = avviato;
	}


}
