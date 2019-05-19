package centraleOperativa.Boundary;

public class ServizioDiComunicazioneInterface {

public ServizioDiComunicazioneInterface() {}
	
	public static void contattaProprietario(String messaggio, String recapito) {
			System.out.println("[SERVIZIO COMUNICAZIONE ESTERNO] si invia <"+messaggio+"> al numero "+recapito);;
		}
}
