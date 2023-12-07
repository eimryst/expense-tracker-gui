import java.util.ArrayList;
import java.util.List;

class ExpenseTracker extends GUI {
    private List<Expense> expenses;

    // Constructor
    public ExpenseTracker() {
        expenses = new ArrayList<>();
    }

    // Setter
    public void addExpense(String category, double amount, String description) {
        Expense newExpense = new Expense(category, amount, description);
        expenses.add(newExpense);
    }

    // Getter
    public List<Expense> getExpenses() {
        return new ArrayList<>(expenses);
    }

    // Method to calculate total expenses
    private String printAllExpenses(String value) {
        for (Expense expense : expenses) {
            value = expense.getCategory() + ": " + expense.getDescription() + " ——————————————————— ₱"
                    + expense.getAmount() + "<br>";
        }
        return value;
    }

    public String allExpensesGetter(String value) {
        value = value + printAllExpenses(value);
        return value;
    }
}