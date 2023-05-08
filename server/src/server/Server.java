package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.ServerSocket;
;
import commands.CommandRequest;
import commands.CommandResponse;
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
            executor.executeCommand(new CommandRequest("save", new Object[]{}, null));
        } catch (Exception e) {
            throw new RuntimeException(e);
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
    }

}
