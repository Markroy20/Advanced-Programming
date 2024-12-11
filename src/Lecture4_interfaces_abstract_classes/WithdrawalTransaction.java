package Lecture4_interfaces_abstract_classes;

import org.jetbrains.annotations.NotNull;
import java.util.Calendar;

public class WithdrawalTransaction extends BaseTransaction {
    public WithdrawalTransaction(int amount, @NotNull Calendar date) {
// Custom Exception Class to handle insufficient funds
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
// BaseTransaction Class implementing common transaction functionality
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
    public abstract void apply(BankAccount ba) throws InsufficientFundsException; // Using throws for exception handling
}
// DepositTransaction Class extending BaseTransaction
class DepositTransaction extends BaseTransaction {
    public DepositTransaction(int amount, @NotNull Calendar date) {
        super(amount, date);
    }

    private boolean checkDepositAmount(int amt) {
        if (amt < 0) {
            return false;
        } else {
            return true;
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

    // Method to reverse the transaction
    public boolean reverse() {
    @Override
    public void printTransactionDetails() {
        System.out.println("Deposit Transaction Details:");
        System.out.println("Transaction ID: " + getTransactionID());
        System.out.println("Amount: " + getAmount());
        System.out.println("Date: " + getDate().getTime());
    }
}
// WithdrawalTransaction Class extending BaseTransaction
class WithdrawalTransaction extends BaseTransaction {
    public WithdrawalTransaction(int amount, @NotNull Calendar date) {
        super(amount, date);
    }
    // Reverse the withdrawal by restoring the original balance
    @Override
    public boolean reverse(BankAccount ba) {
        double currentBalance = ba.getBalance();
        double newBalance = currentBalance + getAmount();
        ba.setBalance(newBalance);
        System.out.println("Reversed Withdrawal: Restored " + getAmount() + " to account.");
        return true;
    } // return true if reversal was successful
    }
    // Apply the withdrawal with exception handling for insufficient funds
    @Override
    public void apply(BankAccount ba) throws InsufficientFundsException {
        double currentBalance = ba.getBalance();
        try {
            if (currentBalance < getAmount()) {
                throw new InsufficientFundsException("Insufficient funds for withdrawal of " + getAmount());
            } else if (currentBalance > 0 && currentBalance < getAmount()) {
                // If the balance is less than the withdrawal amount but greater than 0, withdraw all available balance
                double newBalance = 0;
                ba.setBalance(newBalance);
                System.out.println("Withdrawn all available balance. New Balance: " + newBalance);
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

    // Method to print a transaction receipt or details
    @Override
    public void printTransactionDetails() {
        System.out.println("Deposit Trasaction: " + this.toString());
        System.out.println("Withdrawal Transaction Details:");
        System.out.println("Transaction ID: " + getTransactionID());
        System.out.println("Amount: " + getAmount());
        System.out.println("Date: " + getDate().getTime());
    }
}

    /*
    Oportunity for assignment: implementing different form of withdrawal
     */
    public void apply(BankAccount ba) {
        double curr_balance = ba.getBalance();
        if (curr_balance > getAmount()) {
            double new_balance = curr_balance - getAmount();
            ba.setBalance(new_balance);
        }
// BankAccount Class to handle the balance and set/get balance operations
class BankAccount {
    private double balance;
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }
    public double getBalance() {
        return balance;
    }

    /*
    Assignment 1 Q3: Write the Reverse method - a method unique to the WithdrawalTransaction Class
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
}

// Test class to demonstrate the transactions and exception handling
public class BankTransactionTest {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(500.0); // Initial balance is 500
        // Create a DepositTransaction
        DepositTransaction deposit = new DepositTransaction(200, Calendar.getInstance());
        deposit.apply(account);
        deposit.printTransactionDetails();
        // Create a WithdrawalTransaction
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(600, Calendar.getInstance());
        try {
            withdrawal.apply(account);
        } catch (InsufficientFundsException e) {
            System.out.println("Error during withdrawal: " + e.getMessage());
        }
        withdrawal.printTransactionDetails();
    }
}