import user.Expense;
import user.User;

public class App {
    public static void main(String[] args) throws Exception {
        User bob = new User("Bob");
        User alice = new User("Alice");
        User charlie = new User("Charlie");
        User[] participants = {alice, charlie};
        Expense expense = new Expense(bob, participants, 100, "Dinner", "Equal");

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
        System.out.println("People owed: " + alice.getNamesOwed());



        
    }
}
