package ru.bgbrakhi.ood.store;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ConrolQualityTest {

    @Test
    public void mainTest() {

        Food milk1 = null;
        Food meat1 = null;
        Food drink1 = null;
        Food milk2 = null;
        Food meat2 = null;
        Food drink2 = null;

        BasicStore pool = new BasicStore(new Warehouse(new Shop(new Trash(null))));

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            milk1 = new Milk("Молоко_1", sdf.parse("2019-05-12"), sdf.parse("2019-05-03"), 100);
            drink1 = new Drink("Сок_1", sdf.parse("2019-06-15"), sdf.parse("2019-01-01"), 100);
            meat1 = new Meat("Мясо_1", sdf.parse("2019-07-10"), sdf.parse("2019-04-25"), 100);
            drink2 = new Drink("Сок_2", sdf.parse("2019-04-15"), sdf.parse("2018-01-01"), 100);
            milk2 = new Milk("Молоко_2", sdf.parse("2019-05-06"), sdf.parse("2019-04-25"), 100);
            meat2 = new Meat("Мясо_2", sdf.parse("2019-04-10"), sdf.parse("2019-02-25"), 100);
            pool.add(milk1);  // < 25%
            pool.add(drink1); // 25 .. 75 %
            pool.add(meat1);  // < 25%
            pool.add(drink2); // > 1
            pool.add(milk2);  // > 75 %
            pool.add(meat2);  // > 1
            ConrolQuality cq = new ConrolQuality(pool);
            cq.analizeStore(sdf.parse("2019-05-04"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        BasicStore warehouse = (BasicStore) pool.component;
        BasicStore shop = (BasicStore) warehouse.component;
        BasicStore trash = (BasicStore) shop.component;

        assertTrue(Arrays.equals(warehouse.asArray(), new Food[]{milk1, meat1}));
        assertTrue(Arrays.equals(shop.asArray(), new Food[]{drink1, milk2}));
        assertTrue(Arrays.equals(trash.asArray(), new Food[]{drink2, meat2}));

        assertThat(milk2.getPrice(), is(80.0f));
    }
}