package ru.bgbrakhi.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
            // инициализация параметров
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

    private String getExtention(String filename) {
        String result = "";
        int index = filename.lastIndexOf(".");
        if (index != -1) {
            result = filename.substring(index);
        }
        return result;
    }

    private List<File> getFiles(String rootDir) {
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
            if (!getExtention(root.getName()).equals(args.exclude)) {
                result.add(root);
            }
        }
    }

    public void zipFiles() {
        ArrayList<File> files = (ArrayList<File>) getFiles(args.sourceDir);
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
            zout.close();
        } catch (Exception e)  {
            e.printStackTrace();
        }
    }
}
