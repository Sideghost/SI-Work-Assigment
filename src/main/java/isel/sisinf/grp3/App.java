package isel.sisinf.grp3;

import isel.sisinf.grp3.logic.repos.JPAContext;
import isel.sisinf.grp3.ui.UserInterface;


/**
 * Entry point of the SI phase II.
 */
public class App {
    public static void main(String[] args) throws Exception {

        try (JPAContext ctx = new JPAContext()) /* TRY MONGO CONNECTION*/ {
            //(123456780, 'joca', 'casa do joca', 123456780, 'P',null)
            //PrivateClient pv = ctx.getPrivateClients().findByKey("123456780");
            //System.out.println(pv);
            System.out.println(ctx.numberOfAlarmsWLicensePlate(2022,"12345A" ));

            //System.out.println(ctx.getPrivateClients().findByKey("010101010"));
//            Map<String, Command> dispatcher = Handler.buildCommandDispatcher();
//            UserInterface.printCommands();
//            while(true){
//                UserInterface.printPrompt();
//                String command = UserInterface.readCommand();
//                Command event = dispatcher.get(command);
//                if(event == null){
//                    System.out.println("Invalid command");
//                    continue;
//                }
//                event.execute();
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
