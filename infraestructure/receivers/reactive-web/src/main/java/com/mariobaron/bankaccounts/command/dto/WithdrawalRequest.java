package com.mariobaron.bankaccounts.command.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawalRequest {

    private String accountId;
    private BigDecimal amount;
}
