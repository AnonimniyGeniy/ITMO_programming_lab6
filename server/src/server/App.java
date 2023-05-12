package server;

import managers.CollectionManager;
import managers.FileManager;

public class App {
    public static void main(String[] args) {
        String path = null;
        try {
            path = System.getenv("FILENAME");
        } catch (Exception e) {
            System.out.println("Environment variable FILENAME is not set");
            System.exit(1);
        }
        if (path == null) {
            System.out.println("Environment variable FILENAME is not set");
            System.exit(1);
        }
        FileManager fileManager = new FileManager(path);
        CollectionManager collectionManager = new CollectionManager(fileManager);
        try {
            collectionManager.loadCollection();
        } catch (Exception e) {
            System.out.println("Error while loading collection from file");
            System.exit(1);
        }

        Server server = new Server(collectionManager);
        //while (true) {
        server.run();

        //}

    }
}