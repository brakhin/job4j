package ru.bgbrakhi.servlets.servletsjsp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.bgbrakhi.servlets.DbStore;
import ru.bgbrakhi.servlets.User;
import ru.bgbrakhi.servlets.ValidateService;
import ru.bgbrakhi.servlets.ValidateServiceStub;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserCreateServletTest {
    @Test
    public void whenAddUserThenStoreIt() throws ServletException, IOException {
        ValidateServiceStub validate = new ValidateServiceStub();
        PowerMockito.mockStatic(ValidateService.class);

        when(ValidateService.getInstance()).thenReturn(validate);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse responce = mock(HttpServletResponse.class);
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("city")).thenReturn("1");
        when(request.getParameter("role")).thenReturn("1");

        new UserCreateServlet().doPost(request, responce);
        assertThat(validate.findAll().iterator().next().getLogin(), is("login"));
    }

    @Test
    public void whenUpdateUserThenStoreIt() throws ServletException, IOException {
        ValidateServiceStub validate = new ValidateServiceStub();
        validate.add(new User(0, "root", "root", 0, 1));

        PowerMockito.mockStatic(ValidateService.class);

        when(ValidateService.getInstance()).thenReturn(validate);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse responce = mock(HttpServletResponse.class);

        when(request.getParameter("action")).thenReturn("update");
        when(request.getParameter("id")).thenReturn("0");
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("city")).thenReturn("1");
        when(request.getParameter("role")).thenReturn("1");

        new UserUpdateServlet().doPost(request, responce);
        assertThat(validate.findAll().iterator().next().getLogin(), is("login"));
    }
}