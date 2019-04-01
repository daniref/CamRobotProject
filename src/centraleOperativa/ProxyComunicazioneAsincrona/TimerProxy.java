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
	double tempo;
	MessageConsumer consumer;
    private volatile boolean exit = false;

	
	public TimerProxy(int t, MessageConsumer c,double secondi){
		this.tipo=t;
		this.consumer=c;
		this.tempo=secondi;
	}
	public TimerProxy(int t,double minuti){
		this.tipo=t;
		tempo= minuti*60;
	}
	
	public void run(){
		switch(tipo) {
			case 0: { //se tipo==0 -> monitoragio
				   	try {
						while (!exit) {
							System.out.println("[Thread Scheduling segnalazioni d'allarme");					
							RiceviSegnalazione(consumer);
							Thread.sleep((long)(tempo*1000));
				   			}
						System.out.println("Thread che schedula le segnalazioni d'allarme chiuso");
						}
				   	catch (JMSException | InterruptedException | ParseException e) {
						e.printStackTrace();
				   		}
						
					}
		
			case 1: { //tipo ==1(keep)
					try {
						while (!exit) {
							System.out.println("[Thread Scheduling keep alive");					
							RiceviKeep(consumer);
							Thread.sleep((long)(tempo*1000));
							}
						System.out.println("Thread che schedula i keep alive chiuso");
						}	
					 catch (JMSException | InterruptedException | ParseException e) {
						e.printStackTrace();
						}
					 }
			default:{ //tipo diverso da 1 e 2...
					try {
						while (!exit) {
							System.out.println("[Thread]Controllo malfunzionamento");					
							ControllaMalfunzionamenti((int)(this.tempo));
							Thread.sleep((long)(tempo*1000));
							}
						System.out.println("Thread che verifica i malfunzionamenti chiuso");
						}	
					 catch (InterruptedException e) {
						e.printStackTrace();
					 	}
					}
			}
	}
	 public void stoppa(){
	        exit = true;
	    }

	//metodo per ricevere, stampare, riconoscere e gestire i dati relativi ad un messaggio di SEGNALAZIONE D'ALLARME ricevuto!
	public  void RiceviSegnalazione(MessageConsumer c) throws JMSException, ParseException{		
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
		}

	//metodo necessario per ricevere e stampare i dati relativi ad un messaggio di KEEP ALIVE ricevuto
	public void RiceviKeep(MessageConsumer c) throws JMSException, ParseException{
    	CentraleOperativaController coc=CentraleOperativaController.getIstance();
		Message message = c.receive();
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
	
	//metodo necessario per controllare eventuali malunzionamenti da robot ON che non inviano più dei Keep Alive
	//dunque tale malfunzionamento deve essere comunicato al proprietario
	public void ControllaMalfunzionamenti(int minuti){
		Date d= new Date();
		CentraleOperativaController coc=CentraleOperativaController.getIstance();
		coc.gestisciMalfunzionamenti(d,minuti);
	}
	
	
}
