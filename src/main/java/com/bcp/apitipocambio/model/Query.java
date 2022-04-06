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
@Table(name = "query")
public class Query {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idUser", nullable = false)
	@JsonIgnore
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idExchangeRate", nullable = false)
	private Exchangerate exchangerate;

	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

	@Column(name = "originCurrency", nullable = false, length = 5)
	private String originCurrency;

	@Column(name = "destinationCurrency", nullable = false, length = 5)
	private String destinationCurrency;

	@Column(name = "type", nullable = false, length = 1)
	private Character type;

	@Column(name = "registrationDate", nullable = false)
	private LocalDateTime registrationDate;

	@Column(name = "updateDate", nullable = true)
	private LocalDateTime updateDate;

	public Query() {
		super();
	}

	public Query(User user, Exchangerate exchangerate, BigDecimal amount, String originCurrency,
			String destinationCurrency, Character type, LocalDateTime registrationDate, LocalDateTime updateDate) {
		super();
		this.user = user;
		this.exchangerate = exchangerate;
		this.amount = amount;
		this.originCurrency = originCurrency;
		this.destinationCurrency = destinationCurrency;
		this.type = type;
		this.registrationDate = registrationDate;
		this.updateDate = updateDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Exchangerate getExchangerate() {
		return exchangerate;
	}

	public void setExchangerate(Exchangerate exchangerate) {
		this.exchangerate = exchangerate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getOriginCurrency() {
		return originCurrency;
	}

	public void setOriginCurrency(String originCurrency) {
		this.originCurrency = originCurrency;
	}

	public String getDestinationCurrency() {
		return destinationCurrency;
	}

	public void setDestinationCurrency(String destinationCurrency) {
		this.destinationCurrency = destinationCurrency;
	}

	public Character getType() {
		return type;
	}

	public void setType(Character type) {
		this.type = type;
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
		return "Query [user=" + user + ", exchangerate=" + exchangerate + ", amount=" + amount + ", originCurrency="
				+ originCurrency + ", destinationCurrency=" + destinationCurrency + ", type=" + type
				+ ", registrationDate=" + registrationDate + ", updateDate=" + updateDate + "]";
	}

}
