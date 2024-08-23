package user;
import java.util.*;


public class User {
    private String userName;
    private float moneyTotal;
    private Map<String, Float> peopleList;
    private Map<Expense, Float> expensesOwed;


    public User(String name) {
        this.userName = name;
        this.moneyTotal = 0;
        this.peopleList = new HashMap<>();
        this.expensesOwed = new HashMap<>();
    }

    /* gets user's Name */
    public String getName() {
        return userName;
    }
    /* gets total amount owed */
    public float getUserTotal() {
        return moneyTotal;
    }

    /* gets names of people that user owed */
    public ArrayList<String> getNamesOwed() {
        if (!peopleList.isEmpty()) {
            return new ArrayList<>(peopleList.keySet()); 
        }
        return new ArrayList<>();
    }

    /* gets amount owed to a paricular person */
    public float getAmountOwedtoName(String name){
        if (!peopleList.isEmpty()) {
            return peopleList.get(name);
        }
        return 0;
    }

    /*adds Expense to user list */
    public void addExpenseToList(Expense expense, float amount) {
        this.expensesOwed.put(expense, amount);
    }

    /*gets name of expenses user owes */
    public List<String> getExpensesName() {
        List<String> expenseNames = new ArrayList<>();
        for (Expense expense : expensesOwed.keySet()) {
            expenseNames.add(expense.getPurpose()); // Assuming `getPurpose()` returns the name or description of the expense
        }
        if (expensesOwed.isEmpty()) {
            return new ArrayList<>();
        }
        return expenseNames;

    } 

    /* gets name */
    public float getAmountOwedForExpense(Expense expense) {
        return expensesOwed.getOrDefault(expense, 0f);
    }

    /* gets expense by name */
    public Expense getExpenseByName(String expenseName) {

        for (Expense expense : expensesOwed.keySet()) {
            if (expenseName == expense.getPurpose()) {
                return expense;
            }
            
        }
        return null;
    }

    /* get User by name */
   /* public User getUserByName(String name) {
        /* database to get all users 
        return new User(name);
    } */

    /* adds to total expense */
    public void addExpense(float amount) {
        moneyTotal += amount;
    }

    /* subtracts from total expense */
    public void paidExpense(float amount) {
        moneyTotal -= amount;
    }

    /* adds person to persons owed and amount owed */
    public void addOwed(String name, float amount) {


        if (peopleList.get(name) == null) {
            peopleList.put(name, amount);
        }
        else {
            float oldAmount = peopleList.get(name);
            float newAmount = oldAmount + amount;
            peopleList.replace(name, newAmount);
        }
        addExpense(amount);
    }


    /* subtracts amount that was owed, delete person to owe */
    public void clearOwed(String name, float amount) {
        float debtTotal = peopleList.get(name);
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