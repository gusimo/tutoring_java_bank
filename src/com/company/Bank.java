package com.company;

import java.util.ArrayList;

public class Bank {
    private ArrayList<BankAccount> accounts;

    public Bank() {
        this.accounts = new ArrayList<BankAccount>();
    }

    public ArrayList<BankAccount> listAccounts(){
        return accounts;
    }

    public BankAccount createAccount(String firstName, String lastName, int dispo) {
        BankAccount result = new BankAccount(String.format("%08d", this.listAccounts().size()+1), firstName, lastName, dispo);
        this.accounts.add(result);
        return result;
    }
}
