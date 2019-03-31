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
	public ArrayList<String> ControllaMalfunzionamenti(Date d, int minuti) {
		ArrayList<String> buffer_idRobot=new ArrayList<String>();
		ArrayList<keepAlive_Entity> lista=new ArrayList<keepAlive_Entity>();
		try {
			registroKeepAlive_Entity registrok = registroKeepAlive_Entity.getInstance(); //dammi  registro dei keep alive
			lista=registrok.getListaKeepAlive();
			for(int i=0;i<lista.size();i++) {
				   long diff=d.getTime()-lista.get(i).getDataTime().getTime();
				   System.out.println("KEEP "+(i+1)+" differenza di tempo: " +diff);
				   if(diff>(minuti*60*1000)) {
					   	if(diff<=(2*minuti*60*1000)) {
					   		buffer_idRobot.add(lista.get(i).getIdRobot());
					   		}
					   	else {
					   		System.out.println("KEEP <"+ lista.get(i).getId() +"> del robot <"+lista.get(i).getIdRobot()+"> obsoleto!");
					   		//il keep deve essere cancellato dalla lista xk significa che il robot è disattivato e già è stato inviato un messaggio al proprietario
					   		}
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
				return re.getStato().compareTo("ON")==0;
			} catch (PersistentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	}
	
}
