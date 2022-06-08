package isel.sisinf.grp3;

import isel.sisinf.grp3.logic.repos.JPAContext;
import isel.sisinf.grp3.ui.UserInterface;

import java.util.Scanner;


/**
 * Entry point of the SI phase II.
 */
public class App {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        try(JPAContext ctx = new JPAContext()) {

            while(true) {
                UserInterface.printCommands();
                String command = UserInterface.readCommand();


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

