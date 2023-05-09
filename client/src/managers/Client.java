package managers;

import commands.CommandDescription;
import commands.CommandRequest;
import commands.CommandResponse;
import interfaces.ClientInterface;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client implements ClientInterface {
    private String host;
    private int port;
    private Socket socket;
    private SocketChannel client;
    private Serializer serializer;
    private Deserializer deserializer;
    private ByteBuffer buffer;
    private OutputStream outputStream;
    private InputStream inputStream;

    private CommandDescription[] commandDescriptions;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        serializer = new Serializer();
        deserializer = new Deserializer();

        this.buffer = ByteBuffer.allocate(100000);
    }

    public void connect() throws IOException {
        try {

            client = SocketChannel.open();
            client.configureBlocking(false);
            client.connect(new InetSocketAddress(host, port));
            while (!client.finishConnect()) {
                continue;
                // Дополнительные действия или ожидание
            }


            //socket = new Socket(host, port);

            //outputStream = socket.getOutputStream();
            //inputStream = socket.getInputStream();
            //System.out.println("Connected to server");

        } catch (IOException e) {
            System.out.println("Error while connecting to server");
            e.printStackTrace();
        }
    }

    @Override
    public CommandResponse run(CommandRequest obj) {
        return null;
    }

    public Object readObject() throws ClassNotFoundException, IOException {
        //while (true) {
        try {
            client.read(buffer);
            //buffer.flip();
            ByteArrayInputStream byteStream = new ByteArrayInputStream(buffer.array());
            ObjectInputStream objectStream = new ObjectInputStream(byteStream);
            Object object = objectStream.readObject();

            return object;
        } catch (Exception e) {
            System.out.println("Error while reading object");
            e.printStackTrace();
        }
        return null;
        //}
    }

    public void sendObject(Object obj) throws IOException {
        try {
            client.write(serializer.serialize(obj));
        } catch (IOException e) {
            System.out.println("Error while sending object");
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        //socket.close();
        client.close();
    }
}
