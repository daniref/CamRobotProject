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
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Cliente")
public class Cliente implements Serializable {
	public Cliente() {
	}
	
	public Cliente(String id, String nome, String cognome, String username, String password, String recapito,
			Date data_di_nascita, String luogo_di_nascita) {
	
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.recapito = recapito;
		this.data_di_nascita = data_di_nascita;
		this.luogo_di_nascita = luogo_di_nascita;
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_CLIENTE_CONTRATTO) {
			return ORM_contratto;
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
	@GeneratedValue(generator="CLIENTE_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="CLIENTE_ID_GENERATOR", strategy="native")	
	private String id;
	
	@Column(name="nome", nullable=false, length=15)	
	private String nome;
	
	@Column(name="cognome", nullable=false, length=15)	
	private String cognome;
	
	@Column(name="username", nullable=false, unique=true, length=15)	
	private String username;
	
	@Column(name="password", nullable=false, length=15)	
	private String password;
	
	@Column(name="recapito", nullable=false, length=13)	
	private String recapito;
	
	@Column(name="`data di nascita`", nullable=false)	
	private Date data_di_nascita;
	
	@Column(name="`luogo di nascita`", nullable=false, length=20)	
	private String luogo_di_nascita;
	
	@OneToMany(mappedBy="utente", targetEntity=Contratto.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_contratto = new java.util.HashSet();
	
	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return id;
	}
	
	public String getORMID() {
		return getId();
	}
	
	public void setNome(String value) {
		this.nome = value;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setCognome(String value) {
		this.cognome = value;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setRecapito(String value) {
		this.recapito = value;
	}
	
	public String getRecapito() {
		return recapito;
	}
	
	public void setData_di_nascita(Date value) {
		this.data_di_nascita = value;
	}
	
	public Date getData_di_nascita() {
		return data_di_nascita;
	}
	
	public void setLuogo_di_nascita(String value) {
		this.luogo_di_nascita = value;
	}
	
	public String getLuogo_di_nascita() {
		return luogo_di_nascita;
	}
	
	private void setORM_Contratto(java.util.Set value) {
		this.ORM_contratto = value;
	}
	
	private java.util.Set getORM_Contratto() {
		return ORM_contratto;
	}
	

	
}
