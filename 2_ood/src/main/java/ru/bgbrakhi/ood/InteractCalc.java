package ru.bgbrakhi.ood;

import ru.job4j.calculator.Calculator;

import java.util.Scanner;

public class InteractCalc {
    private double lastValue = 0d;
    private Calculator calc = new Calculator();

    public void calculate(String operation, double value) {
        if ("+".equals(lastValue)) {
            calc.add(lastValue, value);
        } else if ("-".equals(lastValue)) {
            calc.subtraction(lastValue, value);
        } else if ("*".equals(lastValue)) {
            calc.mult(lastValue, value);
        } else if ("/".equals(lastValue)) {
            calc.divide(lastValue, value);
        }
        lastValue = calc.getResult();
    }

    public double getLastValue() {
        return lastValue;
    }

    public static void main(String[] args) {
        InteractCalc ic = new InteractCalc();
        String input = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Required input : <operation> <value>, empty input for quit.\n"
                + "Available operations : +, -,*,/\n"
                + "Initial value : 0");
        do {
            System.out.print(String.valueOf(ic.getLastValue()) + " ");
            input = sc.nextLine();
            if (!input.isEmpty()) {
                String[] data = input.trim().split(" ");
                if (data.length != 2) {
                    System.out.println("Invalid input");
                } else {
                    ic.calculate(data[0], Double.parseDouble(data[1]));
                }
            }
        } while (!input.isEmpty());
    }
}
