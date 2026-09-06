package socrates;

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

    public static void handleList(Task[] list, int n) {
        if (list.length == 0) {
            formatPrint("List is empty");
        } else {
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

    public static void handleToDo(Task[] list, String[] input, int n) throws SocratesException {
        try {
            ToDo temp = new ToDo(input[1]);
            list[n] = temp;
            n++;
            formatPrint(String.format("Got it. Ive added this task:\n\t   " +
                    temp.getStatusLine() +
                    "\n\t Now you have %d tasks in the list", n
            ));
        } catch (IndexOutOfBoundsException e) {
            throw new SocratesException("Task not found, use case: todo {task}");
        }
    }

    public static void handleDeadlines(Task[] list, String[] input, int n) throws SocratesException {
        // format the rest of the string
        String[] formattedDescription = input[1].split(" /by ");
        if (formattedDescription.length == 1) {
            throw new SocratesException("Deadline not found, use case: deadline {task} /by {deadline}");
        }
        Deadlines temp = new Deadlines(formattedDescription[0], formattedDescription[1]);
        list[n] = temp;
        n++;
        formatPrint(String.format("Got it. Ive added this task:\n\t   " +
                temp.getStatusLine() +
                "\n\t Now you have %d tasks in the list", n
        ));
    }

    public static void handleEvents(Task[] list, String[] input, int n) throws SocratesException {
        String[] formattedDescription = input[1].split(" /from ");
        if (formattedDescription.length == 1) {
            throw new SocratesException("Event timings not found, use case: event {event description} /from {start date and time} /to {end date and time}");
        }
        String[] dateRange = formattedDescription[1].split(" /to ");
        if (dateRange.length == 1) {
            throw new SocratesException("Event timings not found, use case: event {event description} /from {start date and time} /to {end date and time}");
        }
        Events temp = new Events(formattedDescription[0], dateRange[0], dateRange[1]);
        list[n] = temp;
        n++;
        formatPrint(String.format("Got it. Ive added this task:\n\t   " +
                temp.getStatusLine() +
                "\n\t Now you have %d tasks in the list", n
        ));
    }

    public static int handleInput(Task[] list, String[] input, int listIdx) throws SocratesException {
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
                formatPrint(
                        "I do not know what that means, please follow one of these commands:" +
                                "\n\t\tlist\n\t\tmark\n\t\tunmark\n\t\ttodo\n\t\tdeadline\n\t\tevent"
                );
            }
        }
        return listIdx;
    }
}