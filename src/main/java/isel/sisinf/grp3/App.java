package isel.sisinf.grp3;

import isel.sisinf.grp3.ui.Commands;
import isel.sisinf.grp3.ui.UserInterface;

import java.util.Map;

/**
 * Entry point of the SI phase II.
 */
public class App {
    public static void main(String[] args) throws Exception {

        /* TRY MONGO CONNECTION*/
        /*UserInterface.printCommands();
        UserInterface.printPrompt();*/
        /*while (true) {
            String command = UserInterface.readCommand();*/

        //(123456780, 'joca', 'casa do joca', 123456780, 'P',null)
        //PrivateClient pv = ctx.getPrivateClients().findByKey("123456780");
        //System.out.println(pv);
        //System.out.println(ctx.numberOfAlarmsWLicensePlate(2022,"12345A" ));

        //System.out.println(ctx.numberOfAlarms(2022,"12345A"));

        //System.out.println(ctx.getPrivateClients().findByKey("010101010"));

        Map<String, Object> dispatcher = new Commands().getCommands();
        UserInterface.printCommands();
        while (true) {
            UserInterface.printPrompt();
            String commands = UserInterface.readCommand();
            System.out.println(commands);
            Commands event = (Commands) dispatcher.get(commands);//GG aqui
            System.out.println(event);
            if (event == null) {
                System.out.println("Invalid command");
                continue;
            }
            event.getCommands().get(commands);
        }

    }

}


