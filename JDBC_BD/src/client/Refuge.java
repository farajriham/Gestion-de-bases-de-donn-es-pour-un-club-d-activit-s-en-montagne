package client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Refuge {
    private String emailRef;
    private String nomRefuge;
    private String SecteurGeo;
    private String nb_places_repas;
    private String nb_places_dormir;
    private String prix_nuit;
    private String text_presentation;

    public Refuge(String emailRef, String nomRef, String SecteurGeo, String nb_places_repas, String nb_places_dormir, String prix_nuit, String text_presentation) {
        this.emailRef = emailRef;
        this.nomRefuge = nomRef;
        this.SecteurGeo = SecteurGeo;
        this.nb_places_repas = nb_places_repas;
        this.nb_places_dormir = nb_places_dormir;
        this.prix_nuit = prix_nuit;
        this.text_presentation = text_presentation;
    }

    public Refuge (String nomRef) {
        this.nomRefuge = nomRef;
    }

    public String getEmailRef() {
        return emailRef;
    }

    public String getNomRef() {
        return nomRefuge;
    }

    public String getSecteurGeo() {
        return SecteurGeo;
    }

    public String getNb_places_repas() {
        return nb_places_repas;
    }

    public String getNb_places_dormir() {
        return nb_places_dormir;
    }

    public String getPrix_nuit() {
        return prix_nuit;
    }

    public String getText_presentation() {
        return text_presentation;
    }

    public void afficherRefuge() throws SQLException {
        PreparedStatement pstmt = Client.connection.prepareStatement("SELECT * FROM Refuges WHERE nomRef =?");
        pstmt.setString(1, nomRefuge);
        ResultSet rs = pstmt.executeQuery();
        QueryManager.dumpResultSet(rs);
    }

    public static int[] showRepas(String nomRefuge, Member Membre,Scanner sc) throws SQLException, ParseException {
        PreparedStatement pstmt = Client.connection.prepareStatement("SELECT NOM_REPAS, PRIX_REPAS FROM Refuges JOIN ProposeRepas ON Refuges.EMAILREF = ProposeRepas.EMAILREF WHERE nomRef =?");
        pstmt.setString(1, nomRefuge);
        ResultSet rs = pstmt.executeQuery();
        int nbrepas = 0;
        int prixtotalrepas = 0;
        if(rs.next()){
        rs = pstmt.executeQuery();
        while (rs.next()) {
            boolean nonbonchoix = true;
            while(nonbonchoix){
                System.out.println(" Vous voulez : " + rs.getString(1) + "  de prix : " + rs.getString(2) +" oui/non");
                String dec= sc.nextLine();
                if (dec.equals("oui")) {
                    nbrepas++;
                    prixtotalrepas += rs.getInt(2) ;
                    nonbonchoix=false;
                }else if (dec.equals("non")){
                    nonbonchoix=false;

                } else {
                    System.out.println("Choix non valide");
                }

            }
        }
        }else{
            System.out.println("Refuge non valide");
            ServicesNavigation.displayMenu(Membre,sc);
        }
        return new int[]{nbrepas, prixtotalrepas};
    }


}
