package org.alexiscao.bank;

import java.time.Instant;
import java.util.Objects;

public class Operation {

    private final String description;
    private final Instant executionDate;
    private final int amount;

    private Operation(String description, Instant executionDate, int amount) {
        this.description = description;
        this.executionDate = executionDate;
        this.amount = amount;
    }

    /**
     * Factory method for a deposit operation
     */
    public static Operation deposit(int amount, Instant executionDate) {
        return new Operation("Deposit", executionDate, amount);
    }

    /**
     * Factory method for a withdraw operation
     */
    public static Operation withdraw(int amount, Instant executionDate) {
        return new Operation("Withdraw", executionDate, -amount);
    }

    /**
     * Description of the operation
     */
    public String getDescription() {
        return description;
    }

    /**
     * The instant when the operation was executed
     */
    public Instant getExecutionDate() {
        return executionDate;
    }

    /**
     * The amount by which the account will be impacted (added) when applying the operation.
     * Can be positive in case the the account should be credited and negative in case the accounted should be debited.
     */
    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return amount == operation.amount &&
                Objects.equals(description, operation.description) &&
                Objects.equals(executionDate, operation.executionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, executionDate, amount);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "description='" + description + '\'' +
                ", executionDate=" + executionDate +
                ", amount=" + amount +
                '}';
    }
}
