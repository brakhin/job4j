package ru.bgbrakhi.depsort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DepSorter {
    private DepRef deps;

    private Comparator<String> comparatorAsc;
    private Comparator<String> comparatorDesc;

    public DepSorter(DepRef deps) {
        this.deps = deps;

        comparatorAsc = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result = 0;

                String[] arr1 = o1.split("\\\\", 3);
                arr1 = Arrays.copyOf(arr1, 3);

                String[] arr2 = o2.split("\\\\", 3);
                arr2 = Arrays.copyOf(arr2, 3);

                for (int i = 0; i < 3; i++) {

                    int cval = arr1[i] == null ? -1 : (arr2[i] == null ? 1 : arr1[i].compareTo(arr2[i]));
                    if (cval != 0) {
                        result = cval;
                        break;
                    }
                }
                return result;
            }
        };

        comparatorDesc = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result = 0;

                String[] arr1 = o1.split("\\\\", 3);
                arr1 = Arrays.copyOf(arr1, 3);

                String[] arr2 = o2.split("\\\\", 3);
                arr2 = Arrays.copyOf(arr2, 3);

                for (int i = 0; i < 3; i++) {

                    int cval = arr2[i] == null ? 1 : (arr1[i] == null ? -1 : arr2[i].compareTo(arr1[i]));
                    if (cval != 0) {
                        result = cval;
                        break;
                    }
                }
                return result;
            }
        };
    }

    public List<String> sortAsc() {
        deps.validateData();
        List<String> result = deps.getData();
        result.sort(comparatorAsc);
        return result;
    }

    public List<String> sortDesc() {
        deps.validateData();
        List<String> result = deps.getData();
        result.sort(comparatorDesc);
        return result;
    }

}
