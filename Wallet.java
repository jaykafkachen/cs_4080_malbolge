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
    private Map<String, Double> currMap;
    private Map<String, Currency> contents;
    private Set<String> currNames;

    Wallet() {
        currMap = new HashMap<>();
        contents = new HashMap<>();
        currNames = new HashSet<>();
        addNewCurr("USD", 1);
        addNewCurr("EUR", 0.919243);
        addNewCurr("GBP", 0.801796);
        addNewCurr("CNY", 7.0625);
        addNewCurr("JPY", 106.5875);
        addNewCurr("CAD", 1.40359);
        addNewCurr("AUD", 1.546716);
    }

    public void addNewCurr(String name, double conRate) {
        if (!currNames.contains(name)) {
            currMap.put(name, conRate);
            contents.put(name, new Currency(0, name));
            currNames.add(name);
        } else {
            System.err.println("Error: Currency already in set.");
        }
    }

    public void printWallet() {
        String[] currNames = Arrays.copyOf(this.currNames.toArray(), this.currNames.toArray().length, String[].class);
//        String[] currNames = (String[])this.currNames.toArray();
        for (int i = 0; i < currNames.length; i++) {
            System.out.println(currNames[i] + ": " + contents.get(currNames[i]).getAmount());
        }
    }

    public double convert(double amount, String typeFrom, String typeTo) {
        contents.get(typeFrom).subtract(amount);
        double addTo = amount/currMap.get(typeFrom) * currMap.get(typeTo);
        contents.get(typeTo).add(addTo);
        System.out.println("Converted " + amount + " " + typeFrom + " to " + addTo + " " + typeTo + ".");
        return addTo;
    }

    public double convert(double amount, Currency currFrom, Currency currTo) {
        currFrom.subtract(amount);
        double addTo = amount/currMap.get(currFrom.getCurrType()) * currMap.get(currTo.getCurrType());
        currTo.add(addTo);
        System.out.println("Converted " + amount + " " + currFrom.getCurrType() + " to " + addTo + " " + currTo.getCurrType() + ".");
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