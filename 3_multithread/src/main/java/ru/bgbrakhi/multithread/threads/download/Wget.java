package ru.bgbrakhi.multithread.threads.download;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Wget {

    private static class Loader implements Runnable {

        private final String fileUrl;
        private final String fileName;
        private final int maxSpeed;

        public Loader(String fileUrl, String fileName, int maxSpeed) {
            this.fileUrl = fileUrl;
            this.fileName = fileName;
            this.maxSpeed = maxSpeed;
        }

        @Override
        public void run() {
            try (BufferedInputStream in = new BufferedInputStream(new URL(fileUrl).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(fileName)
            ) {
                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                long startTime = System.currentTimeMillis();
                int downloaded = 0;
                int sleepTime = 500;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    Thread.sleep(sleepTime);
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                    downloaded++;
                    if (System.currentTimeMillis() - startTime > 1000) {
                        sleepTime = downloaded / maxSpeed;
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void load(String fileUrl, String fileName, int maxSpeed) {
        new Thread(new Loader(fileUrl, fileName, maxSpeed));
    }

    public static void main(String[] args) {
        Wget wget = new Wget();
        wget.load(args[0], "filename.dat", Integer.parseInt(args[1]));
    }
}
