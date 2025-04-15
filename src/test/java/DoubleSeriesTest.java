import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.Series.DoubleSeries;
import com.Series.Series;

public class DoubleSeriesTest {
    private DoubleSeries doubleSeries;

    @BeforeEach
    void setUp() {
        Double[] data = { 1.5, null, 3.0, 5.5 };
        doubleSeries = new DoubleSeries("test", data);
    }

    @Test
    void testGet() {
        assertEquals(1.5, doubleSeries.get(0));
        assertNull(doubleSeries.get(1));
    }

    @Test
    void testCount() {
        assertEquals(3, doubleSeries.count());
    }

    @Test
    void testSize() {
        assertEquals(4, doubleSeries.size());
    }

    @Test
    void testSelect() {
        Series<Double> selected = doubleSeries.select(new Integer[] { 0, 2 });
        assertEquals(2, selected.size());
        assertEquals(1.5, selected.get(0));
        assertEquals(3.0, selected.get(1));
    }

    @Test
    void testSum() {
        assertEquals(10.0, doubleSeries.sum());
    }

    @Test
    void testMean() {
        assertEquals(3.333, doubleSeries.mean(), 0.001);
    }

    @Test
    void testMin() {
        assertEquals(1.5, doubleSeries.min());
    }

    @Test
    void testMax() {
        assertEquals(5.5, doubleSeries.max());
    }

    @Test
    void testAllNulls() {
        DoubleSeries series = new DoubleSeries("allNulls", new Double[] { null, null });
        assertEquals(0.0, series.sum());
        assertEquals(0.0, series.mean());
        assertEquals(0.0, series.min());
        assertEquals(0.0, series.max());
    }
}