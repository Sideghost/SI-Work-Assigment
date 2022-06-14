//package isel.sisinf.grp3.ui;
//
////package isel.sisinf.grp3.ui;
////
////import java.sql.*;
////import java.util.Scanner;
////import java.util.regex.Matcher;
////import java.util.regex.Pattern;
////
/////**
//// * todo
//// */
//
//import org.eclipse.persistence.internal.jaxb.many.MapEntry;
//
//import java.sql.SQLException;
//import java.util.Map;
//
//public interface Command {
//    void execute() throws SQLException;
//}
//
//class Commands {
//
//
//
//
//}
////
////    //Constants
////    private static final int assetNameIdx = 1;
////    private static final int assetValueIdx = 1;
////
////
////    /**
////     * Executes the Insert, Update or Create query.
////     *
////     * @param con   database connection
////     * @param stmt  statement to be used
////     * @param query query to be executed
////     */
////
////    private static void commitUpdate(Connection con, Statement stmt, String query) {
////        con.setAutoCommit(false);
////        stmt.executeUpdate(query);
////        con.commit();
////        con.setAutoCommit(true);
////    }
////
////    /**
////     * Executes the Insert, Update or Create query in the prepared statement.
////     *
////     * @param con  database connection
////     * @param stmt statement to be used
////     */
////
////    private static void commitUpdate(Connection con, PreparedStatement stmt) {
////        con.setAutoCommit(false);
////        stmt.executeUpdate();
////        con.commit();
////        con.setAutoCommit(true);
////    }
////
////
////    static class exitCommand implements ICommand {
////
////        /**
////         * Exits the application.
////         */
////
////        @Override
////        public void execute() {
////            System.out.println("Exiting...");
////            System.exit(1);
////        }
////    }
////
////
////    static class updatePrivateClient implements ICommand { // TODO: 06/06/2022
////
////        private static final int idIdx = 1;
////        private static final int nameIdx = 2;
////        private static final int stateIdx = 3;
////        private static final int acquisitionDateIdx = 4;
////        private static final int modelIdx = 5;
////        private static final int brandIdx = 6;
////        private static final int locationIdx = 7;
////        private static final int topLevelIdIdx = 8;
////        private static final int typeIdIdx = 9;
////        private static final int companyIdIdx = 10;
////        private static final int managerIdIdx = 11;
////
////
////        @Override
////        public void execute() {
////            UserInterface.privateClientOptions();
////            String selectedOption = UserInterface.readCommand();
////            switch (selectedOption) {
////                case "UPDATE": {
////                    System.out.println("Write the client nif you want to update");
////                    String nif = new Scanner(System.in).toString();
////
////                }
////                case "REMOVE": {
////
////                }
////
////                case "INSERT": {
////
////                }
////                default: {
////                    System.out.println("Try again");
////                    execute();
////                }
////            }
////
////            String insertAsset = "insert into ATIVO values (?,?,?::bit,?,?,?,?,?,?,?,?)";
////
////            Connection con = Connect.getConnected();
////            PreparedStatement stmt = con.prepareStatement(insertAsset);
////            Pattern idRegex = Pattern.compile("[a-z][0-9]{4}", Pattern.CASE_INSENSITIVE);
////
////            Scanner reader = new Scanner(System.in);
////
////            System.out.print("Enter an ID:");
////            String id = reader.nextLine().trim();
////            Matcher matcher = idRegex.matcher(id);
////            if (!matcher.matches()) {
////                System.out.println("Please insert a valid asset id.\nAborting...");
////                return;
////            }
////
////            System.out.print("Enter a name:");
////            String name = reader.nextLine().trim();
////            if (name.equals("")) {
////                System.out.println("Please insert a valid name.\nAborting...");
////                return;
////            }
////
////            System.out.print("Enter the current operational state (0 if not active else 1):");
////            String state = reader.nextLine().trim();
////            if (!state.equals("0") && !state.equals("1")) {
////                System.out.println("Please insert a valid state.\nAborting...");
////                return;
////            }
////
////            System.out.print("Enter the asset model (you can not specify this):");
////            String model = reader.nextLine().trim();
////            if (model.isEmpty()) model = null;
////
////            System.out.print("Enter the asset brand (you can not specify this):");
////            String brand = reader.nextLine().trim();
////            if (brand.isEmpty()) brand = null;
////
////            System.out.print("Enter a location:");
////            String location = reader.nextLine().trim();
////            if (location.isEmpty()) {
////                System.out.println("Please insert a valid location.\nAborting...");
////                return;
////            }
////
////            System.out.print("Enter the id of the top-level asset:");
////            String topLevelId = reader.nextLine().trim();
////            matcher = idRegex.matcher(topLevelId);
////            Statement checkExistence = con.createStatement();
////            ResultSet result = checkExistence.executeQuery("select id from ATIVO where id = '" + id + "'");
////            if (!matcher.matches() || (!result.next() && !topLevelId.equals(id))) {
////                System.out.println("Please insert a valid assetId.\nAborting...");
////                return;
////            }
////
////            System.out.print("Enter the type id of the asset:");
////            int typeId = Integer.parseInt(reader.nextLine().trim());
////
////            System.out.print("Enter the id of the company responsible:");
////            int companyId = Integer.parseInt(reader.nextLine().trim());
////
////            System.out.print("Enter the manager id:");
////            int managerId = Integer.parseInt(reader.nextLine().trim());
////
////            java.util.Date date = new java.util.Date();
////            java.sql.Date acquisitionDate = new Date(date.getTime());
////
////            stmt.setString(idIdx, id);
////            stmt.setString(nameIdx, name);
////            stmt.setString(stateIdx, state);
////            stmt.setDate(acquisitionDateIdx, acquisitionDate);
////            stmt.setString(modelIdx, model);
////            stmt.setString(brandIdx, brand);
////            stmt.setString(locationIdx, location);
////            stmt.setString(topLevelIdIdx, topLevelId);
////            stmt.setInt(typeIdIdx, typeId);
////            stmt.setInt(companyIdIdx, companyId);
////            stmt.setInt(managerIdIdx, managerId);
////
////            commitUpdate(con, stmt);
////            System.out.println("Done!");
////        }
////    }
////
////
////    static class totalAlarms implements ICommand {
////
////        /**
////         * Replaces a person in a team with another one by specifying the substitute and removed person ids.
////         *
////         * @throws SQLException
////         */
////
////        @Override
////        public void execute() throws SQLException {
////
////            Connection con = Connect.getConnected();
////            Statement stmt = con.createStatement();
////            Scanner reader = new Scanner(System.in);
////
////            System.out.print("Enter the year:");
////            String year = UserInterface.readCommand();
////            System.out.print("Enter the licence plate:");
////            String licencePlate = UserInterface.readCommand();
////            if (licencePlate.isEmpty()) {
////                ResultSet result = stmt.executeQuery("count(matricula) numero_alarmes from Alarmes join GPS on id_gps = GPS.id");
////            } else
////                ResultSet result = stmt.executeQuery("count(matricula) numero_alarmes from Alarmes join GPS on id_gps = GPS.id where (matricula = matricula_ and extract(year from GPS.marca_temporal) = ano)");
////            //String teamId = reader.nextLine().trim();
////            ResultSet result = stmt.executeQuery("select codigo from EQUIPA where codigo = " + teamId);
////            if (!result.next()) {
////                System.out.println("Please insert a valid team Id. Aborting...");
////                return;
////            }
////
////            System.out.print("Enter the person id you want to substitute:");
////            String replacedId = reader.nextLine().trim();
////            result = stmt.executeQuery("select id from PESSOA where id = " + replacedId + " and equipa = " + teamId);
////            if (!result.next()) {
////                System.out.println("Please insert a valid person Id. Aborting...");
////                return;
////            }
////
////
////            System.out.print("Enter the person id you want to add:");
////            String substituteId = reader.nextLine().trim();
////            result = stmt.executeQuery("select id from PESSOA where id = " + substituteId + " and equipa != " + teamId);
////            if (!result.next()) {
////                System.out.println("Please insert a valid person Id. Aborting...");
////                return;
////            }
////
////            String removeFromTeam = "update PESSOA set equipa = " + "NULL" + " " + "where id = " + replacedId;
////            String addToTeam = "update PESSOA set equipa = " + teamId + " " + "where id = " + substituteId;
////            commitUpdate(con, stmt, removeFromTeam);
////            commitUpdate(con, stmt, addToTeam);
////            System.out.println("Done!");
////        }
////    }
////
////
////    static class makeAssetOutOfService implements ICommand {
////
////        /**
////         * Puts an asset out of service by assigning 0 to state column.
////         *
////         * @throws SQLException
////         */
////
////        @Override
////        public void execute() throws SQLException {
////
////            Connection con = Connect.getConnected();
////            Statement stmt = con.createStatement();
////            Scanner reader = new Scanner(System.in);
////
////            System.out.print("Enter the asset Id that you want to put out of service:");
////            String assetId = reader.nextLine().trim();
////            ResultSet result = stmt.executeQuery("select id from ATIVO where id = '" + assetId + "'");
////            if (!result.next()) {
////                System.out.println("Please insert a valid asset Id. Aborting...");
////                return;
////            }
////
////            String makeOutOfService = "update ATIVO set estado = b'0' where id = '" + assetId + "'";
////            commitUpdate(con, stmt, makeOutOfService);
////            System.out.println("Done!");
////        }
////    }
////
////
////    static class getAssetTotalCost implements ICommand {
////
////        /**
////         * Gets the total cost of an asset specified by the user.
////         *
////         * @throws SQLException
////         */
////
////        @Override
////        public void execute() throws SQLException {
////
////            Connection con = Connect.getConnected();
////            Statement stmt = con.createStatement();
////            Scanner reader = new Scanner(System.in);
////
////            System.out.print("Enter the asset Id that you to want to get the total cost of:");
////            String assetId = reader.nextLine().trim();
////            ResultSet result = stmt.executeQuery("select id from ATIVO where id = '" + assetId + "'");
////            if (!result.next()) {
////                System.out.println("Please insert a valid asset Id. Aborting...");
////                return;
////            }
////
////            String getBuyCost = "select valor from ATIVO join VCOMERCIAL on id = activo where id = '" + assetId + "' and dtaquisicao = dtvcomercial";
////            ResultSet buyCostRes = stmt.executeQuery(getBuyCost);
////            int totalCost = 0;
////            if (buyCostRes.next()) totalCost = buyCostRes.getInt(assetValueIdx);
////
////            String getInterventionsCost = "select Sum(valcusto) as valcusto from ATIVO join INTERVENCAO on id = activo where id = '" + assetId + "'";
////            ResultSet interventionsCostRes = stmt.executeQuery(getInterventionsCost);
////            if (interventionsCostRes.next()) totalCost += interventionsCostRes.getInt(1);
////
////            System.out.println("Active Total value is " + totalCost);
////        }
////    }
////
////
////    static class getWorkersAndManagersOf implements ICommand {
////
////        /**
////         * Gets all the people working on or managing a certain asset.
////         *
////         * @throws SQLException
////         */
////
////        @Override
////        public void execute() throws SQLException {
////
////            Scanner reader = new Scanner(System.in);
////            System.out.print("Enter the id of the active which you want to get all people inspecting or managing:");
////            String assetId = reader.nextLine().trim();
////
////            Connection con = Connect.getConnected();
////            Statement stmt = con.createStatement(TYPE_SCROLL_INSENSITIVE, CONCUR_READ_ONLY);
////            ResultSet result = stmt.executeQuery("select id from ATIVO where id = '" + assetId + "'");
////            if (!result.next()) {
////                System.out.println("Please insert a valid asset Id. Aborting...");
////                return;
////            }
////
////            String getWorkersFrom =
////                    "SELECT *\n" +
////                            "FROM PESSOA PessoasEquipa\n" +
////                            "WHERE EXISTS (\n" +
////                            "\tSELECT codigo\n" +
////                            "\tFROM EQUIPA Equipa\n" +
////                            "\tWHERE EXISTS (\n" +
////                            "\t\tSELECT equipa\n" +
////                            "\t\tFROM INTER_EQUIPA interequipa\n" +
////                            "\t\tWHERE EXISTS (\n" +
////                            "\t\t\tSELECT noint\n" +
////                            "\t\t\tFROM INTERVENCAO Intervencao\n" +
////                            "\t\t\tWHERE EXISTS (\n" +
////                            "\t\t\t\tSELECT *\n" +
////                            "\t\t\t\tFROM ATIVO Ativo1\n" +
////                            "\t\t\t\tWHERE Ativo1.id = '" + assetId + "' AND Ativo1.id = Intervencao.activo \n" +
////                            "\t\t\t\t\t\t\t\t\tAND interequipa.integerervencao =  Intervencao.noint AND interequipa.equipa = Equipa.codigo \n" +
////                            "\t\t\t\t\t\t\t\t\tAND PessoasEquipa.equipa = Equipa.codigo \n" +
////                            "\t\t\t)))) UNION SELECT * \n" +
////                            "\t\t\t\t\t   FROM PESSOA Pessoa\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
////                            "\t\t\t\t\t   WHERE EXISTS (\n" +
////                            "\t\t\t\t\t\t\tSELECT *\n" +
////                            "\t\t\t\t\t\t\tFROM ATIVO Ativo2\n" +
////                            "\t\t\t\t\t\t\tWHERE Ativo2.id = '" + assetId + "' AND Pessoa.id = Ativo2.pessoa \n" +
////                            "\t\t\t\t\t\t);";
////
////
////            result = stmt.executeQuery(getWorkersFrom);
////            if (!result.next()) {
////                System.out.println("No data to be shown");
////                return;
////            }
////            result.beforeFirst();
////            System.out.println(UserInterface.getTable(result));
////        }
////    }
////
////
////    static class getAllActivesOf implements ICommand {
////
////        /**
////         * Gets all actives names managed or inspected by a certain person.
////         *
////         * @throws SQLException
////         */
////
////        @Override
////        public void execute() throws SQLException {
////
////            Scanner reader = new Scanner(System.in);
////            System.out.print("Enter the id of the person which you want to know about all managed or inspected assets:");
////            String personId = reader.nextLine().trim();
////            try {
////                Integer.parseInt(personId);
////            } catch (NumberFormatException e) {
////                System.out.println("Please insert a valid person Id. Aborting...");
////                return;
////            }
////
////            Connection con = Connect.getConnected();
////            Statement stmt = con.createStatement(TYPE_SCROLL_INSENSITIVE, CONCUR_READ_ONLY);
////            ResultSet result = stmt.executeQuery("select id from PESSOA where id = " + personId);
////            if (!result.next()) {
////                System.out.println("Please insert a valid person Id. Aborting...");
////                return;
////            }
////
////            String getAllActivesOf =
////                    "SELECT nome\n" +
////                            "FROM ATIVO Ativo\n" +
////                            "WHERE EXISTS (\n" +
////                            "\tSELECT activo\n" +
////                            "\tFROM INTERVENCAO Intervençao\n" +
////                            "\tWHERE EXISTS (\n" +
////                            "\t\tSELECT integerervencao\n" +
////                            "\t\tFROM INTER_EQUIPA interequipa\n" +
////                            "\t\tWHERE EXISTS (\n" +
////                            "\t\t\tSELECT equipa \n" +
////                            "\t\t\tFROM PESSOA Pessoa\n" +
////                            "\t\t\tWHERE id = " + personId + " AND interequipa.equipa = Pessoa.equipa AND interequipa.integerervencao = Intervençao.noint AND Intervençao.activo = Ativo.id \n" +
////                            "\t\t))) UNION SELECT nome\n" +
////                            "\t\t\t\t  FROM ATIVO Ativo\n" +
////                            "\t\t\t\t  WHERE EXISTS (\n" +
////                            "\t\t\t\t\tSELECT *\n" +
////                            "\t\t\t\t\tFROM PESSOA Pessoa\n" +
////                            "\t\t\t\t\tWHERE Pessoa.id = " + personId + " AND Ativo.pessoa = Pessoa.id\n" +
////                            "\t\t\t\t);";
////
////            result = stmt.executeQuery(getAllActivesOf);
////            if (!result.next()) {
////                System.out.println("No data to be shown");
////                return;
////            }
////            result.beforeFirst();
////            while (result.next()) {
////                System.out.println(result.getString(assetNameIdx));
////            }
////        }
////    }
////
////
////    static class getTeamManagersThatHadOrHaveAssets implements ICommand {
////
////        /**
////         * Gets the team's managers that had or have assets.
////         *
////         * @throws SQLException
////         */
////
////        @Override
////        public void execute() throws SQLException {
////
////            Connection con = Connect.getConnected();
////            Statement stmt = con.createStatement(TYPE_SCROLL_INSENSITIVE, CONCUR_READ_ONLY);
////            String getTeamManagersThatHadOrHaveAssets =
////                    "SELECT nome, profissao, telefone\n" +
////                            "FROM((\tSELECT id, nome, profissao\n" +
////                            "\t\tFROM PESSOA Pessoa\n" +
////                            "\t\tWHERE EXISTS (\n" +
////                            "\t\t\tSELECT * \n" +
////                            "\t\t\tFROM EQUIPA Equipa\n" +
////                            "\t\t\tWHERE Pessoa.id = Equipa.responsavel) \n" +
////                            "INTERSECT \n" +
////                            "\t\tSELECT id, nome, profissao\n" +
////                            "\t\tFROM PESSOA Pessoa\n" +
////                            "\t\tWHERE EXISTS (\n" +
////                            "\t\t\tSELECT * \n" +
////                            "\t\t\tFROM ATIVO Ativo\n" +
////                            "\t\t\tWHERE Pessoa.id = Ativo.pessoa)) AS ResultadoSemTelefone\n" +
////                            "JOIN TEL_PESSOA ON id = pessoa \n" +
////                            "\t);  ";
////
////            ResultSet result = stmt.executeQuery(getTeamManagersThatHadOrHaveAssets);
////            if (!result.next()) {
////                System.out.println("No data to be shown");
////                return;
////            }
////            result.beforeFirst();
////            //System.out.println(UserInterface.getTable(result));
////        }
////    }
////
////
////    static class everyInterventionUntil implements ICommand {
////
////        /**
////         * Gets the future interventions in a set time specified by the user ( in days ).
////         */
////
////        @Override
////        public void execute() {
////
////            Scanner reader = new Scanner(System.in);
////            Connection con = Connect.getConnected();
////            Statement stmt = con.createStatement(TYPE_SCROLL_INSENSITIVE, CONCUR_READ_ONLY);
////
////            System.out.print("Please enter the time interval in number of days:");
////            String numberOfDays = reader.nextLine().trim();
////            try {
////                Integer.parseInt(numberOfDays);
////            } catch (NumberFormatException e) {
////                System.out.println("Please insert a valid time interval. Aborting...");
////                return;
////            }
////
////            String everyInterventionUntil =
////                    "SELECT descricao, id, nome\n" +
////                            "FROM ((SELECT descricao, activo \n" +
////                            "\t   FROM INTERVENCAO\n" +
////                            "\t   WHERE (dtinicio - integer '" + numberOfDays + "' >= current_date)) AS AUX1 JOIN ATIVO ON activo = id)";
////
////            ResultSet result = stmt.executeQuery(everyInterventionUntil);
////            if (!result.next()) {
////                System.out.println("No data to be shown");
////                return;
////            }
////            result.beforeFirst();
////            //System.out.println(UserInterface.getTable(result));
////        }
////    }
////}
