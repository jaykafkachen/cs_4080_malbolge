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
public class ConceptsProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public class Wallet {
        private Map<String, Double> currMap;
        private Map<String, Currency> contents;
        private Set<String> currNames;
        
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
            String[] currNames = (String[])this.currNames.toArray();
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
    
    public class Currency {
        private double amount;
        private String currType;
        
        public Currency(double amount, String currType) {
            this.amount = amount;
            this.currType = currType;
        }
        
        public void add(double a) {
            amount += a;
        }
        
        public void subtract(double a) {
            amount -= a;
        }
        
        public String getCurrType() {
            return currType;
        }
        
        public double getAmount() {
            return amount;
        }
    }
}
