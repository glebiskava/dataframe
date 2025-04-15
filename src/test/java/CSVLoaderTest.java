import org.junit.jupiter.api.Test;

import com.Dataframe.CSVLoader;
import com.Dataframe.DataFrame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

public class CSVLoaderTest {
    @Test
    void testLoadCSV() throws IOException {
        String csvContent = "name,age\nAlice,25\nBob,30\n\"Charlie\",35.5";
        Path tempFile = Files.createTempFile("test", ".csv");
        Files.write(tempFile, csvContent.getBytes());

        DataFrame df = CSVLoader.fromCSV(tempFile.toString());
        assertEquals(3, df.size());
        assertArrayEquals(new String[] { "name", "age" }, df.getHeaders());
        assertEquals("String", df.getColumn("name").getType());
        assertEquals("Double", df.getColumn("age").getType());
    }

    @Test
    void testEmptyCSV() {
        assertThrows(IllegalArgumentException.class, () -> CSVLoader.fromCSV("nonexistent.csv"));
    }

    @Test
    void testTypeInference() throws IOException {
        String csvContent = "intCol,doubleCol,stringCol\n5,3.14,text\n7,2.71,moreText";
        Path tempFile = Files.createTempFile("test", ".csv");
        Files.write(tempFile, csvContent.getBytes());

        DataFrame df = CSVLoader.fromCSV(tempFile.toString());
        assertEquals("Integer", df.getColumn("intCol").getType());
        assertEquals("Double", df.getColumn("doubleCol").getType());
        assertEquals("String", df.getColumn("stringCol").getType());
    }

    @Test
    void testLoadCSVWithMissingValues() throws IOException {

        String csvContent = "name,age\nAlice,\nBob,30\n,35.5";
        Path tempFile = Files.createTempFile("test", ".csv");
        Files.write(tempFile, csvContent.getBytes());

        DataFrame df = CSVLoader.fromCSV(tempFile.toString());
        assertEquals(3, df.size());
        assertNull(df.getColumn("age").get(0));
        assertNull(df.getColumn("name").get(2));
    }

    @Test
    void testLoadCSVWithInvalidNumberFormat() throws IOException {
        String csvContent = "name,age\nAlice,25\nBob,invalid\nCharlie,35.5";
        Path tempFile = Files.createTempFile("test", ".csv");
        Files.write(tempFile, csvContent.getBytes());

        DataFrame df = CSVLoader.fromCSV(tempFile.toString());
        assertEquals(3, df.size());
        assertEquals("String", df.getColumn("age").getType()); // The age column will be inferred as a String due to
                                                               // invalid value.
    }

}
