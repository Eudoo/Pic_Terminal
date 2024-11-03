package gr12;

import java.util.List;
import java.util.ArrayList;

public class Administrateur extends Utilisateur {

    public Administrateur(String nom, String email, String password) {
        super(nom, email, password);
        liste_user.remove(this); // Assurez-vous que this est bien une référence valide ici
    }

    public void creerCategorie(String nom, String description) {
        Categorie categorie = new Categorie(nom, description);
        if (!Categorie.categories.contains(categorie)) {
            Categorie.categories.add(categorie);
            System.out.println("\nNouvelle catégorie créée :");
            categorie.afficher_categorie();
        } else {
            System.out.println("Cette catégorie existe déjà.");
        }
    }

    public void ajouterImage(Image image, Categorie categorie) {
        categorie.ajouter_image(image);
    }

    public void afficherToutesCategories() {
        System.out.println("\n\t♂ Toutes les catégories disponibles ♂");
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

    public void supprimerImage(Image image) {
        Image.imagescreer.remove(image);
        System.out.println("Image supprimée : '" + image.get_titre() + "'");
    }

    public void consulterUtilisateurs() {
        if (Utilisateur.liste_user.isEmpty()) {
            System.out.println("Aucun utilisateur enregistré.");
        } else {
            System.out.println("\n ♂ Liste des utilisateurs :");
            for (Utilisateur utilisateur : liste_user) {
                System.out.println("- " + utilisateur.get_nom() + " | Statut : " + (utilisateur.suspendu ? "Suspendu" : "Actif"));
            }
        }
    }

    public void ajouterUtilisateur(String nom, String email, String password) {
        Utilisateur utilisateur = new Utilisateur(nom, email, password);
        if (!liste_user.contains(utilisateur)) {
            liste_user.add(utilisateur);
            System.out.println("Utilisateur ajouté : '" + utilisateur.get_nom() + "'");
        } else {
            System.out.println("Utilisateur déjà présent.");
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
    
    /*
	public void voirStatistiques(Statistique stats) {
		System.out.println("Nombre total d'images : " + stats.getNbreTotalImage());
        System.out.println("Nombre total de téléchargements : " + stats.getNbreTelechargement());
        
        System.out.println("Images populaires : ");
        for (Image img : stats.getImagesPopulaires()) {
            System.out.println(img.get_titre() + " - Likes : " + img.get_like());
        }
        
        System.out.println("Images par catégorie : ");
        for (Map.Entry<Categorie, Integer> entry : stats.getImageParCategorie().entrySet()) {
            System.out.println(entry.getKey().get_nom_categorie() + " : " + entry.getValue() + " images.");
        }
		
	}*/
}

	
	



