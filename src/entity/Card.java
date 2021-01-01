package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CARD database table.
 * 
 */
@Entity
public class Card implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id", insertable=false, nullable=true, updatable=true)
	private long id;

	private String tipo;

	//bi-directional many-to-one association to Sailor
	@ManyToOne
	@JoinColumn(name="IDSAILOR")
	private Sailor sailor;

	public Card() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Sailor getSailor() {
		return this.sailor;
	}

	public void setSailor(Sailor sailor) {
		this.sailor = sailor;
	}

}