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
@Table(name="Sensore")
public class Sensore implements Serializable {
	public Sensore() {
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_SENSORE_ROBOT) {
			this.robot = (Robot) owner;
		}
		
		else if (key == ORMConstants.KEY_SENSORE_SEGNALAZIONE_DI_ALLARME) {
			this.segnalazione_di_allarme = (Segnalazione_di_allarme) owner;
		}
	}
	
	@Transient	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	@Column(name="Id", nullable=false, unique=true, length=7)	
	@Id	
	@GeneratedValue(generator="SENSORE_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="SENSORE_ID_GENERATOR", strategy="native")	
	private String id;
	
	@Column(name="soglia", nullable=false, length=5)	
	private float soglia;
	
	@Column(name="tipologia", nullable=false, length=20)	
	private String tipologia;
	
	@ManyToOne(targetEntity=Robot.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="robotId", referencedColumnName="Id", nullable=false) }, foreignKey=@ForeignKey(name="FKSensore164434"))	
	private Robot robot;
	
	@OneToOne(mappedBy="sensore", targetEntity=Segnalazione_di_allarme.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	private Segnalazione_di_allarme segnalazione_di_allarme;
	
	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return id;
	}
	
	public String getORMID() {
		return getId();
	}
	
	public void setSoglia(float value) {
		this.soglia = value;
	}
	
	public float getSoglia() {
		return soglia;
	}
	
	public void setTipologia(String value) {
		this.tipologia = value;
	}
	
	public String getTipologia() {
		return tipologia;
	}
	

	
	public Robot getRobot() {
		return robot;
	}
	
	/**
	 * This method is for internal use only.
	 */
	public void setORM_Robot(Robot value) {
		this.robot = value;
	}
	
	private Robot getORM_Robot() {
		return robot;
	}
	
	public void setSegnalazione_di_allarme(Segnalazione_di_allarme value) {
		if (this.segnalazione_di_allarme != value) {
			Segnalazione_di_allarme lsegnalazione_di_allarme = this.segnalazione_di_allarme;
			this.segnalazione_di_allarme = value;
			if (value != null) {
				segnalazione_di_allarme.setSensore(this);
			}
			if (lsegnalazione_di_allarme != null && lsegnalazione_di_allarme.getSensore() == this) {
				lsegnalazione_di_allarme.setSensore(null);
			}
		}
	}
	
	public Segnalazione_di_allarme getSegnalazione_di_allarme() {
		return segnalazione_di_allarme;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
