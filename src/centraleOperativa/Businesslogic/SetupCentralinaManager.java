package centraleOperativa.Businesslogic;

import java.util.ArrayList;

import centraleOperativa.Entity.area_Entity;
import centraleOperativa.Entity.robot_Entity;
import centraleOperativa.Entity.sensore_Entity;

public class SetupCentralinaManager {

	public SetupCentralinaManager() {}
	
//in questo metodo si permette di ricercare tutti i sensori di un robot, e fare un casting a String per ogni sensore
	public ArrayList<String> setupSensori(String s){		
		//vai all'aria 1
		//cerca un robot con quell'id
		//	ArrayList<sensore_entity> entity_buffer=new ArrayList<sensore_entity>();
		//per quel robot restituisci un'arrayList di oggetti di tipo sensore_entity
		//entity_buffer=
		ArrayList<String> datiSensori=new ArrayList<String>();
		try {
			area_Entity area = area_Entity.getInstance("ar0001");
			robot_Entity r = new robot_Entity();
			r=area.getRobotById(s);
			r.fillSensoriList();
			String msg="";
			for(sensore_Entity sens : r.getSensoriList()) {
				msg=sens.getId()+";"+sens.getTipologia()+";"+sens.getSoglia();
				datiSensori.add(msg);
			}
			
			return datiSensori;

		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}

		/*
			ArrayList<String> datiSensori=new ArrayList<String>();
			datiSensori.add("ss123;T;50.10");
			datiSensori.add("s1223;F;40.90");
			datiSensori.add("s1013;P;25.11");
			datiSensori.add("s1530;P;50.22");
		*/	
		//	String msg;
		/*
		 * for(int i=0;i<ffer.size();i++){
		 * 		msg=""+entity_buffer.get(i).getID()+";"+entity_buffer.get(i).getTipologia()+";"+String.format("%.2f", entity_buffer.get(i).getSoglia()).replace(",", ".")+";";
		 * 		datiSensori.add(msg);
		 * }
		 * */
	//		Per la soglia so che è un float nel formato xx.xx (50.00)	String.format("%.2f", entity_buffer.get(i).getSoglia()).replace(",", ".")
	}
}
