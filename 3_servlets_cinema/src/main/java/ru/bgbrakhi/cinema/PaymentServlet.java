package ru.bgbrakhi.cinema;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class PaymentServlet extends HttpServlet {
    private final Validator logic = Validator.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        int id = Integer.parseInt(req.getParameter("seat"));
        Seat seat = logic.getSeat(id);
        if (seat != null && seat.getIdAccount() == -1L) {
            writer.append(String.format("{\"line\":%d,\"col\":%d}", seat.getLine(), seat.getCol()));
        } else if (seat == null) {
            writer.append("{\"error\":1}");
        } else {
            writer.append(String.format("{\"error\":2,\"line\":%d,\"col\":%d}", seat.getLine(), seat.getCol()));
        }
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = new BufferedReader(req.getReader());
        String line;
        StringBuilder builder = new StringBuilder("{");
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        builder.append("}");

        String jsonStr = builder.toString()
                .replace("&", ",")
                .replace("=", ":")
                .replace(":", "\":\"")
                .replace(",", "\",\"")
                .replace("}", "\"}")
                .replace("{", "{\"");

        ObjectMapper objectMapper = new ObjectMapper();
        Account account = objectMapper.readValue(jsonStr, Account.class);
        logic.createAccount(account);
    }
}
