package user;
import java.util.*;  

public class Expense {
    private User userPaid;
    private User[] usersOwe; 
    private int amount;
    private String purpose; 
    private int partySize;
    private String typeSplit;
    private Map<String, Integer> userOwedAmounts;

    private int statusPaid;

    public Expense(User user, User[] users, int amount, String purpose, String typeSplit) {
        this.userPaid = user;
        this.usersOwe = users;
        this.amount = amount;
        this.purpose = purpose;
        this.partySize = 1 + users.length;
        this.typeSplit = typeSplit;
        this.statusPaid = amount;
        this.userOwedAmounts = new HashMap<>();

        if (typeSplit == "equal" || typeSplit == "Equal") {
            equalSplit();
        }

    }

    public String getPurpose() {
        return purpose;
    }

    public int getExpenseAmount() {
        return amount;
    }

    public String getUserPaid() {
        return userPaid.getName();
    }

    public ArrayList<String> getUsersInvolved() {
        ArrayList<String> usersInvolved = new ArrayList<String>(); 
        for (User user : usersOwe ) {
            usersInvolved.add(user.getName());
        }
        return usersInvolved;
    }

    public int getPartySize() {
        return partySize;
    }

    public void equalSplit() {
        int splitAmount = amount / partySize;

        //System.out.println(splitAmount);

        for (User user : usersOwe) {
            user.addOwed(userPaid.getName(), splitAmount);
            userOwedAmounts.put(user.getName(), userOwedAmounts.getOrDefault(userOwedAmounts, 0) + splitAmount);

        }
    }

    public Map<String, Integer> getUserOwedAmounts() {
        return new HashMap<>(userOwedAmounts);
    }

    public void payExpense(User user, int amount) {
        int userOwes = userOwedAmounts.get(user.getName());
        userOwes -= amount;

        userOwedAmounts.replace(user.getName(), userOwes);
        statusPaid -= amount;

        //edits to User
        user.clearOwed(user.getName(), amount);
        
    }

    public boolean isExpensePaid() {
        if (statusPaid == 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        System.out.println(("hello"));
    }

    
    
}
