package machine;
import java.util.Scanner;

public class CoffeeMachine {
    // instance variables
    public int money;
    public int water;
    public int milk;
    public int beans;
    public int cups;
    // constructor
    public CoffeeMachine(int money, int water, int milk, int beans, int cups) {
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
    public void buy(Scanner scanner) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String item = scanner.next();
        switch (item) {
            case "1":
                if (this.water < 250) {
                    System.out.println("Sorry, not enough water!");
                    break;
                } else if (this.beans < 16) {
                    System.out.println("Sorry, not enough beans!");
                    break;
                } else if (this.cups < 1) {
                    System.out.println("Sorry, not enough cups!");
                    break;
                } else {
                    this.money += 4;
                    this.water -= 250;
                    this.beans -= 16;
                    this.cups -= 1;
                    System.out.println("I have enough resources, making you a coffee!");
                    break;
                }
            case "2":
                if (this.milk < 75) {
                    System.out.println("Sorry, not enough milk!");
                    break;
                } else if (this.water < 350) {
                    System.out.println("Sorry, not enough water!");
                    break;
                } else if (this.beans < 20) {
                    System.out.println("Sorry, not enough beans!");
                    break;
                } else if (this.cups < 1) {
                    System.out.println("Sorry, not enough cups!");
                    break;
                } else {
                    this.money += 7;
                    this.water -= 350;
                    this.milk -= 75;
                    this.beans -= 20;
                    this.cups -= 1;
                    System.out.println("I have enough resources, making you a coffee!");
                    break;
                }
            case "3":
                if (this.milk < 100) {
                    System.out.println("Sorry, not enough milk!");
                    break;
                } else if (this.water < 200) {
                    System.out.println("Sorry, not enough water!");
                    break;
                } else if (this.beans < 12) {
                    System.out.println("Sorry, not enough beans!");
                    break;
                } else if (this.cups < 1) {
                    System.out.println("Sorry, not enough cups!");
                    break;
                } else {
                    this.money += 6;
                    this.water -= 200;
                    this.milk -= 100;
                    this.beans -= 12;
                    this.cups -= 1;
                    System.out.println("I have enough resources, making you a coffee!");
                    break;
                }
            case "back":
                break;
            default: System.out.println("Choose buy to try again.");
        }
    }
    public void fill(Scanner scanner) {
        System.out.println("Write how many ml of water do you want to add:");
        int waterAdded = scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add::");
        int milkAdded = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        int beansAdded = scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int cupsAdded = scanner.nextInt();
        water += waterAdded;
        milk += milkAdded;
        beans += beansAdded;
        cups += cupsAdded;
    }
    public void take() {
        int moneyToTake = money;
        money = 0;
        System.out.println("I gave you $" + moneyToTake);
    }
    public static void main(String[] args) {
        CoffeeMachine cm = new CoffeeMachine(550, 400, 540, 120, 9);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = scanner.next();
            if (action.equals("exit")) {
                break;
            }
            switch (action) {
                case "buy":
                    System.out.println();
                    cm.buy(scanner);
                    System.out.println();
                    break;
                case "fill":
                    System.out.println();
                    cm.fill(scanner);
                    System.out.println();
                    break;
                case "take":
                    System.out.println();
                    cm.take();
                    System.out.println();
                    break;
                case "remaining":
                    System.out.println();
                    cm.printState();
                    System.out.println();
                    break;
                default:
                    System.out.println("action must be buy, fill, take, remaining, or exit");
            }

        }
    }
}
