package Lecture4_interfaces_abstract_classes;

import org.jetbrains.annotations.NotNull;
import java.util.Calendar;

/**
 * Abstract class representing a base transaction.
 * Implements TransactionInterface.
 */
public abstract class BaseTransaction implements TransactionInterface {

    // Fields for transaction details
    private final int amount;
    private final Calendar date;
    private final String transactionID;

    /**
     * Constructor for BaseTransaction
     *
     * @param amount Transaction amount as an integer.
     * @param date   Transaction date, must not be null and should be a Calendar object.
     */
    public BaseTransaction(int amount, @NotNull Calendar date) {
        this.amount = amount;
        this.date = (Calendar) date.clone(); // Clone to avoid reference issues.
        int uniq = (int) (Math.random() * 10000); // Generate a random unique ID component.
        this.transactionID = date.toString() + uniq;
    }

    /**
     * Returns the transaction amount.
     *
     * @return Transaction amount as an integer.
     */
    @Override
    public int getAmount() {
        return amount;
    }

    /**
     * Returns the transaction date.
     *
     * @return A clone of the transaction date as a Calendar object.
     */
    @Override
    public Calendar getDate() {
        return (Calendar) date.clone(); // Defensive copying.
    }

    /**
     * Returns the unique transaction ID.
     *
     * @return Transaction ID as a String.
     */
    @Override
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * Abstract method to print transaction details.
     * Must be implemented by subclasses.
     */
    public abstract void printTransactionDetails();

    /**
     * Abstract method to apply the transaction to a BankAccount.
     * Must be implemented by subclasses.
     *
     * @param ba BankAccount instance to apply the transaction on.
     */
    public abstract void apply(BankAccount ba);

    /**
     * Concrete implementation to print transaction details.
     */
    @Override
    public void printTransactionDetails() {
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Amount: " + amount);
        System.out.println("Date: " + date.getTime());
    }

    /**
     * Base implementation of the apply method.
     * Can be overridden by subclasses.
     *
     * @param ba BankAccount instance to apply the transaction on.
     */
    @Override
    public void apply(BankAccount ba) {
        System.out.println("BaseTransaction applied on BankAccount.");
        // Placeholder logic for applying a transaction.
    }
}
