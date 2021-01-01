package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the MECHANIC_CONTROLSHEET database table.
 * 
 */
@Entity
@Table(name="MECHANIC_CONTROLSHEET")
public class MechanicControlsheet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idmechanic;

	@Lob
	private String observations;

	@Column(name="\"TIME\"")
	private Timestamp time;


	//bi-directional one-to-one association to Mechanic
	//@OneToOne(mappedBy="mechanicControlsheet")
	@MapsId
	@OneToOne
	@JoinColumn(name="idmechanic")
	private Mechanic mechanic;

	public MechanicControlsheet() {
	}

	public long getIdmechanic() {
		return this.idmechanic;
	}

	public void setIdmechanic(long idmechanic) {
		this.idmechanic = idmechanic;
	}

	public String getObservations() {
		return this.observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	
	public Mechanic getMechanic() {
		return this.mechanic;
	}

	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

}