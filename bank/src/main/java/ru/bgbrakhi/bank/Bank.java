package ru.bgbrakhi.bank;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Bank {

    private TreeMap<User, ArrayList<Account>> data = new TreeMap<>();

    public void addUser(User user) {
        data.putIfAbsent(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        data.remove(user);
    }

/*
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
*/

    public void addAccountToUser(String passport, Account account) {
        Optional<ArrayList<Account>> accounts = data.keySet().stream()
                .filter(user -> user.passport.equals(passport))
                .map(data::get)
                .findAny();
        accounts.ifPresent(ac -> new Consumer<Account>() {
                    public void accept(Account a) {
                        if (!ac.contains(account)) {
                            ac.add(account);
                        }
                    }
                });
    }

    public void deleteAccountFromUser(String passport, Account account) {
        final Optional<ArrayList<Account>> accounts = data.keySet().stream()
                .filter(user -> user.passport.equals(passport))
                .map(data::get)
                .findAny();
        accounts.ifPresent(ac -> ac.remove(account));
    }

    public List<Account> getUserAccounts(String passport) {
        return data.keySet().stream()                           // стримим user
                .filter(user -> user.passport.equals(passport))    // фильтруем user
                .map(data::get)                                    // конвертируем в стрим accounts всех user с passport
                .flatMap(ac -> ac.stream())                        // сливаем accounts всех user с passport в один стрим
                .collect(Collectors.toList());                     // конвертируем стрим в лист
    }

    private Account getAccount(String passport, String requisite) {
        List<Account> result = data.keySet().stream()
                .filter(user -> user.passport.equals(passport))
                .map(data::get)
                .flatMap(ac -> ac.stream())
                .distinct()
                .filter(account -> account.requisites.equals(requisite))
                .collect(Collectors.toList());
        return result.size() == 0 ? null : result.get(0);
    }

    public boolean transferMoney(String srcPassport, String srcRequisite, String dstPassport, String dstRequisite, double amount) {
        boolean result = false;
        Account srcAccount = getAccount(srcPassport, srcRequisite);
        Account dstAccount = getAccount(dstPassport, dstRequisite);
        if (srcAccount != null && dstAccount != null) {
            srcAccount.decBalance(amount);
            dstAccount.incBalance(amount);
        }
        return result;
    }
}
