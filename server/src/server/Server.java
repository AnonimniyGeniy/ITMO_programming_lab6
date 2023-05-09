package server;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Arrays;

import commands.*;
import managers.CollectionManager;
import managers.Executor;

/**
 * Class that manages connections with clients
 */


public class Server {
    private int port;
    private Socket socket;
    private ServerSocket server;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private InputStream stream;
    private final CollectionManager collectionManager;
    private final Executor executor;

    public Server(CollectionManager collectionManager) {
        this.port = 2222;
        boolean isRunning = false;
        while (!isRunning) {
            try {
                server = new ServerSocket(port);
                isRunning = true;
                System.out.println("Server is running on port " + port);
            } catch (IOException e) {
                port++;
            }
        }
        stream = System.in;
        this.collectionManager = collectionManager;
        this.executor = new Executor(collectionManager);


    }
    public void run(){
        try{
            connect();
            //creating array of command-descriptions by using factory for each command in commands array from executor
            AbstractCommand[] commands = executor.getCommandsArray();
            CommandDescription[] commandDescriptions = new CommandDescription[commands.length];
            for (int i = 0; i < commands.length; i++) {
                commandDescriptions[i] = CommandDescriptionFactory.createCommandDescription(commands[i].getClass());
            }
            sendObject(commandDescriptions);
            CommandRequest commandRequest = null;
            while (commandRequest == null){
                try {
                    commandRequest = (CommandRequest) readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            CommandResponse result = executor.executeCommand(commandRequest);
            sendObject(result);
            executor.executeCommand(new CommandRequest("save", new String[]{}, null));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        try {
//            if (stream.available() > 0){
//                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
//                if (reader.readLine().equals("save")){
//                    executor.executeCommand(new CommandRequest("save", new String[]{}, null));
//                    System.out.println("Collection saved");
//                }
//            }
//        }catch (Exception e){
//            System.out.println("Error while saving collection");
//        }
    }


    private void connect() throws Exception {
        socket = server.accept();
        System.out.println("Client connected");
    }

    private Object readObject() throws Exception {
        inputStream = new ObjectInputStream(socket.getInputStream());
        return inputStream.readObject();
    }

    private void sendObject(Object object) throws IOException {
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(object);
        outputStream.flush();
    }

}
