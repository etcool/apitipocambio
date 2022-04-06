package com.bcp.apitipocambio.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "exchangerate")
public class Exchangerate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idUser", nullable = false)
	@JsonIgnore
	private User user;

	@Column(name = "originCurrency", nullable = false, length = 5)
	private String originCurrency;

	@Column(name = "year", nullable = false)
	private int year;

	@Column(name = "month", nullable = false)
	private int month;

	@Column(name = "day", nullable = false)
	private int day;

	@Column(name = "type", nullable = false)
	private Character type;

	@Column(name = "destinationCurrency", nullable = false)
	private String destinationCurrency;

	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

	@Column(name = "registrationDate", nullable = false)
	private LocalDateTime registrationDate;

	@Column(name = "updateDate", nullable = true)
	private LocalDateTime updateDate;

	public Exchangerate() {
		super();
	}

	public Exchangerate(Long id, User user, String originCurrency, int year, int month, int day, Character type,
			String destinationCurrency, BigDecimal amount, LocalDateTime registrationDate, LocalDateTime updateDate) {
		super();
		this.id = id;
		this.user = user;
		this.originCurrency = originCurrency;
		this.year = year;
		this.month = month;
		this.day = day;
		this.type = type;
		this.destinationCurrency = destinationCurrency;
		this.amount = amount;
		this.registrationDate = registrationDate;
		this.updateDate = updateDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOriginCurrency() {
		return originCurrency;
	}

	public void setOriginCurrency(String originCurrency) {
		this.originCurrency = originCurrency;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Character getType() {
		return type;
	}

	public void setType(Character type) {
		this.type = type;
	}

	public String getDestinationCurrency() {
		return destinationCurrency;
	}

	public void setDestinationCurrency(String destinationCurrency) {
		this.destinationCurrency = destinationCurrency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Exchangerate [id=" + id + ", user=" + user + ", originCurrency=" + originCurrency + ", year=" + year
				+ ", month=" + month + ", day=" + day + ", type=" + type + ", destinationCurrency="
				+ destinationCurrency + ", amount=" + amount + ", registrationDate=" + registrationDate
				+ ", updateDate=" + updateDate + "]";
	}

}
