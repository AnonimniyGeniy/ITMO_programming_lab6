package managers;

import collections.HumanBeing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * Class for managing file that stores collection
 */
public class FileManager {
    private final String path;
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public FileManager(String path) {
        //if file does not exist, create it using File
        if (!new File(path).exists()) {
            //path = "../" + path;
        }

        this.path = path;
    }


    /**
     * writes collection to file
     *
     * @param collection - collection to write
     */
    public void writeCollection(TreeMap<Integer, HumanBeing> collection) {
        try (PrintWriter writer = new PrintWriter(new File(path))) {
            writer.println(gson.toJson(collection));
            System.out.println("Collection saved to file");
        } catch (IOException e) {
            System.out.println("Error during opening file: " + e.getMessage());
        }
    }

    /**
     * reads collection from file using java.io.BufferedReader
     *
     * @return collection from file
     */
    public TreeMap<Integer, HumanBeing> readCollection() {
        if (path != null && !path.isEmpty()) {
            try (FileReader reader = new FileReader(path)) {
                var CollectionType = new TypeToken<TreeMap<Integer, HumanBeing>>() {
                }.getType();
                var Reader = new BufferedReader(reader);

                var json = new StringBuilder();

                String line;
                while ((line = Reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        json.append(line);
                    }
                }
                if (json.toString().isEmpty()) {
                    json = new StringBuilder("[]");
                }

                TreeMap<Integer, HumanBeing> collection = gson.fromJson(json.toString(), CollectionType);

                System.out.println("Collection loaded from file");

                /**
                 * if every element of collection is not valid, return empty collection and print error
                 */
                
                for (var human : collection.values()) {
                    if (!human.validate()) {
                        System.out.println("Collection is not valid");
                        return new TreeMap<>();
                    }
                }

                return collection;

            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (NoSuchElementException exception) {
                System.out.println("File is empty");
            } catch (JsonParseException exception) {
                System.out.println("File is corrupted");
            } catch (IllegalStateException | IOException exception) {
                System.out.println("Error during reading file: " + exception.getMessage());
                System.exit(0);
            }
        } else {
            System.out.println("Path is not found");
        }

        return new TreeMap<>();
    }


}
