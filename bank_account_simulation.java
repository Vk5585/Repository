package bank_account_simulation;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Account 
{
    private double balance;
    private ArrayList<String> transactionHistory;

    public Account(double initialBalance) 
    {
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account opened with balance: " + initialBalance + " at " + new Date());
    }

    public void deposit(double amount) 
    {
        if (amount <= 0) 
        {
            transactionHistory.add("Failed deposit: " + amount + " at " + new Date());
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        transactionHistory.add("Deposited: " + amount + " at " + new Date());
        System.out.println("Deposit successful.");
    }

    public void withdraw(double amount) 
    {
        if (amount <= 0) {
            transactionHistory.add("Failed withdrawal: " + amount + " at " + new Date());
            System.out.println("Withdraw amount must be positive.");
            return;
        }
        if (balance >= amount) 
        {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount + " at " + new Date());
            System.out.println("Withdrawal successful.");
        } else 
        {
            transactionHistory.add("Failed withdrawal, insufficient balance for: " + amount + " at " + new Date());
            System.out.println("Insufficient balance.");
        }
    }

    public double getBalance() 
    {
        return balance;
    }

    public ArrayList<String> getTransactionHistory() 
    {
        return transactionHistory;
    }
}

public class bank_account_simulation 
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter initial account balance: ");
        double initialBalance = scanner.nextDouble();
        Account acc = new Account(initialBalance);

        while (true)
        {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. View Balance");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) 
            {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double dep = scanner.nextDouble();
                    acc.deposit(dep);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double wd = scanner.nextDouble();
                    acc.withdraw(wd);
                    break;
                case 3:
                    System.out.println("Current balance: " + acc.getBalance());
                    break;
                case 4:
                    System.out.println("Transaction History:");
                    for (String t : acc.getTransactionHistory()) 
                    {
                        System.out.println(t);
                    }
                    break;
                case 5:
                    System.out.println("Thank you! Exiting.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }
}