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
        String byeMessage = "\tBye. Hope to see you again soon!\n";
        System.out.println(banner + lineBreak);
        lineBreak = "\t" + lineBreak;
        Scanner scanner = new Scanner(System.in);
        while(true){
            // get input
            // strip input
            String input = scanner.nextLine().strip();
            // check if input is not bye
            if (input.equals("bye")){
                System.out.print(lineBreak + byeMessage + lineBreak);
                break;
            }
            // print input
            else{
                input = "\t" + input + "\n";
                System.out.print(lineBreak + input + lineBreak);
            }
        }
    }
}
