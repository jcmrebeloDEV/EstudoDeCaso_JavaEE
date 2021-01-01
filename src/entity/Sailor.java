package entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SAILOR database table.
 * 
 */
@Entity
public class Sailor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private double rating;

	private String sname;

	//bi-directional many-to-one association to Card
	@OneToMany(mappedBy="sailor", orphanRemoval=true, cascade={CascadeType.ALL})
	private List<Card> cards;

	public Sailor() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public double getRating() {
		return this.rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public List<Card> getCards() {
		
		return this.cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public Card addCard(Card card) {
		if (this.cards==null)  this.setCards(new ArrayList<Card>());
		this.getCards().add(card);
		card.setSailor(this);

		return card;
	}

	public Card removeCard(Card card) {
		this.getCards().remove(card);
		card.setSailor(null);

		return card;
	}

}