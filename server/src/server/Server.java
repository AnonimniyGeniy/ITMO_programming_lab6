package server;

import commands.CommandDescription;
import managers.CollectionManager;
import managers.Executor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class that manages connections with clients
 */
public class Server {
    private final CollectionManager collectionManager;
    private final Executor executor;
    private int port;
    private Socket socket;
    private ServerSocket server;
    private ObjectInputStream inputStream;

    private ObjectOutputStream outputStream;
    private InputStream stream;

    public Server(CollectionManager collectionManager) {
        this.port = 5555;
        boolean isRunning = false;
        while (!isRunning) {
            try {
                server = new ServerSocket(port);
                server.setReceiveBufferSize(1000000);
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

    public void run() {
        try {
            connect();
            //while (true) {
            Object obj = null;
            try {
                obj = readObject();
            } catch (Exception e) {
                //continue;
            }
            System.out.println("Received object");
            //if obj in not null print if
            if (obj != null) {
                System.out.println(obj.getClass());
                //System.out.println(((CommandDescription) obj).getDescription());
            }else {
                System.out.println("Object is null");

            }
            //}
            //creating array of command-descriptions by using factory for each command in commands array from executor
//            AbstractCommand[] commands = executor.getCommandsArray();
//            CommandDescription[] commandDescriptions = new CommandDescription[commands.length];
//            for (int i = 0; i < commands.length; i++) {
//                commandDescriptions[i] = CommandDescriptionFactory.createCommandDescription(commands[i].getClass());
//            }
//            System.out.println("Sending command descriptions");
//            sendObject(commandDescriptions);

            //server.close();
            //Object obj = readObject();
//            String s = (String) obj;
//            System.out.println(s);
//            CommandRequest commandRequest = null;
//
//            while (commandRequest == null){
//                try {
//                    commandRequest = (CommandRequest) readObject();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            CommandResponse result = executor.executeCommand(commandRequest);
//            sendObject(result);
//            executor.executeCommand(new CommandRequest("save", new String[]{}, null));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error while running server");
        }
    }

    /**
     * Method that reads object from client
     *
     * @return Object
     * @throws Exception
     */
    private Object readObject() throws Exception {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream(), 1000000);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bufferedInputStream.readAllBytes());

        inputStream = new ObjectInputStream(byteArrayInputStream);

        Object obj = inputStream.readObject();

        return obj;
    }

    private void connect() throws Exception {
        this.socket = server.accept();
        System.out.println("Client connected");
    }

}
