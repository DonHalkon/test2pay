package com.naiden.dto;

import com.naiden.model.CurrencyEnum;
import com.naiden.model.EOrder;
import com.naiden.validation.OrderConstraint;

@OrderConstraint
public class OrderDTO {

	private String number;
	private String description;
	private Double amount;
	private CurrencyEnum currency;

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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public CurrencyEnum getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}


	public EOrder toEOrder() {
		return new EOrder(this.getNumber(), this.getDescription(), this.getAmount(), this.getCurrency());
	}

	@Override
	public String toString() {
		return "Order{" +
				"number='" + number + '\'' +
				", description='" + description + '\'' +
				", amount=" + amount +
				", currency=" + currency +
				'}';
	}
}
