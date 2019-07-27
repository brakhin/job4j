package ru.bgbrakhi.servlets;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DispatchPattern {
    private final Map<String, Function<User, Object>> dispatch = new HashMap<>();
    private final ValidateService validateLogic = ValidateService.getInstance();



    public DispatchPattern init() {
        dispatch.put("add", user -> validateLogic.add(user));
        dispatch.put("update", user -> validateLogic.update(user));
        dispatch.put("delete", user -> validateLogic.delete(user));
        dispatch.put("all", user -> validateLogic.findAll());
        dispatch.put("find", user -> validateLogic.findById(user));
        return this;
    }

    public Object process(String action, User user) {
        Function<User, Object> function = dispatch.get(action);
        return function == null ? ("find".equals(action) ? null : false) : function.apply(user);
    }
}
