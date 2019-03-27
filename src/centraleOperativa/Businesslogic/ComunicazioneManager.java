package centraleOperativa.Businesslogic;

import org.orm.PersistentException;

import centraleOperativa.Entity.area_Entity;
import centraleOperativa.Entity.cliente_Entity;
import centraleOperativa.Entity.contratto_Entity;
import centraleOperativa.Entity.gestore_Entity;
import centraleOperativa.Entity.robot_Entity;

public class ComunicazioneManager {

	String msg;
	String idrobot;
	String idgestore;
	//costruttore con 2 argomenti
	public ComunicazioneManager(String m, String idr) { 
		this.msg=m;
		this.idrobot=idr;
		}
	//costruttore con 3 argomenti
	public ComunicazioneManager(String m, String idr, String idGestore) { 
		String msg;
		this.idrobot=idr;
		this.idgestore=idGestore;
		}
	
	public String recuperaRecapito()	{
		contratto_Entity cc=new contratto_Entity();
		try {
			String idutente;
			idutente = cc.getIdUtenteByIdRobot(idrobot);
			cliente_Entity utente=new cliente_Entity();
			String recapito=utente.getRecapitoById(idutente);
			return recapito;
			//
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	

public String recuperaNumeroEmergenza()	{
	
	try {
		gestore_Entity ge=gestore_Entity.getInstance(idgestore);
		return ge.getRecapito();
		//
	} catch (PersistentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	return null;
}
	


public String recuperaIndirizzo()	{
	try {
		area_Entity ae=area_Entity.getInstance("ar0001");
		robot_Entity re= new robot_Entity();
		re=ae.getRobotById(this.idrobot);
		return re.getIndirizzo();
	} catch (PersistentException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	return null;
	}
}
