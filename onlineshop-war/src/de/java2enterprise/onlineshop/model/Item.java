package de.java2enterprise.onlineshop.model;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable {

	private static final long serialVersionUID = 8651209098945069549L;

	private Long id;
	private String title;
	private String description;
	private Double price;
	private byte[] foto;
	private Long sellerId;
	private Long buyerId;
	private Date traded;

	public Item(String title, String description, Double price, Long sellerId) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.sellerId = sellerId;
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

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
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

	@Override
	public String toString() {
		return "Item [getId()=" + getId() + ", getTitle()=" + getTitle()
				+ ", getDescription()=" + getDescription() + ", getPrice()="
				+ getPrice() + ", getSellerId()=" + getSellerId()
				+ ", getBuyerId()=" + getBuyerId() + ", getTraded()="
				+ getTraded() + "]";
	}

}
