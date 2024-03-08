package com.collegesoft.carservice;

class BankAccount {
    private int balance = 1000;

    // synchronization to ensure that deposit and withdrawal operations are
    // atomic and avoid inconsistencies due to concurrent access
    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited " + amount + ", Balance: " + balance);
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn " + amount + ", Balance: " + balance);
        } else {
            System.out.println("Insufficient funds for withdrawal");
        }
    }
}

class TransactionThread extends Thread {
    private BankAccount account;
    private boolean isDeposit;
    private int amount;

    public TransactionThread(BankAccount account, boolean isDeposit, int amount) {
        this.account = account;
        this.isDeposit = isDeposit;
        this.amount = amount;
    }

    public void run() {
        if (isDeposit) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }
    }
}

public class ConcurrencyBankingDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        TransactionThread depositThread1 = new TransactionThread(account, true, 500);
        TransactionThread withdrawThread1 = new TransactionThread(account, false, 200);
        TransactionThread depositThread2 = new TransactionThread(account, true, 1000);

        depositThread1.start();
        withdrawThread1.start();
        depositThread2.start();
    }
}

