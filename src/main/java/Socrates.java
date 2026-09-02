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
        Task[] list = new Task[100];
        int listIdx = 0;
        String byeMessage = "\t Bye. Hope to see you again soon!";
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] formattedInput = CommandHandler.formatInput(input);
        while (!formattedInput[0].equals("bye")) {
            try {
                listIdx = CommandHandler.handleInput(list, formattedInput, listIdx);
            } catch (Exception e) {
                CommandHandler.formatPrint("Check your input");
            }
            input = scanner.nextLine();
            formattedInput = CommandHandler.formatInput(input);
        }
        CommandHandler.formatPrint(byeMessage);
    }
}
