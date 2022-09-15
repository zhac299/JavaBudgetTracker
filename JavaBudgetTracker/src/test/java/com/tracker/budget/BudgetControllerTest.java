package com.tracker.budget;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BudgetControllerTest {

    @Test
    void loadBudget() {
        BudgetController bc = new BudgetController();
        Budget b = new Budget();
        b.setEstSavings(1000.00);
        bc.saveBudget(b);


        if (b != null) {
            assertTrue(b.equals(bc.loadBudget()));
        }
    }

    @Test
    void saveBudget() {
        BudgetController bc = new BudgetController();
        Budget b = new Budget();

        b.setEstSavings(2000.00);
        bc.saveBudget(b);

        Budget b2 = bc.loadBudget();

        if (b2 != null) {
            assertEquals(2000.00, b2.getSavings());
        }

    }

    @Test
    void calculateSavedVsTotalExpenditure() {
        BudgetController bc = new BudgetController();
        Budget b = new Budget();

        b.setEstSavings(100.00);
        bc.addExpense(100.00, b);

        assertEquals(0.00, bc.calculateSavedVsTotalExpenditure());
    }

    @Test
    void addExpense() {
        BudgetController bc = new BudgetController();
        Budget b = new Budget();

        bc.addExpense(150.00, b);

        assertEquals(150, b.getTotalExpendituress());
    }

    @Test
    void calcWeeklyExpenditure() {
        BudgetController bc = new BudgetController();
        Budget b = new Budget();

        LocalDate ld = LocalDate.now();
        ld = ld.minusWeeks(1);

        double sum = 0.00;

        for (int i = 0; i < 7; i++) {
            bc.addExpense(100.00, b);
            ld.plusDays(1);
            sum += 100;
        }
        assertEquals(sum, bc.calcWeeklyExpenditure(b));
    }

    @Test
    void calcMonthlyExpenditure() {
        BudgetController bc = new BudgetController();
        Budget b = new Budget();

        LocalDate ld = LocalDate.now();
        ld = ld.minusDays(30);

        double sum = 0.00;

        for (int i = 0; i < 31; i++) {
            bc.addExpense(100.00, b);
            ld.plusDays(1);
            sum += 100;
        }
        assertEquals(sum, bc.calcMonthlyExpenditure(b), "Weekly expenditure calculated");
    }
}