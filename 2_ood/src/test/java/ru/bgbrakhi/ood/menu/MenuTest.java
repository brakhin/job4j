package ru.bgbrakhi.ood.menu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MenuTest {
    private PrintStream out = System.out;
    private ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private static final String LN = System.getProperty("line.separator");

    @Before
    public void setMemOutput() {
        System.setOut(new PrintStream(mem));
    }

    @After
    public void setStandartOutput() {
        System.setOut(out);
    }

    @Test
    public void testMenuPrint() {
        Menu menu = new Menu();
        menu.print();
        assertThat(mem.toString(), is(new StringJoiner(LN, "", "")
                .add("Item_1")
                .add("-GROUP_ITEM_1")
                .add("--Item_1_1")
                .add("--Item_1_2")
                .add("--GROUP_ITEM_1_1")
                .add("---Item_1_1_1")
                .add("---Item_1_1_2")
                .add("Item_2")
                .add("Item_3")
                .toString()
                + LN)
        );
    }

    @Test
    public void testMenuClick() {
        Menu menu = new Menu();
        menu.clickItem(5);
        assertThat(mem.toString(), is("Item #5 was clicked." + LN)
        );
    }
}