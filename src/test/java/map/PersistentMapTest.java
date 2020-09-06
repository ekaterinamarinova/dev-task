package map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(JUnit4.class)
public class PersistentMapTest {

    private static final String ABSOLUTE_PATH = Paths.get(".").toAbsolutePath().normalize().toString();
    private static final Path CUSTOM_PATH = Path.of(ABSOLUTE_PATH, "/src/test/resources/Output.txt");
    private static final String KEY = "someKey";

    private PersistentMap persistentMap;

    @Before
    public void setUp() {
        persistentMap = new PersistentMap(5, CUSTOM_PATH);
    }

    @Test
    public void testPutInFile() throws IOException {
        persistentMap.putInFile(KEY, "someValue");

        Assert.assertTrue(Files.exists(CUSTOM_PATH));

        Files.deleteIfExists(CUSTOM_PATH);
    }

    @Test
    public void testRemoveFromFile() throws IOException {
        persistentMap.putInFile(KEY, "someValue");
        var newPath = persistentMap.removeFromFile(KEY);

        boolean doesContain = Files.readAllLines(newPath).contains(KEY);

        Assert.assertFalse(doesContain);

        Files.deleteIfExists(CUSTOM_PATH);
    }

    @Test
    public void testGetFromFile() throws IOException {
        persistentMap.putInFile(KEY, "someValue");

        var value = persistentMap.getFromFile(KEY);

        Assert.assertEquals(value, "someValue");
    }


}
