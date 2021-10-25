package com.mariobaron.bankaccounts.query;

import com.mariobaron.bankaccounts.query.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}
