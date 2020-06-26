package com.mishamba.day4.entity;

import com.mishamba.day4.exception.ProgramException;

public class CustomArray {
    private final int[] array;

    public CustomArray(CustomArray sourceArray) throws ProgramException {
        if (sourceArray == null) {
            throw new ProgramException("i've got null array.");
        }
        this.array = new int[sourceArray.getLength()];
        for (int i = 0; i < sourceArray.getLength(); i++) {
            this.array[i] = sourceArray.getByIndex(i);
        }
    }

    public CustomArray(int [] array) throws ProgramException {
        if (array == null) {
            throw new ProgramException("i've got null array.");
        }
        this.array = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i];
        }
    }

    public int getByIndex(int index) {
        return array[index];
    }

    public int getLength() {
        return array.length;
    }

    public CustomArray getPartOfArray(int left, int right) throws ProgramException {
        int[] newArray = new int[right - left + 1];
        for (int i = left; i < right + 1; i++) {
            newArray[i - left] = array[i];
        }

        return new CustomArray(newArray);
    }

    public boolean isSorted() {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }

        return true;
    }

    public void setElementByIndex(int data, int index) {
        if ((index < 0) || (index >= array.length)) {
            return;
        }

        array[index] = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomArray)) {
            return false;
        }

        CustomArray array1 = (CustomArray) o;
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
        int prime = 52;
        int hash = 1;
        for (int elem : array) {
            hash += (prime * hash) + elem;
        }

        return hash;
    }
}
