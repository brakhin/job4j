package ru.bgbrakhi.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private Socket socket;
    private Map<String, String> data = new HashMap<>();

    public Server(Socket socket) {
        this.socket = socket;
        data.put("hello", "hello responce");
        data.put("hi", "hi responce");
        data.put("how are you", "how are you responce");
        data.put("good bye", "good bye responce");
    }

    public void start() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("input command, or \"exit\" for end");
            String response = null;
            String input = null;
            do {
                System.out.println("wait command ...");
                input = in.readLine();
                System.out.println("received command " + input);
                response = data.get(input);
                if (!"exit".equals(input)) {
                    if (response != null) {
                        out.println("     " + response);
                    } else {
                        out.println("     invalid command");
                    }
                }
                out.println();
            } while (!"exit".equals(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new ServerSocket(5000).accept();
        Server server = new Server(socket);
        server.start();
    }
}
