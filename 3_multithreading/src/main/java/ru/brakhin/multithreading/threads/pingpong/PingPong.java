package ru.brakhin.multithreading.threads.pingpong;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;

import static java.awt.Color.BLUE;

public class PingPong extends Application {
    private static final String JOB4J = "Пинг-понг www.job4j.ru";

    @Override
    public void start(Stage stage) {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 50, 10, 10);
        group.getChildren().add(rect);
        new Thread(new RectangleMove(rect, limitX)).start();
        stage.setScene(new Scene(group, limitX, limitY));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();


    }
}