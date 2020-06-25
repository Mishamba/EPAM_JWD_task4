package com.mishamba.day4.service.task2;

import org.jetbrains.annotations.NotNull;

public class JaggedArrayService {
    public int[][] sort(int[] @NotNull [] array, Marker larger) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (compare(array[i], array[j], larger)) {
                    int[] tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }

        return array;
    }

    private boolean compare(int[] firstArray, int[] secondArray,
                            @NotNull Marker expression) {
        int firstMark = expression.calculate(firstArray);
        int secondMark = expression.calculate(secondArray);

        return (firstMark > secondMark);
    }
}
