package managers;

import commands.CommandDescription;
import commands.CommandRequest;
import commands.CommandResponse;
import interfaces.ClientInterface;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.StandardSocketOptions;
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

            client = SocketChannel.open(new InetSocketAddress(host, port));
            client.setOption(StandardSocketOptions.SO_RCVBUF, 1000000);
            client.setOption(StandardSocketOptions.SO_SNDBUF, 1000000);
            client.configureBlocking(false);
        } catch (IOException e) {
            System.out.println("Error while connecting to server");
            e.printStackTrace();
        }
    }

    @Override
    public CommandResponse run(CommandRequest obj) {
        return null;
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
