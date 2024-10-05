package client;

import javax.xml.transform.Result;
import java.sql.*;

import static client.Client.connection;

public class Member {
    private int IdMembre;
    private String email;
    private String password;
    private String nom;
    private String prenom;

    private String adr_postale;
    private String somme_due;
    private String somme_reglee;

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean VerifyMemberCredentials(String email, String password) throws SQLException {

        // requete sql pour verifier si le membre existe.

        // Création d'un objet Statement
        PreparedStatement pstmt = Client.connection.prepareStatement("SELECT * FROM Membre WHERE ADR_EMAIL= ? AND MOT_DE_PASSE= ?");
        pstmt.setString(1, email);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();

        

        if (!rs.next()) {
            // le membre n'existe pas
            return false;
        }

        // le membre existe
        System.out.println("Vous etes connecté");
        return true;
    }

    public boolean VerifyMemberAdhesion() throws SQLException {

        // requete sql pour verifier si le membre est adhérent.
        PreparedStatement ps = Client.connection.prepareStatement("SELECT IDADHERENT FROM Adherent WHERE IDMEMBRE =? ");
        ps.setInt(1, IdMembre);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            // le membre est adhérent
            System.out.println("Vous etes adhérent");
            return true;
        }

        return false;
    }

    public void Oublier() throws SQLException {

        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(connection.TRANSACTION_SERIALIZABLE);

            PreparedStatement ps = Client.connection.prepareStatement("SELECT SOMME_DUE, SOMME_REGLEE FROM MEMBRE WHERE ADR_EMAIL = ?");
            ps.setString(1, email);
            ResultSet rs1 = ps.executeQuery();
            rs1.next();
            int somme_due = rs1.getInt(1);
            int somme_reglee = rs1.getInt(2);
            if (somme_due == somme_reglee){
                PreparedStatement pstmt1 = Client.connection.prepareStatement("SELECT IDMEMBRE FROM Membre WHERE ADR_EMAIL= ?");
                pstmt1.setString(1, email);
                ResultSet rs = pstmt1.executeQuery();
            
                rs.next();
    
                
                int OldIdMembre = rs.getInt(1);

                PreparedStatement pstmt2 = Client.connection.prepareStatement("DELETE FROM MEMBRE WHERE ADR_EMAIL=?");
                pstmt2.setString(1, email);
                pstmt2.executeQuery();
                

                Statement stmt = Client.connection.createStatement();
                ResultSet rslt = stmt.executeQuery("SELECT MAX(IDMEMBRE) FROM MEMBRE");
                rslt.next();
                
                int NewIdMembre = rslt.getInt(1) + 1;

                PreparedStatement pstmt3 = Client.connection.prepareStatement("INSERT INTO UTILISATEUR VALUES (?)");
                pstmt3.setInt(1, NewIdMembre);
                pstmt3.executeQuery();
                


                PreparedStatement pstmt4 = Client.connection.prepareStatement("UPDATE RESERVEREFUGE SET IDMEMBRE=? WHERE IDMEMBRE=?");
                pstmt4.setInt(1, NewIdMembre);
                pstmt4.setInt(2, OldIdMembre);
                pstmt4.executeQuery();
                

                PreparedStatement pstmt5 = Client.connection.prepareStatement("UPDATE ADHERENT SET IDMEMBRE=? WHERE IDMEMBRE=?");
                pstmt5.setInt(1, NewIdMembre);
                pstmt5.setInt(2, OldIdMembre);
                pstmt5.executeQuery();
                

                PreparedStatement pstmt6 = Client.connection.prepareStatement("UPDATE RESERVEREFUGE SET IDMEMBRE=? WHERE IDMEMBRE=?");
                pstmt6.setInt(1, NewIdMembre);
                pstmt6.setInt(2, OldIdMembre);
                pstmt6.executeQuery();
                

                PreparedStatement pstmt7 = Client.connection.prepareStatement("DELETE FROM UTILISATEUR WHERE IDMEMBRE=?");
                pstmt7.setInt(1, OldIdMembre);

                connection.commit();

                
                System.out.println("Vous etes oublié");
                }else{System.out.println("Vous ne pouvez pas vous oublier car vous avez des dettes");}
            } catch (SQLException e) {
                System.out.println("Erreur lors de la requête : <br/>" + e.getMessage());
                e.printStackTrace();
                connection.rollback();
                }
            }






    public void ajouterMember(){
        ResultSet rs = QueryManager.simpleQuery(Client.connection, "INSERT INTO Membre (IdMembre, adr_email, mdp, nom, prenom, adr_postale, somme_due, somme_reglee) VALUES (" + IdMembre + ", " + email + ", " + password + ", " + nom + ", " + prenom + ", " + adr_postale + ", " + somme_due + ", " + somme_reglee + ")");
    }



    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getIdAherent() throws SQLException {
        PreparedStatement pstmt3 = Client.connection.prepareStatement("SELECT IDADHERENT FROM ADHERENT WHERE IDMEMBRE = ?");
        pstmt3.setInt(1, IdMembre);
        ResultSet rs = pstmt3.executeQuery();
        rs.next();
        int IdAdherent = rs.getInt(1);
        return IdAdherent;
    }

    public int getIdMembre() {
        return IdMembre;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdr_postale() {
        return adr_postale;
    }

    public String getSomme_due() {
        return somme_due;
    }

    public String getSomme_reglee() {
        return somme_reglee;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password= password;
    }

    public void setIdMembre() throws SQLException {
        PreparedStatement pstmt3 = Client.connection.prepareStatement("SELECT IDMEMBRE FROM MEMBRE WHERE ADR_EMAIL = ?");
        pstmt3.setString(1, email);
        ResultSet rs = pstmt3.executeQuery();
        rs.next();
        this.IdMembre = rs.getInt(1);
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdr_postale(String adr_postale) {
        this.adr_postale = adr_postale;
    }

    public void setSomme_due(String somme_due) {
        this.somme_due = somme_due;
    }

    public void setSomme_reglee(String somme_reglee) {
        this.somme_reglee = somme_reglee;
    }
}
