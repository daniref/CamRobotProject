package centralinaRobot.Sense;

import java.util.Random;

public class SensoreSimulation extends Thread{

	//F=Sensore di Fumo, P=Prossimità, T=Temperatura
	private float valore;

	private static int INIT; //15 gradi iniziali
	private static int RANGE;	
	private static int ADJ=RANGE;
	private boolean pause;
	static Random ran = new Random();
	

	public SensoreSimulation(String T){
		pause=false;
		switch(T) {
			case "F": //il rilevatore di FUMO restituisce la concentrazione di monossido di carbonio presente nell’aria (ppm)
					  //http://www.las.provincia.venezia.it/discscien/chimica/iperqualitaria/inquinanti/monossidocarbonio1.htm
				INIT=50;
				RANGE=10;
				break;
			case "T": //il sensore di Temperatura è un termometro che restituisce la temperatura rilevata in gradi centigradi.
				INIT=20;
				RANGE=3;
				break;
			case "P": //il sensore di Prossimità se si supera 50 indica true altrimenti false
				INIT=45;
				RANGE=5;
				break;

			default: 
				INIT=12;
				RANGE=3;
				break;
									
		}
		ADJ=RANGE/3;
		this.valore=INIT;
	}
	
	public float getValore(){
		return valore;
	}
	
	public void setValore(float v){
		this.valore=v;
	}
	
	
	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	@Override
	public void run(){
		  try{				  
			do {
			//System.out.println("[Sensore Simulation]RUN...");
			Thread.sleep(2000);
				while(!pause) {
				//	System.out.println("[Sensore Simulation]Aggiornamento valore...");
					//se non è stato messo in pausa, ogni 6 secondi il valore è aggiornato
					Thread.sleep(6000);
					setValore((float)Math.random()*(RANGE)-ADJ+valore);	
				  	};
			}while(true);
					  	
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
	}
}
