import java.util.Scanner;

public class CommandHandler {

    public static String[] formatInput(String s) {
        String[] formattedInput = s.split(" ", 2);
        switch (formattedInput[0]) {
            case "list", "mark", "unmark", "todo", "deadline", "event" -> {
            }
            default -> {
                formattedInput[0] = s;
            }
        }
        return formattedInput;
    }

    public static void formatPrint(String s) {
        String indentedLineBreak = "\t____________________________________________________________";
        System.out.println(indentedLineBreak);
        System.out.println("\t " + s.strip());
        System.out.println(indentedLineBreak);
    }

    public static void handleList(Task[] list, int n) {
        String concatenatedString = "";
        for (int i = 0; i < n; i++) {
            concatenatedString = String.format("%s \t %d.%s\n",
                    concatenatedString,
                    i + 1,
                    list[i].getStatusLine()
            );
        }
        formatPrint(concatenatedString);
    }

    public static void handleMark(Task[] list, String[] input) {
        int markIdx = Integer.parseInt(input[1]) - 1;
        list[markIdx].markAsDone();
        formatPrint("\t Nice! I've marked this task as done:\n" +
                "\t   " +
                list[markIdx].getStatusLine()
        );
    }

    public static void handleUnmark(Task[] list, String[] input) {
        int unmarkIdx = Integer.parseInt(input[1]) - 1;
        list[unmarkIdx].markAsNotDone();
        formatPrint("\t Okay, I've marked this task as not done yet:\n" +
                "\t   " +
                list[unmarkIdx].getStatusLine()
        );
    }

    public static void handleToDo(Task[] list, String[] input, int n) {
        ToDo temp = new ToDo(input[1]);
        list[n] = temp;
        n++;
        formatPrint(String.format("Got it. Ive added this task:\n\t   " +
                temp.getStatusLine() +
                "\n\t Now you have %d tasks in the list", n
        ));
    }

    public static void handleDeadlines(Task[] list, String[] input, int n) {
        // format the rest of the string
        String[] formattedDescription = input[1].split("/by ");
        Deadlines temp = new Deadlines(formattedDescription[0], formattedDescription[1]);
        list[n] = temp;
        n++;
        formatPrint(String.format("Got it. Ive added this task:\n\t   " +
                temp.getStatusLine() +
                "\n\t Now you have %d tasks in the list", n
        ));
    }

    public static void handleEvents(Task[] list, String[] input, int n) {
        String[] formattedDescription = input[1].split(" /from ");
        String[] dateRange = formattedDescription[1].split(" /to ");
        Events temp = new Events(formattedDescription[0], dateRange[0], dateRange[1]);
        list[n] = temp;
        n++;
        formatPrint(String.format("Got it. Ive added this task:\n\t   " +
                temp.getStatusLine() +
                "\n\t Now you have %d tasks in the list", n
        ));
    }

    public static void handleDefault(Task[] list, String[] input, int n) {
        Task temp = new Task(input[0]);
        String formattedInput = "\t added: " + input[0];
        formatPrint(formattedInput);
        list[n] = temp;
    }

    public static int handleInput(Task[] list, String[] input, int listIdx) {
        switch (input[0]) {
            case "list" -> {
                handleList(list, listIdx);
            }
            case "mark" -> {
                handleMark(list, input);
            }
            case "unmark" -> {
                handleUnmark(list, input);
            }
            case "todo" -> {
                handleToDo(list, input, listIdx);
                listIdx++;
            }
            case "deadline" -> {
                handleDeadlines(list, input, listIdx);
                listIdx++;
            }
            case "event" -> {
                handleEvents(list, input, listIdx);
                listIdx++;
            }
            default -> {
                handleDefault(list, input, listIdx);
                listIdx++;
            }
        }
        return listIdx;
    }
}