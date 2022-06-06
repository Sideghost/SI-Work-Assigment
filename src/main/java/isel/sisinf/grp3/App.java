package isel.sisinf.grp3;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion;
import isel.sisinf.grp3.logic.Restrictions;
//import isel.sisinf.grp3.ui.Command;
import isel.sisinf.grp3.ui.Handler;
import isel.sisinf.grp3.ui.UserInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Map;
import java.util.Scanner;


/**
 * Hello world!
 */
public class App {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        try{
            UserInterface.printCommands();
            String command = new Scanner(System.in).toString().trim();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

