package com.ficrew.yourbutler.Chat.infrastructure.persistence.jpa;

import com.ficrew.yourbutler.Chat.domain.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaBankRepository extends JpaRepository<Bank, Long> {

    Optional<Bank> findByBankName(String bankName);

}
