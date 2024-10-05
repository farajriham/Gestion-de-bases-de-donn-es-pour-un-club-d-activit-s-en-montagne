package client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static client.Client.connection;

public class BookRefuge {

    String nomRef;

    int nb_nuits;

    int IdRes;

    String date_res;

    String heure_res;

    String email;

    int idMembre;

    int nbrepas;

    int prixrepas;

    public BookRefuge(String nomRef, int nb_nuits, int nbrepas, int prixrepas,  String date_res, String heure_res, int idMembre, Member Membre, Scanner sc) throws SQLException, ParseException {
        this.nomRef = nomRef;
        this.nb_nuits = nb_nuits;
        this.date_res = date_res;
        this.nbrepas = nbrepas;
        this.prixrepas = prixrepas;
        this.heure_res = heure_res;

        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

        try{
        Statement statement = Client.connection.createStatement();

        ResultSet rs = statement.executeQuery("SELECT MAX(IDRES) FROM RESERVEREFUGE");
        rs.next();
        this.IdRes = rs.getInt(1) + 1;

        PreparedStatement pstmt = Client.connection.prepareStatement("SELECT EMAILREF FROM REFUGES WHERE nomRef =?");
        pstmt.setString(1, nomRef);
        ResultSet resultSet = pstmt.executeQuery();
        resultSet.next();
        this.email = resultSet.getString(1);
        this.idMembre = idMembre;

        PreparedStatement pss = Client.connection.prepareStatement("SELECT \n" +
                "    (NB_PLACES_DORMIR - COALESCE(NUITES.PLACEDORMIROCCUP, 0)) AS PLACEDORMIRDISPO\n" +
                "FROM REFUGES R\n" +
                "LEFT JOIN\n" +
                "    (SELECT EMAILREF, COUNT(IDRES) AS PLACEDORMIROCCUP FROM RESERVEREFUGE\n" +
                "    WHERE (\n" +
                "(DATE_RES + NB_NUITS >=? AND ?>=DATE_RES) OR (? + ?>= DATE_RES AND (? + ?<= DATE_RES +NB_NUITS)) OR (DATE_RES >=? AND ?+?>=DATE_RES) OR (? + ?>= DATE_RES + NB_NUITS AND  ?<= DATE_RES +NB_NUITS)) AND NB_NUITS>0 GROUP BY EMAILREF) NUITES\n" +
                "ON\n" +
                "    R.EMAILREF = NUITES.EMAILREF\n" +
                "WHERE R.EMAILREF=?");

                
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = dateFormat.parse(date_res);


        pss.setDate(1, new java.sql.Date(date.getTime()));
        pss.setDate(2, new java.sql.Date(date.getTime()));
        pss.setDate(3, new java.sql.Date(date.getTime()));

        pss.setInt(4, nb_nuits);

        pss.setDate(5, new java.sql.Date(date.getTime()));
        pss.setInt(6, nb_nuits);
        pss.setDate(7, new java.sql.Date(date.getTime()));
        pss.setDate(8, new java.sql.Date(date.getTime()));
        pss.setInt(9, nb_nuits);
        pss.setDate(10, new java.sql.Date(date.getTime()));
        pss.setInt(11, nb_nuits);
        pss.setDate(12, new java.sql.Date(date.getTime()));
        pss.setString(13, email);
        ResultSet rss = pss.executeQuery();
        rss.next();
        int nbPLace = rss.getInt(1);

        PreparedStatement pssss = Client.connection.prepareStatement("SELECT\n" +
                "    (NB_PLACES_REPAS - COALESCE(RESERV.PLACEREPASOCCUP, 0)) AS PLACEREPASDISPO\n" +
                "FROM REFUGES R\n" +
                "LEFT JOIN\n" +
                "    (SELECT EMAILREF, COUNT(IDRES) AS PLACEREPASOCCUP FROM RESERVEREFUGE\n" +
                "    WHERE ((DATE_RES + NB_NUITS >=? AND ?>=DATE_RES) OR (? + ?>= DATE_RES AND (? + ?<= DATE_RES +NB_NUITS)) OR (DATE_RES >=? AND ?+?>=DATE_RES) OR (? + ?>= DATE_RES + NB_NUITS AND  ?<= DATE_RES +NB_NUITS)) AND NB_REPAS>0 GROUP BY EMAILREF) RESERV\n" +
                "ON\n" +
                "    R.EMAILREF = RESERV.EMAILREF\n" +
                "WHERE R.EMAILREF=?");


        pssss.setDate(1, new java.sql.Date(date.getTime()));
        pssss.setDate(2, new java.sql.Date(date.getTime()));
        pssss.setDate(3, new java.sql.Date(date.getTime()));

        pssss.setInt(4, nb_nuits);

        pssss.setDate(5, new java.sql.Date(date.getTime()));
        pssss.setInt(6, nb_nuits);
        pssss.setDate(7, new java.sql.Date(date.getTime()));
        pssss.setDate(8, new java.sql.Date(date.getTime()));
        pssss.setInt(9, nb_nuits);
        pssss.setDate(10, new java.sql.Date(date.getTime()));
        pssss.setInt(11, nb_nuits);
        pssss.setDate(12, new java.sql.Date(date.getTime()));
        pssss.setString(13, email);
        ResultSet rsss = pssss.executeQuery();
        int nbPLaceRepas = 0;
        rsss.next();
        nbPLaceRepas = rsss.getInt(1);


        if (!((nbPLace==0 && nb_nuits>0 && nbPLaceRepas>0 && nbrepas>0) || (nbPLace>0 && nb_nuits>0 && nbPLaceRepas==0 && nbrepas>0) || (nbPLace==0 && nb_nuits>0) || (nbPLaceRepas==0 && nbrepas>0))) {
            PreparedStatement pstmt2 = Client.connection.prepareStatement("INSERT INTO RESERVEREFUGE VALUES (?,?,?,?,?,?,?,?)");
            pstmt2.setInt(1, idMembre);
            pstmt2.setInt(2, IdRes);
            pstmt2.setDate(3, new java.sql.Date(date.getTime()));
            pstmt2.setString(4, heure_res);
            pstmt2.setInt(5, nb_nuits);
            pstmt2.setInt(6, nbrepas);

            PreparedStatement ps = Client.connection.prepareStatement("SELECT PRIX_NUIT FROM REFUGES WHERE EMAILREF = ?");
            ps.setString(1, email);
            ResultSet rs1 = ps.executeQuery();
            rs1.next();

            int prixtotal = 0;

            
            int prix_nuit = rs1.getInt(1);
            prixtotal += nb_nuits * prix_nuit;
            

            
            prixrepas = (nb_nuits+1) * prixrepas;
            prixtotal += prixrepas;
            
            pstmt2.setInt(7, prixtotal);

            pstmt2.setString(8, email);
            pstmt2.executeQuery();
            PreparedStatement ps2 = Client.connection.prepareStatement("SELECT SOMME_DUE FROM MEMBRE WHERE IDMEMBRE =?");
            ps2.setInt(1, idMembre);
            ResultSet rs3 = ps2.executeQuery();
            rs3.next();
            int somme = rs3.getInt(1) + prixtotal;
            PreparedStatement up1 = Client.connection.prepareStatement("UPDATE MEMBRE SET SOMME_DUE =? WHERE IDMEMBRE=?");
            up1.setInt(1,somme);
            up1.setInt(2, idMembre);
            up1.executeUpdate();

            
            connection.commit();
            System.out.println("Votre reservation est reussie.");
        } else {
            System.out.println("reservation impossible");
            ServicesNavigation.displayMenu(Membre,sc);
        }

        }catch (SQLException | ParseException e){
            System.out.println("An error has occured");
            System.exit(0); 
         } 

    }
}

