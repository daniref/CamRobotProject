package centraleOperativa.Businesslogic;

import org.orm.PersistentException;

import centraleOperativa.Entity.area_Entity;
import centraleOperativa.Entity.robot_Entity;

//import centrale.entity.robot;
public class CoerenzaMessaggioManager {


	private String idR;

	public String getIdR() {
		return idR;
		
	}

	public CoerenzaMessaggioManager(String idr) {
		this.idR=idr;

	}
	
	public boolean VerificaCoerenzaDati() {
/*'d_robot deve appartenere effettivamente ad un robot del sistema che si trova nello stato REGISTRATO e nella condizione ON)*/
			area_Entity area;
			try {
				area = area_Entity.getInstance("ar0001");
				robot_Entity robot = area.getRobotById(getIdR());
				
				boolean a=robot.getStato().compareTo("REGISTRATO")==0;
				boolean b=robot.getCondizione().compareTo("ON")==0;
				if(a&&b)return true;
				else return false;
			} catch (PersistentException e) {
				// TODO Auto-generated catch block
				return false;				
				//e.printStackTrace();
			}
			
		
	}
	
	
	
	
}

