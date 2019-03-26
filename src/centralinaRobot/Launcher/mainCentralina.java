package centralinaRobot.Launcher;


import java.awt.EventQueue;

import javax.jms.JMSException;

import centralinaRobot.Compute.*;
import centralinaRobot.Control.*;
import centralinaRobot.Sense.*;
import centralinaRobot.ProxyComunicazioneSincrona.*;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;   
 


/*******MAIN DI PROVA*************/
//import centralina.*;
public class mainCentralina {

	
	public static void main(String argv[]) throws JMSException{
		final int PortaClienteCentralina=4000;
		final String urlClienteCentralina= "rmi://localhost:"+PortaClienteCentralina+"/BC";
		   try {
			   //la centralina riceve i messaggi dal cliente sulla porta 4000!
			   Cliente_CentralinaRobotProxy proxyc1 = new Cliente_CentralinaRobotProxy();		
			   Registry mioregistro1=LocateRegistry.createRegistry(PortaClienteCentralina);
			   Naming.rebind(urlClienteCentralina, proxyc1);
			   System.out.println("Centralina pronta a ricever richieste dal Cliente.");
			   }catch (Exception e) {
				   System.out.println("Centralina Server/Cliente error: " + e);
				}
		   
		System.out.println("*****CENTRALINA****");
			CentralinaRobotController c = CentralinaRobotController.getCentralinaRobot();
			System.out.println("[main_centralina][CONTROLLER][0]-controller creato");
			c.configuration();		
			System.out.println("[main_centralina][CONTROLLER][1]-controller configurato");

			
			Display disp = new Display(c.getSensori(),c.getID(),CentralinaRobotController.getSensoriSoglie());
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
