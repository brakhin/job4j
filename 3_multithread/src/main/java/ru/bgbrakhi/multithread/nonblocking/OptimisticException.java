package ru.bgbrakhi.multithread.nonblocking;

public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}