package isel.sisinf.grp3;

import isel.sisinf.grp3.ui.Commands;
import isel.sisinf.grp3.ui.IComands;
import isel.sisinf.grp3.ui.UserInterface;

import java.util.HashMap;


/**
 * Entry point of the SI phase II.
 */
public class App {
    public static void main(String[] args) {
        HashMap<String, IComands> cmds = new Commands().getCommands();
        UserInterface.printCommands();
        while (true) {
            String commands = UserInterface.readCommand();
            IComands cmd = cmds.get(commands);
            if (cmd == null) {
                System.out.println("Invalid Command! \nUse the Command HELP for available command list.");
            } else {
                try {
                    cmd.action();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
