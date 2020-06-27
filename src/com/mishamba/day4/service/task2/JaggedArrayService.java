package com.mishamba.day4.service.task2;

import com.mishamba.day4.exception.ProgramException;

public class JaggedArrayService {
    public int[][] sort(int[][] array, Marker larger) throws ProgramException {
        if (array == null) {
            throw new ProgramException("array is null");
        }
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
                            Marker expression) {
        int firstMark = expression.calculate(firstArray);
        int secondMark = expression.calculate(secondArray);

        return (firstMark > secondMark);
    }
}
