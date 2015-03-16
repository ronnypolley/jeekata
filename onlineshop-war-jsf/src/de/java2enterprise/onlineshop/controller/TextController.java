package de.java2enterprise.onlineshop.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class TextController implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean a;

	public boolean isA() {
		return a;
	}

	public void setA(boolean a) {
		this.a = a;
	}

}
