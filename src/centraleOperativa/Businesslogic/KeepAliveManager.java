package centraleOperativa.Businesslogic;

import java.util.Date;

import org.orm.PersistentException;

import centraleOperativa.Entity.area_Entity;
import centraleOperativa.Entity.keepAlive_Entity;
import centraleOperativa.Entity.registroKeepAlive_Entity;
import centraleOperativa.Entity.robot_Entity;

public class KeepAliveManager {
	private String idrobot;
	private Date dataora;
	
	public KeepAliveManager(String i,Date d) {
		this.idrobot=i;
		this.dataora=d;
	}

	public void RegistraKeep() {
	
		try {
			registroKeepAlive_Entity registrok = registroKeepAlive_Entity.getInstance();
			keepAlive_Entity k_search = new keepAlive_Entity();
			k_search=registrok.getKeepByIdRobot(this.idrobot);
			keepAlive_Entity ka = new keepAlive_Entity(dataora,idrobot);
			if(k_search.getId()==null) {	
				registrok.addKeep(ka);
				}
			else {
				registrok.updateKeep(ka);
			}
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void aggiornaFunzionamentoRobot() {
		try {
			area_Entity ae= area_Entity.getInstance("ar0001");
			robot_Entity re= new robot_Entity();
			synchronized(this) {
				re=ae.getRobotById(idrobot);
				if(re.getFunzionamento().compareTo("OK")!=0) {
					re.setFunzionamento("OK");
					ae.updateRobot(re);
				}
			}
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
