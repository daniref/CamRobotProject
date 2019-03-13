package centralinaRobot.launcher;


import java.awt.EventQueue;

import javax.jms.JMSException;

import centralinaRobot.compute.*;
import centralinaRobot.control.*;
import centralinaRobot.sense.*;


/*******MAIN DI PROVA*************/
//import centralina.*;
public class mainCentralina {

	
	public static void main(String argv[]) throws JMSException{

		System.out.println("*****CENTRALINA****");
			CentralinaRobotController c = CentralinaRobotController.getCentralinaRobot();
			c.configuration();		
			System.out.println("[DEBUG][CONTROLLER][0]-controller creato");
			System.out.println("[DEBUG][CONTROLLER][1]-controller configurato");
			Display disp = new Display(c.getSensori(),c.getID());
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						disp.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			TimerInterface t1 = new TimerInterface(0,disp);	//timer che scatena monitoraggio
			TimerInterface t2 = new TimerInterface(1, disp);	//Timer che scatena il controllo funzionamento
//			TimerInterface t1 = new TimerInterface(0,c,disp);	//timer che scatena monitoraggio
//			TimerInterface t2 = new TimerInterface(1,c, disp);	//Timer che scatena il controllo funzionamento
			t1.start();
			t2.start();
			
		}
}
			
/*			System.out.println("["+Calendar.getInstance().getTime()+"]");
			System.out.println("[DEBUG][CONTROLLER][0]-creazione controller");
			System.out.println("[DEBUG][CONTROLLER][1]-controller configurato");
			for(int i=0;i<10;i++) {
				System.out.println("["+(i+1)+"]");
				try {
					Thread.sleep(5000);
					ManagerMon.Monitora(getID());

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("[DEBUG][CONTROLLER][3]- ritorno al controller");
			
		}
	}
	};
	/*
	for(int i=0;i<10;i++) {
			
			System.out.println("["+Calendar.getInstance().getTime()+"]");

			System.out.println("\tSensore(ID: "+id1+"): "+ s1.Leggi()+"°");
			System.out.println("\tSensore(ID: "+id2+"): "+ s2.Leggi()+"ppm");
			System.out.print("\tSensore(ID: "+id3+"): " +s3.Leggi());
			if(s3.Leggi()>s3.getSoglia())System.out.println(" CHIUSO");
			else System.out.println(" APERTO");
			System.out.print("\tSensore(ID: "+id4+"): "+s4.Leggi());
			if(s4.Leggi()>s4.getSoglia())System.out.println(" CHIUSO");
			else System.out.println(" APERTO");
			System.out.println();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
*/

