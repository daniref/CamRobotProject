package centraleOperativa.Entity;

import centraleOperativa.Entity.*;
import centraleOperativa.DB.Robot;
import centraleOperativa.DB.RobotDAO;
import java.util.ArrayList;

import org.orm.PersistentException;


public class area_Entity {
	
	//creazione del SINGLETON
	private static area_Entity area=null;
	
	//lista di tutti i robot appartenenti all'area
	private static ArrayList<robot_Entity> listaRobot;
	
	//costruttore privato
	private area_Entity(String idArea) throws PersistentException{
		try {
			listaRobot=new ArrayList<robot_Entity>();
        		ArrayList<Robot> robotList = new ArrayList<Robot>();
        		RobotDAO rob=new RobotDAO();
        		robotList=rob.getRobotListByIdArea(idArea);
        		for(Robot r : robotList) {
        			robot_Entity new_robot = new robot_Entity();
        			new_robot.setId(r.getId());
        			new_robot.setStato(r.getStato());
        			new_robot.setCondizione(r.getCondizione());
        			new_robot.setFunzionamento(r.getFunzionamento());
        			new_robot.setIndirizzo(r.getIndirizzo());
        			new_robot.setAreaId(r.getAreaId());
        			listaRobot.add(new_robot);
        		}
		}
        	catch(Exception e) {
        		e.printStackTrace();
        		throw new PersistentException(e);
        	}
		
	}
	
	//metodo usato per l'accesso alla classe singleton
	public static synchronized area_Entity getInstance(String idArea) throws PersistentException{
		if(area==null) {
			try {
				area=new area_Entity(idArea);
			}
			catch(Exception e) {
				e.printStackTrace();
				throw new PersistentException(e);
			}
		}
		return area;
	}
	
	public void addRobotToList(Robot robot) {
		robot_Entity new_robot = new robot_Entity();
		new_robot.setId(robot.getId());
		new_robot.setStato(robot.getStato());
		new_robot.setCondizione(robot.getCondizione());
		new_robot.setFunzionamento(robot.getFunzionamento());
		new_robot.setIndirizzo(robot.getIndirizzo());
		new_robot.setAreaId(robot.getAreaId());
		listaRobot.add(new_robot);
	}

	public static ArrayList<robot_Entity> getListaRobot() {
		return listaRobot;
	}
	
	public static robot_Entity getRobotById(String idRobot) {
		ArrayList<robot_Entity> robotList = getListaRobot();
		int i=0;
		boolean trovato=false;
		while(i<robotList.size() && !trovato) {
			robot_Entity r = robotList.get(i);
			if(r.getId().compareTo(idRobot)==0) {
				trovato=true;
			}
			else {
				i++;
			}
		}
		return robotList.get(i);
	}
	

}
