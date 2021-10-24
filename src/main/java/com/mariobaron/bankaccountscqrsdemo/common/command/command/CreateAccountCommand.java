package com.mariobaron.bankaccountscqrsdemo.common.command.command;

import java.math.BigDecimal;

public class CreateAccountCommand extends BaseCommand<String> {

    private final BigDecimal balance;

    public CreateAccountCommand(String id, BigDecimal balance) {
        super(id);
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
