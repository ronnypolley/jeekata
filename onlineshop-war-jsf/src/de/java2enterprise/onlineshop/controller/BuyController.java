package de.java2enterprise.onlineshop.controller;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;

import de.java2enterprise.onlineshop.model.Customer;
import de.java2enterprise.onlineshop.model.Item;

@Named
@RequestScoped
public class BuyController implements Serializable {

	@PersistenceUnit
	private EntityManagerFactory emf;

	@Resource
	private UserTransaction ut;

	public String update(Long id, SigninController signinController) {
		Customer customer = signinController.getCustomer();

		try {
			ut.begin();
			EntityManager em = emf.createEntityManager();

			Item item = em.find(Item.class, id);
			item.setBuyer(customer);
			item.setSold(new Date());

			em.merge(item);
			ut.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "search";
	}
}
