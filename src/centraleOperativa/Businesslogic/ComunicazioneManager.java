package centraleOperativa.Businesslogic;

public class ComunicazioneManager {

	String msg;
	String idrobot;
	public ComunicazioneManager(String m, String idr) { 
		this.msg=m;
		String idrobot=idr;
		}
	
	public void ContattaProprietario()	{
//		Contratto c= Contratto.getIstance(); ???????????????
//		String idcliente=c.getUtente(idrobot);
							//???
//		String recapito=c.getUtente(idrobot);
		//A PARTRE DAL ID ROBOT, vai in contratto, prendi il cliente e restituiscimi il suo numero di telefono
		
		System.out.println("E' stato inviato un SMS al cliente!");
	}
	
}
