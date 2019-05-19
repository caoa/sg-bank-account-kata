package org.alexiscao.bank;

public interface AccountApi {

    /**
     * Make a deposit, ie *increase* the current balance of the account by the given amount. The amount MUST be positive.
     *
     * @param amount amount of money to add
     * @throws IllegalArgumentException if the amount is negative
     */
    void deposit(int amount);

    /**
     * Make a deposit, ie *decrease* the current balance of the account by the given amount. The amount MUST be positive.
     *
     * @param amount amount of money to remove
     * @throws IllegalArgumentException if the amount is negative or the balance is lower than the amount
     */
    void withdraw(int amount);

    /**
     * Returns the current statement of the account (operations history and balance)
     */
    Statement getStatement();

}
