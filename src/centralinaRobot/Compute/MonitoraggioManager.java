package centralinaRobot.Compute;

import java.util.ArrayList;
import javax.jms.JMSException;


import centralinaRobot.proxyComunicazioneAsincrona.CentraleOperativaProxy;
import centralinaRobot.Control.*;
import centralinaRobot.Sense.SensoreInterface;


public class MonitoraggioManager {
	private static ArrayList<SensoreInterface> Sensori= new ArrayList<SensoreInterface>();
	private static ArrayList<Integer> SensoriSoglie= new ArrayList<Integer>();	
	private final static CentraleOperativaProxy proxyAsincr= new CentraleOperativaProxy();	

	public MonitoraggioManager(ArrayList<SensoreInterface> SI) {
		Sensori=SI;
	}
	
//Per ogni sensore si inizializza un vettore di Soglie .
//la soglia è settata in modo conforme al tipo di Sensore
public void configurationSoglie() {
	for(int i=0;i<Sensori.size();i++) {
	switch(Sensori.get(i).getTipo()) {
		case "F":
			SensoriSoglie.add(80);
			break;
		case "T":
			SensoriSoglie.add(30);
			break;
		case "P":
			SensoriSoglie.add(50);
			break;	
		default: 
			SensoriSoglie.add(20);
			break;									
		}
	}
}

public ArrayList <Integer> getSensoriSoglie(){
	return SensoriSoglie;
}

//funzione per poter impostare un nuovo valore di soglia per un determinato sensore
//è importante che l'ordinamento dei Sensori sia corrispondente all'ordinamento delle soglie
public void changeSoglia(int index, int newSoglia) {
	 SensoriSoglie.set(index, newSoglia);
 }
		
	
public boolean VerificaSuperamentoSoglia(float valore, int soglia){
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
	
public void Monitora(String id_robot, Display d) throws JMSException{
	float valore;
	//	per ogni sensore gestito dalla centralina viene letto il valore e la soglia
	for(int i=0;i<Sensori.size();i++){
		System.out.println("[DEBUG][MANAGERMONITORA[] iterazione :"+i);
		valore=Sensori.get(i).Leggi();
		d.showval(i,valore);
		if(VerificaSuperamentoSoglia(valore, SensoriSoglie.get(i))==true){
	//		System.out.println("[DEBUG][MANAGERMONITORA](verifica) - soglia superata 1)richiama proxy");
	//		estrae il tipo di allarme dall'id del sensore
	//		System.out.println("[DEBUG][MANAGERMONITORA](verifica) - invia parametri al proxy:{Robot: "+id_robot +",Sensore: "+Sensori.get(i)+"}");
			proxyAsincr.GeneraAllarme(id_robot,Sensori.get(i).getID(),valore);
	//		System.out.println("[DEBUG][MANAGERMONITORA](verifica) - soglia superata 2)ritorno al manager");
		}
	}
}

}
