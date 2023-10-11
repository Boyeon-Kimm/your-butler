package com.ficrew.yourbutler.chat.infrastructure.persistence.jpa;

import com.ficrew.yourbutler.chat.domain.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaBankRepository extends JpaRepository<Bank, Long> {

    Optional<Bank> findByBankName(String bankName);

}
