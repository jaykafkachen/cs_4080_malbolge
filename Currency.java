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
    private double rate;
    
    public Currency(double amount, double rate) {
        this.amount = amount;
        this.rate = rate;
    }

    public void add(double a) {
        amount += a;
    }

    public void subtract(double a) {
        amount -= a;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
    
    public void setRate(double rate) {
        this.rate = rate;
    }
    
    public double getRate() {
        return rate;
    }
}