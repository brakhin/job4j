package ru.brakhin.multithreading.threads.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private final int fieldWidth;

    public RectangleMove(Rectangle rect, int fieldWidth) {
        this.rect = rect;
        this.fieldWidth = fieldWidth;
    }

    @Override
    public void run() {
        int direction = 1;
        while (true) {
            if (this.rect.getX() == this.fieldWidth - rect.getWidth()) {
                direction = -1;
            }
            if (this.rect.getX() == 0) {
                direction = 1;
            }
            this.rect.setX(this.rect.getX() + direction);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


