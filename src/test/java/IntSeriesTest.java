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

    @Test
    void testSelectDuplicateIndices() {
        Series<Integer> selected = intSeries.select(new Integer[] { 0, 0, 2 }); // [1 1 3] -> take from the setUp values at index 0, 0 ,2

        assertEquals(3, selected.size());
        assertEquals(1, selected.get(0));
//        System.out.println(selected.get(0));
        assertEquals(1, selected.get(1));
//        System.out.println(selected.get(1));
        assertEquals(3, selected.get(2));
//        System.out.println(selected.get(2));

    }

    @Test
    void testMaxOnSingleValue() {
        IntSeries single = new IntSeries("single", new Integer[] { 100 });
        assertEquals(100, single.max());
    }

    @Test
    void testNegativeValues() {
        IntSeries negative = new IntSeries("neg", new Integer[] { -1, -5, null, 0 });
        assertEquals(-5, negative.min());
        assertEquals(0, negative.max());
        assertEquals(-2.0, negative.mean()); // (-1 + -5) / 3
    }
}