package com.naiden.validation;

import com.naiden.dto.OrderDTO;
import com.naiden.model.CurrencyEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OrderValidator implements ConstraintValidator<OrderConstraint, OrderDTO> {

    private Double eurToUsdRate;

    public OrderValidator(Double eurToUsdRate) {
        this.eurToUsdRate = eurToUsdRate;
    }

    @Override
    public void initialize(OrderConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(OrderDTO order, ConstraintValidatorContext constraintValidatorContext) {
        Double amount = CurrencyEnum.EUR.equals(order.getCurrency())? EURtoUSD(order.getAmount()) : order.getAmount();
        return amount < 5000 && amount > 0 && order.getNumber().matches("[A-Za-z0-9]+"); // 5000 USD and other validation
    }

    private double EURtoUSD(double usd) {
        return usd * eurToUsdRate;
    }
}
