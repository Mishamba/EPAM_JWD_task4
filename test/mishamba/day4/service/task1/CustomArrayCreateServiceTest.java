package mishamba.day4.service.task1;

import com.mishamba.day4.entity.CustomArray;
import com.mishamba.day4.exception.ProgramException;
import com.mishamba.day4.service.task1.CustomArrayCreateService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.testng.Assert.*;

public class CustomArrayCreateServiceTest {

    @Contract(" -> new")
    @DataProvider(name = "existing correct files")
    private Object[] @NotNull [] validFiles() {
        return new Object[][] {
                {Paths.get("valid_data/testSource1"),
                        new CustomArray(new int[] {4, 2, 5, 8, 4, 2, 6, 0, 5})},
                {Paths.get("valid_data/testSource2"),
                        new CustomArray(new int[] {5, 1, 3, 8, 3, 1})},
                {Paths.get("valid_data/testSource3"),
                        new CustomArray(new int[] {3, 3, 3, 3})},
                {Paths.get("valid_data/testSource4"),
                        new CustomArray(new int[] {})}
        };
    }

    @Test(dataProvider = "existing correct files")
    public void customArrayByFile_valid(Path filePath, CustomArray expectedArray) {
        CustomArrayCreateService service = new CustomArrayCreateService();
        try {
            CustomArray actualArray = service.customArrayByFile(filePath);
            assertEquals(actualArray, expectedArray);
        } catch (ProgramException ex) {
            fail("got exception");
        }
    }

    @Contract(" -> new")
    @DataProvider(name = "not existing or incorrect files")
    private Object[] @NotNull [] invalidFiles() {
        return new Object[][] {
                {"invalid_data/testSource1"},
                {"invalid_data/testSource2"},
                {"invalid_data/testSource3"}
        };
    }

    @Test(dataProvider = "not existing or incorrect files",
            expectedExceptions = ProgramException.class)
    public void customArraybyFiles_invalid(String filePath)
            throws ProgramException {
        Path file = Paths.get(filePath);
        CustomArrayCreateService service = new CustomArrayCreateService();
        service.customArrayByFile(file);
    }
}