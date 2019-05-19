package org.alexiscao.bank;

import java.util.List;
import java.util.Objects;

public class Statement {

    private final long balance;
    private final List<Operation> history;

    public Statement(List<Operation> history, long balance) {
        this.balance = balance;
        this.history = history;
    }

    /**
     * Current balance of the account
     */
    public long getBalance() {
        return balance;
    }

    /**
     * List of all operations performed on the account
     */
    public List<Operation> getHistory() {
        return history;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statement statement = (Statement) o;
        return balance == statement.balance &&
                Objects.equals(history, statement.history);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, history);
    }

    @Override
    public String toString() {
        return "Statement{" +
                "balance=" + balance +
                ", history=" + history +
                '}';
    }
}
