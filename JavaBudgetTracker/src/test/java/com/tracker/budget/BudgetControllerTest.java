package com.tracker.budget;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BudgetControllerTest {

    @Test
    void loadBudget() {
        Budget b = new Budget();
        BudgetController bc = new BudgetController(b);

        b.setEstSavings(1000.00);
        bc.saveBudget();

        if (b != null) {
            assertTrue(b.equals(bc.loadBudget()));
        }
    }

    @Test
    void saveBudget() {        // maybe better way to test save
        Budget b = new Budget();
        BudgetController bc = new BudgetController(b);

        b.setEstSavings(2000.00);
        bc.saveBudget();

        Budget b2 = bc.loadBudget();

        if (b2 != null) {
            assertEquals(2000.00, b2.getSavings());
        }

    }

    @Test
    void calculateonTargetSavedVsTotalExpenditure() {        
        Budget b = new Budget();
        BudgetController bc = new BudgetController(b);

        b.setEstSavings(100.00);
        bc.addExpense(100.00);

        assertFalse(bc.calculateOnTargetSavedVsTotalExpenditure());

        b.setEstSavings(200.00);
        assertTrue(bc.calculateOnTargetSavedVsTotalExpenditure());
    }

    @Test
    void calculateSavedVsTotalExpenditure() {        
        Budget b = new Budget();
        BudgetController bc = new BudgetController(b);

        b.setEstSavings(100.00);
        bc.addExpense(100.00);

        assertEquals(0.00, bc.calculateSavedVsTotalExpenditure());

        b.setEstSavings(200.00);
        assertEquals(100.00, bc.calculateSavedVsTotalExpenditure());
    }

    @Test
    void addExpense() {
        Budget b = new Budget();
        BudgetController bc = new BudgetController(b);

        bc.addExpense(150.00);

        assertEquals(150, b.getTotalExpendituress());
    }

    @Test
    void calcWeeklyExpenditure() {
        Budget b = new Budget();
        BudgetController bc = new BudgetController(b);

        LocalDate ld = LocalDate.now();
        ld = ld.minusWeeks(1);

        double sum = 0.00;

        for (int i = 0; i < 7; i++) {
            bc.addExpense(100.00);
            ld.plusDays(1);
            sum += 100;
        }
        assertEquals(sum, bc.calcWeeklyExpenditure());
    }

    @Test
    void calcMonthlyExpenditure() {
        Budget b = new Budget();
        BudgetController bc = new BudgetController(b);

        LocalDate ld = LocalDate.now();
        ld = ld.minusDays(30);

        double sum = 0.00;

        for (int i = 0; i < 31; i++) {
            bc.addExpense(100.00);
            ld.plusDays(1);
            sum += 100;
        }
        assertEquals(sum, bc.calcMonthlyExpenditure());
    }
}