package centraleOperativa.ProxyComunicazioneAsincrona;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;

import centraleOperativa.Control.*;

public class TimerProxy extends Thread{
	private int tipo;
	MessageConsumer consumer;
	
	
	public TimerProxy(int t, MessageConsumer c){
		this.tipo=t;
		this.consumer=c;
		System.out.println("[DEBUG]setup Timer Proxy completato");
	}
	
	public void run(){
		if(tipo==0) {
		   	try {
				//La funzionalità 'GestioneSegnalazioneAllarme()' viene invovata ciclicamente per il consumo  
				//dei messaggi d'allarme dalla coda delle segnalazioni e la loro gestione
				while (true) {//!interrupted()){
				//	System.out.println("[DEBUG][thread allarme](1) richiama ricevisegnalazione");
					RiceviSegnalazione(consumer);
				//	System.out.println("[DEBUG][thread allarme](2) ritorno ricevisegnalazione");
					Thread.sleep(2300);

		   			}
				}
		   	catch (JMSException | InterruptedException | ParseException e) {
					e.printStackTrace();
		   			}
				
			}
		
		else { //tipo ==1(keep)
			try {
				while (true) {//!interrupted()){
			//		System.out.println("[DEBUG][thread keep](1) richiama ricevikeep");
					RiceviKeep(consumer);
			//		System.out.println("[DEBUG][thread keep](2) ritorno ricevikeep");
					Thread.sleep(5500);
					}
				}	
			 catch (JMSException | InterruptedException | ParseException e) {
					e.printStackTrace();
					}
			 }
		}
	
	

	//metodo per ricevere, stampare, e riconoscere e gestire i dati relativi ad un messaggio di SEGNALAZIONE D'ALLARME ricevuto!
	public synchronized void RiceviSegnalazione(MessageConsumer c) throws JMSException, ParseException{		
    	CentraleOperativaController coc=CentraleOperativaController.getIstance();
		Message message = c.receive();
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
	        System.out.println("[ALLARME]\n"+textMessage.getText()+"\n");
	        ///ESTRAZIONE DELLE INFO DAL MESSAGGIO KEEP (idrobot;idsensore;valore;dataora.)

	           String [] arrOfStr= new String[4];
			   arrOfStr = (textMessage.getText()).split(";");
    		   String idrobot=arrOfStr[0];
    		   String idsensore=arrOfStr[1];
    		   float number = Float.valueOf(arrOfStr[2]);
    		   Date dataora=new SimpleDateFormat("dd:MM:yyyy-HH:mm:ss").parse(arrOfStr[3]); 
			   coc.gestisciSegnalazione(idrobot, idsensore,number, dataora);
		}

		//Stampa del messaggio
	}

	//funzione per ricevere e stampare i dati relativi ad un messaggio di KEEP ALIVE ricevuto!


	
	public synchronized void RiceviKeep(MessageConsumer c) throws JMSException, ParseException{
	//	System.out.println("[debug]RICEVI KEEP - function");
    //	System.out.println("[debug]RICEVI KEEP - function - Consumer invocato : "+c);
    	CentraleOperativaController coc=CentraleOperativaController.getIstance();
		Message message = c.receive();
	//	System.out.println(""+message);
		//Stampa del messaggio
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
	        System.out.println("[KEEP]\n"+textMessage.getText()+"\n");
	        
	        
	        ///ESTRAZIONE DELLE INFO DAL MESSAGGIO KEEP (idrobot;dataora.)

	        String [] arrOfStr= new String[2];
			arrOfStr = (textMessage.getText()).split(";");
 		   	String idrobot=arrOfStr[0];
 		    Date dataora=new SimpleDateFormat("dd:MM:yyyy-HH:mm:ss").parse(arrOfStr[1]); 
			coc.gestisciKeep(idrobot,dataora);
	    }
	}
	
}
