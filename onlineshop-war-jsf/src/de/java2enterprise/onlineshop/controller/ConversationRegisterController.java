package de.java2enterprise.onlineshop.controller;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;

import de.java2enterprise.onlineshop.model.Customer;

@Named
@ConversationScoped
public class ConversationRegisterController implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceUnit
	private EntityManagerFactory emf;

	@Resource
	private UserTransaction ut;

	@Inject
	private Customer customer;

	@Inject
	private Conversation conversation;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String step1() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
		return "register1";
	}

	public String step2() {
		return "register2";
	}

	public String persist() {
		try {
			ut.begin();
			emf.createEntityManager().persist(customer);
			ut.commit();
			FacesContext.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage("Succesfully registered!",
							"Your email was saved under id "
									+ customer.getId()));
			if (!conversation.isTransient()) {
				conversation.end();
			}
			return "success";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							e.getMessage(), e.getCause().getMessage()));
		}
		return "failure";
	}
}
