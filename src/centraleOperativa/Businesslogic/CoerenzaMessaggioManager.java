package centraleOperativa.Businesslogic;


//import centrale.entity.robot;
public class CoerenzaMessaggioManager {

	private String stato;
	private String condizione;
	private String idR;

	public String getIdR() {
		return idR;
		
	}

	public CoerenzaMessaggioManager(String idr) {
		this.idR=idr;
		this.stato=LeggiStato();
		this.condizione=LeggiCondizione();
	}
	
	public boolean VerificaCoerenzaDati() {
/*'d_robot deve appartenere effettivamente ad un robot del sistema che si trova nello stato REGISTRATO e nella condizione ON)*/

		return ((this.getStato()=="REGISTRATO") && (this.getCondizione()=="ON"));
		/*		robot_Entity r= new robot_Entity();
		r.getStatoById(idRobot)
	*/	
	}
	
	
	public String getStato() {
		return stato;
	}



	public String getCondizione() {
		return condizione;
	}



	public void setStato(String stato) {
		this.stato = stato;
	}



	public void setCondizione(String condizione) {
		this.condizione = condizione;
	}



	public String LeggiStato() {
/*		robot_Entity r= new robot_Entity();
		setStato(r.getStatoById(this.getIdR());
*/		
		return "REGISTRATO";
	}
	
	public String LeggiCondizione() {
/*		robot_Entity r= new robot_Entity();
		setCondizione(r.getCondizioneById(this.getIdR()));
*/
		return "ON";
	}
	
}
