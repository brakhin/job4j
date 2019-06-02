package ru.brakhin.multithreading.problems;


/**
 * Видимость общих объектов - после изменение в 1м потоке значения value 2й поток будет видеть
 * старое значение value, т.к. для каждого потока value перед изменение записывается в стек вызовов Thread
 * исправляется с помощью объявления value volatile. При этом переменная не копируется в кэш процесса
 * а изменяется непосредственно в куче
 */
public class Example1 {

    private static Integer value = Integer.valueOf(100);

    public static class RunObj implements Runnable {

        private int id;

        public RunObj(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            if (id == 1) value++;
        }
    }

    public static void main(String[] args) {
        new Thread(new RunObj(1)).start();
        new Thread(new RunObj(2)).start();
    }
}
