/*
 * dao 'container managed transaction'
 * 
 */
package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Boat;
import entity.Mechanic;
import entity.Sailor;

/**
 * Session Bean implementation class DaoCmtEJB
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DaoCmtEJB {

	@PersistenceContext
	EntityManager em;

	public DaoCmtEJB() {
		// TODO Auto-generated constructor stub
	}

	public Sailor findSailor(long id) {
		Sailor s = null;
		s = em.find(Sailor.class, id);
		return s;

	}

	public void removeSailor(Sailor s) {

		em.remove(s);

	}

	public void removeSailor(long id) {

		Sailor s = em.find(Sailor.class, id);

		if (s != null)
			em.remove(s);

	}

	public void createSailor(Sailor s) {

		em.persist(s);
	}

	public void updateSailor(Sailor s) {

		em.merge(s);

	}

	public void removeSailCards(Long sailorId) {

		Sailor s = this.findSailor(sailorId);

		if (s != null) {

			s.getCards().clear();
			/*
			 * limpa so da primeira vez (?). Insercoes feitas depois disso no
			 * sqldeveloper nao sao removidas, so com o restart do servidor
			 */

		}

		this.updateSailor(s);

	}

	@SuppressWarnings("unchecked")
	public List<Sailor> listSailorsPag(String query, int start, int max) {
		// em.createQuery("SELECT s FROM Sailor s WHERE s.id = :x").setParameter("x",
		// 21).setMaxResults(10).getResultList();
		List<Sailor> sl = null;

		try {

			sl = em.createQuery(query).setFirstResult(start).setMaxResults(max)
					.getResultList();

		} catch (Exception e) {

			System.out.println(e.toString());
		}

		return sl;
	}

	@SuppressWarnings("unchecked")
	public List<Sailor> listSailors(String query) {

		List<Sailor> sl = null;

		try {

			sl = em.createQuery(query).getResultList();

		} catch (Exception e) {

			System.out.println(e.toString());
		}

		return sl;

	}

	public Boat findBoat(long id) {
		Boat boat = null;
		boat = em.find(Boat.class, id);
		return boat;
	}

	public void createBoat(Boat b) {

		em.persist(b);

	}

	public void updateBoat(Boat b) {

		em.merge(b);

	}

	public void removeBoat(Boat b) {
		this.removeBoat(b.getId());
	}

	public void removeBoat(long id) {

		Boat b = em.find(Boat.class, id);

		if (b != null)
			em.remove(b);

	}

	public Mechanic findMechanic(long id) {
		Mechanic m = null;
		m = em.find(Mechanic.class, id);
		return m;
	}

	public void createMechanic(Mechanic m) {

		em.persist(m);

	}

	public void updateMechanic(Mechanic m) {

		em.merge(m);

	}

	public void removeMechanic(Mechanic m) {

		this.removeMechanic(m.getMat());

	}

	public void removeMechanic(long id) {

		Mechanic m = em.find(Mechanic.class, id);

		if (m != null)
			em.remove(m);

	}

	public void subTeste() {

		Boat b = null;
		b = em.find(Boat.class, (long) 2);

		Mechanic m = em.find(Mechanic.class, (long) 1003);

		b.getMechanics().add(m);

		em.merge(b);

		/*
		 * Boat b =null; b = em.find(Boat.class, (long)2);
		 * 
		 * Mechanic m = em.find(Mechanic.class, (long)1003);
		 * 
		 * b.getMechanics().remove(m);
		 * 
		 * em.merge(b);
		 */

		/*
		 * Iterator<Certification> ic =
		 * em.createQuery("select c from Certification c where c.boat.id=2"
		 * ).getResultList().iterator(); while(ic.hasNext()){ Certification c =
		 * ic.next(); if (c.getId().getType().contains("V")){ //c.setBoat(null);
		 * em.remove(c); }
		 * 
		 * }
		 */

		/*
		 * Certification c = new Certification();
		 * 
		 * CertificationPK cpk = new CertificationPK(); cpk.setType("C");
		 * c.setId(cpk);
		 * 
		 * c.setBoat(b);
		 * 
		 * em.merge(c);
		 */

		/*
		 * Outra maneira de adiconar certificacoes:
		 * 
		 * Certification c2 = new Certification();
		 * 
		 * CertificationPK cpk2 = new CertificationPK(); cpk2.setType("B");
		 * c2.setId(cpk2); c2.setBoat(b);
		 * 
		 * b.getCertifications().add(c2);
		 * 
		 * em.merge(b);
		 */

		/*
		 * 
		 * CertificationPK cpk2 = new CertificationPK(); cpk2.setType("Y");
		 * cpk2.setBoatId((long)2);
		 * 
		 * 
		 * Certification c2 = em.find(Certification.class,cpk2);
		 * 
		 * b.getCertifications().remove(c2);
		 * 
		 * 
		 * em.merge(b);
		 */

	}

	public void teste() {

		// Boat b = this.findBoat(2);

		/*
		 * Certification c2 = new Certification();
		 * 
		 * CertificationPK cpk2 = new CertificationPK(); cpk2.setType("D");
		 * c2.setId(cpk2); c2.setBoat(b);
		 * 
		 * b.getCertifications().add(c2);
		 */

		// b.getMechanics().get(0).setCategory("Ovr");

		// b.getCertifications().get(0).setExpires(new Date("12/12/2012"));

		// if (b.getCertifications().size()>0) b.getCertifications().remove(0);

		// this.updateBoat(b);

		/*
		 * Mechanic m = new Mechanic(); m.setMat(1004); m.setName("Gerard");
		 * m.setCategory("Ovr");
		 * 
		 * MechanicControlsheet ms;
		 * 
		 * ms = new MechanicControlsheet(); ms.setObservations("none");
		 * ms.setMechanic(m); m.setMechanicControlsheet(ms);
		 * 
		 * this.createMechanic(m);
		 */

		this.removeMechanic(1004);

		// this.updateMechanic(m);
		// System.out.println(m.getMechanicControlsheet().getObservations());

	}

}
