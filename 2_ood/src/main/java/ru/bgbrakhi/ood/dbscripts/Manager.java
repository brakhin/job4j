package ru.bgbrakhi.ood.dbscripts;

import java.util.*;
import java.util.stream.Collectors;

public class Manager {
    private final Map<Integer, VulnerabilityScript> scripts = new HashMap<>();

    public Manager init() {
        scripts.put(1, new VulnerabilityScript(1, Arrays.asList(2, 3, 4)));
        scripts.put(2, new VulnerabilityScript(2, Arrays.asList(5, 6)));
        scripts.put(3, new VulnerabilityScript(3, Arrays.asList(7, 8)));
        scripts.put(4, new VulnerabilityScript(4, Arrays.asList(9)));
        scripts.put(5, new VulnerabilityScript(5, Arrays.asList()));
        scripts.put(6, new VulnerabilityScript(6, Arrays.asList(10, 11)));
        scripts.put(7, new VulnerabilityScript(7, Arrays.asList()));
        scripts.put(8, new VulnerabilityScript(8, Arrays.asList(12, 13)));
        scripts.put(9, new VulnerabilityScript(9, Arrays.asList(14, 15, 16)));
        scripts.put(10, new VulnerabilityScript(10, Arrays.asList()));
        scripts.put(11, new VulnerabilityScript(11, Arrays.asList()));
        scripts.put(12, new VulnerabilityScript(12, Arrays.asList()));
        scripts.put(13, new VulnerabilityScript(13, Arrays.asList()));
        scripts.put(14, new VulnerabilityScript(14, Arrays.asList()));
        scripts.put(15, new VulnerabilityScript(15, Arrays.asList()));
        scripts.put(16, new VulnerabilityScript(16, Arrays.asList()));
        return this;
    }
    
    public List<Integer> before(Integer scriptId) {
        List<Integer> result = new ArrayList<>(scriptId);
        List<Integer> processed = new ArrayList<>();
        result.add(scriptId);
        List<Integer> children;
        Integer id = scriptId;
        int index = 0;
        while (index < result.size()) {
            id = result.get(index);
            if (processed.indexOf(id.intValue()) < 0) {
                processed.add(id);
                if ((children = scripts.get(id).getDependencies()).size() > 0) {
                    result.addAll(result.indexOf(id), children);
                    index = 0;
                } else {
                    index++;
                }
            } else {
                index++;
            }
        }
        return result;
    }
}
