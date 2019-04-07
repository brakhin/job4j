package ru.bgbrakhi.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileScanner {

    private List<String> extensions;

    public List<File> getFiles(String rootDir, List<String> extensions) {
        this.extensions = extensions;
        List<File> result = new ArrayList<>();
        searchFiles(result, new File(rootDir));
        return result;
    }

    private void searchFiles(List<File> result, File root) {
        if (root.isDirectory()) {
            // если каталог
            for (File file : root.listFiles()) {
                searchFiles(result, file);
            }
        } else {
            // если файл
            String fileName = root.getName();
            int index = fileName.lastIndexOf(".");
            if (index != -1) {
                String fileExt = fileName.substring(index);
                if (extensions.contains(fileExt)) {
                    result.add(root);
                }
            }
        }
    }
}
