package de.java2enterprise.onlineshop.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class LocaleController implements Serializable {

	private static final long serialVersionUID = -6244147586883064687L;

	private String lang;

	@PostConstruct
	public void init() {
		setLang("de");
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String change(String lang) {
		setLang(lang);
		return "/index.xhtml";
	}

}
