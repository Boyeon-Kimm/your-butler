package com.ficrew.yourbutler.Chat.application.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FlaskResult {

    @JsonProperty("loan_name")
    private String loanName;

    @JsonProperty("loan_interest")
    private Double loanInterest;

    @JsonProperty("bank_name")
    private String bankName;

    private String message;

    private String information;

    @JsonProperty("paying_off_period")
    private Integer payingOffPeriod;

    public FlaskResult(String loanName, Double loanInterest, String bankName, String message, String information, Integer payingOffPeriod) {
        this.loanName = loanName;
        this.loanInterest = loanInterest;
        this.bankName = bankName;
        this.message = message;
        this.information = information;
        this.payingOffPeriod = payingOffPeriod;
    }

}
