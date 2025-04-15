import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.Series.IntSeries;
import com.Series.Series;

public class IntSeriesTest {
    private IntSeries intSeries;

    @BeforeEach
    void setUp() {
        Integer[] data = { 1, null, 3, 5 };
        intSeries = new IntSeries("test", data);
    }

    @Test
    void testGet() {
        assertEquals(1, intSeries.get(0));
        assertNull(intSeries.get(1));
    }

    @Test
    void testCount() {
        assertEquals(3, intSeries.count());
    }

    @Test
    void testSize() {
        assertEquals(4, intSeries.size());
    }

    @Test
    void testSelect() {
        Series<Integer> selected = intSeries.select(new Integer[] { 0, 2 });
        assertEquals(2, selected.size());
        assertEquals(1, selected.get(0));
        assertEquals(3, selected.get(1));
    }

    @Test
    void testSum() {
        assertEquals(9, intSeries.sum());
    }

    @Test
    void testMean() {
        assertEquals(3.0, intSeries.mean());
    }

    @Test
    void testMin() {
        assertEquals(1, intSeries.min());
    }

    @Test
    void testMax() {
        assertEquals(5, intSeries.max());
    }

    @Test
    void testAllNulls() {
        IntSeries series = new IntSeries("allNulls", new Integer[] { null, null });
        assertEquals(0, series.sum());
        assertEquals(0.0, series.mean());
        assertEquals(0, series.min());
        assertEquals(0, series.max());
    }
}