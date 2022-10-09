package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.view.View;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Model {

    private View view;
    private Provider[] providers;

    public Model(View view, Provider... providers) {
        if (view == null || providers == null || providers.length == 0) {
            throw new IllegalArgumentException();
        }
        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String city) {
        view.update(Arrays.stream(providers)
                .flatMap(provider -> Stream.of(provider.getJavaVacancies(city)))
                .flatMap(List::stream)
                .collect(Collectors.toList()));
    }
}
