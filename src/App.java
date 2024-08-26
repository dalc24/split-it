import user.Expense;
import user.User;
import user.ExpensesPage.ExpensesPage;
import user.UserHomePage.UserHomePage;
import user.FauxDB;

import MainApplication.*;

public class App {
    public static void main(String[] args) throws Exception {

      FauxDB db = new FauxDB();

      User bob = new User("Bob");
      User alice = new User("Alice");
      User charlie = new User("Charlie");

      FauxDB.addUser(bob);
      FauxDB.addUser(alice);
      FauxDB.addUser(charlie);

      User[] participants = {alice, charlie};
      Expense expense = new Expense(bob, participants, 150, "Dinner", "Equal", 6, 20);
      FauxDB.addExpense(expense);

      User[] participants2 = {alice, bob};
      Expense expense2 = new Expense(charlie, participants2, 9, "Candy", "equal", 5, 20);
      FauxDB.addExpense(expense2);

      //expense.payExpense(alice, 20);
      System.out.println("Aliie owes bob: " + alice.getAmountOwedtoName("Bob"));

      User robert = new User("Robert");
      User sasha = new User("Sasha");
      User kate = new User("Kate");
      
      FauxDB.addUser(robert);
      FauxDB.addUser(sasha);
      FauxDB.addUser(kate);

      User[] participants3 = {alice, bob, robert, charlie, kate};
      Expense rent = new Expense(sasha, participants3, 750, "rent", "equal", 3, 20);
      FauxDB.addExpense(rent);

      Expense lunch = new Expense(bob, participants, 9, "for funzies", "equal", 4, 20);
      FauxDB.addExpense(lunch);

      System.out.println("Alice's expenses: " + alice.getExpensesName());

   //   System.out.println(FauxDB.getAllExpenses());

      //  alice.clearOwed("Sasha", 125);

      User barbie = new User("Barbie");
      FauxDB.addUser(barbie);

      //MainApplication mainApp = new MainApplication(alice);
      //mainApp.showPage();




    //  UserHomePage homePage = new UserHomePage(barbie);
        
        // Call the method to create and show the window
       // homePage.createAndShowWindow();

      // ExpensesPage expensesPage = new ExpensesPage(barbie);
       // expensesPage.createAndShowWindow();

   //   addExpensePage addPage = new addExpensePage(alice);
     // addPage.createAndShowWindow();

     User user = new User("username"); // Assuming you have a User class with a constructor
     FauxDB.addUser(user);
     

     // Initialize and display the main application with the user
     MainApplication mainApp = new MainApplication(user);
     mainApp.setVisible(true);
   //  UserHomePage homePage = new UserHomePage(alice, mainApp);
   //  homePage.createAndShowWindow();





       
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
