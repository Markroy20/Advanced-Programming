package Lecture4_interfaces_abstract_classes;

import java.util.Calendar;

public class Main {
    public static void main(String[] args) {

        BankAccount account = new BankAccount(1000);

        // Creating a deposit transaction for an amount of 500
        DepositTransaction deposit = new DepositTransaction(500, Calendar.getInstance(), "TX001");
        System.out.println("\nProcessing Deposit Transaction:");
        deposit.printTransactionDetails();
        deposit.apply(account);
        System.out.println("Updated account balance after deposit: " + account.getBalance());


        // Creating a withdrawal transaction for an amount of 200
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(200, Calendar.getInstance(), "TX002");
        System.out.println("\nProcessing Withdrawal Transaction:");
        withdrawal.printTransactionDetails();
        withdrawal.apply(account);
        System.out.println("Updated account balance after withdrawal: " + account.getBalance());


        System.out.println("\nReversing Withdrawal Transaction:");
        if (withdrawal.reverse(account)) {
            System.out.println("Withdrawal successfully reversed.");
        } else {
            System.out.println("Reversal failed due to insufficient funds.");
        }
        System.out.println("Account balance after reversal: " + account.getBalance());


        System.out.println("\nReversing Deposit Transaction:");
        if (deposit.reverse(account)) {
            System.out.println("Deposit successfully reversed.");
        } else {
            System.out.println("Reversal failed due to insufficient funds.");
        }
        System.out.println("Account balance after deposit reversal: " + account.getBalance());
    }
}
