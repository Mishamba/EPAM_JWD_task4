package com.mishamba.day4.entity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class CustomArray {
    private int[] array;

    public CustomArray(@NotNull CustomArray sourceArray) {
        this.array = new int[sourceArray.getLength()];
        for (int i = 0; i < sourceArray.getLength(); i++) {
            this.array[i] = sourceArray.getByIndex(i);
        }
    }

    public CustomArray(int @NotNull [] array) {
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

    public CustomArray getPartOfArray(int left, int right) {
        int[] newArray = new int[right - left + 1];
        for (int i = 0; i < right - left + 1; i++) {
            newArray[i] = array[i];
        }

        return new CustomArray(newArray);
    }

    public boolean isSorted() {
        try {
            for (int i = 0; i < array.length; i++) {
                if (array[i] > array[i + 1]) {
                    return false;
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
            //we catch this exception if we tried to compare element
            // with index > array.length.
        }

        return true;
    }

    public boolean setElementByIndex(int data, int index) {
        if ((index < 0) || (index >= array.length)) {
            return false;
        }

        array[index] = data;
        return true;
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
