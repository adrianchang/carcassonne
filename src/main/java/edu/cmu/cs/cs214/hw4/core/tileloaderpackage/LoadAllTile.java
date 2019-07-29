package edu.cmu.cs.cs214.hw4.core.tileloaderpackage;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Load all 24 tiles with snake yaml
 */
public class LoadAllTile {
    /**
     * parse inputstream to tileLoader
     * @param fileName the name of the file
     * @return a TileLoader class
     */
    public static AllTileLoader parse(String fileName) {
        Yaml yaml = new Yaml();
        try (InputStream is = new FileInputStream(fileName)) {
            return yaml.loadAs(is, AllTileLoader.class);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File " + fileName + " not found!");
        } catch (IOException e) {
            throw new IllegalArgumentException("Error when reading " + fileName + "!");
        }
    }
}
