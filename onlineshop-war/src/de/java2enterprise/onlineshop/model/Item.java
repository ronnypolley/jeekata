package de.java2enterprise.onlineshop.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Item implements Serializable {

	private static final long serialVersionUID = 8651209098945069549L;

	@Id
	private Long id;

	@Basic
	private String title;

	@Basic
	private String description;

	@Basic
	private Double price;

	@Lob
	private byte[] foto;

	@Column(name = "buyer_id")
	private Long buyerId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date traded;

	@ManyToOne
	@JoinColumn(name = "seller_id")
	private Customer seller;

	public Item() {
	}

	public Item(String title, String description, Double price, Customer seller) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.seller = seller;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public Date getTraded() {
		return traded;
	}

	public void setTraded(Date traded) {
		this.traded = traded;
	}

	public Customer getSeller() {
		return seller;
	}

	public void setSeller(Customer seller) {
		this.seller = seller;
	}

	@Override
	public String toString() {
		return "Item [getId()=" + getId() + ", getTitle()=" + getTitle()
				+ ", getDescription()=" + getDescription() + ", getPrice()="
				+ getPrice() + ", getSellerId()=" + getSeller().getId()
				+ ", getBuyerId()=" + getBuyerId() + ", getTraded()="
				+ getTraded() + "]";
	}

}
