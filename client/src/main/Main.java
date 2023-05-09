package main;


import commands.CommandDescription;
import managers.CommandManager;
import managers.CommandParser;
import managers.Console;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Console console = new managers.UserConsole();
        CommandManager commandManager = new CommandManager(new CommandDescription[]{});
        managers.Executor executor = new managers.Executor(commandManager, console);
        CommandParser.setScanner(new Scanner(System.in));
        executor.consoleMode();


    }
}
