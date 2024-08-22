package user;
import java.util.*;  

public class Expense {
    private User userPaid;
    private User[] usersOwe; 
    private float amount;
    private String purpose; 
    private float partySize;
    private Map<String, Float> userOwedAmounts;
    private int month;
    private int date;

    private float statusPaid;

    public Expense(User user, User[] users, float amount, String purpose, String typeSplit, int month, int date) {
        this.userPaid = user;
        this.usersOwe = users;
        this.amount = amount;
        this.purpose = purpose;
        this.partySize = 1 + users.length;
        this.statusPaid = amount;
        this.userOwedAmounts = new HashMap<>();
        this.month = month;
        this.date = date;

        if (typeSplit == "equal" || typeSplit == "Equal") {
            equalSplit();
        }

    }

    public String getPurpose() {
        return purpose;
    }

    public float getExpenseAmount() {
        return amount;
    }

    public String getUserPaid() {
        return userPaid.getName();
    }

    public float getMonth() {
        return month;
    }

    public float getDate() throws Exception {

        ArrayList<Integer> months31 = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 8, 10, 12));
        ArrayList<Integer> months30 = new ArrayList<>(Arrays.asList(4, 6, 9, 11));


        if (month == 2) { 
            if (date > 28) {
                throw new Exception("error: February cannot have more than 28 days.");
            }
        } else if (months31.contains(month)) { 
            if (date > 31) {
                throw new Exception("error: This month cannot have more than 31 days.");
            }
        } else if (months30.contains(month)) { 
            if (date > 30) {
                throw new Exception("error: This month cannot have more than 30 days.");
            }
        } else {
            throw new Exception("error: Invalid month.");
        }
        return date;
    }


    public ArrayList<String> getUsersInvolved() {
        ArrayList<String> usersInvolved = new ArrayList<String>(); 
        for (User user : usersOwe ) {
            usersInvolved.add(user.getName());
        }
        return usersInvolved;
    }

    public float getPartySize() {
        return partySize;
    }

    public void equalSplit() {
        float splitAmount = amount / partySize;

        //System.out.println(splitAmount);

        for (User user : usersOwe) {
            user.addOwed(userPaid.getName(), splitAmount);
            userOwedAmounts.put(user.getName(), userOwedAmounts.getOrDefault(userOwedAmounts, 0f) + splitAmount);

        }
    }

    public Map<String, Float> getUserOwedAmounts() {
        return new HashMap<>(userOwedAmounts);
    }

    public void payExpense(User user, float amount) {
        float userOwes = userOwedAmounts.get(user.getName());
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
