package socrates;


import socrates.storage.FileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Socrates {
    public static void main(String[] args) {
        ArrayList<Task> list = FileHandler.loadFile();
        String banner = "   _____                                          \n" +
                "  / ____|                                         \n" +
                " | (___   ___   ___ _ __ __ _| |_ ___  ___\n" +
                "  \\___ \\ / _ \\ / __| '__/ _` | __/ _ \\/ __|\n" +
                "  ____) | (_) | (__| | | (_| | ||  __/\\__ \\\n" +
                "  |____/ \\___/ \\___|_|  \\__,_|\\__\\___||___/\n" +
                "Hello! I'm Socrates.\n" +
                "What shall we examine together today? (say 'help' if you're unsure) \n" +
                "____________________________________________________________";
        System.out.println(banner);
        String byeMessage = "\t Farewell, friend. Remember - the unexamined task list is not worth keeping!";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String[] formattedInput = CommandHandler.formatInput(scanner.nextLine());
                if (formattedInput[0].equals("bye")) {
                    break;
                }
                CommandHandler.handleInput(list, formattedInput);
                FileHandler.saveFile(list);
            } catch (SocratesException e) {
                CommandHandler.formatPrint(e.getMessage());
            } catch (Exception e) {
                CommandHandler.formatPrint("Error: " + e.getMessage());
            }
        }
        CommandHandler.formatPrint(byeMessage);
    }
}
