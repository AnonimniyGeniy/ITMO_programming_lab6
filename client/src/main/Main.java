package main;


import collections.askers.PortAsker;
import commands.CommandDescription;
import commands.CommandResponse;
import managers.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Console console = new managers.UserConsole();
        CommandParser.setScanner(new Scanner(System.in));

        int port = PortAsker.askPort();
        Client client = new Client("localhost", port);
        //CommandDescription[] commandDescriptions = new CommandDescription[]{
        //        new CommandDescription("help", "вывести справку по доступным командам", 0, new ArrayList<>() {}, Void.class),
        //};
        client.connect();

        //client.sendObject("123123123");
        CommandResponse response = new CommandResponse("123123123", null);
        CommandDescription commandDescription = new CommandDescription("help", "вывести справку по доступным командам", 0, new ArrayList<>() {}, Void.class);
        client.sendObject(commandDescription);

        client.close();

        //serialize and deserialize object to send
//        Serializer serializer = new Serializer();
//        Deserializer deserializer = new Deserializer();
//        ByteBuffer buffer = serializer.serialize(response);
//        ByteBuffer buffer1 = serializer.serialize(commandDescription);
//        try {
//            CommandResponse response1 = (CommandResponse) deserializer.deserialize(buffer);
//            CommandDescription commandDescription1 = (CommandDescription) deserializer.deserialize(buffer1);
//            System.out.println(response1.getMessage());
//            System.out.println(commandDescription1.getDescription());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        //CommandManager commandManager = new CommandManager(commandDescriptions);
        //managers.Executor executor = new managers.Executor(commandManager, console);
        //executor.setClient(client);
        //executor.consoleMode();


    }
}
