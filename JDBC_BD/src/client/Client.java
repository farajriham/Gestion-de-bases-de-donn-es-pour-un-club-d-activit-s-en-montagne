package client;

import java.sql.*;

public class Client {
    // Informations de connexion à la base de données
    private static final String DB_URL = "jdbc:oracle:thin:@oracle1.ensimag.fr:1521:oracle1";
    private static final String userName = "charkim";
    private static final String password = "charkim";

    public static Connection connection;



    public static void DriverRegister() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("Driver chargé");
        } catch (SQLException e) {
            System.out.println("Erreur lors du chargement : <br/>" + e.getMessage());
            e.printStackTrace();
        }
    }

    // Méthode pour se connecter à la base de données
    public void Connect() {

        try {
            // établissement de la connexion
            connection = DriverManager.getConnection(DB_URL , userName , password );
            System.out.println("Connexion effective !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion : <br/>" + e.getMessage());
            e.printStackTrace();
        }

        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }


    // Méthode pour se déconnecter de la base de données
    public static void closeConnection(){
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connexion fermée !");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion : <br/>" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public String getDB_URL() {
        return DB_URL;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    public static void transaction(Connection connection) throws SQLException {
        try {
            // Désactivation de l'autocommit
            connection.setAutoCommit(false);

            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            // Création d'un objet Statement
            PreparedStatement pstmt = connection.prepareStatement
                    ("select * from CineSalles where nbplaces > ? and NomCine like ?");            // Intérrogation de la base de données
            ResultSet resultSet = pstmt.executeQuery();
            // mise à jour de la base de données
//            int result = pstmt.executeUpdate("UPDATE ");
            // Validation de la transaction
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la requête : <br/>" + e.getMessage());
            e.printStackTrace();
            // Annulation de la transaction
            connection.rollback();
        }
    }

    public static void savepoint(Connection connection) throws SQLException {
        Savepoint savepoint = null;
        try {
            // Désactivation de l'autocommit
            connection.setAutoCommit(false);
            // Création d'un objet Statement
            Statement statement = connection.createStatement();
            // Intérrogation de la base de données
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ");
            // mise à jour de la base de données
            int result = statement.executeUpdate("UPDATE ");
            // Création d'un point de sauvegarde
            savepoint = connection.setSavepoint();
            // mise à jour de la base de données
            result = statement.executeUpdate("UPDATE ");
            // Validation de la transaction
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la requête : <br/>" + e.getMessage());
            e.printStackTrace();
            // Annulation de la transaction
            connection.rollback(savepoint);
        }
    }

    public static void metadata(Connection connection) throws SQLException {
        try {
            // Création d'un objet DatabaseMetaData
            DatabaseMetaData dbmd = connection.getMetaData();
            // Récupération des informations sur la base de données
            System.out.println("Nom du produit : " + dbmd.getDatabaseProductName());
            System.out.println("Version du produit : " + dbmd.getDatabaseProductVersion());
            System.out.println("Nom du driver : " + dbmd.getDriverName());
            System.out.println("Version du driver : " + dbmd.getDriverVersion());
            System.out.println("URL : " + dbmd.getURL());
            System.out.println("Nom d'utilisateur : " + dbmd.getUserName());
        } catch (SQLException e) {
            System.out.println("Erreur lors de la requête : <br/>" + e.getMessage());
            e.printStackTrace();
        }
    }
}

