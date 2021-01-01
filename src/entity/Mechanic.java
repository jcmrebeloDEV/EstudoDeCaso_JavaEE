package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the MECHANIC database table.
 * 
 */
@Entity
public class Mechanic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long mat;

	private String category;

	private String name;

	//bi-directional one-to-one association to MechanicControlsheet
	@OneToOne(mappedBy="mechanic",  orphanRemoval=true, cascade={CascadeType.ALL})
	//@OneToOne(cascade=CascadeType.ALL)
	//@PrimaryKeyJoinColumn(name="mat")
	private MechanicControlsheet mechanicControlsheet;
	
	@ManyToMany(mappedBy="mechanics")
	private List<Boat> boats;

	public Mechanic() {
	}

	public long getMat() {
		return this.mat;
	}

	public void setMat(long mat) {
		this.mat = mat;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MechanicControlsheet getMechanicControlsheet() {
		return this.mechanicControlsheet;
	}

	public void setMechanicControlsheet(MechanicControlsheet mechanicControlsheet) {
		this.mechanicControlsheet = mechanicControlsheet;
	}

	public List<Boat> getBoats() {
		return boats;
	}

	public void setBoats(List<Boat> boats) {
		this.boats = boats;
	}

}