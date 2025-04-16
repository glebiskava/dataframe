import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.Series.Series;
import com.Series.StringSeries;

public class StringSeriesTest {
    private StringSeries stringSeries;

    @BeforeEach
    void setUp() {
        String[] data = { "Alice", null, "Charlie", "" };
        stringSeries = new StringSeries("test", data);
    }

    @Test
    void testGet() {
        assertEquals("Alice", stringSeries.get(0));
        assertNull(stringSeries.get(1));
        assertEquals("", stringSeries.get(3));
    }

    @Test
    void testCount() {
        assertEquals(3, stringSeries.count()); // "" is considered non-null
    }

    @Test
    void testSize() {
        assertEquals(4, stringSeries.size());
    }

    @Test
    void testSelect() {
        Series<String> selected = stringSeries.select(new Integer[] { 0, 2 });
        assertEquals(2, selected.size());
        assertEquals("Alice", selected.get(0));
        assertEquals("Charlie", selected.get(1));
    }

    @Test
    void testType() {
        assertEquals("String", stringSeries.getType());
    }

    @Test
    void testSelectWithNullIndices() {
        assertThrows(NullPointerException.class, () -> stringSeries.select(null));
    }

    @Test
    void testCountEmptyStrings() {
        StringSeries emptyStrings = new StringSeries("empty", new String[] { "", "", null });
        assertEquals(2, emptyStrings.count());
    }

    @Test
    void testGetTypeConsistency() {
        StringSeries empty = new StringSeries("empty", new String[] {});
        assertEquals("String", empty.getType());
    }
}