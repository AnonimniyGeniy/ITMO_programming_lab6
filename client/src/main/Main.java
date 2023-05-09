package main;


import collections.askers.PortAsker;
import commands.CommandDescription;
import managers.Client;
import managers.CommandParser;
import managers.Console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Console console = new managers.UserConsole();
        CommandParser.setScanner(new Scanner(System.in));

        int port = PortAsker.askPort();
        Client client = new Client("localhost", port);
        CommandDescription[] commandDescriptions = new CommandDescription[]{
                new CommandDescription("help", "вывести справку по доступным командам", 0, new ArrayList<>() {
                }, Void.class),
        };
        client.connect();
        Object result = null;
        try {
            result = client.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String s = (String) result;
        System.out.println(s);
        //client.sendObject(new String("Client message"));
        client.close();
        //CommandManager commandManager = new CommandManager(commandDescriptions);
        //managers.Executor executor = new managers.Executor(commandManager, console);
        //executor.setClient(client);
        //executor.consoleMode();


    }
}
