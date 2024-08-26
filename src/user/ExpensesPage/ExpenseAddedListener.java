package user.ExpensesPage;
import user.*;

public interface ExpenseAddedListener {
    void onExpenseAdded(Expense expense);
    void onExpensePaid(Expense expense);
}