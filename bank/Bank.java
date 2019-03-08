package ru.job4j.chess.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Bank {

    private TreeMap<User, ArrayList<Account>> data = new TreeMap<>();

    public void addUser(User user) {
        data.put(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        data.remove(user);
    }

    private User getUser(String passport) {
        User result = null;
        for (User user : data.keySet()){
            if (user.passport.equals(passport)) {
                result = user;
                break;
            }
        }
        return result;
    }

    public void addAccountToUser(String passport, Account account) {
        User user = getUser(passport);
        if (user == null) {
            user = new User("", passport);
        }
        ArrayList<Account> list = data.get(user);
        if (list == null) {
            list = new ArrayList<>();
        }
        if (list.indexOf(account) == -1) {
            list.add(account);
            data.put(user, list);
        }
    }

    public void deleteAccountFromUser(String passport, Account account) {
        User user = getUser(passport);
        if (user != null) {
            ArrayList<Account> list = data.get(user);
            if (list != null) {
                if (list.remove(account)) {
                    data.put(user, list);
                }
            }
        }
    }

    public List<Account> getUserAccounts (String passport) {
        List<Account> result = null;
        User user = getUser(passport);
        if (user != null) {
            result = data.get(user);
        }
        return result;
    }

    private Account getAccount(String passport, String requisite) {
        Account result = null;
        for (Account account : getUserAccounts(passport)) {
            if (account.requisites.equals(requisite)) {
                result = account;
                break;
            }
        }
        return result;
    }

    public boolean transferMoney (String srcPassport, String srcRequisite, String dstPassport, String dstRequisite, double amount) {
        boolean result = false;
        Account srcAccount = getAccount(srcPassport, srcRequisite);
        Account dstAccount = getAccount(dstPassport, dstRequisite);
        if (srcAccount != null && dstAccount != null) {
            srcAccount.value -= amount;
            dstAccount.value += amount;
        }
        return result;
    }

}
