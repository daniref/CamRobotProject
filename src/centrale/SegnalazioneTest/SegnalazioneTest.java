package centrale.SegnalazioneTest;

import java.util.Date;

public class SegnalazioneTest {

	
	private String id;
	private String stato;
	private float valoreAllarme;
	Date dataora;
	String idgestore;
	String idsensore;
	
	
	
	
	public SegnalazioneTest(String id, String stato, float valoreAllarme, Date dataora, String idgestore,
			String idsensore) {
		super();
		this.id = id;
		this.stato = stato;
		this.valoreAllarme = valoreAllarme;
		this.dataora = dataora;
		this.idgestore = idgestore;
		this.idsensore = idsensore;
	}
	@Override
	public String toString() {
		return "SegnalazioneTest [id=" + id + ", stato=" + stato + ", valoreAllarme=" + valoreAllarme + ", dataora="
				+ dataora + ", idgestore=" + idgestore + ", idsensore=" + idsensore + "]";
	}
	public String getId() {
		return id;
	}
	public String getStato() {
		return stato;
	}
	public float getValoreAllarme() {
		return valoreAllarme;
	}
	public Date getDataora() {
		return dataora;
	}
	public String getIdgestore() {
		return idgestore;
	}
	public String getIdsensore() {
		return idsensore;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public void setValoreAllarme(float valoreAllarme) {
		this.valoreAllarme = valoreAllarme;
	}
	public void setDataora(Date dataora) {
		this.dataora = dataora;
	}
	public void setIdgestore(String idgestore) {
		this.idgestore = idgestore;
	}
	public void setIdsensore(String idsensore) {
		this.idsensore = idsensore;
	}
	
}
