package com.naiden.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "e_order")
public class EOrder implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "order_id")
	private Long id;

	@Column
	private String number;

	@Column
	private String description;

	@Column
	private double amount;

	@Column
	private CurrencyEnum currency;

	public EOrder() {
	}

	public EOrder(String number, String description, double amount, CurrencyEnum currency) {
		this.number = number;
		this.description = description;
		this.amount = amount;
		this.currency = currency;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EOrder eOrder = (EOrder) o;
		return Objects.equals(id, eOrder.id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "EOrder{" +
				"id=" + id +
				", number='" + number + '\'' +
				", description='" + description + '\'' +
				", amount=" + amount +
				", currency=" + currency +
				'}';
	}
}
