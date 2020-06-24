package com.mishamba.day4.service;

import com.mishamba.day4.entity.CustomArray;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class CustomArraySortService {
    public CustomArray sortByQuicksort(CustomArray array) {
        CustomArray customArray = new CustomArray(array);
        intervalQuicksort(0, array.getLength() - 1, customArray);
        return customArray;
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
        CustomArray sortedArray = new CustomArray(array);
        return sort(sortedArray);
    }

    private @NotNull CustomArray sort(@NotNull CustomArray givenArray) {
        if (givenArray.getLength() == 1) {
            return givenArray;
        }

        int middle = givenArray.getLength() / 2;

        CustomArray leftArray = givenArray.getPartOfArray(0, middle - 1);
        CustomArray rightArray = givenArray.getPartOfArray(middle,
                givenArray.getLength() - 1);

        sort(leftArray);
        sort(rightArray);

        return merge(leftArray, rightArray);
    }

    @Contract("_, _ -> new")
    private @NotNull CustomArray merge(@NotNull CustomArray leftArray,
                                       @NotNull CustomArray rightArray) {
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
                int tmp = array.getByIndex(j);
                array.setElementByIndex(array.getByIndex(j + 1), j);
                array.setElementByIndex(tmp, j + 1);
                j--;
            }

            array.setElementByIndex(key, j + 1);
        }

        return array;
    }
}
