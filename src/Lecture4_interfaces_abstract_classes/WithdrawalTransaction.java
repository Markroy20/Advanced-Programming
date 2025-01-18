package Lecture4_interfaces_abstract_classes;

import java.util.Calendar;

public class WithdrawalTransaction extends BaseTransaction {
    public WithdrawalTransaction(double amount, Calendar date, String transactionID) {
        super(amount, date, transactionID);
    }

    @Override
    public void apply(BankAccount ba) {
        if (ba.getBalance() >= getAmount()) {
            ba.withdraw(getAmount());
            System.out.println("Withdrawn: " + getAmount());
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }

    @Override
    public boolean reverse(BankAccount ba) {
        ba.deposit(getAmount());
        System.out.println("Withdrawal reversed: " + getAmount());
        return true;
    }
}
