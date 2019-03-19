package ru.bgbrakhi.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class BankStream {

    private TreeMap<User, ArrayList<Account>> data = new TreeMap<>();

    public void addUser(User user) {
        data.put(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        data.remove(user);
    }

    private User getUser(String passport) {
        List<User> users = data.keySet().stream().filter(user -> user.passport.equals(passport)).collect(Collectors.toList());
        return users == null || users.size() == 0 ? null : users.get(0);
    }

    public void addAccountToUser(String passport, Account account) {
        User user = getUser(passport);
        if (user != null) {
            ArrayList<Account> list = data.get(user);
            if (list == null) {
                list = new ArrayList<>();
            }
            if (list.indexOf(account) == -1) {
                list.add(account);
                data.put(user, list);
            }
        }
    }

    public void deleteAccountFromUser(String passport, Account account) {
        User user = getUser(passport);
        if (user != null) {
            ArrayList<Account> list = data.get(user);
            if (list != null) {
                list.remove(account);
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
        List<Account> accounts = getUserAccounts(passport);
        if (accounts != null) {
            List<Account> results = accounts.stream().filter(account -> account.requisites.equals(requisite)).collect(Collectors.toList());
            result = results == null || results.size() == 0 ? null : results.get(0);
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
