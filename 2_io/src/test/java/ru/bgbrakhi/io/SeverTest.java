package ru.bgbrakhi.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class SeverTest {
    private static final String LN = System.getProperty("line.separator");

    private void testServer(String request, String responce) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(
                request.getBytes()
        );
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.start();
        assertThat(out.toString(), is(responce));
    }

    @Test
    public void whenGetInvalidCommandThenInvalidCommandResponce() throws IOException {
        testServer(String.format("asdf%sexit", LN), String.format("     invalid command%s%s%s", LN, LN, LN));
    }

    @Test
    public void whenGetHiCommandThenHiResponce() throws IOException {
        testServer(String.format("hi%sexit", LN), String.format("     hi responce%s%s%s", LN, LN, LN));
    }

    @Test
    public void whenGetExitCommandThenCloseServer() throws IOException {
        testServer("exit", LN);
    }
}