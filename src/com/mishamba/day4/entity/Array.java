package com.mishamba.day4.entity;

import com.mishamba.day4.exception.ProgramException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Array {
    private int[] array;

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

    public void sortByMergeSort() throws ProgramException {
        if (this == null) {
            throw new ProgramException("no object created");
        }
        this.array = sort(this.array);
    }

    @Contract("_ -> param1")
    private int @NotNull [] sort(int @NotNull [] givenArray) {
        if (givenArray.length == 1) {
            return givenArray;
        }

        int middle = givenArray.length / 2;

        int[] leftArray = new int[middle];
        int[] rightArray = new int[middle +1];



        leftArray = sort(leftArray);
        rightArray = sort(rightArray);

        return merge(leftArray, rightArray);
    }

    @Contract(pure = true)
    private int @NotNull [] merge(int @NotNull [] leftArray, int @NotNull [] rightArray) {
        int[] mergedArray = new int[leftArray.length + rightArray.length];
        int positionLeft = 0;
        int positionRight = 0;

        for (int i = 0;i<mergedArray.length;i++) {
            if ((positionRight == rightArray.length) ||
                    (leftArray[positionLeft] < rightArray[positionRight])) {
                mergedArray[i] = leftArray[positionLeft];
                positionLeft++;
            } else {
                mergedArray[i] = rightArray[positionRight];
                positionRight++;
            }
        }

        return mergedArray;
    }

    public void sortByInsert() {
        int key;
        for (int i = 1; i < array.length; i++) {
            key = array[i];
            int j = i - 1;

            while (j >= 0 && key < array[j]) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }
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
        Reporter func = this::single;
        return findElements(func);
    }

    public int[] findFibonacciNumbers() {
        Reporter func = this::fibonacci;
        return findElements(func);
    }

    public int[] findNumbersWithNoSameLetters() {
        Reporter func = this::noSameLetters;
        return findElements(func);
    }

    private boolean single(int elem) {
        double mark = 0;
        for (int j = elem; j > 0; j--) {
            mark += ((double) elem) / j;
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

    private int @NotNull [] findElements(Reporter func) {
        int quantity = 0;
        for (int elem : array) {
            quantity += (func.appear(elem)) ? 1 : 0;
        }

        int[] numbers = new int[quantity];
        int j = 0;
        for (int elem : array) {
            if (func.appear(elem)) {
                numbers[j] = elem;
                j++;
            }
        }

        return numbers;
    }

    public void fillArrayByConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter new array length");
        int newLength = scanner.nextInt();
        this.array = new int[newLength];
        for (int i = 0; i < newLength; i++) {
            System.out.println("enter value of element with index " + i);
            this.array[i] = scanner.nextInt();
        }
    }

    public void fillArrayByFile(Path path) {
        this.array = getDataFromFile(path);
    }

    public void fillArrayWithRandom(int newLength) {
        this.array = new int[newLength];
        for (int i = 0; i < newLength; i++) {
            this.array[i] = (int) Math.random() % 30;
        }
    }

    private int[] getDataFromFile(Path path) {
        int[] fileArray = null;
        try (Stream<String> lines = Files.lines(path)) {
            fileArray = lines.mapToInt(Integer::parseInt).toArray();
        } catch (IOException ignored) {
        }

        return fileArray;
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
    boolean appear(int elem);
}
