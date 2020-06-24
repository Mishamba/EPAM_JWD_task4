package com.mishamba.day4.service;

import com.mishamba.day4.entity.CustomArray;
import com.mishamba.day4.exception.ProgramException;
import org.jetbrains.annotations.NotNull;

public class CustomArrayFindService {
    public int findElemIndex(int elem, @NotNull CustomArray array)
            throws ProgramException {
        if (!array.isSorted()) {
            throw new ProgramException("sort array before find elem");
        }
        int right = array.getLength();
        int left = 0;
        int mid = (right + left) / 2;

        while (mid != left && mid != right) {
            if (array.getByIndex(mid) == elem) {
                return mid;
            } else if (array.getByIndex(mid) > elem) {
                right = mid;
                mid = (right + left) / 2;
            } else {
                left = mid;
                mid = (right + left) / 2;
            }
        }

        throw new ProgramException("no such elem");
    }

    public int[] findPrimeNumbers(CustomArray array) {
        Reporter prime = this::prime;
        return findElements(prime, array);
    }

    public int[] findFibonacciNumbers(CustomArray array) {
        Reporter fibonacci = this::fibonacci;
        return findElements(fibonacci, array);
    }

    public int[] findNumbersWithNoSameLetters(CustomArray array) {
        Reporter noSameLetters = this::noSameLetters;
        return findElements(noSameLetters, array);
    }

    private boolean prime(int elem) {
        if (elem < 0) {
            return false;
        }

        int mark = 0;
        for (int j = elem - 1; j > 1; j--) {
            double tmp = ((double) elem)/j;
            if (tmp == (int) tmp) {
                mark++;
            }
        }

        return (mark == 0);
    }

    private boolean fibonacci(int elem) {
        int MAX_FIBONACCI_NUMBER = 47;
        for (int i = 0; i < MAX_FIBONACCI_NUMBER; i++) {
            if (elem == calculateFibonacci(i)) {
                return true;
            }
        }

        return false;
    }

    private int calculateFibonacci(int n) {
        double phi = (Math.sqrt(5) + 1) / 2;
        return (int) (Math.pow(phi, n) / Math.sqrt(5) + 0.5);
    }

    private boolean noSameLetters(int elem) {
        int unity = elem % 10;
        int ten = (elem % 100 - unity) / 10;
        int century = (elem - ten * 10 - unity) / 100;
        return (ten == century) && (unity == century);
    }

    private int @NotNull [] findElements(Reporter func,
                                         @NotNull CustomArray array) {
        int quantity = 0;
        for (int i = 0; i< array.getLength();i++) {
            quantity += (func.appear(array.getByIndex(i))) ? 1 : 0;
        }

        int[] numbers = new int[quantity];
        int j = 0;
        for (int i = 0;i<array.getLength();i++) {
            if (func.appear(array.getByIndex(i))) {
                numbers[j] = array.getByIndex(i);
                j++;
            }
        }

        return numbers;
    }
}

interface Reporter {
    boolean appear(int elem);
}
