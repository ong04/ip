import java.util.Scanner;

public class Socrates {
    private static String[] formatInput(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().strip();
        String[] formattedInput = input.split(" ");
        switch (formattedInput[0]) {
            case "mark", "unmark" -> {}
            default -> {
                formattedInput[0] = input;
            }
        }
        return formattedInput;
    }
    public static void main(String[] args) {
        String banner = "   _____                                          \n" +
                "  / ____|                                         \n" +
                " | (___   ___   ___ _ __ __ _| |_ ___  ___\n" +
                "  \\___ \\ / _ \\ / __| '__/ _` | __/ _ \\/ __|\n" +
                "  ____) | (_) | (__| | | (_| | ||  __/\\__ \\\n" +
                "  |____/ \\___/ \\___|_|  \\__,_|\\__\\___||___/\n" +
                "Hello! I'm Socrates.\n" +
                "What can I do for you? \n";
        String lineBreak = "____________________________________________________________\n";
        String byeMessage = "\t Bye. Hope to see you again soon!\n";

        System.out.print(banner + lineBreak);
        lineBreak = "\t" + lineBreak;
        String[] input = formatInput();
        Task[] list = new Task[100];
        int listIdx = 0;
        while(!input[0].equals("bye")){
            // get input
            // strip input
            // check if input is not bye
            switch(input[0]) {
                case "list" -> {
                    System.out.print(lineBreak);
                    for(int i = 0 ; i < listIdx; i++){
                        System.out.printf("\t %s. %s\n", i+1, list[i].getTask());
                    }
                    System.out.print(lineBreak);
                }
                case "mark" -> {
                    int markIdx = Integer.parseInt(input[1]) - 1;
                    list[markIdx].setIsDone();
                    System.out.println(lineBreak + "\t Nice! I've marked this task as done:");
                    System.out.println("\t   " + list[markIdx].getTask());
                    System.out.print(lineBreak);
                }
                case "unmark" -> {
                    int unmarkIdx = Integer.parseInt(input[1]) - 1;
                    list[unmarkIdx].setNotDone();
                    System.out.println(lineBreak + "\t Okay, I've marked this task as not done yet:");
                    System.out.println("\t   " + list[unmarkIdx].getTask());
                    System.out.print(lineBreak);
                }
                // print input
                default -> {
                    Task temp = new Task(input[0]);
                    input[0] = "\t added: " + input[0] + "\n";
                    System.out.print(lineBreak + input[0] + lineBreak);
                    list[listIdx] = temp; listIdx++;
                }
            }
            input = formatInput();
        }
        System.out.print(lineBreak + byeMessage + lineBreak);
    }
}
