package mishamba.day4.service.task2;

import com.mishamba.day4.service.task2.JaggedArrayService;
import com.mishamba.day4.service.task2.SortOptions;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class JaggedArrayServiceTest {

    @Contract(value = " -> new", pure = true)
    @DataProvider(name = "arrays to sum sort")
    private Object[] @NotNull [] sumArrays() {
        return new Object[][] {
                {new int[][] {
                        {1, 5, 3, 2},
                        {4, 2, 8, 5, 2},
                        {10, 1},
                        {6, 52, 1, 3, 7, 8, 0, 5, 9}},
                new int[][] {
                        {1, 5, 3, 2},
                        {10, 1},
                        {4, 2, 8, 5, 2},
                        {6, 52, 1, 3, 7, 8, 0, 5, 9}}},
                {new int[][] {
                        {5, 3},
                        {1},
                        {3, 2},
                        {893, -382}},
                new int[][] {
                        {1},
                        {3, 2},
                        {5, 3},
                        {893, -382}}}
        };
    }

    @Test(dataProvider = "arrays to sum sort")
    public void sort_sumStringElements(int[][] arrayToSort,
                                       int[][] expectedSortedArray) {
        JaggedArrayService service = new JaggedArrayService();
        int[][] actualSortedArray = service.sort(arrayToSort, SortOptions::largerSum);
        assertEquals(actualSortedArray, expectedSortedArray);
    }

    @Contract(value = " -> new", pure = true)
    @DataProvider(name = "arrays to sort by max element in string")
    private Object[] @NotNull [] maxArrays() {
        return new Object[][] {
                {new int[][] {
                        {5, 2, 7, 10},
                        {10, 22, 5},
                        {3, 0, -1},
                        {5, 102, -43}},
                new int[][] {
                        {3, 0, -1},
                        {5, 2, 7, 10},
                        {10, 22, 5},
                        {5, 102, -43}}},
                {new int[][] {
                        {21, 52, 5},
                        {29, -1, 0},
                        {-2}},
                new int[][] {
                        {-2},
                        {29, -1, 0},
                        {21, 52, 5}}}
        };
    }

    @Test(dataProvider = "arrays to sort by max element in string")
    public void sort_maxStringElement(int[][] arrayToSort,
                                  int[][] expectedSortedArray) {
        JaggedArrayService service = new JaggedArrayService();
        int[][] actualSortedArray = service.sort(arrayToSort, SortOptions::maxElement);
        assertEquals(actualSortedArray, expectedSortedArray);
    }

    @Contract(value = " -> new", pure = true)
    @DataProvider(name = "arrays to sort by min element in string")
    private Object[] @NotNull [] minArrays() {
        return new Object[][]{
                {new int[][]{
                        {5, 2, 8, 3, 1, 12},
                        {2, 1, -1},
                        {6, -4, 4, 3, 4},
                        {6, -2, 8}},
                new int[][] {
                        {6, -4, 4, 3, 4},
                        {6, -2, 8},
                        {2, 1, -1},
                        {5, 2, 8, 3, 1, 12}}},
                {new int[][] {
                        {23, 6, 1, 7, -4},
                        {-4, 32, 6, 3, 10},
                        {-4, 6, 2, 6},
                        {-4, 6, 2, 1, 1, 19}},
                new int[][] {
                        {23, 6, 1, 7, -4},
                        {-4, 32, 6, 3, 10},
                        {-4, 6, 2, 6},
                        {-4, 6, 2, 1, 1, 19}}
                }
        };
    }

    @Test(dataProvider = "arrays to sort by min element in string")
    public void sort_minStringElement(int[][] arrayToSort,
                                      int[][] expectedSortedArray) {
        JaggedArrayService service = new JaggedArrayService();
        int[][] actualSortedArray = service.sort(arrayToSort, SortOptions::minElement);
        assertEquals(actualSortedArray, expectedSortedArray);
    }
}
