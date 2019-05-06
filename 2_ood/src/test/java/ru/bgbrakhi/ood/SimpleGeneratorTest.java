package ru.bgbrakhi.ood;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleGeneratorTest {

    private final String testStr = "I am ${name}. Who are ${subject} ?";

    @Test
    public void whenSetEqualValuesThanGetString() throws MoreValuesException, LessValuesException {
        Map<String, String> values = new HashMap();
        values.put("name", "Boris");
        values.put("subject", "you");
        SimpleGenerator sg = new SimpleGenerator(values);
        assertThat(sg.calcString(testStr), is("I am Boris. Who are you ?"));
    }

    @Test(expected = MoreValuesException.class)
    public void whenSetMoreValuesThanGetString() throws LessValuesException, MoreValuesException {
        Map<String, String> values = new HashMap();
        values.put("name", "Boris");
        values.put("subject", "you");
        values.put("some key", "some value");
        SimpleGenerator sg = new SimpleGenerator(values);
        sg.calcString(testStr);
    }

    @Test(expected = LessValuesException.class)
    public void whenSetLessValuesThanGetString() throws LessValuesException, MoreValuesException {
        Map<String, String> values = new HashMap();
        values.put("name", "Boris");
        SimpleGenerator sg = new SimpleGenerator(values);
        sg.calcString(testStr);
    }

}