package mishamba.day4.service.task1;

import com.mishamba.day4.entity.CustomArray;
import com.mishamba.day4.exception.ProgramException;
import com.mishamba.day4.service.task1.CustomArrayFindService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CustomArrayFindServiceTest {

    @Contract(value = " -> new", pure = true)
    @DataProvider(name = "random array")
    private Object[] @NotNull [] arrayWithIndex() {
        return new Object[][] {
                {new int[] {1, 3, 4, 5, 7, 9, 10}, 6},
                {new int[] {4, 4, 6, 7, 10, 11}, 2},
                {new int[] {-3, 0, 1, 2, 4, 5, 7, 8, 9}, 4}
        };
    }

    @Test(dataProvider = "random array")
    public void findElementIndex_valid(int[] sourceArray, int expectedIndex) {
        CustomArray array = null;
        try {
            array = new CustomArray(sourceArray);
        } catch (ProgramException ex) {
            fail("got exception");
        }
        CustomArrayFindService service = new CustomArrayFindService();
        try {
            int actualIndex = service.findElemIndex(sourceArray[expectedIndex],
                    array);
            assertEquals(actualIndex, expectedIndex);
        } catch (ProgramException ex) {
            fail("got exception");
        }
    }

    @Contract(value = " -> new", pure = true)
    @DataProvider(name = "array with no such elements")
    private Object[] @NotNull [] arrayWithoutIndex() {
        return new Object[][] {
                {new int[] {1, 2, 3, 4, 6, 7}, 5},
                {new int[] {}, 2},
                {new int[] {5, 2, 1}, 3}
        };
    }

    @Test(dataProvider = "array with no such elements",
            expectedExceptions = ProgramException.class)
    public void findElementIndex_invalid(int[] sourceArray, int elementToFind)
            throws ProgramException {
        CustomArray array = new CustomArray(sourceArray);
        CustomArrayFindService service = new CustomArrayFindService();
        service.findElemIndex(elementToFind, array);
    }

    @Contract(value = " -> new", pure = true)
    @DataProvider(name = "array with prime numbers")
    private Object[] @NotNull [] arrayWithPrimes() {
        return new Object[][] {
                {new int[] {4, 1, 2, 6, 5, 7, 8, 9}, new int[] {1, 2, 5, 7}},
                {new int[] {-4, -2, 1, 6, 13, 4, 5}, new int[] {1, 13, 5}},
                {new int[] {1, 1, 1 , 17, 19, 10}, new int[] {1, 1, 1, 17, 19}}
        };
    }

    @Test(dataProvider = "array with prime numbers")
    public void findPrimeNumbers(int[] sourceArray, int[] primeArray) {
        CustomArray array = null;
        try {
            array = new CustomArray(sourceArray);
        } catch (ProgramException ex) {
            fail("got exception");
        }
        CustomArrayFindService service = new CustomArrayFindService();
        try {
            int[] actualArray = service.findPrimeNumbers(array);
            assertEquals(actualArray, primeArray);
        } catch (ProgramException ex) {
            fail("got exception");
        }
    }

    @Contract(value = " -> new", pure = true)
    @DataProvider(name = "array with fibonacci numbers")
    private Object[] @NotNull [] arrayWithFibonacci() {
        return new Object[][] {
                {new int[] {4, 3, 1, 6, 5, 8}, new int[] {3, 1, 5, 8}},
                {new int[] {9, 2, 4, 6, 5}, new int[] {2, 5}},
                {new int[] {1, 2, 3, 5, 8, 13, 4, 21},
                        new int[] {1, 2, 3, 5, 8, 13, 21}}
        };
    }

    @Test(dataProvider = "array with fibonacci numbers")
    public void findFibonacciNumbers(int[] sourceArray, int[] fibonacciArray) {
        CustomArray array = null;
        try {
            array = new CustomArray(sourceArray);
        } catch (ProgramException ex) {
            fail("got exception");
        }
        CustomArrayFindService service = new CustomArrayFindService();
        try {
            int[] actualArray = service.findFibonacciNumbers(array);
            assertEquals(actualArray, fibonacciArray);
        } catch (ProgramException ex) {
            fail("got exception");
        }
    }

    // This dataprovider named as arraywith strange numbers because
    // this numbers are too big, to been typed here by chance
    @Contract(value = " -> new", pure = true)
    @DataProvider(name = "array with strange numbers")
    private Object[] @NotNull [] strangeArray() {
        return new Object[][] {
                {new int[] {123, 444, 521, 222, 1, 4, 23, 22},
                        new int[] {444, 222}},
                {new int[] {533, 543, 12, 1, 44, 1835, 4444},
                        new int[] {}},
                {new int[] {112, 444, 222, 555, 666, 777},
                        new int[] {444, 222, 555, 666, 777}}
        };
    }

    @Test(dataProvider = "array with strange numbers")
    public void findNumbersWithNoSameLetters(int[] sourceArray,
                                                 int[] strangeArray) {
        CustomArray array = null;
        try {
            array = new CustomArray(sourceArray);
        } catch (ProgramException ex) {
            fail("got exception");
        }
        CustomArrayFindService service = new CustomArrayFindService();
        try {
            int[] actualArray = service.findNumbersWithNoSameLetters(array);
            assertEquals(actualArray, strangeArray);
        } catch (ProgramException ex) {
            fail("got exception");
        }
    }
}