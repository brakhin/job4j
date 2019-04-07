package ru.bgbrakhi.io;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FileScannerTest {

    @Test
    public void getFiles() {
        FileScanner fs = new FileScanner();
        ArrayList<File> list = (ArrayList<File>) fs.getFiles("FileScannerTestDir", Arrays.asList(new String[]{".java", ".xml"}));
    }
}