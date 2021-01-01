package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CERTIFICATION database table.
 * 
 */
@Embeddable
public class CertificationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="BOAT_ID")
	private long boatId;

	@Column(name="TYPE")
	private String type;

	public CertificationPK() {
	}
	public long getBoatId() {
		return this.boatId;
	}
	public void setBoatId(long boatId) {
		this.boatId = boatId;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CertificationPK)) {
			return false;
		}
		CertificationPK castOther = (CertificationPK)other;
		return 
			(this.boatId == castOther.boatId)
			&& this.type.equals(castOther.type);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.boatId ^ (this.boatId >>> 32)));
		hash = hash * prime + this.type.hashCode();
		
		return hash;
	}
}