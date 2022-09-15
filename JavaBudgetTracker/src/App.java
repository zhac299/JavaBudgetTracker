import main.java.budget.Budget;
import main.java.budget.BudgetController;

public class App {
    public static void main(String[] args) throws Exception {

        Budget b = new Budget();

        b.setEstSavings(200.00);
        b.setEstMonthlyBudget(50.00);
        b.setEstWeeklyBudget(1.50);

        BudgetController bc = new BudgetController(b);

        bc.saveBudget();
        bc.loadBudget();
    }
}