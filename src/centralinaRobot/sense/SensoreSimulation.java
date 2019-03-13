package centralinaRobot.sense;

import java.util.Random;

public class SensoreSimulation extends Thread{

	//F=Sensore di Fumo, P=Prossimità, T=Temperatura
	private float valore;

	private static int INIT; //15 gradi iniziali
	private static int RANGE;	
	private static int ADJ=RANGE;
	
	static Random ran = new Random();
	

	public SensoreSimulation(String T){
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
	
	@Override
	public void run(){
	do {
	  try{
		//ogni tot secondi il valore è aggiornato
		Thread.sleep(6000);
		setValore((float)Math.random()*(RANGE)-ADJ+valore);	
		
	//	setValore(ran.nextInt(RANGE)-ADJ+valore);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}while(true);
	}

}
