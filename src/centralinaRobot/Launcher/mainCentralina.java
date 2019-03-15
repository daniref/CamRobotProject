package centralinaRobot.Launcher;


import java.awt.EventQueue;

import javax.jms.JMSException;

import centralinaRobot.Compute.*;
import centralinaRobot.Control.*;
import centralinaRobot.Sense.*;


/*******MAIN DI PROVA*************/
//import centralina.*;
public class mainCentralina {

	
	public static void main(String argv[]) throws JMSException{

		System.out.println("*****CENTRALINA****");
			CentralinaRobotController c = CentralinaRobotController.getCentralinaRobot();
			System.out.println("[main_centralina][CONTROLLER][0]-controller creato");
			c.configuration();		
			System.out.println("[main_centralina][CONTROLLER][1]-controller configurato");
			Display disp = new Display(c.getSensori(),c.getID());
			System.out.println("[main_centralina][Dysplay][0]-costruttore display invocato");
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						disp.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			System.out.println("[DEBUG][main_centralina][0]-controller creato");
			TimerInterface t1 = new TimerInterface(0,disp);	//timer che scatena monitoraggio
			TimerInterface t2 = new TimerInterface(1, disp);	//Timer che scatena il controllo funzionamento
			t1.start();
			t2.start();
			
		}
}
