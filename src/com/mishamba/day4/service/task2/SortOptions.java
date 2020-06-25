package com.mishamba.day4.service.task2;

import org.jetbrains.annotations.NotNull;

public class SortOptions {
    public static int largerSum(int @NotNull [] array) {
        int sum = 0;
        for (int elem : array) {
            sum += elem;
        }

        return sum;
    }

    public static int maxElement(int @NotNull [] array) {
        int maxElement = 0;
        for (int elem : array) {
            if (maxElement < elem) {
                maxElement = elem;
            }
        }

        return maxElement;
    }

    public static int minElement(int @NotNull [] array) {
        int minElement = Integer.MAX_VALUE;
        for (int elem : array) {
            if (minElement > elem) {
                minElement = elem;
            }
        }

        return minElement;
    }
}
