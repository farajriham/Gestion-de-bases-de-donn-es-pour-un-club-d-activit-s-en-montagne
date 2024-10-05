package client;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static client.Client.connection;

public class BookMateriel {

    int idAdherent;

    int nbPieceReserv;

    String marque;

    String modele;

    String Daterecup;

    String Dateretour;

    int annee;

    int idLoc;

    public BookMateriel(String marque,String modele,int annee,int nbPieceReserv,String Daterecup,String Dateretour, int idAdherent,Member Membre,Scanner sc) throws SQLException, ParseException {
        try {
            this.marque=marque;
            this.modele= modele;
            this.annee=annee;
            this.idAdherent = idAdherent;

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            Statement statement = Client.connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(IDLOC) FROM LOCATIONS");
            rs.next();
            this.idLoc = rs.getInt(1) + 1;


            PreparedStatement pstmt = Client.connection.prepareStatement("SELECT TF.MARQUE,TF.MODELE,TF.ANNEE,TF.PIECESDISPO  FROM\n" +
            "(SELECT T.MARQUE,T.MODELE,T.ANNEE ,LM.NBPIECES,(LM.NBPIECES -T.PIECESPERDUES-T.PIECESRESERVEES) AS PIECESDISPO\n" +
            "FROM\n" +
            "(SELECT T2.MARQUE,T2.MODELE,T2.ANNEE,COALESCE(T2.PIECESPERDUES,0) AS PIECESPERDUES ,COALESCE(T3.PIECESRESERVEES,0) AS PIECESRESERVEES\n" +
            "FROM\n" +
            "(SELECT T1.MARQUE,T1.MODELE,T1.ANNEE,COALESCE(SUM(L.NBPIECESPERDUES),0) AS PIECESPERDUES\n" +
            "FROM\n" +
            "(\n" +
            "(SELECT MARQUE,MODELE,ANNEE FROM LOTMATERIEL )\n" +
            "MINUS\n" +
            "(SELECT L.MARQUE,L.MODELE,L.ANNEE FROM LOTMATERIEL L\n" +
            "JOIN MATERIELPERISSABLE MP\n" +
            "ON L.MARQUE = MP.MARQUE\n" +
            "AND L.MODELE = MP.MODELE\n" +
            "AND L.ANNEE = MP.ANNEE\n" +
            "WHERE MP.DATEPEREMPTION <= ?\n" +
            ")\n" +
            ") T1\n" +
            "LEFT JOIN LOCATIONS L\n" +
            "ON L.MARQUE = T1.MARQUE\n" +
            "AND L.MODELE = T1.MODELE\n" +
            "AND L.ANNEE = T1.ANNEE\n" +
            "GROUP BY T1.MARQUE,T1.MODELE,T1.ANNEE\n" +
            ") T2\n" +
            "LEFT JOIN (\n" +
            "SELECT MARQUE,MODELE,ANNEE,SUM(NBPIECES) AS PIECESRESERVEES\n" +
            "FROM LOCATIONS L\n" +
            "WHERE (L.DATERETOUR >=? AND ?>=L.DATERECUP) OR(?>=L.DATERECUP AND ?<=L.DATERETOUR) OR (L.DATERECUP >=? AND ?>=L.DATERECUP) OR (?>=L.DATERETOUR AND ?<=L.DATERETOUR) \n" +
            "GROUP BY MARQUE, MODELE, ANNEE\n" +
            ") T3\n" +
            "ON T2.MARQUE = T3.MARQUE\n" +
            "AND T2.MODELE = T3.MODELE\n" +
            "AND T2.ANNEE = T3.ANNEE\n" +
            ") T\n" +
            "LEFT JOIN LOTMATERIEL LM\n" +
            "ON\n" +
            "T.MARQUE = LM.MARQUE\n" +
            "AND T.MODELE = LM.MODELE\n" +
            "AND T.ANNEE = LM.ANNEE\n" +
            "WHERE T.PIECESRESERVEES + T.PIECESPERDUES < LM.NBPIECES\n" +
            ") TF\n" +
            "WHERE TF.MARQUE=? AND TF.MODELE=? AND TF.ANNEE=? ");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date1 = dateFormat.parse(Daterecup);
            Date date2 = dateFormat.parse(Dateretour);
            pstmt.setDate(1, new java.sql.Date(date2.getTime()));

            pstmt.setDate(2, new java.sql.Date(date2.getTime()));
            pstmt.setDate(3, new java.sql.Date(date2.getTime()));
            
            pstmt.setDate(4, new java.sql.Date(date1.getTime()));
            pstmt.setDate(5, new java.sql.Date(date1.getTime()));
            
            pstmt.setDate(6, new java.sql.Date(date1.getTime()));
            pstmt.setDate(7, new java.sql.Date(date2.getTime()));

            pstmt.setDate(8, new java.sql.Date(date2.getTime()));
            pstmt.setDate(9, new java.sql.Date(date1.getTime()));

            pstmt.setString(10, marque);
            pstmt.setString(11, modele);
            pstmt.setInt(12, annee);
            ResultSet resultSet = pstmt.executeQuery();
            int nbPiece=0;
            if(resultSet.next()){
                nbPiece = resultSet.getInt(4);
            }else{
                System.out.println("Réservation impossible, matériel indisponible selon vos critères.");
                ServicesNavigation.displayMenu(Membre, sc);

            }

        

        
            

            if (nbPiece >= nbPieceReserv) {
            PreparedStatement pstmt2 = Client.connection.prepareStatement("INSERT INTO LOCATIONS VALUES (?,?,?,?,?,?,?,?,?)");
            pstmt2.setInt(1, idLoc);
            pstmt2.setInt(2, nbPieceReserv);
            pstmt2.setDate(3, new java.sql.Date(date1.getTime()));
            pstmt2.setDate(4, new java.sql.Date(date2.getTime()));
            pstmt2.setInt(5, 0);
            pstmt2.setString(6, marque);
            pstmt2.setString(7, modele);
            pstmt2.setInt(8, annee);
            pstmt2.setInt(9, idAdherent);
            pstmt2.executeQuery();
            
            System.out.println("Votre réservation a bien été prise en compte");
            


            } else {
               System.out.println("Réservation impossible, nombre de pièces demandées excède celles disponibles");
            }
            connection.commit();
            ServicesNavigation.displayMenu(Membre, sc);
        } catch (SQLException e) {
                System.out.println("Erreur lors de la requête : <br/>" + e.getMessage());
                e.printStackTrace();
                connection.rollback();
            }
        }

}

