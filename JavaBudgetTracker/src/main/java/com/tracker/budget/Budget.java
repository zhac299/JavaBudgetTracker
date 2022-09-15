package com.tracker.budget;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashMap;

public class Budget implements Serializable {
    private double monthlyBudget; // actual monthly budget
    private double estMonthlyBudget; // monthly budget goal
    private double weeklyBudget; // actual weekly budget
    private double estWeeklyBudget; // weekly budget goal
    private double estSavings; // savings goal
    private double totalExpenditures; // sum of all expenditures from moneyInOut
    LinkedHashMap<LocalDate, Double> moneyInOut = new LinkedHashMap<>(); // money going In and Out of budget

    public double getMonthlyBudget() {
        return this.monthlyBudget;
    }

    public double getEstMonthlyBudget() {
        return this.estMonthlyBudget;
    }

    public double getWeeklyBudget() {
        return this.weeklyBudget;
    }

    public double getEstWeeklyBudget() {
        return this.estWeeklyBudget;
    }

    public double getSavings() {
        return this.estSavings;
    }

    public double getTotalExpendituress() {
        double i = 0.00;
        for(double j : moneyInOut.values()) {
            i += j;
        }
        this.totalExpenditures = i;
        return i;
    }

    public LinkedHashMap<LocalDate, Double> getMoneyInOut() {
        return this.moneyInOut;
    }

    public void setMonthlyBudget(double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
    }

    public void setEstMonthlyBudget(double estMonthlyBudget) {
        this.estMonthlyBudget = estMonthlyBudget;
    }

    public void setWeeklyBudget(double weeklyBudget) {
        this.weeklyBudget = weeklyBudget;
    }

    public void setEstWeeklyBudget(double estWeeklyBudget) {
        this.estWeeklyBudget = estWeeklyBudget;
    }

    public void setEstSavings(double estSavings) {
        this.estSavings = estSavings;
    }

    @Override
    public String toString() {
        return "Monthly Budget: " + monthlyBudget + " " +
                "Est Monthly Budget: " + estMonthlyBudget + " " +
                "Weekly Budget: " + weeklyBudget + " " +
                "Est Weekly Budget: " + estWeeklyBudget + " " +
                "Est Savings: " + estSavings + " " +
                "Total Expenditures: " + totalExpenditures + " " +
                "MoneyInOut: " + moneyInOut ;
    }
}