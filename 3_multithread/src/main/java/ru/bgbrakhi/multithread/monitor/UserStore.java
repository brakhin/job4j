package ru.bgbrakhi.multithread.monitor;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ThreadSafe
public class UserStore {

    @GuardedBy("this")
    private final List<User> users = new ArrayList<>();

    public synchronized void add(User user) {
        users.add(user);
    }

    public synchronized void update(User user) {
        int index = users.indexOf(user);
        users.set(index, user);
    }

    public synchronized boolean delete(User user) {
        return users.remove(user);
    }


    public synchronized void transfer(int fromId, int toId, int amount) {

        List<User> fromUsers = users.stream().filter(i -> i.getId() == fromId).collect(Collectors.toList());
        List<User> toUsers = users.stream().filter(i -> i.getId() == toId).collect(Collectors.toList());

        if (fromUsers.size() > 0 && toUsers.size() > 0) {
            User fromUser = fromUsers.get(0);
            User toUser = toUsers.get(0);

            fromUser.setAmount(fromUser.getAmount() - amount);
            toUser.setAmount(toUser.getAmount() + amount);

            update(fromUser);
            update(toUser);
        }
    }


}
