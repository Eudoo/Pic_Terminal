package gr12;


import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MainClass {

    private static Scanner scanner;
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserFileManager.loadUsers();  // Charger les utilisateurs depuis le fichier
        List<Categorie> categories = UserFileManager.chargerCategories(); // Charger les catégories
        List<Image> images = UserFileManager.chargerImages();
        
        
        //  ############ Instance Administrateur cree une seule fois ############### 
        /*
        Administrateur admin1 = new Administrateur("a", "a", "a");
        if (!UserFileManager.utilisateurExiste(admin1.get_email())) {
            UserFileManager.ajouterUtilisateur(admin1); // Sauvegarde l'administrateur dans le fichier
            System.out.println("Administrateur initial créé : " + admin1.get_nom());
        }
        
         
        // Ajout des images de base
        Image image1 = new Image("paris.jpg", "Vue de la Tour Eiffel à Paris");
        Image image2 = new Image("newyork.jpg", "Skyline de New York");
        Image image3 = new Image("pomme.jpg", "Pomme rouge");
        Image image4 = new Image("banane.jpg", "Banane jaune");
        Image image5 = new Image("fraise.jpg", "Fraise fraîche");
        
        // Ajouter les images à la liste d'images disponibles
        images.add(image1);
        images.add(image2);
        images.add(image3);
        images.add(image4);
        images.add(image5);
        */
        
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
                            menuAdministrateur((Administrateur) utilisateur, categories, images, scanner);
                        } else {
                            menuUtilisateur(utilisateur, categories, scanner);
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Merci d'avoir utilisé la plateforme!");
                    UserFileManager.saveUsers();
                    UserFileManager.sauvegarderCategories(categories);
                    UserFileManager.sauvegarderImages(images);
                    scanner.close();
                    return;
                }
                default -> System.out.println("Option non valide, veuillez réessayer.");
            }
        }
    }

    private static void menuUtilisateur(Utilisateur utilisateur, List<Categorie> categories, Scanner scanner) {
        int men;
    	do {
            System.out.println("\n--- Menu Utilisateur ---");
            System.out.println("1. Voir votre galerie");
            System.out.println("2. Voir les images disponibles sur le site");
            System.out.println("3. Télécharger une image");
            System.out.println("4. Mon profil");
            System.out.println("5. Modifier profil");
            System.out.println("6. Déconnexion");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // vider le tampon

            switch (choix) {
            	case 1 -> utilisateur.afficher_galerie();
                case 2 -> afficherImages(categories,utilisateur);
                case 3 -> telechargerImage(utilisateur, categories, scanner);
                case 4 -> utilisateur.afficher_infos();
                case 5 -> utilisateur.modifierProfil();
                case 6 -> {
                    System.out.println("Déconnexion réussie.");
                    return;
                }
                default -> System.out.println("Option non valide, veuillez réessayer.");
            }
            System.out.print("\n1. Menu \n2. Quitter");
            System.out.print("\nVotre choix :");
            men = scanner.nextInt();
            scanner.nextLine(); // vider le tampon 
        }while( men != 2 );
    }

    private static void afficherImages(List<Categorie> categories, Utilisateur utilisateur) {
    	UserFileManager.chargerCategories();
        Scanner scanner = new Scanner(System.in);
    	System.out.println("\n--- Images Disponibles ---");
        for (Categorie categorie : categories) {
            System.out.println("\nCatégorie: " + categorie.get_nom_categorie());
            categorie.afficher_categorie();
        }
        
        boolean continuer = true;
        while (continuer) {
        	
            System.out.println("\nEntrez l'ID de l'image pour interagir, ou -1 pour revenir au menu principal : ");
            int imageId = scanner.nextInt();
            scanner.nextLine(); 
            if (imageId == -1) {
                continuer = false;
                break;
            }

            // Rechercher l'image correspondant à l'ID
            Image imageSelectionnee = null;
            for (Categorie categorie : categories) {
                for (Image image : categorie.get_images()) {
                    if (image.get_id() == imageId) {
                        imageSelectionnee = image;
                        break;
                    }
                }
                if (imageSelectionnee != null) break;
            }

            if (imageSelectionnee != null) {
                boolean sousMenu = true;
                while (sousMenu) {
                    // Afficher les options pour l'image sélectionnée
                    System.out.println("\nOptions pour l'image : " + imageSelectionnee.get_titre());
                    System.out.println("1. Liker");
                    System.out.println("2. Télécharger");
                    System.out.println("3. Retourner à la sélection d'image");

                    System.out.print("Entrez votre choix : ");
                    int choix = scanner.nextInt();
                    scanner.nextLine(); // Consomme la nouvelle ligne

                    switch (choix) {
                        case 1:
                        	boolean imageLikée = imageSelectionnee.liker(utilisateur.email);
                            if (imageLikée) {
                                UserFileManager.sauvegarderImages(listerToutesLesImages(categories)); // Sauvegarder après modification
                                System.out.println("Image likée avec succès.");
                            }
                            break;
                        case 2:
                            utilisateur.telecharger(imageSelectionnee);
                            System.out.println("Image téléchargée avec succès.");
                            break;
                        case 3:
                            sousMenu = false;
                            break;
                        default:
                            System.out.println("Choix invalide. Veuillez réessayer.");
                            break;
                    }
                }
            } else {
                System.out.println("Image avec ID " + imageId + " non trouvée. Veuillez réessayer.");
            }
        }
           
    }
    
    private static List<Image> listerToutesLesImages(List<Categorie> categories) {
        List<Image> toutesLesImages = new ArrayList<>();
        for (Categorie categorie : categories) {
            toutesLesImages.addAll(categorie.get_images());
        }
        return toutesLesImages;
    }
    
       
    private static void telechargerImage(Utilisateur utilisateur, List<Categorie> categories, Scanner scanner) {
        System.out.print("Entrez le nom de l'image à télécharger : ");
        String nomImage = scanner.nextLine();
        
        for (Categorie categorie : categories) {
            for (Image image : categorie.get_images()) {
                if (image.get_nomfichier().equals(nomImage)) {
                    utilisateur.telecharger(image);
                    UserFileManager.sauvegarderCategories(categories);
                    System.out.println("Image téléchargée avec succès.");
                    return;
                }
            }
        }
        System.out.println("Image non trouvée.");
    }

    private static void proposerImage(Administrateur admin, List<Categorie> categories, Scanner scanner) {
        System.out.print("Entrez le nom de votre image : ");
        String nomImage = scanner.nextLine();
        System.out.print("Entrez le titre de l'image : ");
        String titre = scanner.nextLine();
        System.out.print("Entrez une description de l'image : ");
        String description = scanner.nextLine();
        System.out.print("Entrez la categorie de l'image : ");
        String nomCategorie = scanner.nextLine();
        
        Categorie categorieSelectionnee = null;
        for (Categorie categorie : categories) {
        	if (categorie.get_nom_categorie() == nomCategorie) {
                	 categorieSelectionnee = categorie;
                    break;
                }
            if (categorieSelectionnee != null) break;
        }

        
        Image imageProposee = new Image(nomImage, titre, description);
            if (categorieSelectionnee != null) {
            	admin.ajouterImage(categories,categorieSelectionnee.get_images());
            UserFileManager.sauvegarderImages(categorieSelectionnee.get_images());
            System.out.println("Image proposée avec succès. En attente d'approbation par un administrateur.");
        } else {
            System.out.println("Catégorie non trouvée.");
        }
    }

    
    
    private static void menuAdministrateur(Administrateur admin, List<Categorie> categories, List<Image> images, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Menu Administrateur ---");
            System.out.println("1. Creer une Image");
            System.out.println("2. Voir les images du site");
            System.out.println("3. Gestion des images");
            System.out.println("4. Creer une nouvelle categorie");
            System.out.println("5. Ajouter une image a une categorie");
            System.out.println("6. Voir les utilisateurs");
            System.out.println("7. Déconnexion");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // vider le tampon

            switch (choix) {
            	case 1 -> admin.creerImage(images);
            	case 2 -> ListerLesImages(categories);
            	case 3 -> GererImages(categories,admin);
            	case 4 -> creerCategorie(categories);
                case 5 -> admin.ajouterImage(categories, images);
                case 6 -> admin.consulterUtilisateurs();
                case 7 -> {
                    System.out.println("Déconnexion réussie.");
                    return;
                }
                default -> System.out.println("Option non valide, veuillez réessayer.");
            }
        }
    }
    
    public static void creerCategorie(List<Categorie> categories) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom de la nouvelle catégorie : ");
        String nomCategorie = scanner.nextLine();
        Categorie nouvelleCategorie = new Categorie(nomCategorie);
        categories.add(nouvelleCategorie); // Ajouter la catégorie à la liste des catégories
        System.out.println("Catégorie '" + nomCategorie + "' créée avec succès.");
        UserFileManager.sauvegarderCategories(categories);
    }
    
    private static void ListerLesImages(List<Categorie> categories) {
    	UserFileManager.chargerCategories();
        System.out.println("\n\t--- Liste des Images du Site ---");
        for (Categorie categorie : categories) {
            System.out.println("\n     * Catégorie: " + categorie.get_nom_categorie());
            for (Image image : categorie.get_images()) {
                image.afficher_propriete(); // Affiche les détails de l'image
            }
        }
     }
    
    
    private static void GererImages(List<Categorie> categories, Administrateur admin) {
    	UserFileManager.chargerCategories();
        Scanner scanner = new Scanner(System.in);
    	System.out.println("\n--- Images Disponibles ---");
        for (Categorie categorie : categories) {
            System.out.println("\nCatégorie: " + categorie.get_nom_categorie());
            categorie.afficher_categorie();
        }
        
        boolean continuer = true;
        while (continuer) {
        	
            System.out.println("\nEntrez l'ID de l'image pour interagir, ou -1 pour revenir au menu principal : ");
            int imageId = scanner.nextInt();
            scanner.nextLine(); 
            if (imageId == -1) {
                continuer = false;
                break;
            }

            // Rechercher l'image correspondant à l'ID
            Image imageSelectionnee = null;
            for (Categorie categorie : categories) {
                for (Image image : categorie.get_images()) {
                    if (image.get_id() == imageId) {
                        imageSelectionnee = image;
                        break;
                    }
                }
                if (imageSelectionnee != null) break;
            }

            if (imageSelectionnee != null) {
                boolean sousMenu = true;
                while (sousMenu) {
                    // Afficher les options pour l'image sélectionnée
                    System.out.println("\nOptions pour l'image : " + imageSelectionnee.get_titre());
                    System.out.println("1. Approuver l'image");
                    System.out.println("2. Modifier l'image");
                    System.out.println("3. Supprimer l'image");
                    System.out.println("4. Retourner à la sélection d'image");

                    System.out.print("Entrez votre choix : ");
                    int choix = scanner.nextInt();
                    scanner.nextLine(); // Consomme la nouvelle ligne

                    switch (choix) {
                        case 1-> {admin.validerImage(imageSelectionnee);   
                        		UserFileManager.sauvegarderCategories(categories);
                        }
                        case 2-> {imageSelectionnee.modifierImage();   
                				UserFileManager.sauvegarderCategories(categories);
                        }
                        case 3->{
	                        	admin.supprimerImage(imageSelectionnee);
	                        	UserFileManager.sauvegarderCategories(categories);
                        }
                        case 4->{
                            	sousMenu = false;
                            	break;
                        }
                        default->{
                            System.out.println("Choix invalide. Veuillez réessayer.");
                            break;
                        }
                    }
                }
            } else {
                System.out.println("Image avec ID " + imageId + " non trouvée. Veuillez réessayer.");
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
