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
		final int PortaCentralinaCliente=4000;
		final String urCentralinalCliente= "rmi://localhost:"+PortaCentralinaCliente+"/BC";
		CentralinaRobotController c = CentralinaRobotController.getCentralinaRobot();
		c.configuration();		
		System.out.println("*****CENTRALINA <"+ c.getID()+">*****");
		   try {
			   //la centralina riceve i messaggi dal cliente sulla porta 4000!
			   Cliente_CentralinaRobotProxy proxyc1 = new Cliente_CentralinaRobotProxy();		
			   Registry mioregistro1=LocateRegistry.createRegistry(PortaCentralinaCliente);
			   Naming.rebind(urCentralinalCliente, proxyc1);
			   System.out.println("Centralina pronta a ricever richieste dal Cliente.");
			   }catch (Exception e) {
				   System.out.println("Centralina Server/Cliente error: " + e);
				}
		   

			
			Display disp = new Display(c.getSensori(),c.getID(),CentralinaRobotController.getSensoriSoglie());
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
