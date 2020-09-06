package map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MapTest {

    private static final String KEY = "someKey";

    private Map map;

    @Before
    public void setUp() {
        map = new Map();
        map.put(KEY, 34);
    }

    @Test
    public void testPut() {
        Assert.assertNotNull(map.get(KEY));
    }

    @Test
    public void testContains() {
        Assert.assertTrue(map.contains(KEY));
    }

    @Test
    public void testRemove() {
        map.remove(KEY);

        Assert.assertFalse(map.contains(KEY));
    }
}
