package com.mariobaron.bankaccountscqrsdemo.common.command.service;

import com.mariobaron.bankaccountscqrsdemo.common.command.command.CreateAccountCommand;
import com.mariobaron.bankaccountscqrsdemo.common.command.command.DepositMoneyCommand;
import com.mariobaron.bankaccountscqrsdemo.common.command.command.WithdrawMoneyCommand;
import com.mariobaron.bankaccountscqrsdemo.common.command.dto.CreateAccountRequest;
import com.mariobaron.bankaccountscqrsdemo.common.command.dto.DepositRequest;
import com.mariobaron.bankaccountscqrsdemo.common.command.dto.WithdrawalRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Service
public class AccountCommandService {

    private final CommandGateway commandGateway;

    public AccountCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> createAccount(CreateAccountRequest createAccountRequest) {
        return commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                createAccountRequest.getStartingBalance())
        );
    }

    public CompletableFuture<String> depositToAccount(DepositRequest depositRequest) {
        return commandGateway.send(new DepositMoneyCommand(
                depositRequest.getAccountId(),
                depositRequest.getAmount()
        ));
    }

    public CompletableFuture<String> withdrawFromAccount(WithdrawalRequest withdrawalRequest) {
        return commandGateway.send(new WithdrawMoneyCommand(
                withdrawalRequest.getAccountId(),
                withdrawalRequest.getAmount()
        ));
    }
}
