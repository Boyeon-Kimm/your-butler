package com.ficrew.yourbutler.Chat.domain.repository;

import com.ficrew.yourbutler.Chat.domain.entity.Bank;

import java.util.Optional;

public interface BankRepository {

    Bank save(Bank bank);
    Optional<Bank> findByBankName(String bankName);

}
