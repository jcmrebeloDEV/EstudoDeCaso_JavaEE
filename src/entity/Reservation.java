package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RESERVATION database table.
 * 
 */
@Entity
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReservationPK id;

	//uni-directional many-to-one association to Boat
	@MapsId("bid")
	@ManyToOne
	@JoinColumn(name="BID")
	private Boat boat;

	//uni-directional many-to-one association to Sailor
	@MapsId("sid")
	@ManyToOne
	@JoinColumn(name="SID")
	private Sailor sailor;

	public Reservation() {
	}

	public ReservationPK getId() {
		return this.id;
	}

	public void setId(ReservationPK id) {
		this.id = id;
	}

	public Boat getBoat() {
		return this.boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	public Sailor getSailor() {
		return this.sailor;
	}

	public void setSailor(Sailor sailor) {
		this.sailor = sailor;
	}

}