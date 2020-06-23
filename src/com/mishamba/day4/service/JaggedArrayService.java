package com.mishamba.day4.service;

import org.jetbrains.annotations.NotNull;

public class JaggedArrayService {
    public int[][] sort(int[] @NotNull [] array, Comparator larger) {
        for (int i = 0; i < array.length;i++ ) {
            for (int j = i+1;j<array.length;j++) {
                if (larger.condition(array[i], array[j])) {
                    int[] tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }

        return array;
    }

    public boolean compare(int[] firstArray, int[] secondArray, @NotNull Marker expression) {
        int firstMark = expression.create(firstArray);
        int secondMark = expression.create(secondArray);

        return (firstMark > secondMark);
    }

    public int largerSum(int @NotNull [] array) {
        int sum = 0;
        for (int elem : array) {
            sum += elem;
        }

        return sum;
    }

    public int maxElement(int @NotNull [] array) {
        int maxElement = 0;
        for (int elem : array) {
            if (maxElement < elem) {
                maxElement = elem;
            }
        }

        return maxElement;
    }

    public int minElement(int @NotNull [] array) {
        int minElement = Integer.MAX_VALUE;
        for (int elem : array) {
            if (minElement > elem) {
                minElement = elem;
            }
        }

        return minElement;
    }
}

interface Comparator {
    boolean condition(int[] firstArray, int[] secondArray);
}

interface Marker {
    int create(int[] array);
}
