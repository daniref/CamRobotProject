package centralinaRobot.Compute;

import java.util.ArrayList;
import javax.jms.JMSException;


import centralinaRobot.proxyComunicazioneAsincrona.CentraleOperativaProxy;
import centralinaRobot.Control.*;
import centralinaRobot.Sense.SensoreInterface;


public class MonitoraggioManager {
	private static ArrayList<SensoreInterface> Sensori= new ArrayList<SensoreInterface>();
	private static ArrayList<Float> SensoriSoglie= new ArrayList<Float>();	
	private static String idRobot;
	private final static CentraleOperativaProxy proxyAsincr= new CentraleOperativaProxy();	

	public MonitoraggioManager(String idr,ArrayList<SensoreInterface> SI, ArrayList <Float >SS ) {
		setIdRobot(idr);
		setSensori(SI);
		setSensoriSoglie(SS);
	}
	
public ArrayList <Float> getSensoriSoglie(){
	return SensoriSoglie;
}

public static ArrayList<SensoreInterface> getSensori() {
	return Sensori;
}

public static void setSensori(ArrayList<SensoreInterface> sensori) {
	Sensori = sensori;
}

public static String getIdRobot() {
	return idRobot;
}

public static void setIdRobot(String idRobot) {
	MonitoraggioManager.idRobot = idRobot;
}

public static void setSensoriSoglie(ArrayList<Float> sensoriSoglie) {
	SensoriSoglie = sensoriSoglie;
}

//funzione per poter impostare un nuovo valore di soglia per un determinato sensore
//è importante che l'ordinamento dei Sensori sia corrispondente all'ordinamento delle soglie
public void changeSoglia(int index, float newSoglia) {
	 SensoriSoglie.set(index, newSoglia);
 }
		
	
public boolean VerificaSuperamentoSoglia(float valore, float soglia){
	if(valore>soglia)
		return true;
	else 
		return false;
}
	
public ArrayList<Number> LetturaValori(){
	ArrayList<Number> valoriletti= new ArrayList<Number>();
	for(int i=0;i<Sensori.size();i++){
		valoriletti.add(Sensori.get(i).Leggi());
	}
	return valoriletti;
}
	
public void Monitora(Display d) throws JMSException{
	float valore;
	//	per ogni sensore gestito dalla centralina viene letto il valore e la soglia
	for(int i=0;i<Sensori.size();i++){
		valore=Sensori.get(i).Leggi();
		d.showval(i,valore);
		if(VerificaSuperamentoSoglia(valore, SensoriSoglie.get(i))==true){
	
	//		estrae il tipo di allarme dall'id del sensore
			proxyAsincr.GeneraAllarme(getIdRobot() ,Sensori.get(i).getID(),valore);
		}
	}
}
public ArrayList<String> MonitoraggioRemoto() {
	ArrayList<String> lista_msg=new ArrayList<String>();
	String msg;
	for(int i=0;i<Sensori.size();i++){
		  msg=Sensori.get(i).toString()+" "+"Valore: "+String.format ("%.2f",Sensori.get(i).getMisura());  
		  lista_msg.add(msg);
		}
	return lista_msg;
	}
}