package ru.bgbrakhi.ood.ticktacktoe;

import java.util.Scanner;

public class Input implements IInput {

    private Scanner sc = new Scanner(System.in);

    @Override
    public String getInput() {
        System.out.print("Enter position (line col) ");
        return sc.nextLine().trim();
    }
}
