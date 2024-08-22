import user.Expense;
import user.User;

public class App {
    public static void main(String[] args) throws Exception {

        User bob = new User("Bob");
        User alice = new User("Alice");
        User charlie = new User("Charlie");
        User[] participants = {alice, charlie};
        Expense expense = new Expense(bob, participants, 150, "Dinner", "Equal", 6, 20);

        User[] participants2 = {alice, bob};
        Expense expense2 = new Expense(charlie, participants2, 9, "Candy", "equal", 5, 20);

        //expense.payExpense(alice, 20);
        System.out.println("Aliie owes bob: " + alice.getAmountOwedtoName("Bob"));

        User robert = new User("Robert");
        User sasha = new User("Sasha");
        User kate = new User("Kate");

        User[] participants3 = {alice, bob, robert, charlie, kate};
        Expense rent = new Expense(sasha, participants3, 750, "rent", "equal", 3, 20);

        Expense lunch = new Expense(bob, participants, 9, "for funzies", "equal", 4, 20);

      //  alice.clearOwed("Sasha", 125);



        UserHomePage homePage = new UserHomePage(alice);
        
        // Call the method to create and show the window
        homePage.createAndShowWindow();
       
/* 
        System.out.println();
        System.out.println("User Paid: " + expense.getUserPaid());
        System.out.println("Expense Amount: " + expense.getExpenseAmount());
        System.out.println("Users Involved: " + expense.getUsersInvolved());
        System.out.println();

        expense.equalSplit("equal");
        System.out.println("How much owed: " + expense.getUserOwedAmounts());

        System.out.println();

        System.out.println("Name: " + alice.getName());
        System.out.println("Expense: " + alice.getUserTotal());
        System.out.println("People owed: " + alice.getNamesOwed());

        expense.payExpense(alice, 40);
        System.out.println("Alice pays");
        System.out.println("How much owed: " + expense.getUserOwedAmounts());
        System.out.println("Expense: " + alice.getUserTotal());
        System.out.println("People owed: " + alice.getNamesOwed()); */



        
    }
}
