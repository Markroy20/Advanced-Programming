package Lecture4_interfaces_abstract_classes;

import org.jetbrains.annotations.NotNull;
import java.util.Calendar;

// Exception class for handling insufficient funds during withdrawal
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// Abstract base class for all transactions
abstract class BaseTransaction {
    private int amount;
    private Calendar date;
    private String transactionID;

    public BaseTransaction(int amount, @NotNull Calendar date) {
        this.amount = amount;
        this.date = (Calendar) date.clone();
        this.transactionID = generateTransactionID();
    }

    public double getAmount() {
        return amount;
    }

    public Calendar getDate() {
        return (Calendar) date.clone();
    }

    public String getTransactionID() {
        return transactionID;
    }

    // Helper method to generate a unique transaction ID
    private String generateTransactionID() {
        int uniq = (int) (Math.random() * 10000);
        return date.toString() + uniq;
    }

    // Abstract methods for derived classes to implement
    public abstract void printTransactionDetails();
    public abstract void apply(BankAccount ba) throws InsufficientFundsException;
}

// Deposit Transaction class extending BaseTransaction
class DepositTransaction extends BaseTransaction {
    public DepositTransaction(int amount, @NotNull Calendar date) {
        super(amount, date);
    }

    // Validate deposit amount (should be non-negative)
    private boolean checkDepositAmount(int amt) {
        return amt >= 0;
    }

    @Override
    public void apply(BankAccount ba) {
        if (!checkDepositAmount(getAmount())) {
            System.out.println("Invalid deposit amount: " + getAmount() + ". Deposit not applied.");
            return;
        }
        double currentBalance = ba.getBalance();
        double newBalance = currentBalance + getAmount();
        ba.setBalance(newBalance);
        System.out.println("Deposit of " + getAmount() + " applied. New Balance: " + newBalance);
    }

    // Print transaction details
    @Override
    public void printTransactionDetails() {
        System.out.println("Deposit Transaction Details:");
        System.out.println("Transaction ID: " + getTransactionID());
        System.out.println("Amount: " + getAmount());
        System.out.println("Date: " + getDate().getTime());
    }
}

// Withdrawal Transaction class extending BaseTransaction
class WithdrawalTransaction extends BaseTransaction {
    public WithdrawalTransaction(int amount, @NotNull Calendar date) {
        super(amount, date);
    }

    // Reverse the withdrawal by restoring the original balance
    public boolean reverse(BankAccount ba) {
        double currentBalance = ba.getBalance();
        double newBalance = currentBalance + getAmount();
        ba.setBalance(newBalance);
        System.out.println("Reversed Withdrawal: Restored " + getAmount() + " to account.");
        return true;
    }

    // Apply the withdrawal with exception handling for insufficient funds
    @Override
    public void apply(BankAccount ba) throws InsufficientFundsException {
        double currentBalance = ba.getBalance();
        try {
            if (currentBalance < getAmount()) {
                throw new InsufficientFundsException("Insufficient funds for withdrawal of " + getAmount());
            } else {
                double newBalance = currentBalance - getAmount();
                ba.setBalance(newBalance);
                System.out.println("Withdrawal of " + getAmount() + " applied. New Balance: " + newBalance);
            }
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Transaction processing complete.");
        }
    }

    // Print transaction details
    @Override
    public void printTransactionDetails() {
        System.out.println("Withdrawal Transaction Details:");
        System.out.println("Transaction ID: " + getTransactionID());
        System.out.println("Amount: " + getAmount());
        System.out.println("Date: " + getDate().getTime());
    }
}

// Bank Account class to manage account balance
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    // Method to set a new balance
    public void setBalance(double balance) {
        this.balance = balance;
    }
}

// Test class to demonstrate transactions and exception handling
public class BankTransactionTest {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(500.0); // Initial balance is 500
        
        // Create and apply Deposit Transaction
        DepositTransaction deposit = new DepositTransaction(200, Calendar.getInstance());
        deposit.apply(account);
        deposit.printTransactionDetails();

        // Create and apply Withdrawal Transaction
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(600, Calendar.getInstance());
        try {
            withdrawal.apply(account);
        } catch (InsufficientFundsException e) {
            System.out.println("Error during withdrawal: " + e.getMessage());
        }
        withdrawal.printTransactionDetails();
    }
}
