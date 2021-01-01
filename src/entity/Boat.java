package entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the BOAT database table.
 * 
 */
@Entity
public class Boat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String color;

	private String name;

	//bi-directional many-to-one association to Certification
	@OneToMany(mappedBy="boat", orphanRemoval=true, cascade={CascadeType.ALL})
	private List<Certification> certifications;

	//bi-directional many-to-many association to Mechanic
	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name="BOAT_MECHANIC", joinColumns=@JoinColumn(name="BOAT_ID", referencedColumnName="id"),inverseJoinColumns=@JoinColumn(name="MECH_ID", referencedColumnName="mat"))
	private List<Mechanic> mechanics;

	public Boat() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Certification> getCertifications() {
		return this.certifications;
	}

	public void setCertifications(List<Certification> certifications) {
		this.certifications = certifications;
	}

	public Certification addCertification(Certification certification) {
		getCertifications().add(certification);
		certification.setBoat(this);

		return certification;
	}

	public Certification removeCertification(Certification certification) {
		getCertifications().remove(certification);
		certification.setBoat(null);

		return certification;
	}

	public List<Mechanic> getMechanics() {
		return this.mechanics;
	}

	public void setMechanics(List<Mechanic> mechanics) {
		this.mechanics = mechanics;
	}

}