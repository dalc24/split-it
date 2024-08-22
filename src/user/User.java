package user;
import java.util.*;


public class User {
    private String userName;
    private int moneyTotal;
    private Map<String, Integer> peopleList;

    public User(String name) {
        this.userName = name;
        this.moneyTotal = 0;
        this.peopleList = new HashMap<>();
    }

    /* gets user's Name */
    public String getName() {
        return userName;
    }
    /* gets total amount owed */
    public int getUserTotal() {
        return moneyTotal;
    }

    /* gets names of people that user owed */
    public ArrayList<String> getNamesOwed() {
        if (!peopleList.isEmpty()) {
            return new ArrayList<>(peopleList.keySet()); 
        }
        return new ArrayList<>();
    }

    public int getAmountOwedtoName(String name){
        if (!peopleList.isEmpty()) {
            return peopleList.get(name);
        }
        return 0;
    }

    /* adds to total expense */
    public void addExpense(int amount) {
        moneyTotal += amount;
    }

    /* subtracts from total expense */
    public void paidExpense(int amount) {
        moneyTotal -= amount;
    }

    /* adds person to persons owed and amount owed */
    public void addOwed(String name, int amount) {


        if (peopleList.get(name) == null) {
            peopleList.put(name, amount);
        }
        else {
            int oldAmount = peopleList.get(name);
            int newAmount = oldAmount + amount;
            peopleList.replace(name, newAmount);
        }
        addExpense(amount);
    }


    /* subtracts amount that was owed, delete person to owe */
    public void clearOwed(String name, int amount) {
        int debtTotal = peopleList.get(name);
        debtTotal -= amount;

        if (debtTotal == 0) {
            peopleList.remove(name);
        }
        else {
            peopleList.replace(name, debtTotal);
        }
        moneyTotal -= amount;
    }

    public static void main(String[] args){
		System.out.println("This is a test");
        User Bob = new User("Bob");
        System.out.println("Name: " + Bob.getName());
        System.out.println("Expense: " + Bob.getUserTotal());
        System.out.println("People owed: " + Bob.getNamesOwed());

        System.out.println();
      /*  Bob.addOwed("Owen", 60);
        System.out.println("Expense: " + Bob.getUserTotal());
        System.out.println("People owed: " + Bob.getNamesOwed());
        
        System.out.println();
        Bob.addOwed("Ciara", 40);
        System.out.println("Expense: " + Bob.getUserTotal());
        System.out.println("People owed: " + Bob.getNamesOwed());

        System.out.println();
        Bob.clearOwed("Ciara", 40);
        System.out.println("Expense: " + Bob.getUserTotal());
        System.out.println("People owed: " + Bob.getNamesOwed());

        System.out.println();
        Bob.clearOwed(owen, 20);
        System.out.println("Expense: " + Bob.getUserTotal());
        System.out.println("People owed: " + Bob.getNamesOwed());

        System.out.println();
        Bob.clearOwed("Owen", 60);
        System.out.println("Expense: " + Bob.getUserTotal());
        System.out.println("People owed: " + Bob.getNamesOwed()); */
	
	}
}