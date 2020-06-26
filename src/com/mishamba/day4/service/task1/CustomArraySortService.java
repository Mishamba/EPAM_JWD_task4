package com.mishamba.day4.service.task1;

import com.mishamba.day4.entity.CustomArray;
import com.mishamba.day4.exception.ProgramException;

public class CustomArraySortService {
    public CustomArray sortByQuicksort(CustomArray array) throws ProgramException {
        CustomArray customArray = new CustomArray(array);
        intervalQuicksort(0, array.getLength() - 1, customArray);
        return customArray;
    }

    private void intervalQuicksort(int left, int right, CustomArray array) {
        int tmp;
        int i = left;
        int j = right;

        if (left > right) {
            return;
        }

        int mid = (right + left) / 2;

        while (i <= j) {
            while (array.getByIndex(i) < array.getByIndex(mid)) {
                i++;
            }

            while (array.getByIndex(j) > array.getByIndex(mid)) {
                j--;
            }

            if (i <= j) {
                tmp = array.getByIndex(i);
                array.setElementByIndex(array.getByIndex(j), i);
                array.setElementByIndex(tmp, j);
                i++;
                j--;
            }
        }

        mid = (i + j) / 2;

        if (left < j) {
            intervalQuicksort(mid + 1, right, array);
        }
        if (right > i) {
            intervalQuicksort(left, mid, array);
        }
    }

    public CustomArray sortByMergeSort(CustomArray array) throws ProgramException {
        CustomArray sortedArray = new CustomArray(array);
        return sort(sortedArray);
    }

    private CustomArray sort(CustomArray givenArray) throws ProgramException {
        if (givenArray.getLength() == 1) {
            return givenArray;
        }

        int middle = givenArray.getLength() / 2;

        CustomArray leftArray = givenArray.getPartOfArray(0, middle - 1);
        CustomArray rightArray = givenArray.getPartOfArray(middle,
                givenArray.getLength() - 1);

        leftArray = sort(leftArray);
        rightArray = sort(rightArray);

        return merge(leftArray, rightArray);
    }

    private CustomArray merge(CustomArray leftArray,
                              CustomArray rightArray) throws ProgramException {
        int[] mergedArray = new int[leftArray.getLength() +
                rightArray.getLength()];
        int positionLeft = 0;
        int positionRight = 0;

        for (int i = 0; i < mergedArray.length; i++) {
            if (positionRight == rightArray.getLength() ||
                    secureElementsCompare(
                            positionLeft, leftArray,
                            positionRight, rightArray)) {
                mergedArray[i] = leftArray.getByIndex(positionLeft);
                positionLeft++;
            } else {
                mergedArray[i] = rightArray.getByIndex(positionRight);
                positionRight++;
            }
        }

        return new CustomArray(mergedArray);
    }

    private boolean secureElementsCompare(int leftIndex, CustomArray leftArray,
                                          int rightIndex, CustomArray rightArray) {
        boolean less = false;
        if ((leftArray.getLength() > leftIndex) &&
                (rightArray.getLength() > rightIndex)) {
            less = (leftArray.getByIndex(leftIndex) <
                    rightArray.getByIndex(rightIndex));
        }

        return less;
    }

    public CustomArray sortByInsert(CustomArray inputArray) throws ProgramException {
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
