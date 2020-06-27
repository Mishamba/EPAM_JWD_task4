package com.mishamba.day4.service.task2;

import com.mishamba.day4.exception.ProgramException;

public class SortOptions {
    public static int largerSum(int [] array) throws ProgramException {
        if (array == null) {
            throw new ProgramException("array is null");
        }
        int sum = 0;
        for (int elem : array) {
            sum += elem;
        }

        return sum;
    }

    public static int maxElement(int [] array) throws ProgramException {
        if (array == null) {
            throw new ProgramException("array is null");
        }
        int maxElement = 0;
        for (int elem : array) {
            if (maxElement < elem) {
                maxElement = elem;
            }
        }

        return maxElement;
    }

    public static int minElement(int [] array) throws ProgramException {
        if (array == null) {
            throw new ProgramException("array is null");
        }
        int minElement = Integer.MAX_VALUE;
        for (int elem : array) {
            if (minElement > elem) {
                minElement = elem;
            }
        }

        return minElement;
    }
}
