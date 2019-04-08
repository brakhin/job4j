package ru.bgbrakhi.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private String answersfile;
    private String logfile;

    public ConsoleChat(String answersfile, String logfile) {
        this.answersfile = answersfile;
        this.logfile = logfile;
    }

    public void start() {
        boolean stopped = false;
        boolean active = true;
        Scanner sc = new Scanner(System.in);
        List<String> answers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(answersfile)); PrintWriter writer = new PrintWriter(new FileWriter(logfile))) {
            reader.lines().forEach(answers::add);
            Random rnd = new Random();
            do {
                String input =  sc.nextLine();
                stopped = "закончить".equals(input);
                active = active && !"стоп".equals(input) || !active && "продолжить".equals(input);
                log(writer, input);
                if (active && !stopped) {
                    String output = answers.get(rnd.nextInt(answers.size()));
                    System.out.println("       " + output);
                    log(writer, "       " + output);
                }
            } while (!stopped);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void log(PrintWriter writer, String message) {
        writer.println(message);
    }

    public static void main(String[] args) {
        System.out.println("Консольный чат");
        ConsoleChat chat = new ConsoleChat("chat_answers.txt", "log.txt");
        chat.start();
    }
}
