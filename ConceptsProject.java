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
        Wallet wallet = new Wallet();
        fxnLoop(wallet);
    }
    
    public static void fxnLoop(Wallet wallet) {
        boolean running = true;
        while(running) {
            Scanner userIn = new Scanner(System.in);
            System.out.println("*******************************");
            System.out.println("Select from the following:");
            System.out.println("1.) View your wallet");
            System.out.println("2.) View exchange rates");
            System.out.println("3.) Convert currency");
            System.out.println("4.) Deposit currency");
            System.out.println("5.) Withdraw currency");
            System.out.println("6.) Add a new kind of currency or update an existing rate");
            System.out.println("7.) Quit");
            try {
                int choice = Integer.parseInt(userIn.nextLine());
                switch(choice) {
                    case 1:
                        wallet.printWallet();
                        break;
                    case 2:
                        wallet.printRates();
                        break;
                    case 3:
                        convCurrInterface(wallet);
                        break;
                    case 4:
                        depositCurrInterface(wallet);
                        break;
                    case 5:
                        withdrawCurrInterface(wallet);
                        break;
                    case 6:
                        addCurrInterface(wallet);
                        break;
                    case 7:
                        running = false;
                        break;
                    default:
                        System.out.println("Number must be between 1 and 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
            
        }
    }

    private static void convCurrInterface(Wallet wallet) {
        Scanner userIn = new Scanner(System.in);
        System.out.println("What kind of currency are you converting from?");
        String from = userIn.nextLine();
        System.out.println("How much " + from + " do you want to convert?");
        boolean valNum = false;
        double amount = -1;
        while(!valNum) {
            try {
                amount = Double.parseDouble(userIn.nextLine());
                if (amount <= 0) {
                    System.out.println("Please enter a valid amount.");
                } else {
                    valNum = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
        System.out.println("What kind of currency are you converting it to?");
        String to = userIn.nextLine();
        System.out.println("Converting " + amount + " " + from + " to " + to + "...");
        wallet.convert(amount, from, to);
    }

    private static void depositCurrInterface(Wallet wallet) {
        Scanner userIn = new Scanner(System.in);
        System.out.println("What kind of currency are you depositing to?");
        String kind = userIn.nextLine();
        System.out.println("How much are you depositing?");
        boolean valNum = false;
        double amount = -1;
        while(!valNum) {
            try {
                amount = Double.parseDouble(userIn.nextLine());
                if (amount <= 0) {
                    System.out.println("Please enter a valid amount.");
                } else {
                    valNum = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
        System.out.println("Depositing " + amount + " " + kind + "...");
        wallet.deposit(amount, kind);
    }

    private static void withdrawCurrInterface(Wallet wallet) {
        Scanner userIn = new Scanner(System.in);
        System.out.println("What kind of currency are you withdrawing from?");
        String kind = userIn.nextLine();
        System.out.println("How much are you withdrawing?");
        boolean valNum = false;
        double amount = -1;
        while(!valNum) {
            try {
                amount = Double.parseDouble(userIn.nextLine());
                if (amount <= 0) {
                    System.out.println("Please enter a valid amount.");
                } else {
                    valNum = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
        System.out.println("Withdrawing " + amount + " " + kind + "...");
        wallet.withdraw(amount, kind);
    }

    private static void addCurrInterface(Wallet wallet) {
        Scanner userIn = new Scanner(System.in);
        System.out.println("Enter name of currency.");
        String kind = userIn.nextLine();
        System.out.println("Enter conversion rate of currency.");
        boolean valNum = false;
        double amount = -1;
        while(!valNum) {
            try {
                amount = Double.parseDouble(userIn.nextLine());
                if (amount <= 0) {
                    System.out.println("Please enter a valid amount.");
                } else {
                    valNum = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
        System.out.println("Adding/updating " + kind + "...");
        wallet.addUpdateCurr(kind, 0, amount);
    }
}
