package ru.bgbrakhi.depsort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DepRef {

    private List<String> data;

    private Comparator<String> comparatorMasterAsc;
    private Comparator<String> comparatorMasterDesc;
    private Comparator<String> comparatorChild;

    public DepRef() {
        data = new ArrayList<>();

        comparatorMasterAsc = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String dep1 = o1.substring(0, Math.max(0, o1.indexOf("\\")));
                if (dep1.isEmpty()) {
                    dep1 = o1;
                }
                String dep2 = o2.substring(0, Math.max(0, o2.indexOf("\\")));
                if (dep2.isEmpty()) {
                    dep2 = o2;
                }
                return dep1.compareTo(dep2);
            }
        };
        comparatorMasterDesc = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String dep1 = o1.substring(0, Math.max(0, o1.indexOf("\\")));
                if (dep1.isEmpty()) {
                    dep1 = o1;
                }
                String dep2 = o2.substring(0, Math.max(0, o2.indexOf("\\")));
                if (dep2.isEmpty()) {
                    dep2 = o2;
                }
                return dep2.compareTo(dep1);
            }
        };
        comparatorChild = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };

    }

    public void addDep(String department) {
        if (!data.contains(department)) {
            data.add(department);
        }
    }

    private void validateData() {
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

    public void sortAsc() {
        validateData();
        data.sort(comparatorMasterAsc.thenComparing(comparatorChild));
    }

    public void sortDesc() {
        validateData();
        data.sort(comparatorMasterDesc.thenComparing(comparatorChild));
    }

    public String toString() {
        return data.toString();
    }
}
