package com.javarush.task.task26.task2613.exception;

public class NotEnoughMoneyException extends Exception {

    public NotEnoughMoneyException() {
    }

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
