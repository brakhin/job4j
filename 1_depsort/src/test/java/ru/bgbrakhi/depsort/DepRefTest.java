package ru.bgbrakhi.depsort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DepRefTest {

    private DepRef dr;
    private DepSorter ds;
    private List<String> etalonAsc;
    private List<String> etalonDesc;

    @Before
    public void init() {
        dr = new DepRef();
        dr.addDep("K1\\SK2");
        dr.addDep("K1\\SK1\\SSK1");
        dr.addDep("K1\\SK1\\SSK2");
        dr.addDep("K2\\SK1\\SSK1");
        dr.addDep("K2\\SK1\\SSK2");
        ds = new DepSorter(dr);

        etalonAsc = new ArrayList<>();
        etalonAsc.add("K1");
        etalonAsc.add("K1\\SK1");
        etalonAsc.add("K1\\SK1\\SSK1");
        etalonAsc.add("K1\\SK1\\SSK2");
        etalonAsc.add("K1\\SK2");
        etalonAsc.add("K2");
        etalonAsc.add("K2\\SK1");
        etalonAsc.add("K2\\SK1\\SSK1");
        etalonAsc.add("K2\\SK1\\SSK2");

        etalonDesc = new ArrayList<>();
        etalonDesc.add("K2");
        etalonDesc.add("K2\\SK1");
        etalonDesc.add("K2\\SK1\\SSK2");
        etalonDesc.add("K2\\SK1\\SSK1");
        etalonDesc.add("K1");
        etalonDesc.add("K1\\SK2");
        etalonDesc.add("K1\\SK1");
        etalonDesc.add("K1\\SK1\\SSK2");
        etalonDesc.add("K1\\SK1\\SSK1");
    }

    @Test
    public void sortAsc() {
        List<String> result = ds.sortAsc();
        Assert.assertEquals(result, etalonAsc);
    }

    @Test
    public void sortDesc() {
        List<String> result = ds.sortDesc();
        Assert.assertEquals(result, etalonDesc);
    }
}

