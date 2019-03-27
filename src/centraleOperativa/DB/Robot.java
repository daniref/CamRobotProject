/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: 
 * License Type: Evaluation
 */
package centraleOperativa.DB;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Robot")
public class Robot implements Serializable {
	
	public Robot() {
	}
	
	public Robot(String id, String stato, String condizione, String funzionamento, String indirizzo, String areaId) {
		this.id = id;
		this.stato = stato;
		this.condizione = condizione;
		this.funzionamento = funzionamento;
		this.indirizzo = indirizzo;
		this.areaId = areaId;
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_ROBOT_SENSORE) {
			return ORM_sensore;
		}
		else if (key == ORMConstants.KEY_ROBOT_SEGNALAZIONE) {
			return ORM_segnalazione;
		}
		
		return null;
	}
	
	@Transient	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	@Column(name="Id", nullable=false, unique=true, length=7)	
	@Id	
	@GeneratedValue(generator="ROBOT_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="ROBOT_ID_GENERATOR", strategy="native")	
	private String id;
	
	@Column(name="stato", nullable=false, length=15)	
	private String stato;
	
	@Column(name="condizione", nullable=false, length=3)	
	private String condizione;
	
	@Column(name="funzionamento", nullable=false, length=5)	
	private String funzionamento = "ok";
	
	@Column(name="indirizzo", nullable=false, length=80)	
	private String indirizzo;
	
	@Column(name="areaId", nullable=false, length=7)	
	private String areaId;
	
	@OneToOne(mappedBy="robot", targetEntity=Contratto.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	private Contratto contratto;
	
	@OneToMany(mappedBy="robot", targetEntity=Sensore.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_sensore = new java.util.HashSet();
	
	@OneToOne(mappedBy="robot", targetEntity=KeepAlive.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	private KeepAlive keepAlive;
	
	@OneToMany(mappedBy="robot", targetEntity=Segnalazione.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_segnalazione = new java.util.HashSet();
	
	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return id;
	}
	
	public String getORMID() {
		return getId();
	}
	
	public void setStato(String value) {
		this.stato = value;
	}
	
	public String getStato() {
		return stato;
	}
	
	public void setCondizione(String value) {
		this.condizione = value;
	}
	
	public String getCondizione() {
		return condizione;
	}
	
	public void setFunzionamento(String value) {
		this.funzionamento = value;
	}
	
	public String getFunzionamento() {
		return funzionamento;
	}
	
	public void setIndirizzo(String value) {
		this.indirizzo = value;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}
	
	public void setAreaId(String value) {
		this.areaId = value;
	}
	
	public String getAreaId() {
		return areaId;
	}
	
	public void setContratto(Contratto value) {
		if (this.contratto != value) {
			Contratto lcontratto = this.contratto;
			this.contratto = value;
			if (value != null) {
				contratto.setRobot(this);
			}
			if (lcontratto != null && lcontratto.getRobot() == this) {
				lcontratto.setRobot(null);
			}
		}
	}
	
	public Contratto getContratto() {
		return contratto;
	}
	
	private void setORM_Sensore(java.util.Set value) {
		this.ORM_sensore = value;
	}
	
	private java.util.Set getORM_Sensore() {
		return ORM_sensore;
	}
	
	public void setKeepAlive(KeepAlive value) {
		if (this.keepAlive != value) {
			KeepAlive lkeepAlive = this.keepAlive;
			this.keepAlive = value;
			if (value != null) {
				keepAlive.setRobot(this);
			}
			if (lkeepAlive != null && lkeepAlive.getRobot() == this) {
				lkeepAlive.setRobot(null);
			}
		}
	}
	
	public KeepAlive getKeepAlive() {
		return keepAlive;
	}
	
	private void setORM_Segnalazione(java.util.Set value) {
		this.ORM_segnalazione = value;
	}
	
	private java.util.Set getORM_Segnalazione() {
		return ORM_segnalazione;
	}
		
}
