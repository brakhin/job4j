package ru.bgbrakhi.io;

import java.io.InputStream;
import java.util.Scanner;

public class IoDemo {

    boolean isNumber(InputStream in) {
        boolean result = false;
        try {
            Scanner sc = new Scanner(in);
            result = sc.hasNextInt() && sc.nextInt() % 2 == 0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
