/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concepts.project;

import java.util.*;

/**
 *
 * @author Euclid
 */
public class Wallet {
    private Map<String, Currency> contents;
    private Set<String> currNames;

    Wallet() {
        contents = new HashMap<>();
        currNames = new HashSet<>();
        addUpdateCurr("USD", 1);
        addUpdateCurr("EUR", 0.919243);
        addUpdateCurr("GBP", 0.801796);
        addUpdateCurr("CNY", 7.0625);
        addUpdateCurr("JPY", 106.5875);
        addUpdateCurr("CAD", 1.40359);
        addUpdateCurr("AUD", 1.546716);
    }

    public void addUpdateCurr(String name, double conRate) {
        contents.put(name, new Currency(0, conRate));
        currNames.add(name);
    }

    public void printWallet() {
        String[] currNames = Arrays.copyOf(this.currNames.toArray(), this.currNames.toArray().length, String[].class);
        for (int i = 0; i < currNames.length; i++) {
            System.out.println(currNames[i] + ": " + contents.get(currNames[i]).getAmount());
        }
    }
    
    public void printRates() {
        String[] currNames = Arrays.copyOf(this.currNames.toArray(), this.currNames.toArray().length, String[].class);
        for (int i = 0; i < currNames.length; i++) {
            System.out.println(currNames[i] + ": " + contents.get(currNames[i]).getRate());
        }
    }

    public double convert(double amount, String typeFrom, String typeTo) {
        Currency from = contents.get(typeFrom);
        Currency to = contents.get(typeTo);
        from.subtract(amount);
        double addTo = amount/from.getRate() * to.getRate();
        to.add(addTo);
        System.out.println("Converted " + amount + " " + typeFrom + " to " + addTo + " " + typeTo + ".");
        return addTo;
    }

    public void deposit(double amount, String curr) {
        contents.get(curr).add(amount);
        System.out.println("New " + curr + " balance: " + contents.get(curr).getAmount());
    }

    public void withdraw(double amount, String curr) {
        contents.get(curr).subtract(amount);
        System.out.println("New " + curr + " balance: " + contents.get(curr).getAmount());
    }
}