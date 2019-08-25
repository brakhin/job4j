package ru.bgbrakhi.servlets.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bgbrakhi.servlets.IndexUser;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class JSONController extends HttpServlet {

    private Map<Integer, IndexUser> data = new ConcurrentHashMap<>();
    private AtomicInteger index = new AtomicInteger(0);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = req.getReader();
        String str;

        StringBuilder builder = new StringBuilder("{");
        while ((str = reader.readLine()) != null) {
            builder.append(str);
        }
        builder.append("}");

        str = builder.toString()
                .replace("&", ",")
                .replace("=", ":")
                .replace(":", "\":\"")
                .replace(",", "\",\"")
                .replace("}", "\"}")
                .replace("{", "{\"");

        ObjectMapper objectMapper = new ObjectMapper();
        IndexUser user = objectMapper.readValue(str, IndexUser.class);

        data.put(index.getAndIncrement(), user);
    }
}
