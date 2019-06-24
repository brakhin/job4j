package ru.bgbrakhi.multithread.problems;

/**
 * Ситуация гокни : при одновременном изменения значения Value их двух процессов,
 * в Heap возвращается первое измененое (из первого процесса)
 * Решается через объявление сихронизированных кусков кода
 */

public class Example2 {
    private static Integer value = Integer.valueOf(100);

    public static class RunObj implements Runnable {

        @Override
        public void run() {
            value++;
        }
    }

    public static void main(String[] args) {
        new Thread(new Example2.RunObj()).start();
        new Thread(new Example2.RunObj()).start();
    }
}

