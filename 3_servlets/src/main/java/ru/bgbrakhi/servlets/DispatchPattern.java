package ru.bgbrakhi.servlets;

import ru.bgbrakhi.servlets.User;
import ru.bgbrakhi.servlets.ValidateService;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DispatchPattern {
    private final Map<String, Function<String[], Object>> dispatch = new HashMap<>();
    private final ValidateService validateLogic = ValidateService.getInstance();


    private final String action;
    private final String login;
    private final String password;
    private final Integer role;
    private final Integer id;

    public DispatchPattern(String action, String login, String password, Integer role, Integer id) {
        this.action = action;
        this.login = login;
        this.password = password;
        this.role = role;
        this.id = id;
    }

    public DispatchPattern init() {
        User user = new User(id, login, password, role);
        dispatch.put("add", a -> validateLogic.add(user));
        dispatch.put("update", a -> validateLogic.update(user));
        dispatch.put("delete", a -> validateLogic.delete(user));
        dispatch.put("all", a -> validateLogic.findAll());
        dispatch.put("find", a -> validateLogic.findById(user));
        return this;
    }

    public Object process(String action, String... params) {
        Function<String[], Object> function = dispatch.get(action);
        return function == null ? ("find".equals(action) ? null : false) : function.apply(params);
    }
}
