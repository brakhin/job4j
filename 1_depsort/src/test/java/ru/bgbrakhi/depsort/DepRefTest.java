package ru.bgbrakhi.depsort;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DepRefTest {

    @Test
    public void sortAsc() {
        DepRef dr = new DepRef();
        dr.addDep("K1\\SK1\\SSK1");
        dr.addDep("K1\\SK1\\SSK2");
        dr.addDep("K2\\SK1\\SSK1");
        dr.addDep("K2\\SK1\\SSK2");
        dr.sortAsc();
        assertThat(dr.toString(), is("[K1, K1\\SK1, K1\\SK1\\SSK1, K1\\SK1\\SSK2, K2, K2\\SK1, K2\\SK1\\SSK1, K2\\SK1\\SSK2]"));
        dr.sortDesc();
        assertThat(dr.toString(), is("[K2, K2\\SK1, K2\\SK1\\SSK1, K2\\SK1\\SSK2, K1, K1\\SK1, K1\\SK1\\SSK1, K1\\SK1\\SSK2]"));
    }
}