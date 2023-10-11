package com.ficrew.yourbutler.chat.domain.repository;

import com.ficrew.yourbutler.chat.domain.entity.Bank;

import java.util.Optional;

public interface BankRepository {

    Bank save(Bank bank);
    Optional<Bank> findByBankName(String bankName);

}
