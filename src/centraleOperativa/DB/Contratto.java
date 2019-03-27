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
import java.sql.Date;

import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Contratto")
public class Contratto implements Serializable {

	public Contratto() {
	}
	
	public Contratto(String id, Cliente utente, Robot robot, Date data_di_inizio, Date data_di_scadenza,
			float canone) {
		this.id = id;
		this.utente = utente;
		this.robot = robot;
		this.data_di_inizio = data_di_inizio;
		this.data_di_scadenza = data_di_scadenza;
		this.canone = canone;
	}



	@Column(name="Id", nullable=false, unique=true, length=7)	
	@Id	
	@GeneratedValue(generator="CONTRATTO_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="CONTRATTO_ID_GENERATOR", strategy="native")	
	private String id;
	
	@ManyToOne(targetEntity=Cliente.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="utenteId", referencedColumnName="Id", nullable=false) }, foreignKey=@ForeignKey(name="FKContratto32992"))	
	private Cliente utente;
	
	@OneToOne(optional=false, targetEntity=Robot.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="robotId", referencedColumnName="Id", nullable=false) }, foreignKey=@ForeignKey(name="FKContratto387871"))	
	private Robot robot;
	
	@Column(name="`data di inizio`", nullable=false)	
	private java.sql.Date data_di_inizio;
	
	@Column(name="`data di scadenza`", nullable=false)	
	private java.sql.Date data_di_scadenza;
	
	@Column(name="canone", nullable=false)	
	private float canone;
	
	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return id;
	}
	
	public String getORMID() {
		return getId();
	}
	
	public void setData_di_inizio(java.sql.Date value) {
		this.data_di_inizio = value;
	}
	
	public java.sql.Date getData_di_inizio() {
		return data_di_inizio;
	}
	
	public void setData_di_scadenza(java.sql.Date value) {
		this.data_di_scadenza = value;
	}
	
	public java.sql.Date getData_di_scadenza() {
		return data_di_scadenza;
	}
	
	public void setCanone(float value) {
		this.canone = value;
	}
	
	public float getCanone() {
		return canone;
	}
	
	public void setRobot(Robot value) {
		if (this.robot != value) {
			Robot lrobot = this.robot;
			this.robot = value;
			if (value != null) {
				robot.setContratto(this);
			}
			if (lrobot != null && lrobot.getContratto() == this) {
				lrobot.setContratto(null);
			}
		}
	}
	
	public Robot getRobot() {
		return robot;
	}
	

	public Cliente getUtente() {
		return utente;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Utente(Cliente value) {
		this.utente = value;
	}
	
	private Cliente getORM_Utente() {
		return utente;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
