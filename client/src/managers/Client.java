package managers;

import collections.Deserializer;
import collections.Serializer;
import commands.CommandDescription;
import commands.CommandRequest;
import commands.CommandResponse;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    private String host;
    private int port;
    private SocketChannel client;
    private Serializer serializer;
    private Deserializer deserializer;
    private ByteBuffer buffer;
    private CommandDescription[] commandDescriptions;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
        serializer = new Serializer();
        deserializer = new Deserializer();
        this.buffer = ByteBuffer.allocate(100000);
        try {
            connect();
            this.commandDescriptions = (CommandDescription[]) receiveObject();
        } catch (Exception e) {
            System.out.println("Error while connecting to server");
        }

    }

    public CommandResponse run(CommandRequest obj) {
        CommandResponse response = null;
        try {
            connect();
            //try receiving command descriptions
            try {
                this.commandDescriptions = (CommandDescription[]) receiveObject();
            } catch (Exception e) {
                System.out.println("Error while receiving command descriptions");
            }
            sendObject(obj);
            response = (CommandResponse) receiveObject();
            close();
        } catch (Exception e) {
            return new CommandResponse("Error while running command", null);
        }
        return response;
    }

    private void connect() throws IOException {
        client = SocketChannel.open(new InetSocketAddress(host, port));
        client.configureBlocking(false);

    }

    private void sendObject(Object obj) throws IOException {
        try {
            client.write(serializer.serialize(obj));
        } catch (Exception e) {
            System.out.println("Error while sending object");
        }
    }

    private Object receiveObject() throws IOException {
        while (true) {
            try {
                client.read(buffer);
                Object o = deserializer.deserialize(buffer);
                buffer = ByteBuffer.allocate(100000);
                return o;
            } catch (Exception e) {
                System.out.println("Error while receiving object");
                return null;
            }
        }
    }

    public CommandDescription[] getCommandDescriptions() {
        return commandDescriptions;
    }

    private void close() throws IOException {
        client.close();
    }

    public void receiveCommandDescriptions() throws IOException {
        connect();
        this.commandDescriptions = (CommandDescription[]) receiveObject();
    }

}
