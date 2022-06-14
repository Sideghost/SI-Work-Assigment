package isel.sisinf.grp3.ui;

import isel.sisinf.grp3.logic.repos.JPAContext;
import isel.sisinf.grp3.logic.restrictions.Restrictions;
import isel.sisinf.grp3.model.AllAlarm;
import isel.sisinf.grp3.model.GreenZone;
import isel.sisinf.grp3.model.Vehicle;
import isel.sisinf.grp3.model.client.Client;
import isel.sisinf.grp3.model.client.PrivateClient;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Commands class parent class that contains all the subclasses corresponding to each operation's  action, show and getSyntax
 */
public class Commands {
    /**
     * @return HashMap<String, IComands></String,IComands>
     */
    public HashMap<String, IComands> getCommands() {
        HashMap<String, IComands> commandsMap = new HashMap<>();
        commandsMap.put("1", new IComands() {
            /**
             * Overriding action method, operation logic
             */
            @Override
            public void action() {
                System.exit(1);
            }

            /**
             * Outputing on the console
             */
            @Override
            public void show() {
                System.out.println("Exiting...");
            }

            /**
             * Correspoding string to the operation itself
             * @return String
             */
            @Override
            public String getSyntax() {
                return "1";
            }
        });
        commandsMap.put("2", new IComands() {
            /**
             * Overriding action method, operation logic
             */
            @Override
            public void action() {
                try (JPAContext ctx = new JPAContext()) {
                    UserInterface.privateClientOptions();

                    String option = new Scanner(System.in).nextLine().toUpperCase();
                    switch (option) {
                        case "INSERT": {
                            System.out.println("Please type a NIF");
                            String nif = new Scanner(System.in).nextLine();
                            System.out.println("Please type a name");
                            String name = new Scanner(System.in).nextLine();
                            System.out.println("Please type a address");
                            String address = new Scanner(System.in).nextLine();
                            System.out.println("Please type a phone");
                            String phone = new Scanner(System.in).nextLine();
                            System.out.println("Please type a reference");
                            String reference = new Scanner(System.in).nextLine();
                            System.out.println("Please type a CC");
                            String CC = new Scanner(System.in).nextLine();
                            Client client = new Client(nif, name, address, phone, ctx.getPrivateClients().findByKey(reference));
                            if (new Restrictions.ClientRestrictions().checkRestrictions(client)) {
                                //ctx.em.merge(client);
                                PrivateClient privateClient = new PrivateClient(client.getNif(), CC);
                                if (new Restrictions.PrivateClientRestrictions().checkRestrictions(privateClient)) {
                                    //ctx.em.merge(privateClient);
                                    ctx.insertPrivateClient(privateClient, reference);
                                }
                            }
                        }
                        case "REMOVE": {
                            System.out.println("Please type a NIF");
                            String nif = new Scanner(System.in).nextLine();
                            ctx.removePrivateClient(nif);
                        }
                        case "UPDATE": {

                            System.out.println("Please type a NIF");
                            String nif = new Scanner(System.in).nextLine();
                            System.out.println("What data do you want to change?");
                            String parameter = new Scanner(System.in).nextLine();
                            String newName = "", newAddress = "", newPhone = "";
                            boolean newStatus = false;
                            switch (parameter.toUpperCase()) {
                                case "NAME": {
                                    newName = new Scanner(System.in).nextLine();
                                }
                                case "ADDRESS": {
                                    newAddress = new Scanner(System.in).nextLine();
                                }
                                case "PHONE": {
                                    newPhone = new Scanner(System.in).nextLine();
                                }
                                case "STATUS": {
                                    String aux = new Scanner(System.in).nextLine().toUpperCase();
                                    newStatus = "TRUE".equals(aux);
                                }
                            }
                            ctx.updatePrivateClient(nif, newName, newAddress, newPhone, newStatus);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /**
             * Outputing on the console
             */
            @Override
            public void show() {
                System.out.println("2.Insert/Remove/Update private clients");
            }

            /**
             * Correspoding string to the operation itself
             * @return String
             */
            @Override
            public String getSyntax() {
                return "2";
            }
        });
        commandsMap.put("3", new IComands() {
            /**
             * Overriding action method, operation logic
             */
            @Override
            public void action() { //nao verificamos inputs estranhos
                try (JPAContext ctx = new JPAContext()) {
                    System.out.println("Type year");
                    String year = new Scanner(System.in).nextLine().trim();
                    System.out.println("Want to type a specific license plate?");
                    System.out.println("y/n Y/N");
                    if (new Scanner(System.in).nextLine().equalsIgnoreCase("Y")) {
                        System.out.println("Type License Plate");
                        String license = new Scanner(System.in).nextLine().trim();
                        System.out.println("Number of alarms of " + license + "-> " + ctx.numberOfAlarms(Integer.parseInt(year), license));
                    }
                    System.out.println(ctx.numberOfAlarms(Integer.parseInt(year)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /**
             * Outputing on the console
             */
            @Override
            public void show() {
                System.out.println("3.Get total alarms for a vehicle in a year");
            }

            /**
             * Correspoding string to the operation itself
             * @return String
             */
            @Override
            public String getSyntax() {
                return "3";
            }

        });
        commandsMap.put("4", new IComands() {
            /**
             * Overriding action method, operation logic
             */
            @Override
            public void action() {
                try (JPAContext ctx = new JPAContext()) {
                    ctx.processRegisters();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /**
             * Outputing on the console
             */
            @Override
            public void show() {
                System.out.println("4.Process all the Unprocessed Registers");
            }

            /**
             * Correspoding string to the operation itself
             * @return String
             */
            @Override
            public String getSyntax() {
                return "4";
            }
        });
        commandsMap.put("5", new IComands() {
            /**
             * Overriding action method, operation logic
             */
            @Override
            public void action() {
                System.out.println("Not implemented");
            }

            /**
             * Outputing on the console
             */
            @Override
            public void show() {
                System.out.println("5.Trigger g");
            }

            /**
             * Correspoding string to the operation itself
             * @return String
             */
            @Override
            public String getSyntax() {
                return "5";
            }
        });
        commandsMap.put("6", new IComands() {
            /**
             * Overriding action method, operation logic
             */
            @Override
            public void action() {
                try (JPAContext ctx = new JPAContext()) {
                    String license, driverName, driverPhone, NIF;
                    String radius, latitude, longitude;

                    System.out.println("Type License Plate");
                    license = new Scanner(System.in).nextLine().trim();
                    System.out.println("Type Driver's Name");
                    driverName = new Scanner(System.in).nextLine().trim();
                    System.out.println("Type Driver's phone number");
                    driverPhone = new Scanner(System.in).nextLine().trim();
                    System.out.println("Type Client's Nif");
                    NIF = new Scanner(System.in).nextLine().trim();

                    Vehicle vehicle = new Vehicle(license, driverName, driverPhone, NIF, 0);
                    if (new Restrictions.VehicleRestrictions().checkRestrictions(vehicle)) {
                        System.out.println("Do you want to create a GreenZone?");
                        System.out.println("y/n Y/N");

                        String answer = new Scanner(System.in).nextLine();
                        if (answer.equalsIgnoreCase("Y")) {
                            System.out.println("Type Radius");
                            radius = new Scanner(System.in).nextLine().trim();
                            System.out.println("Type Latitude");
                            latitude = new Scanner(System.in).nextLine().trim();
                            System.out.println("Type Longitude");
                            longitude = new Scanner(System.in).nextLine().trim();

                            GreenZone greenZone = new GreenZone(Integer.parseInt(radius), BigDecimal.valueOf(Integer.parseInt(latitude)), BigDecimal.valueOf(Integer.parseInt(longitude)), license);
                            if (new Restrictions.GreenZoneRestrictions().checkRestrictions(greenZone)) {
                                ctx.addVehicleToClient(vehicle, greenZone);
                                return;
                            }
                        }
                        ctx.addVehicleToClient(vehicle, new GreenZone(null, null, null, null));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /**
             * Outputing on the console
             */
            @Override
            public void show() {
                System.out.println("6.Create a vehicle");
            }

            /**
             * Correspoding string to the operation itself
             * @return String
             */
            @Override
            public String getSyntax() {
                return "6";
            }
        });
        commandsMap.put("7", new IComands() {
            /**
             * Overriding action method, operation logic
             */
            @Override
            public void action() {
                try (JPAContext ctx = new JPAContext()) {
                    Collection<AllAlarm> allAlarms = ctx.allAlarms();
                    while (!allAlarms.isEmpty()) {
                        System.out.println(allAlarms.iterator().next());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /**
             * Outputing on the console
             */
            @Override
            public void show() {
                System.out.println("7.Create a view with all the alarms");
            }

            /**
             * Correspoding string to the operation itself
             * @return String
             */
            @Override
            public String getSyntax() {
                return "7";
            }
        });
        commandsMap.put("8", new IComands() {
            /**
             * Overriding action method, operation logic
             */
            @Override
            public void action() {
                System.out.println("Not Implemented");
            }

            /**
             * Outputing on the console
             */
            @Override
            public void show() {
                System.out.println("8.Trigger j)");
            }

            /**
             * Correspoding string to the operation itself
             * @return String
             */
            @Override
            public String getSyntax() {
                return "8";
            }
        });
        commandsMap.put("9", new IComands() {
            /**
             * Overriding action method, operation logic
             */
            @Override
            public void action() {
                try (JPAContext ctx = new JPAContext()) {
                    ctx.eliminateInvalidRegisters();
                    System.out.println("Old registers were deleted");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /**
             * Outputing on the console
             */
            @Override
            public void show() {
                System.out.println("9.Delete Invalid Registers from 15 days ago");
            }

            /**
             * Correspoding string to the operation itself
             * @return String
             */
            @Override
            public String getSyntax() {
                return "9";
            }
        });
        commandsMap.put("10", new IComands() {
            /**
             * Overriding action method, operation logic
             */
            @Override
            public void action() {
                try (JPAContext ctx = new JPAContext()) {
                    ctx.deleteClient();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /**
             * Outputing on the console
             */
            @Override
            public void show() {
                System.out.println("10.Trigger l)");
            }

            /**
             * Correspoding string to the operation itself
             * @return String
             */
            @Override
            public String getSyntax() {
                return "10";
            }
        });
        commandsMap.put("HELP", new IComands() {
            /**
             * Overriding action method, operation logic
             */
            @Override
            public void action() {
                show();
            }

            /**
             * Outputing on the console
             */
            @Override
            public void show() {
                UserInterface.printCommands();
            }

            /**
             * Correspoding string to the operation itself
             * @return String
             */
            @Override
            public String getSyntax() {
                return "help";
            }
        });
        commandsMap.put("11", new IComands() {
            /**
             * Overriding action method, operation logic
             */
            @Override
            public void action() {
                try (JPAContext ctx = new JPAContext()) {
                    String license, driverName, driverPhone, NIF;
                    String radius, latitude, longitude;

                    System.out.println("Type License Plate");
                    license = new Scanner(System.in).nextLine().trim();
                    System.out.println("Type Driver's Name");
                    driverName = new Scanner(System.in).nextLine().trim();
                    System.out.println("Type Driver's phone number");
                    driverPhone = new Scanner(System.in).nextLine().trim();
                    System.out.println("Type Client's Nif");
                    NIF = new Scanner(System.in).nextLine().trim();

                    Vehicle vehicle = new Vehicle(license, driverName, driverPhone, NIF, 0);
                    if (new Restrictions.VehicleRestrictions().checkRestrictions(vehicle)) {
                        System.out.println("Do you want to create a GreenZone?");
                        System.out.println("y/n Y/N");

                        String answer = new Scanner(System.in).nextLine();
                        if (answer.equalsIgnoreCase("Y")) {
                            System.out.println("Type Radius");
                            radius = new Scanner(System.in).nextLine().trim();
                            System.out.println("Type Latitude");
                            latitude = new Scanner(System.in).nextLine().trim();
                            System.out.println("Type Longitude");
                            longitude = new Scanner(System.in).nextLine().trim();

                            GreenZone greenZone = new GreenZone(Integer.parseInt(radius), BigDecimal.valueOf(Integer.parseInt(latitude)), BigDecimal.valueOf(Integer.parseInt(longitude)), license);
                            if (new Restrictions.GreenZoneRestrictions().checkRestrictions(greenZone)) {
                                ctx.addVehicleToClientOrNot(license, NIF, Integer.parseInt(radius), BigDecimal.valueOf(Integer.parseInt(latitude)), BigDecimal.valueOf(Integer.parseInt(longitude)), vehicle);
                                return;
                            }
                        }
                        ctx.addVehicleToClientOrNot(license, NIF, null, null, null, vehicle);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /**
             * Outputing on the console
             */
            @Override
            public void show() {
                System.out.println("11.Create a vehicle by hand");//h ha la pata
            }

            /**
             * Correspoding string to the operation itself
             * @return String
             */
            @Override
            public String getSyntax() {
                return "11";
            }
        });
        return commandsMap;
    }
}