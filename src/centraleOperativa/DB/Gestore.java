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
@Table(name="Gestore")
public class Gestore implements Serializable {

	public Gestore() {

	}
	
	public Gestore(String id, String nome, String recapito) {
		this.id=id;
		this.nome=nome;
		this.recapito=recapito;
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_GESTORE_SEGNALAZIONE) {
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
	
	@Column(name="id", nullable=false, unique=true, length=7)	
	@Id	
	@GeneratedValue(generator="GESTORE_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="GESTORE_ID_GENERATOR", strategy="native")	
	private String id;
	
	@Column(name="nome", nullable=false, length=25)	
	private String nome;
	
	@Column(name="recapito", nullable=false, length=13)	
	private String recapito;
	
	@OneToMany(mappedBy="gestore", targetEntity=Segnalazione.class)	
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
	
	public void setNome(String value) {
		this.nome = value;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setRecapito(String value) {
		this.recapito = value;
	}
	
	public String getRecapito() {
		return recapito;
	}
	
	private void setORM_Segnalazione(java.util.Set value) {
		this.ORM_segnalazione = value;
	}
	
	private java.util.Set getORM_Segnalazione() {
		return ORM_segnalazione;
	}
	
	
}
