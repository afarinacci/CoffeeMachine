package machine;
import java.util.Scanner;

public class CoffeeMachine {
    // instance variables
    public Scanner scanner;
    public int money;
    public int water;
    public int milk;
    public int beans;
    public int cups;
    public String[] state;
    public int currentState;
    // constructor
    public CoffeeMachine(int money, int water, int milk, int beans, int cups) {
        this.scanner = new Scanner(System.in);
        this.money = money;
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
    }
    // methods
    public void printState() {
        System.out.println("The coffee machine has:");
        System.out.println(this.water + " of water");
        System.out.println(this.milk + " of milk");
        System.out.println(this.beans + " of coffee beans");
        System.out.println(this.cups + " of disposable cups");
        System.out.println("$" + this.money + " of money");
    }
    private void buy(int type) {
        int water = 0;
        int milk = 0;
        int beans = 0;
        int money = 0;
        switch (type) {
            case 1: {
                water = 250;
                milk = 0;
                beans = 16;
                money = 4;
                break;
            }
            case 2: {
                water = 350;
                milk = 75;
                beans = 20;
                money = 7;
                break;
            }
            case 3: {
                water = 200;
                milk = 100;
                beans = 12;
                money = 6;
                break;
            }
            default: {
                break;
            }
        }
        if (this.water < water) {
            System.out.println("Sorry, not enough water!");
        } else if (this.milk < milk) {
            System.out.println("Sorry, not enough milk!");
        } else if (this.beans < beans) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (this.cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            this.water -= water;
            this.milk -= milk;
            this.beans -= beans;
            this.cups--;
            this.money += money;
        }
    }
    private void processBuy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String type = this.scanner.nextLine();
        if (type.equals("back")) {
            return;
        }
        this.buy(Integer.valueOf(type));
    }
    private void processfill() {
        System.out.println("Write how many ml of water do you want to add:");
        int water = scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        int milk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        int beans = scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int cups = scanner.nextInt();
        this.fill(water, milk, beans, cups);
    }
    private void fill(int water, int milk, int beans, int cups) {
        this.water += water;
        this.milk += milk;
        this.beans += beans;
        this.cups += cups;
    }
    private void take() {
        System.out.println("I gave you $" + this.money);
        this.money = 0;
    }
    public boolean processAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String action = this.scanner.nextLine();
        switch (action) {
            case "buy": {
                this.processBuy();
                break;
            }
            case "fill": {
                this.processfill();
                break;
            }
            case "take": {
                this.take();
                break;
            }
            case "remaining": {
                this.printState();
                break;
            }
            case "exit": {
                return false;
            }
            default: {
                break;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        CoffeeMachine cm = new CoffeeMachine(550, 400, 540, 120, 9);
        boolean status;
        do {
            status = cm.processAction();
        }
        while (status);
    }
}
