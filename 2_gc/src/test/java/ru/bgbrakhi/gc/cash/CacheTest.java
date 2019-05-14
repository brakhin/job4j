package ru.bgbrakhi.gc.cash;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CacheTest {

    @Test
    public void testCash() {
        Cache cache = new Cache();

        String str1 = cache.getFileText("File_1.txt");
        String str2 = cache.getFileText("File_2.txt");
        String str3 = cache.getFileText("File_1.txt");

        assertThat(str1, is(new StringBuilder()
                .append("File_1 text line 1")
                .append(Cache.LN)
                .append("File_1 text line 2")
                .append(Cache.LN)
                .toString())
        );
        assertThat(str2, is(new StringBuilder()
                .append("File_2 text line 1")
                .append(Cache.LN)
                .append("File_2 text line 2")
                .append(Cache.LN)
                .toString())
        );
        assertThat(str3, is(new StringBuilder()
                .append("File_1 text line 1")
                .append(Cache.LN)
                .append("File_1 text line 2")
                .append(Cache.LN)
                .toString())
        );
    }
}