package com.mishamba.day4.service.task1;

import com.mishamba.day4.entity.CustomArray;
import com.mishamba.day4.exception.ProgramException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class CustomArrayCreateService {
    public CustomArray customArrayByConsole() {
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

    public CustomArray customArrayByFile(Path path) throws ProgramException {
        try {
            int[] array = getArrayFromFile(path);
            return new CustomArray(array);
        } catch (IOException ex) {
            throw new ProgramException("reading from file error");
        } catch (NumberFormatException ex) {
            throw new ProgramException("not a number in file");
        }
    }

    private int[] getArrayFromFile(Path path)
            throws IOException, NumberFormatException {
        Stream<String> lines = Files.lines(path);
        int[] fileArray = lines.mapToInt(Integer::parseInt).toArray();
        lines.close();

        return fileArray;
    }

    public CustomArray customArrayWithRandom(int newLength)
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
}
