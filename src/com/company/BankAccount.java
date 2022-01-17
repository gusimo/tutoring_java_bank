package com.company;

import java.util.ArrayList;

public class BankAccount {

    public String accountNumber;
    private String firstName;
    private String lastName;
    private int dispo;
    private double value;
    private ArrayList<AccountTransaction> transactions;

    public BankAccount(String _accountNumber, String _firstName, String _lastName, int _dispo) {
        this.value = 0;
        this.accountNumber = _accountNumber;
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.dispo = _dispo;
        this.transactions = new ArrayList<AccountTransaction>();
    }

    public void changeDispo(int _dispo){
        this.dispo = _dispo;
    }

    public boolean doTransaction(AccountTransaction t) {
        if (this.value + t.value < (this.dispo * -1)) {
            return false;
        }

        this.value += t.value;
        this.transactions.add(t);
        return true;
    }

    public ArrayList<AccountTransaction> getTransactions() {
        return this.transactions;
    }

    public double getValue(){
        return this.value;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

}
