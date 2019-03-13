package centralinaRobot.sense;

public class SensoreInterface {
	
	private String id;
	private String tipo;
	private float misura;
	private SensoreSimulation ss; //thread che simula il sensore reale
	
	
public SensoreInterface(String ID,String T){
		this.id=ID;
		this.tipo=T;
		this.ss= new SensoreSimulation(T);//id,tipo,soglia,valore
		//creazione thread Demone per la lettura del valore
		ss.setDaemon(true);
		ss.start();
	}
	
	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}
	
	public String getTipo() {
		return tipo;
	}
	public String getTipoEsteso() {
		switch(tipo) {
			case "F":
				return "Rilevatore di fumo";
			case "T":
				return "Termometro";
			case "P":
				return "Sensore di prossimita'";
			default: 
				return "Sensore generico";
			}
	}

	public void setTipo(String t) {
		this.tipo = t;
	}	


	public float getMisura() {
		return misura;
	}

	public void setValue(float misura) {
		this.misura = misura;
	}
	 
	public float Leggi() {
		return ss.getValore();
		}
	
	public String toString() {
		return ("ID: "+getID()+", Tipo: "+getTipoEsteso());
	}

};
	
