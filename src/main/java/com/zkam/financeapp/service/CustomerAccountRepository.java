package com.zkam.financeapp.service;

import com.zkam.financeapp.domain.CustomerAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccountEntity, Long> {
}
