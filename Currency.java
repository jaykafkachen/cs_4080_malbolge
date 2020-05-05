/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concepts.project;

/**
 *
 * @author Euclid
 */
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