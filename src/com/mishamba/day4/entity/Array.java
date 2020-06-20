package com.mishamba.day4.entity;

import com.mishamba.day4.exception.ProgramException;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Array {
    private final int[] array;

    public Array(int n) {
        this.array = new int[n];
    }

    public Array(int @NotNull [] array) {
        this.array = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i];
        }
    }

    public int[] getArray() {
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }

        return result;
    }

    public int getByIndex(int index) {
        return array[index];
    }

    public int getLength() {
        return array.length;
    }

    public int findElemIndex(int elem) throws ProgramException {
        if (!this.isSorted()) {
            throw new ProgramException("sort array before find elem");
        }
        int right = array.length;
        int left = 0;
        int mid = (right + left) / 2;
        while (mid != left && mid != right) {
            if (array[mid] == elem) {
                return mid;
            } else if (array[mid] > elem) {
                right = mid;
            } else {
                left = mid;
            }
        }

        throw new ProgramException("no such elem");
    }

    private boolean isSorted() {
        try {
            for (int i = 0; i < array.length; i++) {
                if (array[i] > array[i + 1]) {
                    return false;
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        return true;
    }

    public boolean setElemByIndex(int data, int index) {
        try {
            array[index] = data;
            return true;
        } catch (ArrayIndexOutOfBoundsException ex) {
            return false;
        }
    }

    public void sortByQuicksort() {
        intervalQuicksort(0, getLength() - 1);
    }

    private void intervalQuicksort(int left, int right) {
        int tmp;
        while (left < right) {
            if (array[left] > array[right]) {
                tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;
            }

            left++;
        }

        int middle = (left + right) / 2;
        intervalQuicksort(middle, right);
        intervalQuicksort(left, middle);
    }

    public void sortByMergeSort() {
        // TODO: 6/20/20
    }

    public void sortByInsertation() {
        // TODO: 6/20/20  
    }

    public int min() {
        int min = Integer.MAX_VALUE;
        for (int elem : array) {
            if (min > elem) {
                min = elem;
            }
        }

        return min;
    }

    public int max() {
        int max = Integer.MIN_VALUE;
        for (int elem : array) {
            if (max < elem) {
                max = elem;
            }
        }

        return max;
    }

    public int[] findSingleNumbers() {
        Reporter func = this::isSingle;
        return findElems(func);
    }

    public int[] findFibonacciNumbers() {
        Reporter func = this::isFibonacci;
        return findElems(func);
    }

    public int[] findNumbersWithNoSameLetters() {
        Reporter func = this::noSameLetters;
        return findElems(func);
    }

    private boolean isSingle(int elem) {
        double mark = 0;
        for (int j = elem; j > 0; j--) {
            mark += ((double) elem) / j;
        }

        return (mark == 0);
    }

    private boolean isFibonacci(int elem) {
        int MAX_FIBONACCI_NUMBER = 47;
        for (int i = 0; i < MAX_FIBONACCI_NUMBER; i++) {
            if (elem == fibonacci(i)) {
                return true;
            }
        }

        return false;
    }

    private int fibonacci(int n) {
        double phi = (Math.sqrt(5) + 1) / 2;
        return (int) (Math.pow(phi, n) / Math.sqrt(5) + 0.5);
    }

    private boolean noSameLetters(int elem) {
        int firstNumber = elem % 10;
        int secondNumber = (elem % 100 - elem % 10) / 10;
        int thirdNumber = (elem - secondNumber * 10 - firstNumber) / 100;
        return (secondNumber == thirdNumber) && (firstNumber == thirdNumber);
    }

    private int @NotNull [] findElems(Reporter func) {
        int quantity = 0;
        for (int elem : array) {
            quantity += (func.apear(elem)) ? 1 : 0;
        }

        int[] numbers = new int[quantity];
        int j = 0;
        for (int elem : array) {
            if (func.apear(elem)) {
                numbers[j] = elem;
                j++;
            }
        }

        return numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Array)) {
            return false;
        }

        Array array1 = (Array) o;
        if (array1.getLength() != this.getLength()) {
            return false;
        }

        int mark = 0;
        for (int i = 0; i < this.getLength(); i++) {
            mark += (array1.getByIndex(i) == this.getByIndex(i)) ? 0 : 1;
        }

        return (mark == 0);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getArray());
    }
}

interface Reporter {
    boolean apear(int elem);
}
