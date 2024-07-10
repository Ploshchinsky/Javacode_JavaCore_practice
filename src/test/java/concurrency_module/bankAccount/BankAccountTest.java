package concurrency_module.bankAccount;

import concurrency_module.banckAccount.BankAccount;
import concurrency_module.banckAccount.ConcurrentBank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BankAccountTest {
    @Test
    public void bankAccountTest() {
        BigDecimal expectedBalance, actualBalance;
        actualBalance = BigDecimal.valueOf(0);
        expectedBalance = BigDecimal.valueOf(1_500);

        ConcurrentBank pBank = new ConcurrentBank("P-BANK");
        BankAccount account1 = pBank.newAccount("Vasiliy", BigDecimal.valueOf(1_000));
        BankAccount account2 = pBank.newAccount("Svetlana", BigDecimal.valueOf(500));

        actualBalance = pBank.getTotalBalance();
        System.out.println("Total balance BEFORE: " + actualBalance.toString());
        System.out.println("Account 1: " + account1.getBalance().toString()
                + "\nAccount 2: " + account2.getBalance().toString());

        Thread transfer1 = new Thread(() -> pBank.transfer(account1, account2, BigDecimal.valueOf(300)));
        Thread transfer2 = new Thread(() -> pBank.transfer(account2, account1, BigDecimal.valueOf(500)));

        transfer2.start();
        transfer1.start();
        try {
            transfer2.join();
            transfer1.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        actualBalance = pBank.getTotalBalance();
        System.out.println("Total balance AFTER: " + actualBalance.toString());
        System.out.println("Account 1: " + account1.getBalance().toString()
                + "\nAccount 2: " + account2.getBalance().toString());

        Assertions.assertEquals(expectedBalance, actualBalance);
    }
}
