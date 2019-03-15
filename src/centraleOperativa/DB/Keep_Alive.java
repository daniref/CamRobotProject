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
@Table(name="`Keep Alive`")
public class Keep_Alive implements Serializable {
	public Keep_Alive() {
	}
	
	@Column(name="id", nullable=false, unique=true, length=7)	
	@Id	
	@GeneratedValue(generator="KEEP_ALIVE_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="KEEP_ALIVE_ID_GENERATOR", strategy="native")	
	private String id;
	
	@Column(name="data", nullable=false)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date data;
	
	@Column(name="ora", nullable=false)	
	private java.sql.Time ora;
	
	@OneToOne(optional=false, targetEntity=Robot.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="robotId", referencedColumnName="Id", nullable=false) }, foreignKey=@ForeignKey(name="FKKeep Alive951800"))	
	private Robot robot;
	
	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return id;
	}
	
	public String getORMID() {
		return getId();
	}
	
	public void setData(java.util.Date value) {
		this.data = value;
	}
	
	public java.util.Date getData() {
		return data;
	}
	
	public void setOra(java.sql.Time value) {
		this.ora = value;
	}
	
	public java.sql.Time getOra() {
		return ora;
	}
	

	
	public Robot getRobot() {
		return robot;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
