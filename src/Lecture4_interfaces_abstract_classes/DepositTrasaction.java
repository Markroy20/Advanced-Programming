package Lecture4_interfaces_abstract_classes;

import org.jetbrains.annotations.NotNull;
import java.util.Calendar;

/**
 * Class representing a deposit transaction.
 * Extends the BaseTransaction class.
 */
public class DepositTransaction extends BaseTransaction {

    /**
     * Constructor for DepositTransaction.
     *
     * @param amount Transaction amount (must be non-negative).
     * @param date   Transaction date, must not be null and should be a Calendar object.
     */
    public DepositTransaction(int amount, @NotNull Calendar date) {
        super(amount, date);
    }

    /**
     * Checks if the deposit amount is valid.
     *
     * @param amt Amount to be deposited.
     * @return true if the amount is valid (non-negative), false otherwise.
     */
    private boolean checkDepositAmount(int amt) {
        return amt >= 0;
    }

    /**
     * Applies the deposit transaction to a bank account.
     *
     * @param ba The BankAccount instance where the deposit will be applied.
     */
    @Override
    public void apply(BankAccount ba) {
        // Validate the deposit amount before applying it.
        if (!checkDepositAmount(getAmount())) {
            System.out.println("Invalid deposit amount: " + getAmount() + ". Deposit not applied.");
            return; // Early return if the deposit is invalid.
        }

        // Update the bank account balance.
        double currentBalance = ba.getBalance();
        double newBalance = currentBalance + getAmount();
        ba.setBalance(newBalance);

        // Log the successful deposit.
        System.out.println("Deposit of " + getAmount() + " applied. New Balance: " + newBalance);

        /**
         * **Irreversibility Factor**:
         * This implementation satisfies the assignment constraint by not providing a reverse() method.
         * Once applied, the deposit transaction cannot be undone.
         */
    }

    /**
     * Prints the details of the deposit transaction.
     */
    @Override
    public void printTransactionDetails() {
        System.out.println("Deposit Transaction Details:");
        System.out.println("Transaction ID: " + getTransactionID());
        System.out.println("Amount: " + getAmount());
        System.out.println("Date: " + getDate().getTime());
    }
}
