package ru.bgbrakhi.ood;

import java.util.Arrays;
import java.util.Scanner;

public class EngeneerCalc {

    private InteractCalc interactCalc;
    protected double lastValue = 0d;


    public EngeneerCalc(InteractCalc interactCalc) {
        this.interactCalc = interactCalc;
    }

    public void calculate(String operation, double value) {
        if ("sin".equals(operation)) {
            lastValue = Math.sin(lastValue);
        } else if ("cos".equals(operation)) {
            lastValue = Math.cos(lastValue);
        } else if ("tg".equals(operation)) {
            lastValue = Math.sin(lastValue) / Math.cos(lastValue);
        } else if ("ctg".equals(operation)) {
            lastValue = Math.cos(lastValue) / Math.sin(lastValue);
        } else {
            interactCalc.lastValue = this.lastValue;
            interactCalc.calculate(operation, value);
            lastValue = interactCalc.lastValue();
        }
    }

    public static void main(String[] args) {
        EngeneerCalc ec = new EngeneerCalc(new InteractCalc());
        String input = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Required input : <operation> <value> for BINARY operations (+ - * /), <operation> "
                + "for UNARY operations (sin cos tg ctg), empty input for quit.\n"
                + "Available operations : +, -,*,/\n"
                + "Initial value : 0");
        do {
            System.out.print(ec.lastValue + " ");
            input = sc.nextLine();
            if (!input.isEmpty()) {
                String[] data = input.trim().split(" ");
                if (data.length > 2 || data.length != 2 && Arrays.asList("+", "-", "*", "/").indexOf(data[0].trim().substring(0, 1)) != -1) {
                    System.out.println("Invalid input");
                } else if (data.length == 1) {
                    ec.calculate(data[0], 0);
                } else if (data.length == 2) {
                    ec.calculate(data[0], Double.parseDouble(data[1]));
                }
            }
        } while (!input.isEmpty());
    }

    public double lastValue() {
        return this.lastValue;
    }
}

