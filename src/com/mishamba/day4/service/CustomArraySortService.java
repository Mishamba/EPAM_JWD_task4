package com.mishamba.day4.service;

import com.mishamba.day4.entity.CustomArray;
import com.mishamba.day4.exception.ProgramException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class CustomArraySortService {
    public int findElemIndex(int elem, CustomArray array) throws ProgramException {
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
            } else {
                left = mid;
            }
        }

        throw new ProgramException("no such elem");
    }

    public void sortByQuicksort(CustomArray array) {
        intervalQuicksort(0, array.getLength() - 1, array);
    }

    private void intervalQuicksort(int left, int right, CustomArray array) {
        int tmp;

        if (left > right) {
            return;
        }

        while (left < right) {
            if (array.getByIndex(left) > array.getByIndex(right)) {
                tmp = array.getByIndex(left);
                array.setElementByIndex(array.getByIndex(right), left);
                array.setElementByIndex(tmp, right);
            }

            left++;
        }

        int middle = (left + right) / 2;
        intervalQuicksort(middle, right, array);
        intervalQuicksort(left, middle, array);
    }

    public CustomArray sortByMergeSort(CustomArray array) {
        return sort(array);
    }

    private @NotNull CustomArray sort(@NotNull CustomArray givenArray) {
        if (givenArray.getLength() == 1) {
            return givenArray;
        }

        int middle = givenArray.getLength() / 2;

        CustomArray leftArray = givenArray.getPartOfArray(0, middle);
        CustomArray rightArray = givenArray.getPartOfArray(middle +1,
                givenArray.getLength());

        sort(leftArray);
        sort(rightArray);

        return merge(leftArray, rightArray);
    }

    @Contract("_, _ -> new")
    private @NotNull CustomArray merge(@NotNull CustomArray leftArray, @NotNull CustomArray rightArray) {
        int[] mergedArray = new int[leftArray.getLength() +
                rightArray.getLength()];
        int positionLeft = 0;
        int positionRight = 0;

        for (int i = 0; i < mergedArray.length; i++) {
            if ((positionRight == rightArray.getLength()) ||
                    (leftArray.getByIndex(positionLeft) <
                            rightArray.getByIndex(positionRight))) {
                mergedArray[i] = leftArray.getByIndex(positionLeft);
                positionLeft++;
            } else {
                mergedArray[i] = rightArray.getByIndex(positionRight);
                positionRight++;
            }
        }

        return new CustomArray(mergedArray);
    }

    public CustomArray sortByInsert(CustomArray inputArray) {
        CustomArray array = new CustomArray(inputArray);
        int key;
        for (int i = 1; i < array.getLength(); i++) {
            key = array.getByIndex(i);
            int j = i - 1;

            while (j >= 0 && key < array.getByIndex(j)) {
                array.setElementByIndex(array.getByIndex(j + 1), j);
                j--;
            }

            array.setElementByIndex(key, j + 1);
        }

        return array;
    }
    public int[] findSingleNumbers(CustomArray array) {
        Reporter func = this::single;
        return findElements(func, array);
    }

    public int[] findFibonacciNumbers(CustomArray array) {
        Reporter func = this::fibonacci;
        return findElements(func, array);
    }

    public int[] findNumbersWithNoSameLetters(CustomArray array) {
        Reporter func = this::noSameLetters;
        return findElements(func, array);
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

    public CustomArray CustomArrayByConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter new array length");
        int newLength = scanner.nextInt();
        int[] array = new int[newLength];
        for (int i = 0; i < newLength; i++) {
            System.out.println("enter value of element with index " + i);
            array[i] = scanner.nextInt();
        }

        return new CustomArray(array);
    }

    public CustomArray CustomArrayByFile(Path path) throws IOException {
        int[] array = getArrayFromFile(path);
        return new CustomArray(array);
    }

    private int[] getArrayFromFile(Path path) throws IOException {
        int[] fileArray = null;
        Stream<String> lines = Files.lines(path);
        fileArray = lines.mapToInt(Integer::parseInt).toArray();
        lines.close();

        return fileArray;
    }

    public CustomArray CustomArrayWithRandom(int newLength)
            throws ProgramException {
        int[] array = new int[newLength];
        try {
            Random generator = SecureRandom.getInstanceStrong();
            for (int i = 0; i < newLength; i++) {
                array[i] = generator.nextInt() % 30;
            }
        } catch (NoSuchAlgorithmException ignored) {
            throw new ProgramException("generator error");
        }

        return new CustomArray(array);
    }

    private int @NotNull [] findElements(Reporter func, @NotNull CustomArray array) {
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
