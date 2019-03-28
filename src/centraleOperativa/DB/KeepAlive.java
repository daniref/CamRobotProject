package centraleOperativa.DB;
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
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="KeepAlive")
public class KeepAlive implements Serializable {
	public KeepAlive() {
	}
	
	public KeepAlive(String id,Date data,Time ora,Robot robot) {
		
		this.id=id;
		this.data=data;
		this.ora=ora;
		this.robot=robot;
		
	}
	
	@Column(name="id", nullable=false, unique=true, length=7)	
	@Id	
	@GeneratedValue(generator="KEEPALIVE_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="KEEPALIVE_ID_GENERATOR", strategy="native")	
	private String id;
	
	@Column(name="data", nullable=false)	
	private Date data;
	
	@Column(name="ora", nullable=false)	
	private Time ora;
	
	@OneToOne(optional=false, targetEntity=Robot.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="robotId", referencedColumnName="Id", nullable=false) }, foreignKey=@ForeignKey(name="FKKeepAlive563869"))	
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
	
	public void setData(Date value) {
		this.data = value;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setOra(Time value) {
		this.ora = value;
	}
	
	public Time getOra() {
		return ora;
	}
	
	public Robot getRobot() {
		return robot;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
