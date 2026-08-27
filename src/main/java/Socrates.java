import java.util.Scanner;

public class Socrates {
    private static String[] formatInput(Scanner scanner) {
        String input = scanner.nextLine().strip();
        String[] formattedInput = input.split(" ");
        switch (formattedInput[0]) {
            case "mark", "unmark" -> {
            }
            default -> {
                formattedInput[0] = input;
            }
        }
        return formattedInput;
    }

    private static void formatPrint(String s) {
        String indentedLineBreak = "\t____________________________________________________________";
        System.out.println(indentedLineBreak);
        System.out.println("\t " + s.strip());
        System.out.println(indentedLineBreak);
    }

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
        String byeMessage = "\t Bye. Hope to see you again soon!";
        Scanner scanner = new Scanner(System.in);
        String[] input = formatInput(scanner);
        Task[] list = new Task[100];
        int listIdx = 0;
        while (!input[0].equals("bye")) {
            // get input
            // strip input
            // check if input is not bye
            switch (input[0]) {
                case "list" -> {
                    String concatenatedString = "";
                    for (int i = 0; i < listIdx; i++) {
                        concatenatedString = String.format("%s \t %d.%s\n",
                                concatenatedString,
                                i + 1,
                                list[i].getStatusLine()
                        );
                    }
                    formatPrint(concatenatedString);
                }
                case "mark" -> {
                    int markIdx = Integer.parseInt(input[1]) - 1;
                    list[markIdx].markAsDone();
                    formatPrint("\t Nice! I've marked this task as done:\n" +
                            "\t   " +
                            list[markIdx].getStatusLine()
                    );
                }
                case "unmark" -> {
                    int unmarkIdx = Integer.parseInt(input[1]) - 1;
                    list[unmarkIdx].markAsNotDone();
                    formatPrint("\t Okay, I've marked this task as not done yet:\n" +
                            "\t   " +
                            list[unmarkIdx].getStatusLine()
                    );
                }
                // print input
                default -> {
                    Task temp = new Task(input[0]);
                    String formattedInput = "\t added: " + input[0];
                    formatPrint(formattedInput);
                    list[listIdx] = temp;
                    listIdx++;
                }
            }
            input = formatInput(scanner);
        }
        formatPrint(byeMessage);
    }
}
