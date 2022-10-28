package com.javarush.task.task26.task2613;

import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        map.entrySet().stream()
                .filter(entry -> !entry.getKey().equals(currencyCode.toUpperCase()))
                .findFirst()
                .ifPresent(entry -> map.put(currencyCode, new CurrencyManipulator(currencyCode)));
        return map.get(currencyCode);
    }
}
