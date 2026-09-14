package socrates;

import java.util.ArrayList;
import java.util.Scanner;

public class Socrates {
    public static void main(String[] args) {
        String banner = "   _____                                          \n" +
                "  / ____|                                         \n" +
                " | (___   ___   ___ _ __ __ _| |_ ___  ___\n" +
                "  \\___ \\ / _ \\ / __| '__/ _` | __/ _ \\/ __|\n" +
                "  ____) | (_) | (__| | | (_| | ||  __/\\__ \\\n" +
                "  |____/ \\___/ \\___|_|  \\__,_|\\__\\___||___/\n" +
                "Hello! I'm Socrates.\n" +
                "What can I do for you? \n" +
                "____________________________________________________________";
        System.out.println(banner);
        ArrayList<Task> list = new ArrayList<>();
        String byeMessage = "\t Bye. Hope to see you again soon!";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String[] formattedInput = CommandHandler.formatInput(scanner.nextLine());
                if (formattedInput[0].equals("bye")) {
                    break;
                }
                CommandHandler.handleInput(list, formattedInput);
            } catch (SocratesException e) {
                CommandHandler.formatPrint(e.getMessage());
            } catch (Exception e) {
                CommandHandler.formatPrint("Error: " + e.getMessage());
            }
        }
        CommandHandler.formatPrint(byeMessage);
    }
}
