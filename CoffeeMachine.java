package machine;
import java.util.Scanner;

public class CoffeeMachine {
    private enum CoffeeType {
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6);
        public int water;
        public int milk;
        public int beans;
        public int cost;
        public final int cups = 1;
        CoffeeType(int w, int m, int b, int c) {
            water = w;
            milk = m;
            beans = b;
            cost = c;
        }
    }
    enum State {
        MENU, BUY, WATER, MILK, BEANS, CUPS
    }
    // instance variables
    public int money;
    public int water;
    public int milk;
    public int beans;
    public int cups;
    public State state;
    private final String PROMPT_ACTION = "Write action (buy, fill, take, remaining, exit):\n";
    // constructor
    public CoffeeMachine(int money, int water, int milk, int beans, int cups) {
        this.money = money;
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        state = State.MENU;
        System.out.print(PROMPT_ACTION);
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
    public boolean process(String input) {
        switch (state) {
            case MENU:
                boolean keepRunning = processMenu(input);
                if (!keepRunning) {
                    return false;
                }
                break;
            case BUY:
                processBuy(input);
                break;
            case WATER:
                this.water += Integer.parseInt(input);
                changeState(State.MILK);
                break;
            case MILK:
                this.milk += Integer.parseInt(input);
                changeState(State.BEANS);
                break;
            case BEANS:
                this.beans += Integer.parseInt(input);
                changeState(State.CUPS);
                break;
            case CUPS:
                this.cups += Integer.parseInt(input);
                changeState(State.MENU);
                break;
            default:
                break;
        }
        return true;
    }
    private boolean processMenu(String input) {
        switch (input) {
            case "buy":
                changeState(State.BUY);
                break;
            case "fill":
                changeState(State.WATER);
                break;
            case "take":
                System.out.println();
                System.out.println("I gave you $" + this.money);
                this.money = 0;
                changeState(State.MENU);
                break;
            case "remaining":
                printState();
                changeState(State.MENU);
                break;
            case "exit":
                return false;
            default:
                changeState(State.MENU);
                break;
        }
        return true;
    }
    private void changeState(State next) {
        switch (next) {
            case MENU:
                state = State.MENU;
                System.out.println();
                System.out.print(PROMPT_ACTION);
                break;
            case BUY:
                state = State.BUY;
                System.out.println();
                System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:\n");
                break;
            case WATER:
                state = State.WATER;
                System.out.println();
                System.out.print("Write how many ml of water do you want to add:\n");
                break;
            case MILK:
                state = State.MILK;
                System.out.print("Write how many ml of milk do you want to add:\n");
                break;
            case BEANS:
                state = State.BEANS;
                System.out.print("Write how many grams of coffee beans do you want to add:\n");
                break;
            case CUPS:
                state = State.CUPS;
                System.out.print("Write how many disposable cups of coffee do you want to add:\n");
                break;
        }
    }
    private void processBuy(String input) {
        if (input.equals("back")) {
            changeState(State.MENU);
            return;
        }
        int choice = Integer.parseInt(input);
        CoffeeType type = CoffeeType.ESPRESSO;
        switch (choice) {
            case 1:
                type = CoffeeType.ESPRESSO;
                break;
            case 2:
                type = CoffeeType.LATTE;
                break;
            case 3:
                type = CoffeeType.CAPPUCCINO;
                break;
            default:
                break;
        }
        if (this.water < type.water) {
            System.out.println("Sorry, not enough water!");
            changeState(State.MENU);
        } else if (this.milk < type.milk) {
            System.out.println("Sorry, not enough milk!");
            changeState(State.MENU);
        } else if (this.beans < type.beans) {
            System.out.println("Sorry, not enough beans!");
            changeState(State.MENU);
        } else if (this.cups < 1) {
            System.out.println("Sorry, not enough cups!");
            changeState(State.MENU);
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            this.water -= type.water;
            this.milk -= type.milk;
            this.beans -= type.beans;
            this.cups -= type.cups;
            this.money += type.cost;
            changeState(State.MENU);
        }
    }
    public static void main(String[] args) {
        CoffeeMachine cm = new CoffeeMachine(550, 400, 540, 120, 9);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            if (!cm.process(scanner.next())) {
                return;
            }
        }
    }
}
