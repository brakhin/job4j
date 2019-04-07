package ru.bgbrakhi.io;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper {
    private Args args;

    public static class Args {
        private static final int VALUE_DIRECTORY = 0;
        private static final int VALUE_EXCLUDE   = 1;
        private static final int VALUE_OUTPUT    = 2;

        private String sourceDir = "";
        private String exclude = "";
        private String zipFile = "";

        public Args(String[] params) {
            init(params);
        }

        private void init(String[] params) {
            int curValueType = -1;
            for (int i = 0; i < params.length; i++) {
                switch (params[i]) {
                    case "-d":
                        curValueType = VALUE_DIRECTORY;
                        break;
                    case "-e":
                        curValueType = VALUE_EXCLUDE;
                        break;
                    case "-o":
                        curValueType = VALUE_OUTPUT;
                        break;
                    default:
                        setValue(curValueType, params[i]);
                }
            }
        }

        private void setValue(int type, String value) {
            switch (type) {
                case VALUE_DIRECTORY:
                    sourceDir = value;
                    break;
                case VALUE_EXCLUDE:
                    exclude = value;
                    break;
                case VALUE_OUTPUT:
                    zipFile = value;
                    break;
                default:
            }
        }
    }

    public Zipper(Args args) {
        this.args = args;
    }

    public static void main(String[] params) {
        Args args = new Args(params);
        Zipper zipper = new Zipper(args);
        zipper.zipFiles();
    }

    public void zipFiles() {
        ArrayList<File> files = (ArrayList<File>) seek(args.sourceDir);
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(args.zipFile))) {
            for (File file : files) {
                ZipEntry ze = new ZipEntry(file.getAbsolutePath());
                zout.putNextEntry(ze);
                if (file.isFile()) {
                    try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file.getAbsolutePath()))) {
                        byte[] data = reader.readAllBytes();
                        zout.write(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                zout.closeEntry();
            }
        } catch (Exception e)  {
            e.printStackTrace();
        }
    }

    private List<File> seek(String rootDir) {
        File root = new File(rootDir);
        List<File> result = new ArrayList<>();
        Queue<File> data = new LinkedList<>(Collections.singleton(root));

        while (!data.isEmpty()) {
            File file = data.poll();
            if (file.isDirectory()) {
                data.addAll(Arrays.asList(file.listFiles()));
            }
            if (!file.isDirectory()) {
                if (!fileExt(file.getName()).equals(args.exclude)) {
                    result.add(file);
                }
            }
        }
        return result;
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
