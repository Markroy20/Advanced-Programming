package Lecture1_adt;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Client code for accessing and testing the Lecture1_adt.TransactionInterface module.
 */
public class Main {

    /**
     * Tests the functionality of the Transaction1 class.
     */
    public static void testTransaction1() {
        Calendar d1 = new GregorianCalendar();
        Lecture1_adt.Transaction1 t1 = new Lecture1_adt.Transaction1(1000, d1);

        System.out.println(t1.toString());
        System.out.println("Transaction1 Amount: \t" + t1.amount);
        System.out.println("Transaction1 Date: \t" + t1.date);

        // Note: Direct access to class data through the dot operator poses threats
        // to Representation Independence and Preservation of Invariants.
    }

    /**
     * Produces a new Transaction2 with the same amount as the input transaction,
     * scheduled for one month later.
     *
     * @param t The input Transaction2 object.
     * @return A new Transaction2 object one month after the input transaction.
     */
    public static Transaction2 makeNextPayment(Transaction2 t) {
        Calendar d = t.getDate();
        d.add(Calendar.MONTH, 1);
        return new Transaction2(t.getAmount(), d);
    }

    /**
     * Tests the functionality of the Transaction2 class.
     */
    public static void testTransaction2() {
        Calendar d1 = new GregorianCalendar();
        Transaction2 t = new Transaction2(1000, d1);

        Transaction2 modifiedT = makeNextPayment(t);
        System.out.println("\nState of the Object After Client Code Modification:");
        System.out.println("Transaction2 Amount: \t" + modifiedT.getAmount());
        System.out.println("Transaction2 Date: \t" + modifiedT.getDate().getTime());

        // Demonstrates remaining exposure issues when reusing objects in Transaction2.
    }

    /**
     * Creates a list of 12 monthly Transaction3 objects with identical amounts.
     *
     * @param amount The transaction amount.
     * @return A list of 12 monthly payments.
     */
    public static List<Transaction3> makeYearOfPayments(int amount) {
        List<Transaction3> list = new ArrayList<>();
        Calendar date = new GregorianCalendar(2024, Calendar.JANUARY, 3);

        for (int i = 0; i < 12; i++) {
            list.add(new Transaction3(amount, date));
            date.add(Calendar.MONTH, 1);
        }
        return list;
    }

    /**
     * Tests the functionality of the Transaction3 class.
     */
    public static void testTransaction3() {
        List<Transaction3> allPaymentsIn2024 = makeYearOfPayments(1000);

        for (Transaction3 t : allPaymentsIn2024) {
            System.out.println("\nTransaction3 Details:");
            System.out.println("Amount: \t" + t.getAmount());
            System.out.println("Date: \t" + t.getDate().getTime());
        }
    }

    /**
     * Creates a list of 12 monthly Transaction4 objects with improved encapsulation.
     *
     * @param amount The transaction amount.
     * @return A list of 12 monthly payments with defensive programming.
     */
    public static List<Transaction4> makeYearOfPaymentsFinal(int amount) {
        List<Transaction4> list = new ArrayList<>();
        Calendar date = new GregorianCalendar(2024, Calendar.JANUARY, 3);

        for (int i = 0; i < 12; i++) {
            list.add(new Transaction4(amount, date));
            date.add(Calendar.MONTH, 1);
        }
        return list;
    }

    /**
     * Tests the functionality of the Transaction4 class.
     */
    public static void testTransaction4() {
        List<Transaction4> transactionsIn2024 = makeYearOfPaymentsFinal(1200);

        for (Transaction4 t : transactionsIn2024) {
            System.out.println("\nTransaction4 Details:");
            System.out.println("Amount: \t" + t.getAmount());
            System.out.println("Date: \t" + t.getDate().getTime());
        }
    }

    /**
     * Tests the DepositTransaction class.
     */
    public static void testDepositTransaction() {
        DepositTransaction deposit = new DepositTransaction(500, new GregorianCalendar(2024, Calendar.JANUARY, 1));
        deposit.apply();
        System.out.println("Deposit applied: " + deposit.toString());
    }

    /**
     * Tests the WithdrawalTransaction class.
     */
    public static void testWithdrawalTransaction() {
        WithdrawalTransaction withdrawal = new WithdrawalTransaction(200, new GregorianCalendar(2024, Calendar.FEBRUARY, 15));
        withdrawal.apply();
        System.out.println("Withdrawal applied: " + withdrawal.toString());
    }

    /**
     * Main method to execute the test case.
     *
     * Uncomment the desired test methods to run them.
     */
    public static void main(String[] args) {
        // Uncomment the lines below to test specific classes.
        testTransaction1();
        testTransaction2();
        testTransaction3();
        testTransaction4();
        testDepositTransaction();
        testWithdrawalTransaction();
    }
}
