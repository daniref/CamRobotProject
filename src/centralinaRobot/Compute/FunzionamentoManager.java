package centralinaRobot.Compute;



import java.util.Random;

import javax.jms.JMSException;

import centralinaRobot.Control.Display;
import centralinaRobot.proxyComunicazioneAsincrona.CentraleOperativaProxy;

public class FunzionamentoManager {

	private boolean funz; 
	private String idrobot;
	private final static CentraleOperativaProxy proxyAsincr= new CentraleOperativaProxy();

	
	public FunzionamentoManager(String idr) {
		this.funz=true;
		this.idrobot=idr;
	}
	

	public void change(){
		 this.funz=!funz;
		 }
		

	public void setFunz(boolean funz) {
		this.funz = funz;
	}

	public String getIdrobot() {
		return idrobot;
	}


	public void setIdrobot(String idrobot) {
		this.idrobot = idrobot;
	}


	public boolean getFunzionamento(){
			return funz;
	}
	
	//si simula un errato funzionamento solo nel 15% dei casi!
	public boolean simulafunzionamento() {
		Random random=new Random();
		int t= random.nextInt(100);
		if(t>95) return false;	
		return true;
	}

	public boolean CheckFunzionamento() throws JMSException{
		
		Display d= Display.getInstance();
		if (getFunzionamento()!=simulafunzionamento()) change();
		
		if(getFunzionamento()){
				d.showFunzionamento(true);	
				proxyAsincr.GeneraKeep(getIdrobot());
				return true;
				}
			else {
				d.showFunzionamento(false);
				return false;
			}
		}
	
	}
	
