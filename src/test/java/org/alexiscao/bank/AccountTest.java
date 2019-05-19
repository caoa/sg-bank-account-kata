package org.alexiscao.bank;

import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    private AccountApi account;

    @Before
    public void setUp() {
        account = new Account(Clock.systemDefaultZone());
    }

    // US 1

    @Test
    public void simpleDeposit_ShouldAddMoney_ToTheAccount() {
        account.deposit(1000);

        Statement statement = account.getStatement();
        assertEquals(1000, statement.getBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void depositShouldBePositive() {
        account.deposit(-100);
    }

    // US 2

    @Test
    public void simpleWithdraw_shouldRemoveMoney_fromTheAccount() {
        account.deposit(10000);
        account.withdraw(1000);

        Statement statement = account.getStatement();
        assertEquals(9000, statement.getBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdrawShouldBePositive() {
        account.withdraw(-1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdrawShouldBeLowerThanAccountCurrentBalance() {
        account.deposit(1000);
        account.withdraw(4000);
    }

    @Test
    public void multipleOperations_shouldBeAggregatedInTheBalance() {
        account.deposit(5000);
        account.deposit(200);
        account.withdraw(800);
        account.deposit(300);
        account.withdraw(400);
        account.withdraw(500);

        Statement statement = account.getStatement();
        assertEquals(3800, statement.getBalance());
    }

    // US 3

    @Test
    public void operationsHistory_shouldBeInTheStatement() {
        // Inject the clock so we can check the date of operations are correct
        Instant fixedInstant = Instant.ofEpochSecond(1_500_000_000);
        AccountApi account = new Account(Clock.fixed(fixedInstant, ZoneId.of("UTC")));

        account.deposit(3000);
        account.withdraw(1000);
        account.withdraw(500);
        account.deposit(2000);

        Statement statement = account.getStatement();
        List<Operation> history = statement.getHistory();

        assertEquals(Arrays.asList(
                Operation.deposit(3000, fixedInstant),
                Operation.withdraw(1000, fixedInstant),
                Operation.withdraw(500, fixedInstant),
                Operation.deposit(2000, fixedInstant)
        ), history);
    }
}
