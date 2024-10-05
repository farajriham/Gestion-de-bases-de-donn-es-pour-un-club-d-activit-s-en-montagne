package client;


import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class TestClientApp {
    public static void main(String[] args) throws SQLException, ParseException {
        Client ClientApp = new Client();
        Client.DriverRegister();
        ClientApp.Connect();
        Connection connection = ClientApp.getConnection();


        Scanner sc = new Scanner(System.in);

        boolean notexit = true;
        System.out.println("Bienvenue sur l'application !");
        while (notexit){
        System.out.println("Voulez-vous: \n 1) vous connecter \n 2) exercer votre droit à l'oubli \n 3) Quitter ?");
        String choix = sc.nextLine();
        switch (choix) {
            case "1":
                System.out.println("Veuillez saisir votre email : ");
                String email = sc.nextLine();
                System.out.println("Veuillez saisir votre mot de passe : ");
                String password = sc.nextLine();

                Member Membre = new Member(email, password);

                if (Membre.VerifyMemberCredentials(email, password)) {
                    Membre.setIdMembre();
                } else {
                    System.out.println("Vous n'êtes pas connecté car vous n'êtes pas inscrit ou vous avez saisi un mauvais mot de passe");
                    break;
                }
                while (true){
                ServicesNavigation.displayMenu(Membre,sc);
                }


                    case "2":
                        System.out.println("Veuillez saisir votre email : ");
                        String email3 = sc.nextLine();
                        System.out.println("Veuillez saisir votre mot de passe : ");
                        String password3 = sc.nextLine();
                        Member Membre3 = new Member(email3, password3);
                        if (Membre3.VerifyMemberCredentials(email3, password3)) {
                            Membre3.Oublier();
                        } else {
                            System.out.println("Impossible d'exercer ce droit car vous n'êtes pas inscrit ou vous avez saisi un mauvais mot de passe");
                        }
                        break;
                    case "3":
                        notexit=false;
                        break;
                    default:
                        System.out.println("Veuillez saisir un choix valide");
                        break;
                }
            }
            sc.close();
            Client.closeConnection();
        }

}