package com.tracker.budget;

import java.util.Scanner;

public class BudgetTrackerConsole {

    public static void startBudgetTracker(Budget budget, BudgetController budgetController) {

        System.out.println("Would you like to set a Monthly Budget or a Weekly Budget or both?");
        System.out.println("Please enter one of the following options: ");
        System.out.println("M - Monthly");
        System.out.println("W - Weekly");
        System.out.println("B - Both");

        Scanner scan = new Scanner(System.in);
        
        if (scan.next().equals("M")) {
            System.out.println("Please enter your Monthly Budget: ");
            budget.setEstMonthlyBudget(scan.nextInt());
        } else if (scan.next().equals("W")) {
            System.out.println("Please enter your Weekly Budget: ");
            budget.setEstWeeklyBudget(scan.nextInt());
        } else if (scan.next().equals("B")) {

            System.out.println("Please enter your Monthly Budget: ");
            budget.setEstMonthlyBudget(scan.nextInt());

            System.out.println("Please enter your Weekly Budget: ");
            budget.setEstWeeklyBudget(scan.nextInt());
        }

        System.out.println("Please enter your overall Savings Goal: ");
        budget.setEstSavings(scan.nextInt());

        budget.created();

        addExpense(budget, budgetController);

        scan.close();
    }

    public static void addExpense(Budget budget, BudgetController budgetController) {

        boolean done = false;

        System.out.println("Would you like to add an expenditure? Y/N");
        Scanner scan = new Scanner(System.in);

        if (scan.next().equals("Y")) {
            
            while (done == false) {
                System.out.println("Please enter an expenditure: ");
                budgetController.addExpense(scan.nextInt());
                
                System.out.println("Would you like to add another expenditure? Y/N");
                
                if (scan.next().equals("N")) {
                    done = true;
                }
            }

            showVariables(budget, budgetController);

        } else if (scan.next().equals("N")) {

            System.out.println("Would you like to view any specific variables? Y/N");

            boolean done2 = false;
            String output = "";

            while (done2 == false) {
                done2 = dialogueLoop(scan, output);

                if (done2 == true) {
                    if (output.equals("Y")) {
                        System.out.println("Please select a variable: ");
                        showVariables(budget, budgetController);
                    } else if (output.equals("N")){
                        budget.toString();
                        System.out.println("Would you like to end the program? Y/N");
                        dialogueLoop(scan, output);
                    }
                }
            }
        }

        scan.close();
    }

    public static void showVariables(Budget budget, BudgetController budgetController) {
        
    }

    public static void save(Budget budget, BudgetController budgetController) {

    }

    public static boolean dialogueLoop(Scanner scan, String output) {
        if (scan.next().equals("Y")) {
            output = "Y";
            return true;
        } else if (scan.next().equals("N")) {
            output = "N";
            return true;
        } else {
            System.out.println("Please enter a valid value! Y/N");
            return dialogueLoop(scan, output);           
        }
    }
    
}
