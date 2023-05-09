package main;


import collections.askers.PortAsker;
import commands.CommandDescription;
import managers.Client;
import managers.CommandManager;
import managers.CommandParser;
import managers.Console;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Console console = new managers.UserConsole();
        CommandParser.setScanner(new Scanner(System.in));


        int port = PortAsker.askPort();
        Client client = new Client("localhost", port);
        CommandDescription[] commandDescriptions = new CommandDescription[]{};
        while (commandDescriptions.length == 0) {
            try {
                port = PortAsker.askPort();
                client = new Client("localhost", port);
                commandDescriptions = client.getCommandDescriptions();
            } catch (Exception e) {
                System.out.println("Error while connecting to server, trying again...");
            }
        }

        CommandManager commandManager = new CommandManager(commandDescriptions);
        managers.Executor executor = new managers.Executor(commandManager, console);
        executor.setClient(client);
        executor.consoleMode();


    }
}
