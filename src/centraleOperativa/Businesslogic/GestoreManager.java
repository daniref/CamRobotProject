package centraleOperativa.Businesslogic;

import java.util.ArrayList;

import org.orm.PersistentException;

import centraleOperativa.Entity.gestore_Entity;

//singleton
public class GestoreManager {

	private static GestoreManager gestoremanager=null;
	ArrayList<gestore_Entity> gestori= new ArrayList<gestore_Entity>(4);
	
	public static synchronized GestoreManager getInstance() throws PersistentException{

		if(gestoremanager==null) {
			gestoremanager=new GestoreManager();	
		}
		return gestoremanager;
		}
	
	
	private GestoreManager() throws PersistentException {
		System.out.println("Costruttore Gestore Manager");
		gestore_Entity pronto_socc= new gestore_Entity("gs0001");
		gestori.add(pronto_socc);
		gestore_Entity VigiliFuoco= new gestore_Entity("gs0002");
		gestori.add(VigiliFuoco);
		gestore_Entity polizia= new gestore_Entity("gs0003");
		gestori.add(polizia);
		gestore_Entity SecurityAg= new gestore_Entity("gs0004");
		gestori.add(SecurityAg);
	}
	
	
	public int getIndex(String idGestore) {
		switch(idGestore) {
			case "gs0001" : return 0;
			case "gs0002" : return 1;
			case "gs0003" : return 2;
			case "gs0004" : return 3;
			default: return -1;
			}
	}
	
	public gestore_Entity getGestore(String idg) {
		if (getIndex(idg)>-1) {
			return gestori.get(getIndex(idg));
		}
		else return null;
	}
	
	
	

	
}
