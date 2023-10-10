package com.ficrew.yourbutler.Chat.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Embeddable
@Getter
public class Loan {

    private String loanName;
    private Double loanInterest;

    protected Loan() {

    }

    public Loan(String loanName, Double loanInterest) {
        this.loanName = loanName;
        this.loanInterest = loanInterest;
    }

}
