package isel.sisinf.grp3.ui;

import isel.sisinf.grp3.logic.repos.JPAContext;
import isel.sisinf.grp3.model.client.Client;
import isel.sisinf.grp3.model.client.PrivateClient;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class Commands {

    public Map<String, Object> getCommands() {
       return Map.ofEntries(
                Map.entry("1", new Exit()),
                Map.entry("2", new PrivateClientOptions()), //d
                Map.entry("3", new Alarms()), //e
                Map.entry("4", new ProcessRegisters()),//f
                Map.entry("5", new TriggerG()),//g
                Map.entry("6", new CreateVehicle()),//h
                Map.entry("7", new CreateAlarmsView()),//i
                Map.entry("8", new TriggerJ()),//j
                Map.entry("9", new DeleteOldInvalidRegisters()),//k
                Map.entry("10", new TriggerL())//l
        );
    }

    public static class Exit implements IComandsOO {

        @Override
        public void action() {
            System.exit(1);
        }

        @Override
        public void show() {
            System.out.println("Exiting...");
        }

        @Override
        public String getSyntax() {
            return null;
        }
    }

    public static class PrivateClientOptions implements IComandsOO {

        @Override
        public void action() {
            try (JPAContext ctx = new JPAContext()) {
                UserInterface.privateClientOptions();

                String option = new Scanner(System.in).toString().toUpperCase();
                switch (option) {
                    case "INSERT": {
                        System.out.println("Please type a NIF");
                        String nif = new Scanner(System.in).toString();
                        System.out.println("Please type a name");
                        String name = new Scanner(System.in).toString();
                        System.out.println("Please type a address");
                        String address = new Scanner(System.in).toString();
                        System.out.println("Please type a ph;one");
                        String phone = new Scanner(System.in).toString();
                        System.out.println("Please type a reference");
                        String reference = new Scanner(System.in).toString();
                        System.out.println("Please type a CC");
                        String CC = new Scanner(System.in).toString();
                        Client client = new Client(nif, name, address, phone, reference);
                        PrivateClient privateClient = new PrivateClient(client.getNif(), CC);
                        ctx.insertPrivateClient(privateClient);
                    }
                    case "REMOVE": {
                        System.out.println("Please type a NIF");
                        String nif = new Scanner(System.in).toString();
                        ctx.removePrivateClient(nif);
                    }
                    case "UPDATE": {
                        System.out.println("Please type a NIF");
                        String nif = new Scanner(System.in).toString();
                        System.out.println("Please type a name");
                        String newName = new Scanner(System.in).toString();
                        System.out.println("Please type a address");
                        String newAddress = new Scanner(System.in).toString();
                        System.out.println("Please type a phone");
                        String newPhone = new Scanner(System.in).toString();
                        System.out.println("Please type a reference");
                        String aux = new Scanner(System.in).toString().toUpperCase();
                        Boolean newStatus = false;
                        if ("TRUE".equals(aux)) {
                            newStatus = Boolean.TRUE;
                        }
                        ctx.updatePrivateClient(nif, newName, newAddress, newPhone, newStatus);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        @Override
        public void show() {
            System.out.println("2.Insert/Remove/Update private clients");

        }

        @Override
        public String getSyntax() {
            return null;
        }
    }

    public static class Alarms implements IComandsOO {

        @Override
        public void action() { //nao verificamos inputs estranhos
            try (JPAContext ctx = new JPAContext()) {
                System.out.println("Type year");
                String year = new Scanner(System.in).toString().trim();
                System.out.println("Type License Plate");
                String license = new Scanner(System.in).toString().trim();
                ctx.numberOfAlarms(Integer.parseInt(year), license);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void show() {
            System.out.println("3.Get total alarms for a vehicle in a year");
        }

        @Override
        public String getSyntax() {
            return null;
        }
    }

    public static class ProcessRegisters implements IComandsOO {

        @Override
        public void action() {
            try (JPAContext ctx = new JPAContext()) {
                ctx.processRegisters();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void show() {
            System.out.println("4.Process all the Unprocessed Registers");
        }

        @Override
        public String getSyntax() {
            return null;
        }
    }

    public static class TriggerG implements IComandsOO {

        @Override
        public void action() {

        }

        @Override
        public void show() {
            System.out.println("5.Trigger g)");
        }

        @Override
        public String getSyntax() {
            return null;
        }
    }

    public static class CreateVehicle implements IComandsOO {

        @Override
        public void action() {
            try (JPAContext ctx = new JPAContext()){
                System.out.println("Type License Plate");
                String license = new Scanner(System.in).toString().trim();
                System.out.println("Type Driver's Name");
                String DriverName = new Scanner(System.in).toString().trim();
                System.out.println("Type Driver's phone number");
                String DriverPhone = new Scanner(System.in).toString().trim();
                System.out.println("Type Client's Nif");
                String NIF = new Scanner(System.in).toString().trim();
                System.out.println("Type Radius");
                String radius = new Scanner(System.in).toString().trim();
                System.out.println("Type Latitude");
                String latitude = new Scanner(System.in).toString().trim();
                System.out.println("Type Longitude");
                String longitude = new Scanner(System.in).toString().trim();
                ctx.addVehicleToClient(license, DriverName, DriverPhone, NIF, Integer.parseInt(radius), BigDecimal.valueOf(Integer.parseInt(latitude)), BigDecimal.valueOf(Integer.parseInt(longitude)));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void show() {
            System.out.println("6.Create a vehicle");
        }

        @Override
        public String getSyntax() {
            return null;
        }
    }

    public static class CreateAlarmsView implements IComandsOO {

        @Override
        public void action() {

        }

        @Override
        public void show() {
            System.out.println("7.Create a view with all the alarms");
        }

        @Override
        public String getSyntax() {
            return null;
        }
    }

    public static class TriggerJ implements IComandsOO {

        @Override
        public void action() {

        }

        @Override
        public void show() {
            System.out.println("8.Trigger j)");
        }

        @Override
        public String getSyntax() {
            return null;
        }
    }

    public static class DeleteOldInvalidRegisters implements IComandsOO {

        @Override
        public void action() {

        }

        @Override
        public void show() {
            System.out.println("9.Delete Invalid Registers from 15 days ago");
        }

        @Override
        public String getSyntax() {
            return null;
        }
    }

    public static class TriggerL implements IComandsOO {

        @Override
        public void action() {

        }

        @Override
        public void show() {
            System.out.println("10.Trigger l)");
        }

        @Override
        public String getSyntax() {
            return null;
        }
    }
}
