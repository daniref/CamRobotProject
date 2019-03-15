package centralinaRobot.Compute;



import javax.jms.JMSException;

import centralinaRobot.Control.Display;
import centralinaRobot.proxyComunicazioneAsincrona.CentraleOperativaProxy;

public class FunzionamentoManager {

	private boolean funz; 

	private final static CentraleOperativaProxy proxyAsincr= new CentraleOperativaProxy();

	
	public FunzionamentoManager() {
		this.funz=true;
	}
	

	public void change(){
		 this.funz=!funz;
		 }
		
	
	public boolean getFunzionamento(){
			return funz;
	}
	
	public void CheckFunzionamento(String id_robot,Display d) throws JMSException{
		
			if(getFunzionamento()){
				d.showFunzionamento(true);
				System.out.println("[DEBUG][MANAGERFUNZIONAMENTO](verifica) - tutto appost 1)richiama proxy");
				System.out.println("[DEBUG][MANAGERFUNZIONAMENTO](verifica) - invia parametri al proxy:");
				proxyAsincr.GeneraKeep(id_robot);
				System.out.println("[DEBUG][MANAGERFUNZIONAMENTO](verifica) - keep inviato 2)ritorno al manager");
				}
			else d.showFunzionamento(false);
		}
	
	}
	
