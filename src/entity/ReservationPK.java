package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RESERVATION database table.
 * 
 */
@Embeddable
public class ReservationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private long bid;

	private long sid;

	@Temporal(TemporalType.DATE)
	private java.util.Date rdate;

	public ReservationPK() {
	}
	public long getBid() {
		return this.bid;
	}
	public void setBid(long bid) {
		this.bid = bid;
	}
	public long getSid() {
		return this.sid;
	}
	public void setSid(long sid) {
		this.sid = sid;
	}
	public java.util.Date getRdate() {
		return this.rdate;
	}
	public void setRdate(java.util.Date rdate) {
		this.rdate = rdate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReservationPK)) {
			return false;
		}
		ReservationPK castOther = (ReservationPK)other;
		return 
			(this.bid == castOther.bid)
			&& (this.sid == castOther.sid)
			&& this.rdate.equals(castOther.rdate);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.bid ^ (this.bid >>> 32)));
		hash = hash * prime + ((int) (this.sid ^ (this.sid >>> 32)));
		hash = hash * prime + this.rdate.hashCode();
		
		return hash;
	}
}