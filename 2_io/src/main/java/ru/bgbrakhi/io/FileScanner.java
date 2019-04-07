package ru.bgbrakhi.io;

import java.io.File;
import java.util.*;

public class FileScanner implements Iterable<File> {

    private List<String> extensions;
    private File root;

    public List<File> getFiles(String rootDir, List<String> extensions) {
        this.extensions = extensions;
        root = new File(rootDir);
        List<File> result = new ArrayList<>();

        for (Iterator it = iterator(); it.hasNext();) {
            File file = (File) it.next();
            if (!file.isDirectory()) {
                String filename = file.getName();
                if (extensions.contains(fileExt(filename))) {
                    result.add(file);
                }
            }
        }
        return result;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            Queue<File> data = new LinkedList<>(Collections.singleton(root));

            @Override
            public boolean hasNext() {
                return !data.isEmpty();
            }

            @Override
            public File next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                File file = data.poll();
                if (file.isDirectory()) {
                    data.addAll(Arrays.asList(file.listFiles()));
                }
                return file;
            }
        };
    }

    private String fileExt(String filename) {
        String result = "";
        int index = filename.indexOf(".");
        if (index != -1) {
            result = filename.substring(index);
        }
        return result;
    }
}
