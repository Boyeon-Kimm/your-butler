package com.ficrew.yourbutler.chat.application;

public class MonthPayCalculate {

    public long wonrigum_equal(long loan, double interest, int period) {
        double month_interest = interest / 12;
        double month_interest_rate = month_interest / 100;
        double month_pay = loan * (month_interest_rate * Math.pow(1 + month_interest_rate, period)) / (Math.pow(1 + month_interest_rate, period) - 1);
        long month_pay_long = (long) month_pay;
        return month_pay_long;
    }

}
