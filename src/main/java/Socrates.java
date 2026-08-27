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
                "What can I do for you? \n";
        String lineBreak = "____________________________________________________________\n";
        String byeMessage = "\t Bye. Hope to see you again soon!\n";

        System.out.print(banner + lineBreak);
        lineBreak = "\t" + lineBreak;
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().strip();
        String[] list = new String[100];
        int listIdx = 0;
        while(!input.equals("bye")){
            // get input
            // strip input
            // check if input is not bye
            switch(input) {
                case "list" -> {
                    System.out.print(lineBreak);
                    for(int i = 0 ; i < listIdx; i++){
                        System.out.printf("\t %d. %s", i+1, list[i]);
                    }
                    System.out.print(lineBreak);
                }
                // print input
                default -> {
                    input = "added: " + input + "\n";
                    System.out.print(lineBreak + input + lineBreak);
                    list[listIdx] = input; listIdx++;
                }
            }
            input = scanner.nextLine().strip();
        }
        System.out.print(lineBreak + byeMessage + lineBreak);
    }
}
