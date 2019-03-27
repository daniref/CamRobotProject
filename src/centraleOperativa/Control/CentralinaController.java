package centraleOperativa.Control;

import java.util.ArrayList;

import centraleOperativa.Businesslogic.SetupCentralinaManager;

public class CentralinaController {

	public CentralinaController() {}
	
	
	public ArrayList<String> gestisciLoad (String idrobot){
		ArrayList<String > buff= new ArrayList<String> ();
		SetupCentralinaManager scm= new SetupCentralinaManager();
		buff=scm.setupSensori(idrobot);
		return buff;
	}
	
	
}
