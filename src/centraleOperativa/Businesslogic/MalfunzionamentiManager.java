package centraleOperativa.Businesslogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import org.orm.PersistentException;

import centraleOperativa.DB.KeepAlive;
import centraleOperativa.DB.KeepAliveDAO;
import centraleOperativa.Entity.area_Entity;
import centraleOperativa.Entity.gestore_Entity;
import centraleOperativa.Entity.keepAlive_Entity;
import centraleOperativa.Entity.registroKeepAlive_Entity;
import centraleOperativa.Entity.robot_Entity;
import centraleOperativa.Entity.segnalazione_Entity;

public class MalfunzionamentiManager {

	
	//restituisce la lista dei robot che hanno un keep che è stato generato più di mezz'ora prima rispetto alla data passata in argoment
	public ArrayList<String> ControllaMalfunzionamenti(Date d, int minuti_in_secondi) {
		ArrayList<String> buffer_idRobot=new ArrayList<String>();
		ArrayList<keepAlive_Entity> lista=new ArrayList<keepAlive_Entity>();
		long intervallo_limite= minuti_in_secondi*1000;
		System.out.println("Si controlla se sono passati più di "+(minuti_in_secondi/60)+" minuti ("+intervallo_limite+" ms) rispetto a "+d);
		try {
			registroKeepAlive_Entity registrok = registroKeepAlive_Entity.getInstance(); //dammi  registro dei keep alive
			lista=registrok.getListaKeepAlive();
			for(int i=0;i<lista.size();i++) {
				   long diff=d.getTime()-lista.get(i).getDataTime().getTime()+2*60*60*1000;
				   System.out.println((i+1)+") KEEP: Data keep: ["+lista.get(i).getDataTime()+"] differenza di tempo: " +diff);
				   if(diff>(minuti_in_secondi*1000)) {

					   	if(diff<=(2*minuti_in_secondi*1000)) {
					   		System.out.println("Per il robot <"+lista.get(i).getIdRobot()+"> sono passati più di "+(minuti_in_secondi/30)+"min. dall' ultimo keep--VECCHIO (contatta proprietario)");
					   		buffer_idRobot.add(lista.get(i).getIdRobot());
					   		}
					   	else {
					   		System.out.println("Per il robot <"+lista.get(i).getIdRobot()+"> sono passati più di "+(minuti_in_secondi/30)+"min. dall' ultimo keep--OBSOLETO");
					   		//il keep deve essere cancellato dalla lista xk significa che il robot è disattivato e già è stato inviato un messaggio al proprietario
							//buffer_idRobot.add(lista.get(i).getIdRobot());
							}
				   }
				   else {
				   		System.out.println("Per il robot <"+lista.get(i).getIdRobot()+"> sono passati meno di "+(minuti_in_secondi/60)+"min. dall' ultimo keep--OK");

				   }
			}
		} 
		catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buffer_idRobot;
	}
	
	
	
	public void setMalfunzionamento(String idrobot) {
		 area_Entity ae;
			try {
				ae = area_Entity.getInstance("ar0001");
				robot_Entity re= new robot_Entity();
				re=ae.getRobotById(idrobot);
				re.setFunzionamento("error");
				ae.updateRobot(re);
			} catch (PersistentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	public boolean checkRobotON (String idrobot) {
		 area_Entity ae;
			try {
				ae = area_Entity.getInstance("ar0001");
				robot_Entity re= new robot_Entity();
				re=ae.getRobotById(idrobot);
				return re.getCondizione().compareTo("ON")==0;
			} catch (PersistentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	}
	
}
