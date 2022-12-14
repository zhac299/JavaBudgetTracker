package com.tracker.budget;

import java.io.*;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Set;

public class BudgetController {
    Budget budget;

    BudgetController() {}

    public BudgetController(Budget budget) {
        this.budget = budget;
    }

    public Budget loadBudget() {
        try {
            FileInputStream fi = new FileInputStream(new File("src/main/resources/myObjects.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects

            Budget pr1 = (Budget) oi.readObject();
            System.out.println(pr1.toString());
            System.out.println(pr1.getSavings());

            oi.close();
            fi.close();

            budget = pr1; // set loaded budget to instance variable budget

            return pr1;

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public Budget saveBudget() {
        try {
            FileOutputStream f = new FileOutputStream(new File("src/main/resources/myObjects.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(budget);

            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }

        return budget;
    }

    public boolean calculateOnTargetSavedVsTotalExpenditure() {
        double calc = budget.getSavings() - budget.getTotalExpendituress();
        return calc > 0 ? true : false; // if calc is positive then savings is more than expenditures
    }

    public double calculateSavedVsTotalExpenditure() {
        double calc = budget.getSavings() - budget.getTotalExpendituress();
        return calc; // difference of savings and expenditures is returned.
    }

    public void addExpense(double amount) {
        budget.moneyInOut.put(LocalDate.now(), amount);
        System.out.println(amount + " added on " + LocalDate.now());
    }

    public void addExpense(double amount, LocalDate localDate) { // overloading the method from above to include localDate param
        budget.moneyInOut.put(localDate, amount);
        System.out.println(amount + " added on " + localDate);
    }

    public double calcWeeklyExpenditure() {
        LinkedHashMap<LocalDate, Double> hashMap = budget.getMoneyInOut();
        Set<LocalDate> keySet = hashMap.keySet();
        LocalDate lastDateFromSet = getLastValueFromSet(keySet);
        LocalDate priorWeekStartDate = lastDateFromSet.minusWeeks(1);
        double calc = 0.00;

        while (!(priorWeekStartDate.isAfter(lastDateFromSet))) {
            if (hashMap.get(priorWeekStartDate) != null) {
                calc += hashMap.get(priorWeekStartDate);
            }
            priorWeekStartDate = priorWeekStartDate.plusDays(1);
        }

        return calc;
    }

    public double calcMonthlyExpenditure() {
        LinkedHashMap<LocalDate, Double> hashMap = budget.getMoneyInOut();
        Set<LocalDate> keySet = hashMap.keySet();
        LocalDate lastDateFromSet = getLastValueFromSet(keySet);
        LocalDate priorMonthStartDate = lastDateFromSet.minusMonths(1);
        double calc = 0.00;

        while (!(priorMonthStartDate.isAfter(lastDateFromSet))) {
            if (hashMap.get(priorMonthStartDate) != null) {
                calc += hashMap.get(priorMonthStartDate);
            }
            priorMonthStartDate = priorMonthStartDate.plusDays(1);
        }

        return calc;
    }
    
    private LocalDate getLastValueFromSet(Set<LocalDate> set) {
        LocalDate lastElement = null;

        for (LocalDate element : set) {
            lastElement = element;
        }

        if (lastElement != null) {
            return lastElement;
        }
        return null;
    }
}