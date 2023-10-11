package com.ficrew.yourbutler.chat.domain.entity;

import lombok.Getter;

import javax.persistence.*;

/**
 * id, 은행이름, 은행전화번호, 은행사이트
 */
@Entity
@Getter
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false)
    private String bankTel;

    @Column(nullable = false)
    private String bankSite;

    public Bank(String bankName, String bankTel, String bankSite) {
        this.bankName = bankName;
        this.bankTel = bankTel;
        this.bankSite = bankSite;
    }

    protected Bank() {

    }

}
