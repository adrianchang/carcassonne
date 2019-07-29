package edu.cmu.cs.cs214.hw4.core.tileloaderpackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import edu.cmu.cs.cs214.hw4.core.tileloaderpackage.TileLoader;
import org.yaml.snakeyaml.Yaml;

/**
 * load a tileLoader with yaml
 * use parse method to parse
 */
public class LoadYml {
    /**
     * parse inputstream to tileLoader
     * @param fileName the name of the file
     * @return a TileLoader class
     */
    public static TileLoader parse(String fileName) {
        Yaml yaml = new Yaml();
        try (InputStream is = new FileInputStream(fileName)) {
            return yaml.loadAs(is, TileLoader.class);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File " + fileName + " not found!");
        } catch (IOException e) {
            throw new IllegalArgumentException("Error when reading " + fileName + "!");
        }
    }
}
