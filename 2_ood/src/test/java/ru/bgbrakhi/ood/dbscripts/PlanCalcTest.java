package ru.bgbrakhi.ood.dbscripts;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PlanCalcTest {


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

    @Test
    public void newTest() {
        Manager manager = new Manager().init();
        Assert.assertThat(manager.before(1).toString(), is("[5, 10, 11, 6, 2, 7, 12, 13, 8, 3, 14, 15, 16, 9, 4, 1]"));
    }
}