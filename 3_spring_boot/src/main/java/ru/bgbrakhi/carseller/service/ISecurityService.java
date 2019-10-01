package ru.bgbrakhi.carseller.service;

public interface ISecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}
