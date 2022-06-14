package isel.sisinf.grp3.ui;

import java.util.Scanner;

/**
 * Class UserInterface treats all the ui input /output necessities
 */
public class UserInterface {

    /**
     * Prints all the available commands
     */
    public static void printCommands() {
        System.out.println("Commands: \n");
        System.out.println("1.Exit");
        System.out.println("2.Insert/Remove/Update private clients info"); //d
        System.out.println("3.Get total alarms for a vehicle in a year"); //e
        System.out.println("4.Process all the Unprocessed Registers");//f
        System.out.println("5.Trigger g)"); //g
        System.out.println("6.Create a vehicle");//h
        System.out.println("7.Create a view with all the alarms");//i
        System.out.println("8.Trigger j)");//j
        System.out.println("9.Delete Invalid Registers from 15 days ago");//k
        System.out.println("10.Trigger l)");//l
        System.out.println("11.Create a vehicle by hand");//h ha la pata
        System.out.println("12.Help menu");
    }

    /**
     * Prints all the private client options
     */
    public static void privateClientOptions() {
        System.out.println("Client Options:");
        System.out.println("Insert");
        System.out.println("Remove");
        System.out.println("Update");
        printPrompt();
    }

    /**
     * Reads and handles a command.
     */
    public static String readCommand() {
        while (true) {
            printPrompt();
            String command = parseCommand(new Scanner(System.in).nextLine());
            if (command != null)
                return command;

        }
    }

    /**
     * Reads and parses the command
     *
     * @param command
     * @return
     */
    public static String parseCommand(String command) {
        String cleanString = command.trim();
        if (cleanString.isEmpty() || cleanString.isBlank())
            return null;
        else return cleanString;
    }

    /**
     * Print a prompt char in the Standard Output
     */
    public static void printPrompt() {
        System.out.print(">");
    }
}