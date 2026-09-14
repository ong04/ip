package socrates;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {
    public static String[] formatInput(String s) {
        String[] formattedInput = s.strip().split(" ", 2);
        return formattedInput;
    }

    public static void formatPrint(String s) {
        String indentedLineBreak = "\t____________________________________________________________";
        System.out.println(indentedLineBreak);
        System.out.println("\t " + s.strip());
        System.out.println(indentedLineBreak);
    }

    public static void handleList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            formatPrint("List is empty");
        } else {
            String concatenatedString = "";
            int n = 0;
            for (Task i : list) {
                concatenatedString = String.format("%s \t %d.%s\n",
                        concatenatedString,
                        ++n,
                        i.getStatusLine()
                );
            }
            formatPrint(concatenatedString);
        }
    }

    public static void handleMark(ArrayList<Task> list, String[] input) {
        int markIdx = Integer.parseInt(input[1]) - 1;
        list.get(markIdx).markAsDone();
        formatPrint("\t Nice! I've marked this task as done:\n" +
                "\t   " +
                list.get(markIdx).getStatusLine()
        );
    }

    public static void handleUnmark(ArrayList<Task> list, String[] input) {
        int unmarkIdx = Integer.parseInt(input[1]) - 1;
        list.get(unmarkIdx).markAsNotDone();
        formatPrint("\t Okay, I've marked this task as not done yet:\n" +
                "\t   " +
                list.get(unmarkIdx).getStatusLine()
        );
    }

    public static ToDo buildToDo(String description) {
        return new ToDo(description);
    }

    public static Deadlines buildDeadlines(String description, String by) {
        return new Deadlines(description, by);
    }

    public static Events buildEvents(String descriptions, String from, String to) {
        return new Events(descriptions, from, to);
    }

    public static void printAddedTask(Task task, int n) {
        formatPrint(String.format("Got it. Ive added this task:\n\t   " +
                task.getStatusLine() +
                "\n\t Now you have %d tasks in the list", n
        ));
    }

    public static void handleToDo(ArrayList<Task> list, String[] input) throws SocratesException {
        try {
            ToDo temp = buildToDo(input[1]);
            list.add(temp);
            printAddedTask(temp, list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new SocratesException("Task not found, use case: todo {task}");
        }
    }

    public static void handleDeadlines(ArrayList<Task> list, String[] input) throws SocratesException {
        // format the rest of the string
        String[] formattedDescription = input[1].split(" /by ");
        if (formattedDescription.length == 1) {
            throw new SocratesException("Deadline not found, use case: deadline {task} /by {deadline}");
        }
        Deadlines temp = buildDeadlines(formattedDescription[0], formattedDescription[1]);
        list.add(temp);
        printAddedTask(temp, list.size());
    }

    public static void handleEvents(ArrayList<Task> list, String[] input) throws SocratesException {
        String[] formattedDescription = input[1].split(" /from ");
        if (formattedDescription.length == 1) {
            throw new SocratesException("Event timings not found, use case: event {event description} /from {start date and time} /to {end date and time}");
        }
        String[] dateRange = formattedDescription[1].split(" /to ");
        if (dateRange.length == 1) {
            throw new SocratesException("Event timings not found, use case: event {event description} /from {start date and time} /to {end date and time}");
        }
        Events temp = buildEvents(formattedDescription[0], dateRange[0], dateRange[1]);
        list.add(temp);
        printAddedTask(temp, list.size());
    }

    public static void handleDelete(ArrayList<Task> list, String[] input) throws SocratesException {
        int n;
        try {
            n = Integer.parseInt(input[1]) - 1;
        } catch (NumberFormatException e) {
            throw new SocratesException("Use case: delete {index}");
        }
        if (n >= list.size() || n < 0) {
            throw new SocratesException("Index out of bounds");
        }

        formatPrint("Noted. I've removed this task:\n\t   " +
                list.get(n).getStatusLine() +
                "\n\t now you have " +
                (list.size() - 1) +
                " tasks in the list");
        list.remove(n);
    }

    public static void handleInput(ArrayList<Task> list, String[] input) throws SocratesException {
        switch (input[0]) {
            case "list" -> {
                handleList(list);
            }
            case "mark" -> {
                handleMark(list, input);
            }
            case "unmark" -> {
                handleUnmark(list, input);
            }
            case "todo" -> {
                handleToDo(list, input);
            }
            case "deadline" -> {
                handleDeadlines(list, input);
            }
            case "event" -> {
                handleEvents(list, input);
            }
            case "delete" -> {
                handleDelete(list, input);
            }
            default -> {
                formatPrint(
                        "I do not know what that means, please follow one of these commands:" +
                                "\n\t\tlist\n\t\tmark\n\t\tunmark\n\t\ttodo\n\t\tdeadline\n\t\tevent"
                );
            }
        }
    }
}