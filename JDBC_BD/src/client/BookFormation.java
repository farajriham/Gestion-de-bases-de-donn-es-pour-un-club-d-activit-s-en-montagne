package client;

import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;

import static client.Client.connection;

public class BookFormation {

        String nomForm;

        int idAdherent;

        int IdRes;

        int anneeForm;

        int idForm;

        public BookFormation(String nomForm, int idAdherent,Member Membre,Scanner sc) throws SQLException, ParseException {
            try {
                this.nomForm = nomForm;
                this.idAdherent = idAdherent;

                connection.setAutoCommit(false);
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

                Statement statement = Client.connection.createStatement();
                // Intérrogation de la base de données
                ResultSet rs = statement.executeQuery("SELECT MAX(IDRES) FROM RESERVEFORMATION");
                rs.next();
                this.IdRes = rs.getInt(1) + 1;

                PreparedStatement pstmt3 = Client.connection.prepareStatement("SELECT ANNEEFORM,IDFORM FROM FORMATION WHERE nomForm =?");
                pstmt3.setString(1, nomForm);
                ResultSet rslt = pstmt3.executeQuery();
                if(rslt.next()){
                    rslt = pstmt3.executeQuery();
                    this.anneeForm = rslt.getInt(1);
                    this.idForm = rslt.getInt(2);
                } else {
                    System.out.println("Formation non existante");
                    ServicesNavigation.displayMenu(Membre, sc);
                }

                PreparedStatement pstmt = Client.connection.prepareStatement("SELECT F.NBPLACES FROM FORMATION F WHERE F.IDFORM =? AND F.ANNEEFORM =?");
                pstmt.setInt(1, idForm);
                pstmt.setInt(2, anneeForm);
                ResultSet resultSet = pstmt.executeQuery();
                resultSet.next();
                int nbPLace = resultSet.getInt(1);

                PreparedStatement pstmtt = Client.connection.prepareStatement("SELECT COUNT(IDADHERENT) FROM RESERVEFORMATION RF WHERE RF.IDFORM =? AND RF.ANNEEFORM =?");
                pstmtt.setInt(1, idForm);
                pstmtt.setInt(2, anneeForm);
                ResultSet resultSett = pstmtt.executeQuery();
                resultSett.next();
                int nbInscrit = resultSett.getInt(1);

                PreparedStatement pstmt2 = Client.connection.prepareStatement("INSERT INTO RESERVEFORMATION VALUES (?,?,?,?)");
                pstmt2.setInt(1, IdRes);
                pstmt2.setInt(2, idAdherent);
                pstmt2.setInt(3, idForm);
                pstmt2.setInt(4, anneeForm);
                pstmt2.executeQuery();

                if (nbPLace > nbInscrit) {
                    System.out.println("Votre réservation a bien été prise en compte");
                    PreparedStatement ps1 = Client.connection.prepareStatement("SELECT IDMEMBRE FROM ADHERENT WHERE IDADHERENT =?");
                    ps1.setInt(1, idAdherent);
                    ResultSet rs2 = ps1.executeQuery();
                    rs2.next();
                    int idMem = rs2.getInt(1);


                    PreparedStatement ps = Client.connection.prepareStatement("SELECT PRIX FROM FORMATION WHERE IDFORM=? AND ANNEEFORM=?");
                    ps.setInt(1, idForm);
                    ps.setInt(2,anneeForm);
                    ResultSet rs5 = ps.executeQuery();
                    rs5.next();
                    int prixtotal = rs5.getInt(1);

                    PreparedStatement ps2 = Client.connection.prepareStatement("SELECT SOMME_DUE FROM MEMBRE WHERE IDMEMBRE =?");
                    ps2.setInt(1, idMem);
                    ResultSet rs3 = ps2.executeQuery();
                    rs3.next();
                    int somme = rs3.getInt(1) + prixtotal;

                    PreparedStatement up1 = Client.connection.prepareStatement("UPDATE MEMBRE SET SOMME_DUE =? WHERE IDMEMBRE=?");
                    up1.setInt(1,somme);
                    up1.setInt(2, idMem);
                    up1.executeUpdate();
                } else {
                    PreparedStatement pstmt1 = Client.connection.prepareStatement("SELECT MAX(RANGATTENTE) FROM FORMATIONATTENTE JOIN RESERVEFORMATION ON RESERVEFORMATION.IDRES = FORMATIONATTENTE.IDRES WHERE IDFORM = ? AND ANNEEFORM = ?");
                    pstmt1.setInt(1, idForm);
                    pstmt1.setInt(2, anneeForm);
                    ResultSet rs1 = pstmt1.executeQuery();

                    int rang = 1;
                    //dumpResultSet(rs1);
                    if(rs1.next()) {
                        rang = rs1.getInt(1) + 1;
                        System.out.println("Il n'y a plus de places disponibles pour cette formation, votre rang dans la liste d'attente est : " + rang);
                    } else {
                        System.out.println("Il n'y a plus de places disponibles pour cette formation, vous êtes le premier sur la liste d'attente");
                    }
                    PreparedStatement pstmt4 = Client.connection.prepareStatement("INSERT INTO FORMATIONATTENTE VALUES (?,?)");
                    pstmt4.setInt(1, IdRes);
                    pstmt4.setInt(2, rang);
                    pstmt4.executeQuery();


                }
                connection.commit();
                return;
            } catch (SQLException e) {
                    System.out.println("Erreur lors de la requête : <br/>" + e.getMessage());
                    e.printStackTrace();
                    connection.rollback();
                }
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
        }

