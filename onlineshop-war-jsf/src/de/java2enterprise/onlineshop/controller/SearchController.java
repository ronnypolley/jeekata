package de.java2enterprise.onlineshop.controller;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

import de.java2enterprise.onlineshop.model.Item;

@Named
@RequestScoped
public class SearchController implements Serializable {

	@PersistenceUnit
	private EntityManagerFactory emf;

	@Resource
	private UserTransaction ut;

	private List<Item> items = null;

	public List<Item> getItems() {
		items = findAll();
		return items;
	}

	public List<Item> findAll() {
		try {
			TypedQuery<Item> query = emf.createEntityManager()
					.createNamedQuery("Item.findAll", Item.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
