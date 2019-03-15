package centralinaRobot.Sense;

import javax.jms.JMSException;

import centralinaRobot.Compute.CentralinaRobotController;
import centralinaRobot.Control.*;

public class TimerInterface extends Thread {
	private int tipo; //timer monitoraggio(0), timer Keep (1)
	Display Dis;

	public TimerInterface(int t, Display D){
		super();
		this.tipo=t;
	this.Dis=D;
	}
	
	
	public void run(){
		if(tipo==0){//monitoraggio(0)
		do {
			try {
				System.out.println("***MONITORAAA***");
				monitora(Dis);
				Thread.sleep(4500);

				}
			catch (InterruptedException | JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} 
		}while(true);
		}
	else {
		do {
			try {
				 verifica(Dis);
				System.out.println("***CHECK FUNZIONAMENTO***");
				Thread.sleep(20000);
				}
			catch (InterruptedException | JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		}while(true);
		}
}
	
	public void monitora(Display d) throws JMSException {
		CentralinaRobotController r=CentralinaRobotController.getCentralinaRobot();
		r.Misura(d);
	}

	public void verifica(Display d) throws JMSException {
		CentralinaRobotController r=CentralinaRobotController.getCentralinaRobot();
		r.ControllaFunz(d);
	}
	
	};