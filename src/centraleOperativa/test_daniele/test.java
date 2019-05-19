package centraleOperativa.test_daniele;

import javax.jms.JMSException;

import centraleOperativa.Control.AmministratoreController;
import centraleOperativa.ProxyComunicazioneAsincrona.TimerProxy;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//facciamo partire il controllo del monitoraggio!
		AmministratoreController ac=AmministratoreController.getIstance();
		try {

			ac.gestisciStart();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}
