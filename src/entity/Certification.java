package entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the CERTIFICATION database table.
 * 
 */
@Entity
public class Certification implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CertificationPK id;

	@Temporal(TemporalType.DATE)
	private Date expires;

	//bi-directional many-to-one association to Boat
	@MapsId("boatId")
	@ManyToOne
	private Boat boat;

	public Certification() {
	}

	public CertificationPK getId() {
		return this.id;
	}

	public void setId(CertificationPK id) {
		this.id = id;
	}

	public Date getExpires() {
		return this.expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}

	public Boat getBoat() {
		return this.boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
	}

}