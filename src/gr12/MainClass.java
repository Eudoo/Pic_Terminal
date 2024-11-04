package gr12;


import java.util.Scanner;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserFileManager.loadUsers();  // Charger les utilisateurs depuis le fichier
        List<Categorie> categories = UserFileManager.chargerCategories(); // Charger les catégories
        
        Administrateur admin1 = new Administrateur("admin1", "admin1@gmail.com", "1234");
        if (!UserFileManager.utilisateurExiste(admin1.get_email())) {
            UserFileManager.ajouterUtilisateur(admin1); // Sauvegarde l'administrateur dans le fichier
            System.out.println("Administrateur initial créé : " + admin1.get_nom());
        }
        
        System.out.println("Bienvenue sur la plateforme de téléchargement d'images!");

        while (true) {
            System.out.println("\n1. Inscription");
            System.out.println("2. Connexion");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // vider le tampon

            switch (choix) {
                case 1 -> Utilisateur.sinscrire(); // Appel direct à la méthode d'inscription
                case 2 -> {
                    Utilisateur utilisateur = Utilisateur.se_connecter(); // Appel direct à la méthode de connexion
                    if (utilisateur != null) {
                        if (utilisateur instanceof Administrateur) {
                            menuAdministrateur((Administrateur) utilisateur, categories, scanner);
                        } else {
                            menuUtilisateur(utilisateur, categories, scanner);
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Merci d'avoir utilisé la plateforme!");
                    UserFileManager.saveUsers();
                    UserFileManager.sauvegarderCategories(categories);
                    scanner.close();
                    return;
                }
                default -> System.out.println("Option non valide, veuillez réessayer.");
            }
        }
    }

    private static void menuUtilisateur(Utilisateur utilisateur, List<Categorie> categories, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Menu Utilisateur ---");
            System.out.println("0. Voir votre galerie");
            System.out.println("1. Voir les images disponibles sur le site");
            System.out.println("2. Télécharger une image");
            System.out.println("3. Proposer une nouvelle image");
            System.out.println("4. Déconnexion");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // vider le tampon

            switch (choix) {
            	case 0 -> utilisateur.afficher_galerie();
                case 1 -> afficherImages(categories);
                case 2 -> telechargerImage(utilisateur, categories, scanner);
                /*case 3 -> proposerImage(utilisateur, categories, scanner);*/
                case 4 -> {
                    System.out.println("Déconnexion réussie.");
                    return;
                }
                default -> System.out.println("Option non valide, veuillez réessayer.");
            }
        }
    }

    private static void afficherImages(List<Categorie> categories) {
        System.out.println("\n--- Images Disponibles ---");
        for (Categorie categorie : categories) {
            System.out.println("\nCatégorie: " + categorie.get_nom_categorie());
            categorie.afficher_categorie();
        }
    }

    private static void telechargerImage(Utilisateur utilisateur, List<Categorie> categories, Scanner scanner) {
        System.out.print("Entrez le nom de l'image à télécharger : ");
        String nomImage = scanner.nextLine();
        
        for (Categorie categorie : categories) {
            for (Image image : categorie.get_images()) {
                if (image.get_nomfichier().equals(nomImage)) {
                    utilisateur.telecharger(image);
                    System.out.println("Image téléchargée avec succès.");
                    return;
                }
            }
        }
        System.out.println("Image non trouvée.");
    }
/*
    private static void proposerImage(Utilisateur utilisateur, List<Categorie> categories, Scanner scanner) {
        System.out.print("Entrez le nom de votre image : ");
        String nomImage = scanner.nextLine();
        System.out.print("Entrez la catégorie de l'image : ");
        String nomCategorie = scanner.nextLine();
        
        Image imageProposee = new Image(nomImage, utilisateur);
        Categorie categorie = categories.stream()
                                        .filter(cat -> cat.get_nom_categorie().equals(nomCategorie))
                                        .findFirst()
                                        .orElse(null);

        if (categorie != null) {
            categorie.ajouter_image(imageProposee);
            UserFileManager.sauvegarderImages(categorie.get_images());
            System.out.println("Image proposée avec succès. En attente d'approbation par un administrateur.");
        } else {
            System.out.println("Catégorie non trouvée.");
        }
    }
*/
    private static void menuAdministrateur(Administrateur admin, List<Categorie> categories, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Menu Administrateur ---");
            System.out.println("1. Voir les images proposées");
            System.out.println("2. Approuver une image");
            System.out.println("3. Déconnexion");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // vider le tampon

            switch (choix) {
               /* case 1 -> voirImagesProposees(categories);
                case 2 -> approuverImage(admin, categories, scanner);*/
                case 3 -> {
                    System.out.println("Déconnexion réussie.");
                    return;
                }
                default -> System.out.println("Option non valide, veuillez réessayer.");
            }
        }
    }
/*
    private static void voirImagesProposees(List<Categorie> categories) {
        System.out.println("\n--- Images Proposées ---");
        for (Categorie categorie : categories) {
            System.out.println("Catégorie: " + categorie.get_nom_categorie());
            categorie.get_images().stream()
                     .filter(Image::isProposee)
                     .forEach(Image::afficher_propriete);
        }
    }

    private static void approuverImage(Administrateur admin, List<Categorie> categories, Scanner scanner) {
        System.out.print("Entrez le nom de l'image à approuver : ");
        String nomImage = scanner.nextLine();
        
        for (Categorie categorie : categories) {
            for (Image image : categorie.get_images()) {
                if (image.get_nomfichier().equals(nomImage) && image.isProposee()) {
                    admin.approuverImage(image);
                    System.out.println("Image approuvée avec succès.");
                    UserFileManager.sauvegarderImages(categorie.get_images());
                    return;
                }
            }
        }
        System.out.println("Image proposée non trouvée ou déjà approuvée.");
    }
    
   */
}
