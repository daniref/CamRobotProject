package centralinaRobot.Sense;

import javax.jms.JMSException;

import centralinaRobot.Compute.CentralinaRobotController;
import centralinaRobot.Control.*;

public class TimerInterface extends Thread {
	private int tipo; //timer monitoraggio(0), timer Keep (1)
    private volatile boolean exit = false;

	public TimerInterface(int t){
		super();
		this.tipo=t;
	}
	
	
	public void run(){
		if(tipo==0){//monitoraggio(0)
		do {
			try {
				System.out.println("***MONITORAAA***");
				monitora();
				Thread.sleep(4500);

				}
			catch (InterruptedException | JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} 
		}while(!exit);
		}
	else {
		do {
			try {
				 verifica();
				System.out.println("***CHECK FUNZIONAMENTO***");
				Thread.sleep(2000);
				}
			catch (InterruptedException | JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		}while(!exit);
		}
}
	
	
	 public void stoppa(){
	        exit = true;
	    }
	
	public void monitora() throws JMSException {
		CentralinaRobotController r=CentralinaRobotController.getCentralinaRobot();
		r.Misura();
	}

	public void verifica() throws JMSException {
		CentralinaRobotController r=CentralinaRobotController.getCentralinaRobot();
		r.ControllaFunz();
	}
	
	};