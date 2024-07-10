package concurrency_module.banckAccount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentBank {
    private String bankName;
    private List<BankAccount> accounts;

    private Lock lock;

    public ConcurrentBank(String bankName) {
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
        this.lock = new ReentrantLock();
    }

    public BankAccount newAccount(String ownerName, BigDecimal balance) {
        BankAccount temp = new BankAccount(accounts.size(), ownerName, balance);
        accounts.add(temp);
        return temp;
    }

    public boolean transfer(BankAccount from, BankAccount to, BigDecimal amount) {
        lock.lock();
        if (from.getBalance().compareTo(amount) >= 0) {
            from.withdraw(amount);
            to.deposit(amount);
        } else {
            return false;
        }
        lock.unlock();
        return true;
    }

    public BigDecimal getTotalBalance() {
        Optional<BigDecimal> totalBalance =
                accounts.stream().map(BankAccount::getBalance).reduce(BigDecimal::add);
        return totalBalance.orElse(null);
    }
}
