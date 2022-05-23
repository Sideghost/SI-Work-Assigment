/*
MIT License

Copyright (c) 2022, Nuno Datia, ISEL

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package isel.sisinf.ap6;

/**
 * Material didático para apoio
 * à unidade curricular de
 * Sistemas de Informação
 * <p>
 * Os exemplos podem não ser completos e/ou totalmente correctos.
 * São disponibilizados com objectivos pedagógicos e as
 * eventuais incorrecções são alvo de discussão
 * nas aulas.
 * <p>
 * Exemplo de uma applicação que usa JPA
 * Application-managed Entity Manager, com Local Transaction
 * <p>
 * Atenção! não há uma correcta separação entre compoenntes das diferentes camadas
 */

import isel.sisinf.ap6.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.io.Console;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

interface DbWorker {
    void doWork();
}

class App {
    private enum Option {
        Unknown,
        Exit,
        ListStudent,
        ListCourse,
        RegisterStudent,
        EnrolStudent
    }

    private static App __instance = null;
    private String __connectionString;
    private HashMap<Option, DbWorker> __dbMethods;


    /** Devia estar noutra classe, dado que pertende à DAL. Apenas para motivo pedagógicos. */
    private final String __jpaPU = "AP6";
    private EntityManagerFactory __emf = null;
    private Credentials __cred = null;


    private App() {
        __dbMethods = new HashMap<Option, DbWorker>();
        __dbMethods.put(Option.ListStudent, new DbWorker() {
            public void doWork() {
                App.this.ListStudent();
            }
        });
        __dbMethods.put(Option.ListCourse, new DbWorker() {
            public void doWork() {
                App.this.ListCourse();
            }
        });
        __dbMethods.put(Option.RegisterStudent, new DbWorker() {
            public void doWork() {
                App.this.RegisterStudent();
            }
        });
        __dbMethods.put(Option.EnrolStudent, new DbWorker() {
            public void doWork() {
                App.this.EnrolStudent();
            }
        });

    }

    public static App getInstance() {
        if (__instance == null) {
            __instance = new App();
        }
        return __instance;
    }

    public class Credentials {
        private String _userName;
        private String _pass;

        public Credentials(String user, String pass) {
            _userName = user;
            _pass = pass;
        }

        public void Clean() {
            _userName = "";
            _pass = "";

        }

    }

    public void setCredentials(Credentials cred) {
        __cred = cred;
    }

    private Option DisplayMenu() {
        Option option = Option.Unknown;
        try {
            System.out.println("Course management");
            System.out.println();
            System.out.println("1. Exit");
            System.out.println("2. List students");
            System.out.println("3. List courses");
            System.out.println("4. Register student");
            System.out.println("5. Enrol student");
            System.out.print(">");
            Scanner s = new Scanner(System.in);
            int result = s.nextInt();
            option = Option.values()[result];
        } catch (RuntimeException ex) {
            //nothing to do.
        }

        return option;

    }

    private final static void clearConsole() throws Exception {
        for (int y = 0; y < 25; y++) //console is 80 columns and 25 lines
            System.out.println("\n");

    }

    private void Login() {


        Map properties = new HashMap();

        // Configure the internal EclipseLink connection pool
        //properties.put(EntityManagerProperties.JDBC_USER, "postgres");
        //properties.put(EntityManagerProperties.JDBC_PASSWORD, "psqlsupwd12");

        //properties.put(EntityManagerProperties.JDBC_USER, __cred._userName);
        //properties.put(EntityManagerProperties.JDBC_PASSWORD, __cred._pass);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AP6", properties);
        emf.createEntityManager();

        //If no exception is raised, everything is OK.
        __emf = emf;
        __cred.Clean();


    }

    public void Run() throws Exception {
        Login();
        Option userInput = Option.Unknown;
        do {
            clearConsole();
            userInput = DisplayMenu();
            clearConsole();
            try {
                __dbMethods.get(userInput).doWork();
                System.in.read();

            } catch (NullPointerException ex) {
                //Nothing to do. The option was not a valid one. Read another.
            }

        } while (userInput != Option.Exit);
    }

    /**
     *	To implement from this point forward. Do not need to change the code above.
     *
     */

    private <T> void printResults(List<T> results) {

        for (T obj : results)
            System.out.println(obj);
    }

    private void ListStudent() {
        //TODO: IMPLEMENT
        System.out.println("ListStudent()");
    }

    private void ListCourse() {
        //TODO: Implement
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("select c from Course u");
        };
        System.out.println("ListCourse()");
    }

    private void RegisterStudent() {
        //TODO: Implement
        System.out.println("RegisterStudent()");
    }

    private void EnrolStudent() {
        //TODO: Implement
        System.out.println("EnrolStudent()");
    }

}

public class Ap6 {


    public static isel.sisinf.ap6.App.Credentials getCredentials() throws IOException {

        StringBuffer username = new StringBuffer();
        //for security reasons, passwords should be char[]. However, some classes expect String as parameters.
        StringBuffer password = new StringBuffer();


        System.out.println("Enter your username:");
        Scanner scan = new Scanner(System.in);
        username.append(scan.nextLine());


        System.out.println("Enter your password:");
        Console c = System.console();

        if (c == null) //probably on  IDE
            password.append(scan.nextLine());
        else
            password.append(c.readPassword());

        return App.getInstance().new Credentials(username.toString(), password.toString());
    }


    public static void main(String[] args) throws SQLException, Exception {


        isel.sisinf.ap6.App.Credentials credentials = getCredentials();
        App.getInstance().setCredentials(credentials);
        App.getInstance().Run();
    }
}