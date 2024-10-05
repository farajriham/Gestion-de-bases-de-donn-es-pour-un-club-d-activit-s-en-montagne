package client;

import java.sql.*;

public class QueryManager {

    public static ResultSet simpleQuery(Connection connection, String query) {
        ResultSet resultSet = null;
        try {
            // Création d'un objet Statement
            Statement statement = connection.createStatement();
            // Intérrogation de la base de données
            resultSet = statement.executeQuery(query);
            // mise à jour de la base de données
            System.out.println("Resultats de la requête : " );
            //dumpResultSet(resultSet);
            //int result = statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la requête : <br/>" + e.getMessage());
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void dumpResultSet(ResultSet rset) throws SQLException {
        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();
        for (int k=1;k<=i;k++)
            System.out.print(rsetmd.getColumnName(k) + "\t");
        System.out.println();
        while (rset.next()) {
            for (int j = 1; j <= i; j++) {
                System.out.print(rset.getString(j) + "\t");
            }
            System.out.println();
        }
    }

//    public static void preparedQuery(Connection connection, String query) {
//        try {
//            // Création d'un objet Statement
//            PreparedStatement stmt = connection.prepareStatement(query);
//
//            System.out.println("Entrez le numéro de l'employé : ");
//            stmt.setInt(1, 7934);      // 1er parametre
//            System.out.println("*********************");
////            stmt.setString(2, "L%");  // 2eme parametre
//
//            ResultSet resultSet = stmt.executeQuery();
//            // mise à jour de la base de données
//            System.out.println("Resultats de la requête : " );
//            dumpResultSet(resultSet);
////            int result = stmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("Erreur lors de la requête : <br/>" + e.getMessage());
//            e.printStackTrace();
//        }
//    }

}
