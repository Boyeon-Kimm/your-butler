package com.ficrew.yourbutler.chat.infrastructure.persistence;

import com.ficrew.yourbutler.chat.domain.entity.Bank;
import com.ficrew.yourbutler.chat.domain.repository.BankRepository;
import com.ficrew.yourbutler.chat.infrastructure.persistence.jpa.JpaBankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BankRepositoryAdapter implements BankRepository {

    private final JpaBankRepository bankRepository;

    @Override
    public Bank save(Bank bank) {
        return bankRepository.save(bank);
    }

    @Override
    public Optional<Bank> findByBankName(String bankName) {
        return bankRepository.findByBankName(bankName);
    }

}
