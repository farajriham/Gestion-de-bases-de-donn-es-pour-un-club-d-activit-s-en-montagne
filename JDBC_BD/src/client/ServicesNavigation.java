package client;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import static client.Client.connection;


public class ServicesNavigation {

    

    public static void displayMenu(Member Membre, Scanner sc) throws SQLException, ParseException {

        System.out.println("Voici les services disponibles : \n" +
                "1. afficher Formations\n" +
                "2. afficher Matériel\n" +
                "3. afficher Refuges\n" +
                "4. passer une réservation\n" +
                "5. Rendre un matériel\n"+
                "6. Quitter\n" +
                "Veuillez choisir un service : ");

        
        String choice = sc.nextLine();

        switch (choice) {
            case "1":
                displayFormations();
                System.out.println("Vous pouvez consulter une formation en saisissant 1, sinon appuyez sur 0 pour revenir au menu principal : ");
                while(true){
                String choix4 = sc.nextLine();
                if (choix4.equals("0")) {
                    displayMenu(Membre,sc);
                    break;
                } else if (choix4.equals("1")) {
                    System.out.println("Saisir le nom de la formation : ");
                    String nomForm = sc.nextLine();
                    consultFormation(nomForm);
                    break;
                } else {
                     System.out.println("Veuillez saisir un choix valide :");
                }
                }
                break;
            case "2":
                displayMateriel();
                System.out.println("Vous pouvez consulter un lot de matériel en saisissant 1, sinon appuyez sur 0 pour revenir au menu principal : ");
                while(true){
                String choix3 = sc.nextLine();
                if (choix3.equals("0")){
                    displayMenu(Membre,sc);
                    break;
                } else if (choix3.equals("1")) {
                    System.out.println("Saisir la marque : ");
                    String marque = sc.nextLine();
                    System.out.println("Saisir le modèle : ");
                    String modele = sc.nextLine();
                    System.out.println("Saisir l'année : ");
                    String annee = sc.nextLine();
                    consultMateriel(marque, modele, annee);
                    break;
                } else {
                    System.out.println("Veuillez saisir un choix valide :");
                }
                }
                break;
            case "3":
                displayRefuges();
                System.out.println("Vous pouvez consulter un refuge en saisissant 1, sinon appuyez sur 0 pour revenir au menu principal : ");
                while(true){
                    String choix5 = sc.nextLine();
                    if (choix5.equals("0")) {
                        displayMenu(Membre,sc);
                        break;
                    } else if (choix5.equals("1")) {
                    System.out.println("Saisir le nom du refuge : ");
                    String nomRef = sc.nextLine();
                    consultRefuge(nomRef);
                    break;
                    
                }  else {
                    System.out.println("Veuillez saisir un choix valide :");
            
            }
            }
                break;
            case "4":
            System.out.println("Voulez-vous réserver :\n a) un refuge, \n b) un matériel \n c) une formation ?");
            String choix2 = sc.nextLine();
            switch (choix2) {
                case "a":
                    System.out.println("Veuillez saisir le nom du refuge que vous souhaitez réserver : ");
                    String nomReff = sc.nextLine();
                    int[] repas = Refuge.showRepas(nomReff,Membre,sc);
                    if(checkPaiement(nomReff,Membre,sc)){
                    System.out.println("Veuillez saisir le nombre de nuits que vous souhaitez réserver : ");
                    int nb_nuits = Integer.valueOf(sc.nextLine());
                    System.out.println("Veuillez saisir la date à laquelle vous souhaitez réserver : ");
                    String date_res = sc.nextLine();
                    System.out.println("Veuillez saisir l'heure à laquelle vous souhaitez réserver : ");
                    String heure_res = sc.nextLine();
                    BookRefuge Book = new BookRefuge(nomReff, nb_nuits, repas[0], repas[1], date_res, heure_res, Membre.getIdMembre(),Membre,sc);
                    } else
                    {
                        System.out.println("Réservation impossible, la méthode de paiement voulue n'est pas disponible dans ce refuge");
                    }
                    ServicesNavigation.displayMenu(Membre,sc);
                    break;
                case "b":
                    if (!Membre.VerifyMemberAdhesion()){
                        System.out.println("Vous n'etes pas adhérent, vous ne pouvez pas réserver de matériel");
                        ServicesNavigation.displayMenu(Membre,sc);
                    }
                    int IdAdh = Membre.getIdAherent();
                    System.out.println("Veuillez saisir la marque du matériel que vous souhaitez réserver : ");
                    String marque = sc.nextLine();
                    System.out.println("Veuillez saisir le modèle du matériel que vous souhaitez réserver : ");
                    String modele = sc.nextLine();
                    System.out.println("Veuillez saisir l'année du matériel que vous souhaitez réserver : ");
                    int annee = Integer.valueOf(sc.nextLine());
                    System.out.println("Veuillez saisir le nombre de pièces que vous souhaitez réserver : ");
                    int nbPiece = Integer.valueOf(sc.nextLine());
                    System.out.println("Veuillez saisir la date de récupération que vous souhaitez réserver : ");
                    String daterecup = sc.nextLine();
                    System.out.println("Veuillez saisir la date de retour que vous souhaitez réserver : ");
                    String dateretour = sc.nextLine();
                    BookMateriel bookMateriel = new BookMateriel(marque,modele,annee,nbPiece,daterecup,dateretour,IdAdh,Membre,sc);
                    ServicesNavigation.displayMenu(Membre,sc);
                    break;
                    
        
            
                case "c":
                    if (!Membre.VerifyMemberAdhesion()){
                        System.out.println("Vous n'etes pas adhérent, vous ne pouvez pas réserver de formation");
                        ServicesNavigation.displayMenu(Membre,sc);
                    }
                    int IdAdherent = Membre.getIdAherent();
                    System.out.println("Veuillez saisir le nom de la formation que vous souhaitez réserver : ");
                    String nomForm = sc.nextLine();
                    BookFormation bookFormation = new BookFormation(nomForm, IdAdherent,Membre,sc);
                    ServicesNavigation.displayMenu(Membre,sc);
                    break;
                default:
                System.out.println("Choix non valide");
                ServicesNavigation.displayMenu(Membre,sc);
                }
                break;
            case "5":

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

                System.out.println("Quelle est id de votre location ?");
               
                int idLoc = Integer.valueOf(sc.nextLine());
                System.out.println("Combien de pièces avez-vous perdues/cassées ?");
                int nbpiecedeg = Integer.valueOf(sc.nextLine());
                
                PreparedStatement ps2 = Client.connection.prepareStatement("SELECT NBPIECES FROM LOCATIONS WHERE IDLOC =?");
                ps2.setInt(1, idLoc);
                ResultSet rs = ps2.executeQuery();
                if(rs.next()){
                    rs = ps2.executeQuery();
                    rs.next();
                    int nbPiecesRes = rs.getInt(1);
    
                    if(nbpiecedeg<0 || nbpiecedeg>nbPiecesRes){
                        System.out.println("Donnez incoherent");
                        ServicesNavigation.displayMenu(Membre, sc);
                    }
                } else {
                    System.out.println("ID non valide");
                    ServicesNavigation.displayMenu(Membre, sc);
                }


                PreparedStatement ps = Client.connection.prepareStatement("SELECT PRIXDEG,IDADHERENT FROM LOTMATERIEL LM JOIN LOCATIONS L ON L.MARQUE = LM.MARQUE AND L.MODELE = LM.MODELE AND L.ANNEE=LM.ANNEE WHERE IDLOC =?");
                ps.setInt(1, idLoc);
                ResultSet rs1 = ps.executeQuery();
                rs1.next();
                int idadh = rs1.getInt(2);
                
                int prixtotal = nbpiecedeg*rs1.getInt(1);
                
                

                PreparedStatement ps1 = Client.connection.prepareStatement("SELECT IDMEMBRE FROM ADHERENT WHERE IDADHERENT =?");
                ps1.setInt(1, idadh);
                ResultSet rs2 = ps1.executeQuery();
                rs2.next();
                int idMem = rs2.getInt(1);


                PreparedStatement ps5 = Client.connection.prepareStatement("SELECT SOMME_DUE FROM MEMBRE WHERE IDMEMBRE =?");
                ps5.setInt(1, idMem);
                ResultSet rs3 = ps5.executeQuery();
                rs3.next();
                int somme = rs3.getInt(1) + prixtotal;

               

                PreparedStatement up1 = Client.connection.prepareStatement("UPDATE MEMBRE SET SOMME_DUE =? WHERE IDMEMBRE=?");
                up1.setInt(1,somme);
                up1.setInt(2, idMem);
                up1.executeUpdate();

                PreparedStatement up = Client.connection.prepareStatement("UPDATE LOCATIONS SET NBPIECESPERDUES =? WHERE IDLOC=?");
                up.setInt(1,nbpiecedeg);
                up.setInt(2, idLoc);
                up.executeUpdate();
               
                connection.commit();
                System.out.println("Retour avec succès");
                ServicesNavigation.displayMenu(Membre, sc);
            case "6":
                sc.close();
                System.exit(0);
            default:
                System.out.println("Veuillez choisir un service valide");
                displayMenu(Membre,sc);
                break;
        }

    }



