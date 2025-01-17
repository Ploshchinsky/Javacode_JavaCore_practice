package concurrency_module.banckAccount;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int id;
    private final String ownerName;
    private BigDecimal balance;
    private Lock lock;

    public BankAccount(int id, String ownerName, BigDecimal balance) {
        this.id = id;
        this.ownerName = ownerName;
        this.balance = balance;
        this.lock = new ReentrantLock();
    }

    public boolean deposit(BigDecimal amount) {
        lock.lock();
        balance = balance.add(amount);
        lock.unlock();
        return true;
    }

    public boolean withdraw(BigDecimal amount) {
        lock.lock();
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
        } else {
            return false;
        }
        lock.unlock();
        return true;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
