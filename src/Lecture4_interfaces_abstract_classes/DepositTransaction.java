package Lecture4_interfaces_abstract_classes;

import java.util.Calendar;

public class DepositTransaction extends BaseTransaction {
    public DepositTransaction(double amount, Calendar date, String transactionID) {
        super(amount, date, transactionID);
    }

    @Override
    public void apply(BankAccount ba) {
        ba.deposit(getAmount());
        System.out.println("Deposited: " + getAmount());
    }

    @Override
    public boolean reverse(BankAccount ba) {
        if (ba.getBalance() >= getAmount()) {
            ba.withdraw(getAmount());
            System.out.println("Deposit reversed: " + getAmount());
            return true;
        } else {
            System.out.println("Unable to reverse deposit: insufficient balance.");
            return false;
        }
    }
}
