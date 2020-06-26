package mishamba.day4.service.task1;

import com.mishamba.day4.entity.CustomArray;
import com.mishamba.day4.exception.ProgramException;
import com.mishamba.day4.service.task1.CustomArraySortService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import static org.testng.Assert.*;

public class CustomArraySortServiceTest {

    @DataProvider(name = "arraysToSort")
    private Object[][] arraysToSort() throws ProgramException {
        return new Object[][] {
                {new CustomArray(new int[] {6, 1, 3, 8, 4, 1, 7, -3, 8, 13}),
                 new CustomArray(new int[] {-3, 1, 1, 3, 4, 6, 7, 8, 8, 13})},
                {new CustomArray(new int[] {8, 1, 5, 8, 4, 2, 9, 2, 28, 2, 5, 8, 25, 8}),
                 new CustomArray(new int[] {1, 2, 2, 2, 4, 5, 5, 8, 8, 8, 8, 9, 25, 28})},
                {new CustomArray(new int[] {-3 ,1 ,4 ,6}),
                 new CustomArray(new int[] {-3, 1, 4, 6})}
        };
    }

    @Test(dataProvider = "arraysToSort", priority = 3)
    public void sortByQuicksort(CustomArray arrayToSort,
                                    CustomArray providedSortedArray) {
        CustomArraySortService service = new CustomArraySortService();
        CustomArray sortedArray = service.sortByQuicksort(arrayToSort);
        assertEquals(sortedArray, providedSortedArray);
    }

    @Test(dataProvider = "arraysToSort", priority = 2)
    public void sortByMergeSort(CustomArray arrayToSort,
                                CustomArray providedSortedArray)
            throws ProgramException {
        // TODO: 6/24/20  
        CustomArraySortService service = new CustomArraySortService();
        CustomArray sortedArray = service.sortByMergeSort(arrayToSort);
        assertEquals(sortedArray, providedSortedArray);
    }

    @Test(dataProvider = "arraysToSort", priority = 1)
    public void sortByInsert(CustomArray arrayToSort,
                                 CustomArray providedSortedArray) {
        CustomArraySortService service = new CustomArraySortService();
        CustomArray sortedArray = service.sortByInsert(arrayToSort);
        assertEquals(sortedArray, providedSortedArray);
    }
}