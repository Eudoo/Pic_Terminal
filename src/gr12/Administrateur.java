package gr12;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Administrateur extends Utilisateur {

    public Administrateur(String nom, String email, String password) {
        super(nom, email, password);
        liste_user.remove(this); // Assurez-vous que this est bien une référence valide ici
    }

    public void creercategorie() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom de la nouvelle catégorie : ");
        String nom = scanner.nextLine();
        System.out.print("Entrez une description pour la catégorie : ");
        String description = scanner.nextLine();

        Categorie categorie = new Categorie(nom, description);
        if (!Categorie.categories.contains(categorie)) {
            Categorie.categories.add(categorie);
            System.out.println("\nNouvelle catégorie créée :");
            UserFileManager.sauvegarderCategories(Categorie.categories);
            UserFileManager.chargerCategories();
            categorie.afficher_categorie();            
        } else {
            System.out.println("Cette catégorie existe déjà.");
        }
    }
    
    
    

    public void ajouterImage(List<Categorie> categories, List<Image> images) {
        Scanner scanner = new Scanner(System.in);
        for (Categorie categorie : categories) {
            System.out.println("\n   ┌─Catégorie: " + categorie.get_nom_categorie());
            categorie.afficher_categorie();
        }
        System.out.print(" ►Entrez l'ID de l'image à ajouter ou -1 pour annuler : ");
        int imageId = scanner.nextInt();
        scanner.nextLine();  // vider le tampon
        if (imageId == -1) {
            return;
        }
        // Chercher l'image par ID
        Image image = null;
                   
        for (Image img : images) {
            if (img.get_id() == imageId) {
                image = img;
                break;
            }
        }

        if (image == null) {
            System.out.println("Image avec l'ID " + imageId + " non trouvée.");
            return;
        }

        // Afficher les catégories disponibles
        System.out.println("Catégories disponibles :");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println(i + ". " + categories.get(i).get_nom_categorie());
        }

        System.out.print("Choisissez une catégorie pour ajouter l'image : ");
        int choixCategorie = scanner.nextInt();

        if (choixCategorie < 0 || choixCategorie >= categories.size()) {
            System.out.println("Catégorie invalide.");
            return;
        }

        // Ajouter l'image à la catégorie choisie
        categories.get(choixCategorie).ajouter_image(image);
        UserFileManager.sauvegarderCategories(categories);
    }


    public void afficherToutesCategories() {
        System.out.println("\n -------- Toutes les catégories disponibles ---------");
        System.out.println("Nombre de catégories: " + Categorie.categories.size());
        if (Categorie.categories.isEmpty()) {
            System.out.println("\nAucune catégorie n'a été créée !");
        } else {
            for (Categorie categorie : Categorie.categories) {
                categorie.afficher_categorie();
            }
        }
    }

    public void modifierCategorie(Categorie categorie, String nouveauNom, String nouvelleDescription) {
        categorie.set_nom_Categorie(nouveauNom);
        categorie.set_description(nouvelleDescription);
        System.out.println("\nCatégorie modifiée : " + nouveauNom + "\nDescription : " + nouvelleDescription);
    }

    public boolean supprimerCategorie(Categorie categorie) {
        if (Categorie.categories.contains(categorie)) {
            Categorie.categories.remove(categorie);
            System.out.println("Catégorie supprimée : " + categorie.get_nom_categorie());
            return true;
        } else {
            System.out.println("Catégorie '" + categorie.get_nom_categorie() + "' introuvable.");
            return false;
        }
    }

    public void validerImage(Image image) {
        image.set_Statut(true);
        System.out.println("Image '" + image.get_titre() + "' validée.");
    }

    public void rejeterImage(Image image) {
        if (image.get_Statut()) {
            image.set_Statut(false);
            System.out.println("Image '" + image.get_titre() + "' rejetée.");
        } else {
            System.out.println("Image '" + image.get_titre() + "' est déjà rejetée.");
        }
    }

    public void supprimerImage(Image image, List<Categorie> categories) {
        
    	for (Categorie categorie : categories) {
            for (Image img : categorie.get_images()) {
                if (image == img) {
                    categorie.images.remove(image);
                    UserFileManager.sauvegarderCategories(categories);
                    System.out.println("Image supprimée avec succès.");
                    return;
                }
            }
        }
    }

    public void consulterUtilisateurs() {
        if (Utilisateur.liste_user.isEmpty()) {
            System.out.println(" Aucun utilisateur enregistré.");
        } else {
            System.out.println("\n ------- Liste des utilisateurs -------");
            for (Utilisateur utilisateur : liste_user) {
                System.out.println("  - " + utilisateur.get_nom() + " | Statut : " + (utilisateur.suspendu ? "Suspendu" : "Actif"));
            }
        }
    }

    public void ajouterUtilisateur() {
    	System.out.print("\n ------- nouvel utilistaeur ------- \n");
    	Scanner scanner = new Scanner(System.in);
        System.out.print(" Entrez le nom : ");
        String nom = scanner.nextLine();
        
        System.out.print(" Entrez l'email : ");
        String email = scanner.nextLine();
        
        System.out.print(" Entrez le mot de passe : ");
        String password = scanner.nextLine();

        if (verifier_email(email)) {
            System.out.println(" L'email existe déjà");
        } else {
            Utilisateur new_user = new Utilisateur(nom, email, password);
            System.out.println(" ------- Inscription réussie! -------");
            
        }

    }

    public void modifierUtilisateur(Utilisateur utilisateur, String nouveauNom, String nouveauPassword) {
        if (liste_user.contains(utilisateur)) {
            utilisateur.set_nom(nouveauNom);
            utilisateur.set_password(nouveauPassword);
            System.out.println("Informations mises à jour pour : '" + utilisateur.get_nom() + "'");
        } else {
            System.out.println("Utilisateur non trouvé.");
        }
    }

    public void suspendreUtilisateur(Utilisateur utilisateur) {
        if (!utilisateur.suspendu) {
            utilisateur.set_suspendu(true);
            System.out.println("Utilisateur suspendu : '" + utilisateur.get_nom() + "'");
        } else {
            System.out.println("Utilisateur déjà suspendu.");
        }
        UserFileManager.saveUsers();
        UserFileManager.loadUsers();
    }
    public void activerUtilisateur(Utilisateur utilisateur) {
        if (utilisateur.suspendu) {
            utilisateur.set_suspendu(false);
            System.out.println("Utilisateur réactiver : '" + utilisateur.get_nom() + "'");
        } else {
            System.out.println("Utilisateur déjà actif.");
        }
        UserFileManager.saveUsers();
        UserFileManager.loadUsers();
    }

    public void supprimerUtilisateur(Utilisateur utilisateur) {
        if (liste_user.contains(utilisateur)) {
            liste_user.remove(utilisateur);
            System.out.println("Utilisateur supprimé : '" + utilisateur.get_nom() + "'");
        } else {
            System.out.println("Utilisateur non trouvé.");
        }
    }

    public Utilisateur rechercherUtilisateur(String nom) {
        for (Utilisateur utilisateur : liste_user) {
            if (utilisateur.get_nom().equals(nom)) {
                return utilisateur;
            }
        }
        return null;
    }

    public void rechercher(String motCle) {
        List<Image> imagesRecherchees = new ArrayList<>();
        for (Image image : Image.imagescreer) {
            if (image.get_titre().toLowerCase().contains(motCle.toLowerCase()) || image.get_description().toLowerCase().contains(motCle.toLowerCase())) {
                imagesRecherchees.add(image);
            }
        }
        System.out.println("\n" + imagesRecherchees.size() + " images trouvées avec le mot-clé '" + motCle + "'.");
        for (Image image : imagesRecherchees) {
            image.afficher_propriete();
        }
    }

    public void filtrerParCategorie(String nomCategorie) {
        List<Image> imagesFiltrees = new ArrayList<>();
        for (Image image : Image.imagescreer) {
            if (image.get_categorie() != null && image.get_categorie().get_nom_categorie().equals(nomCategorie)) {
                imagesFiltrees.add(image);
            }
        }
        System.out.println("\n♂ " + imagesFiltrees.size() + " images trouvées dans la catégorie '" + nomCategorie + "'. ♂");
        for (Image img : imagesFiltrees) {
            img.afficher_propriete();
        }
    }
    
    
}

	
	



