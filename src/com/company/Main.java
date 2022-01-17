package com.company;

import java.util.Scanner;

public class Main {

    private static Bank bank = new Bank();
    private static BankAccount selectedAccount;

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        String input = "";

        while (input != "x") {
            System.out.println("What do you want to do?");
            System.out.println("x: Exit");
            System.out.println("n: New Account");
            System.out.println("l: List Accounts");
            System.out.println("s: Select Account");
            System.out.println("a: Add Money");
            System.out.println("g: Get Money");
            System.out.println("i: Account info");
            System.out.println("d: Change dispo");
            System.out.print("#> ");

            input = s.nextLine();

            switch (input) {
                case "x": return;
                case "n": newAccount(s);
                    break;
                case "l": listAccounts(s);
                    break;
                case "s": selectAccount(s);
                    break;
                case "a": addMoney(s);
                    break;
                case "g": getMoney(s);
                    break;
                case "i": accountInfo(s);
                    break;
                case "d": changeDispo(s);
                    break;
                default: System.out.println("wrong input");
            }

            input = "";
        }

    }

    private static int readInt(Scanner s){
        String input = s.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1; // we only accept positive values anyway, so this will block things
        }
    }

    private static double readDouble(Scanner s){
        String input = s.nextLine();
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return -1; // we only accept positive values anyway, so this will block things
        }
    }

    private static void newAccount(Scanner s) {
        System.out.print("First name: ");
        String firstName = s.nextLine();

        System.out.print("Last name: ");
        String lastName = s.nextLine();

        System.out.print("Initial Dispo: ");
        int dispo = readInt(s);

        BankAccount account = bank.createAccount(firstName, lastName, dispo);
        selectedAccount = account;

        System.out.println("Selected new Account. Number is " + account.accountNumber);
    }

    private static void listAccounts(Scanner s) {
        System.out.println("Accounts found:");
        for (BankAccount ba:bank.listAccounts()) {
            System.out.println(ba.accountNumber + " | " + ba.getName() + " | " + ba.getValue());
        }
    }

    private static void selectAccount(Scanner s) {

        System.out.println("Account number to select:");
        String accountnr = s.nextLine();

        BankAccount foundAccount = null;

        for (BankAccount ba:bank.listAccounts()) {
            if (ba.accountNumber == accountnr) {
                foundAccount = ba;
            }
        }

        if (foundAccount == null) {
            System.out.println("Account not found");
            selectedAccount = null;
        }
        else {
            System.out.println("Account selected");
            selectedAccount = foundAccount;
        }
    }

    private static void addMoney(Scanner s) {
        if (selectedAccount == null) {
            System.out.println("No account selected");
            return;
        }

        System.out.println("How much to add?");
        double value = readDouble(s);

        if (value <= 0){
            System.out.println("Use a positive value");
            return;
        }

        System.out.println("Enter a description");
        String description = s.nextLine();

        selectedAccount.doTransaction(new AccountTransaction(description, value));

        System.out.println("done");
    }

    private static void getMoney(Scanner s) {
        if (selectedAccount == null) {
            System.out.println("No account selected");
            return;
        }

        System.out.println("How much to get?");
        double value = readDouble(s);

        if (value <= 0){
            System.out.println("Use a positive value");
            return;
        }

        value = value * -1;

        System.out.println("Enter a description");
        String description = s.nextLine();

        boolean success = selectedAccount.doTransaction(new AccountTransaction(description, value));

        if (!success) {
            System.out.println("failed, dispo too small");
        }
        else {
            System.out.println("done");
        }
    }

    private static void accountInfo(Scanner s) {
        if (selectedAccount == null) {
            System.out.println("No account selected");
            return;
        }

        System.out.println("Account owner: " + selectedAccount.getName());

        for (AccountTransaction t:selectedAccount.getTransactions()) {
            System.out.println(t.description + ": " + t.value);
        }

        System.out.println("current value:" + selectedAccount.getValue());
    }

    private static void changeDispo(Scanner s) {
        if (selectedAccount == null) {
            System.out.println("No account selected");
            return;
        }

        System.out.println("How much dispo?");
        int value = readInt(s);

        if (value <= 0){
            System.out.println("Use a positive value");
            return;
        }

        selectedAccount.changeDispo(value);

        System.out.println("done");
    }
}
