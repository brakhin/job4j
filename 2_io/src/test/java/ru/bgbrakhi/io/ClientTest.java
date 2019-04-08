package ru.bgbrakhi.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ClientTest {

    private static final String LN = System.getProperty("line.separator");

    @Test
    public void whernUnknownResponseThenIncnownRequest() throws IOException {
        Socket socket = mock(Socket.class);
        String input = String.format("request%sexit%s", LN, LN);
        Scanner scanner = new Scanner(input);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(
                String.format("response%s%s%s", LN, LN, LN).getBytes()
        );
        when(socket.getOutputStream()).thenReturn(out);
        when(socket.getInputStream()).thenReturn(in);
        Client client = new Client(socket, scanner);
        client.start();
        assertThat(out.toString(), is(input));
    }
}