    public static boolean checkPaiement(String nomRef, Member Membre,Scanner sc) throws SQLException, ParseException{
        PreparedStatement pstmt = Client.connection.prepareStatement("SELECT TYPE_PAIEMENT FROM Refuges JOIN REFUGE_PAIEMENT ON Refuges.EMAILREF = REFUGE_PAIEMENT.EMAILREF WHERE nomRef =?");
        pstmt.setString(1, nomRef);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            rs = pstmt.executeQuery();
            while (rs.next()) {
                boolean nonbonchoix = true;
                while(nonbonchoix){
                    System.out.println(" Vous voulez payer par : " + rs.getString(1) +" oui/non");
                    String dec= sc.nextLine();
                    
                    if (dec.equals("oui")) {
                        return true;
                    }else if (dec.equals("non")){
                        nonbonchoix=false;
    
                    } else {
                        System.out.println("Choix non valide");
                    }
    
                }
            }
            return false;
            }else{
                System.out.println("Refuge non valide");
                ServicesNavigation.displayMenu(Membre,sc);
                return false;
        
            }


    }

    public static void displayRefuges() throws SQLException {
        ResultSet rs = QueryManager.simpleQuery(Client.connection, "SELECT \n" +
                "    R.NOMREF,R.SECTEURGEO,R.DATE_OUVERTURE,\n" +
                "    (R.NB_PLACES_REPAS - COALESCE(RESERV.PLACEREPASOCCUP, 0)) AS PLACEREPASDISPO,\n" +
                "    (NB_PLACES_DORMIR - COALESCE(NUITES.PLACEDORMIROCCUP, 0)) AS PLACEDORMIRDISPO\n" +
                "FROM \n" +
                "    REFUGES R\n" +
                "LEFT JOIN \n" +
                "    (SELECT EMAILREF, COUNT(IDRES) AS PLACEREPASOCCUP FROM RESERVEREFUGE \n" +
                "    WHERE (DATE_RES + NB_NUITS >= CURRENT_DATE AND CURRENT_DATE >= DATE_RES) AND NB_REPAS>0 GROUP BY EMAILREF ) RESERV\n" +
                "ON \n" +
                "    R.EMAILREF = RESERV.EMAILREF\n" +
                "LEFT JOIN \n" +
                "    (SELECT EMAILREF, COUNT(IDRES) AS PLACEDORMIROCCUP FROM RESERVEREFUGE \n" +
                "    WHERE (DATE_RES + NB_NUITS >= CURRENT_DATE AND CURRENT_DATE >= DATE_RES) AND NB_NUITS >0 GROUP BY EMAILREF) NUITES\n" +
                "ON \n" +
                "    R.EMAILREF = NUITES.EMAILREF\n" +
                "ORDER BY PLACEDORMIRDISPO ASC ,PLACEREPASDISPO ASC, R.DATE_OUVERTURE ASC");
        QueryManager.dumpResultSet(rs);
    }

    public static void consultRefuge(String nomRef) throws SQLException {
        PreparedStatement stmt = Client.connection.prepareStatement("SELECT * FROM Refuges WHERE Refuges.nomRef =?"); 
        stmt.setString(1, nomRef); 
        ResultSet rs = stmt.executeQuery();
        if (rs.next()){
            rs = stmt.executeQuery();
            QueryManager.dumpResultSet(rs);
        } else {
            System.out.println("Refuge non trouvé");
        }
    }

    public static void displayFormations() throws SQLException {
        ResultSet rs2 = QueryManager.simpleQuery(Client.connection, "SELECT\n" +
                "    F.NOMFORM AS NomFormation,\n" +
                "    FA.NOMACT AS Activite,\n" +
                "    F.DATE_DEBUT AS DateDebut,\n" +
                "    F.DUREE AS Duree,\n" +
                "    F.NBPLACES - COALESCE(COUNT(RF.IDRES),0) AS PlacesRestantes\n" +
                "FROM\n" +
                "    FORMATION F\n" +
                "JOIN\n" +
                "    FORMATIONACTIVITE FA ON F.IDFORM = FA.IDFORM AND F.ANNEEFORM = FA.ANNEEFORM\n" +
                "LEFT JOIN\n" +
                "    RESERVEFORMATION RF ON F.IDFORM = RF.IDFORM AND F.ANNEEFORM = RF.ANNEEFORM\n" +
                "GROUP BY\n" +
                "    F.IDFORM, F.ANNEEFORM, F.NOMFORM, FA.NOMACT, F.DATE_DEBUT, F.DUREE, F.NBPLACES\n" +
                "ORDER BY\n" +
                "    F.DATE_DEBUT ASC, F.NOMFORM ASC, FA.NOMACT ASC");
        QueryManager.dumpResultSet(rs2);
    }

    public static void consultFormation(String nomForm) throws SQLException {
        PreparedStatement stmt = Client.connection.prepareStatement("SELECT * FROM Formation WHERE Formation.nomForm =?");
        stmt.setString(1, nomForm);  
        ResultSet rs = stmt.executeQuery();
        if (rs.next()){
            rs = stmt.executeQuery();
            QueryManager.dumpResultSet(rs);
        } else {
            System.out.println("Formation non trouvée");
        }
    }

    private static void displayMateriel() throws SQLException {
        ResultSet rs3 = QueryManager.simpleQuery(Client.connection, "SELECT TF.MARQUE,TF.MODELE,TF.ANNEE,TF.NBPIECES,TF.PIECESDISPO , MUA.NOMACT FROM\n" +
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
                "WHERE MP.DATEPEREMPTION <= CURRENT_DATE\n" +
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
                "WHERE L.DATERETOUR > current_date and current_date > L.DATERECUP\n" +
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
                "JOIN MATUTILACTIV MUA\n" +
                "ON\n" +
                "TF.MARQUE = MUA.MARQUE\n" +
                "AND TF.MODELE = MUA.MODELE\n" +
                "AND TF.ANNEE = MUA.ANNEE");
        QueryManager.dumpResultSet(rs3);
    }

    public static void consultMateriel(String marque, String modele, String annee) throws SQLException {
        PreparedStatement stmt = Client.connection.prepareStatement("SELECT * FROM lotMateriel WHERE lotMateriel.marque =? AND lotMateriel.modele =?  AND lotMateriel.annee =?");
        stmt.setString(1, marque);
        stmt.setString(2,modele);
        stmt.setString(3,annee);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()){
            rs = stmt.executeQuery();
            QueryManager.dumpResultSet(rs);
        } else {
            System.out.println("Lot non trouvé");
        }
    }
}
