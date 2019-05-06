package ru.bgbrakhi.ood;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleGenerator {
    private static final Pattern pattern = Pattern.compile("[$][{][a-z]+[}]+?");
    private final Map<String, String> values;

    public SimpleGenerator(Map<String, String> values) {
        this.values = values;
    }

    public String calcString(String str) throws MoreValuesException, LessValuesException {
        String key, value;
        int keys = 0;
        String result = str;
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            key = str.substring(matcher.start() + 2, matcher.end() - 1);
            value = values.get(key);
            if (value == null) {
                throw new LessValuesException();
            }
            result = result.replace(String.format("${%s}", key), value);
            keys++;
        }
        if (keys < values.size()) {
            throw new MoreValuesException();
        }
        return result;
    }
}
