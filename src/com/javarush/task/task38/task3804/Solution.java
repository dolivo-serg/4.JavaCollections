package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

public class Solution {
    public static Class getFactoryClass() {
        return ExceptionFactory.class;
    }

    public static void main(String[] args) {
        ExceptionFactory exceptionFactory=new ExceptionFactory();
        System.out.println(exceptionFactory.getException(ApplicationExceptionMessage.UNHANDLED_EXCEPTION));
        System.out.println(exceptionFactory.getException(ApplicationExceptionMessage.SOCKET_IS_CLOSED));
        System.out.println(exceptionFactory.getException(DatabaseExceptionMessage.NOT_ENOUGH_CONNECTIONS));
        System.out.println(exceptionFactory.getException(DatabaseExceptionMessage.NO_RESULT_DUE_TO_TIMEOUT));
        System.out.println(exceptionFactory.getException(UserExceptionMessage.USER_DOES_NOT_EXIST));
        System.out.println(exceptionFactory.getException(UserExceptionMessage.USER_DOES_NOT_HAVE_PERMISSIONS));
        System.out.println(exceptionFactory.getException(null));
    }

}