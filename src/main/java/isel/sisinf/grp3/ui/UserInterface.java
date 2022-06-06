package isel.sisinf.grp3.ui;

import java.util.Scanner;

public class UserInterface {
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
    }

    static void privateClientOptions() {
        System.out.println("Insert private client");
        System.out.println("Remove private client");
        System.out.println("Update private client");
    }

    public static String readOption() {
        return readCommand().toUpperCase();
    }

    public static String readCommand() {
        Scanner reader = new Scanner(System.in);
        return reader.nextLine().trim();
    }

    public static void printPrompt() {
        System.out.print(">");
    }
}