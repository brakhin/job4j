package ru.bgrakhi.generic;


public class RoleStore extends AbstractStore<Role> implements Store<Role> {
    protected RoleStore(SimpleArray<Role> data) {
        super(data);
    }
}
