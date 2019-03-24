package ru.bgbrakhi.depsort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DepRef {

    private List<String> data;

    public DepRef() {
        data = new ArrayList<>();
    }

    public List<String> getData() {
        return data;
    }

    public void addDep(String department) {
        if (!data.contains(department)) {
            data.add(department);
        }
    }

    public void validateData() {
        for (int i = 0; i < data.size(); i++) {
            String dep = data.get(i);
            int slashIndex = dep.lastIndexOf("\\");
            if (slashIndex > 0) {
                String masterDep = dep.substring(0, slashIndex);
                if (!data.contains(masterDep)) {
                    data.add(masterDep);
                }
            }
        }
    }

    public String toString() {
        return data.toString();
    }
}
