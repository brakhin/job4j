package ru.bgbrakhi.ood.dbscripts;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PlanCalcTest {

    @Test
    public void mainTest() {

    /* Схема зависимостей

                1
              / | \
             /  |  \
            /   |   \
           /    |    \
          /     |     \
         2      3       4
        / \    / \      |
       5   6  7   8     9
          / \    /\    /|\
         10 11 12  13 / | \
                    14 15  16

     */
        DatabaseEmulator db = DatabaseEmulator.getInstance();
        db.add(new VulnerabilityScript(1, Arrays.asList(2, 3, 4)));
        db.add(new VulnerabilityScript(2, Arrays.asList(5, 6)));
        db.add(new VulnerabilityScript(3, Arrays.asList(7, 8)));
        db.add(new VulnerabilityScript(4, Arrays.asList(9)));
        db.add(new VulnerabilityScript(5, Arrays.asList()));
        db.add(new VulnerabilityScript(6, Arrays.asList(10, 11)));
        db.add(new VulnerabilityScript(7, Arrays.asList()));
        db.add(new VulnerabilityScript(8, Arrays.asList(12, 13)));
        db.add(new VulnerabilityScript(9, Arrays.asList(14, 15, 16)));
        db.add(new VulnerabilityScript(10, Arrays.asList()));
        db.add(new VulnerabilityScript(11, Arrays.asList()));
        db.add(new VulnerabilityScript(12, Arrays.asList()));
        db.add(new VulnerabilityScript(13, Arrays.asList()));
        db.add(new VulnerabilityScript(14, Arrays.asList()));
        db.add(new VulnerabilityScript(15, Arrays.asList()));
        db.add(new VulnerabilityScript(16, Arrays.asList()));

        PlanCalc pc = new PlanCalc(db.get(1));
        Assert.assertThat(pc.getDepChain().toString(), is("[5, 10, 11, 6, 2, 7, 12, 13, 8, 3, 14, 15, 16, 9, 4, 1]"));
    }
}