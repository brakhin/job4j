package ru.bgbrakhi.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private Scanner scanner;

    public Client(Socket socket, Scanner scanner) {
        this.socket = socket;
        this.scanner = scanner;
    }

    public void start() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String str = "";
            String input = "";
            do {
                input = scanner.nextLine();
                out.println(input);
                while (!(str = in.readLine()).isEmpty()) {
                    System.out.println(str);
                    if ("exit".equals(input)) {
                        break;
                    }
                }
            } while (!"exit".equals(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        Socket socket = new Socket(address, 5000);
        Scanner scanner = new Scanner(System.in);
        Client client = new Client(socket, scanner);
        client.start();
    }
}
