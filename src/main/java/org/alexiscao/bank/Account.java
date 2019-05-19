package org.alexiscao.bank;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountApi {

    private final Clock clock;
    private final List<Operation> history = new ArrayList<>();

    public Account(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void deposit(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount should be positive.");
        }
        history.add(Operation.deposit(amount, clock.instant()));
    }

    @Override
    public void withdraw(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Withdraw amount should be positive.");
        }

        long balance = computeBalance(history);
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds to withdraw " + amount);
        }

        history.add(Operation.withdraw(amount, clock.instant()));
    }

    @Override
    public Statement getStatement() {
        List<Operation> statementHistory = new ArrayList<>(history);
        long balance = computeBalance(statementHistory);
        return new Statement(statementHistory, balance);
    }

    private long computeBalance(List<Operation> statementHistory) {
        return statementHistory.stream().mapToInt(Operation::getAmount).sum();
    }
}
