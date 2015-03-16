package de.java2enterprise.onlineshop.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class TestController implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<String> days;
	private String day;

	public List<String> getDays() {
		return days;
	}

	public void setDays(List<String> days) {
		this.days = days;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@PostConstruct
	public void init() {
		days = Arrays.asList(new String[] { "Montag", "Dienstag", "Mittwoch",
				"Donnerstag", "Freitag", "Samstag", "Sonntag" });
	}

}
