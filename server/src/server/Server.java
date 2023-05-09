package server;

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

    public void run() {
        try {
            connect();
            sendObject(new String("Some message"));
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
        //OutputStream os = socket.getOutputStream();
        //os.write(object);
    }

}
