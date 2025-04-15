
import com.Dataframe.DataFrame;
import com.Series.IntSeries;
import com.Series.Series;
import com.Series.StringSeries;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class DataFrameTest {
    private DataFrame df;

    @BeforeEach
    void setUp() {
        List<Series<?>> columns = List.of(
                new IntSeries("age", new Integer[] { 25, 30, null, 20 }),
                new StringSeries("name", new String[] { "Alice", "Bob", "Charlie", null }));
        df = new DataFrame(columns);
    }

    @Test
    void testGetHeaders() {
        assertArrayEquals(new String[] { "age", "name" }, df.getHeaders());
    }

    @Test
    void testGetRows() {
        String[][] rows = df.getRows(0, 2);
        assertEquals(2, rows.length);
        assertEquals("25", rows[0][0]);
        assertEquals("Alice", rows[0][1]);
        assertEquals("30", rows[1][0]);
        assertEquals("Bob", rows[1][1]);
    }

    @Test
    void testSelectRows() {
        DataFrame selected = df.selectRows(0, 2);
        assertEquals(2, selected.size());
        assertEquals(25, selected.getColumn("age").get(0));
        assertEquals("Alice", selected.getColumn("name").get(0));
    }

    @Test
    void testSelectColumns() {
        DataFrame selected = df.selectColumns("age");
        assertArrayEquals(new String[] { "age" }, selected.getHeaders());
        assertEquals(4, selected.size());
    }

    @Test
    void testSelectInvalidColumn() {
        assertThrows(IllegalArgumentException.class, () -> df.selectColumns("invalid"));
    }

    @Test
    void testFilterNumeric() {
        DataFrame filtered = df.filter("age > 25");
        assertEquals(1, filtered.size()); // Rows with age 30
        assertEquals(30, filtered.getColumn("age").get(0));
    }

    @Test
    void testFilterString() {
        DataFrame filtered = df.filter("name = Alice");
        assertEquals(1, filtered.size());
        assertEquals("Alice", filtered.getColumn("name").get(0));
    }

    @Test
    void testStatisticalMethods() {
        assertEquals(25.0, df.mean("age"));
        assertEquals(75, df.sum("age"));
        assertEquals(20, df.min("age"));
        assertEquals(30, df.max("age"));
        assertEquals(3, df.count("age")); // One null value
    }

    @Test
    void testEmptyDataFrame() {
        DataFrame empty = new DataFrame(List.of());
        assertEquals(0, empty.size());
        assertArrayEquals(new String[0], empty.getHeaders());
    }
}