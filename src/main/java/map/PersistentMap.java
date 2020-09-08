package map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;

/**
 * This data structure is a map that persists its data
 * in the local file system.
 */
public class PersistentMap extends Map {

    /**
     * The absolute path to the desired storage file + file name + file extension
     * example: C:/Users/User/Desktop/dev-task/Output.txt"
     */
    private final Path pathToLocalDir;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public PersistentMap(int capacity, Path pathToLocalDir) {
        super(capacity);
        this.pathToLocalDir = pathToLocalDir;
    }

    public PersistentMap(Path pathToLocalDir) {
        this.pathToLocalDir = pathToLocalDir;
    }

    /**
     * Puts all the data from the map in a file.
     * Creates the desired file if it doesn't exist;
     * else appends to the existing one.
     *
     * @param key - given key
     * @param value - given value object
     * @throws IOException - if invalid path, if you don't have access to a
     * given directory, etc.
     */
    public void putInFile(String key, Object value) throws IOException {
        super.put(key, value);

        objectMapper.writeValue(new File(pathToLocalDir.toString()), super.nodes);
    }

    /**
     * Removes the node based on the given key
     * and creates a new file without it.
     *
     * @param key - based on which removal is performed
     * @return - {@link Path} to the updated file
     * @throws IOException - if something goes wrong during
     * file processing
     */
    public Path removeFromFile(String key) throws IOException {
        boolean isRemoved = super.remove(key);
        if (!isRemoved) return null;

        Files.deleteIfExists(pathToLocalDir);

        var pathToNewFile = Files.createFile(pathToLocalDir);
        return Files.write(pathToNewFile,
                Collections.singleton(Arrays.toString(nodes)));
    }

    /**
     * Returns the value of the key from the file.
     *
     * Due to serialization and string manipulation, the
     * value is always positioned two indexes after the key, so
     * that's what we return.
     *
     * @param key - string based on which we search
     * @return - a {@link String} formatted object that
     * corresponds to the value of the key. Might need further
     * deserialization.
     *
     * @throws IOException - if reading goes wrong
     */
    public Object getFromFile(String key) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        String data = Files.readString(pathToLocalDir);
        Node[] nodesFromFile = objectMapper.readValue(data,  Map.Node[].class);

        for (Node n:nodesFromFile) {
            if (n == null) continue;
            if (key.hashCode() == n.getHash()) return n.getValue();
        }

        return null;
    }

}
