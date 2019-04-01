package centraleOperativa.Control;

import java.util.ArrayList;

import centraleOperativa.Businesslogic.SetupCentralinaManager;

public class CentralinaController {

	public CentralinaController() {}
	
	
	public ArrayList<String> gestisciLoad (String idrobot){
		System.out.println("[DEBUG](CentralinaController [lato Centrale] - gestisciLoad- 0  ");

		ArrayList<String > buff= new ArrayList<String> ();
		SetupCentralinaManager scm= new SetupCentralinaManager();
		buff=scm.setupSensori(idrobot);
		return buff;
	}
	
	
}
