package gr12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Categorie implements Serializable {
	 private static final long serialVersionUID = 1L;
    // Attributs
    private static int idcompteur = 1;
    private int id;
    private String nom_categorie;
    private String description;
    public List<Image> images;
    public static List<Categorie> categories = new ArrayList<>(); // Chargement des catégories existantes au démarrage

    // Constructeur par défaut
    public Categorie() {
        chargercateDernierID();
        this.id = idcompteur++;
        this.nom_categorie = "Catégorie par défaut";
        this.description = "Description par défaut";
        this.images = new ArrayList<>();
        categories.add(this);
        sauvegardercateDernierID();
        UserFileManager.sauvegarderCategories(categories); // Sauvegarde des catégories
    }

    // Constructeur avec nom de catégorie
    public Categorie(String nomCategorie) {
        chargercateDernierID();
        this.id = idcompteur++;
        this.nom_categorie = nomCategorie;
        this.description = "Pas de description";
        this.images = new ArrayList<>();
        categories.add(this);
        sauvegardercateDernierID();
        UserFileManager.sauvegarderCategories(categories); // Sauvegarde des catégories
    }

    // Constructeur avec nom et description
    public Categorie(String nomCategorie, String Description) {
        chargercateDernierID();
        this.id = idcompteur++;
        this.nom_categorie = nomCategorie;
        this.description = Description;
        this.images = new ArrayList<>();
        categories.add(this);
        sauvegardercateDernierID();
        UserFileManager.sauvegarderCategories(categories); // Sauvegarde des catégories
    }

    // Constructeur avec nom, description et images
    public Categorie(String nomCategorie, String Description, List<Image> Images) {
        chargercateDernierID();
        this.id = idcompteur++;
        this.nom_categorie = nomCategorie;
        this.description = Description;
        this.images = Images;
        categories.add(this);
        sauvegardercateDernierID();
        UserFileManager.sauvegarderCategories(categories); // Sauvegarde des catégories
    }

    // Constructeur de copie
    public Categorie(Categorie original) {
        this.id = original.id;
        this.nom_categorie = original.nom_categorie;
        this.description = original.description;
        this.images = new ArrayList<>(original.images);
    }

    @Override
    public String toString() {
        return "Categorie: " + nom_categorie;
    }

    // Getters
    public int get_idcategorie() {
        return id;
    }

    public String get_nom_categorie() {
        return nom_categorie;
    }

    public String get_description() {
        return description;
    }

    public List<Image> get_images() {
        return images;
    }

    // Setters
    public void set_nom_Categorie(String nomCategorie) {
        this.nom_categorie = nomCategorie;
        UserFileManager.sauvegarderCategories(categories);
    }

    public void set_description(String Description) {
        this.description = Description;
        UserFileManager.sauvegarderCategories(categories);
    }

    public void set_images(List<Image> Images) {
        this.images = Images;
        UserFileManager.sauvegarderCategories(categories);
    }

    // Méthodes
    public void ajouter_image(Image image) {
        if (!images.contains(image)) {
            this.images.add(image);
            image.set_cat(this);
            System.out.println("- " + image.get_nomfichier() + " ajoutée à la catégorie : " + nom_categorie);
            UserFileManager.sauvegarderCategories(categories); // Sauvegarde des catégories
        } else {
            System.out.println("L'image est déjà présente dans cette catégorie.");
        }
    }

    public void supprimer_image(Image image) {
        if (images.contains(image)) {
            images.remove(image);
            System.out.println("Image supprimée de la catégorie : " + nom_categorie);
            UserFileManager.sauvegarderCategories(categories); // Sauvegarde des catégories
        } else {
            System.out.println("L'image n'existe pas dans cette catégorie.");
        }
    }

    public void modifier_categorie(String nouveauNom, String nouvelleDescription) {
        this.nom_categorie = nouveauNom;
        this.description = nouvelleDescription;
        System.out.println("Catégorie modifiée : " + nom_categorie);
        UserFileManager.sauvegarderCategories(categories); // Sauvegarde des catégories
    }

    public void afficher_categorie() {
        System.out.println("\n*ID Catégorie: " + id);
        System.out.println(" Nom: " + nom_categorie);
        System.out.println(" Description: " + description);
        System.out.println(" Images dans cette catégorie:");
        if (images.isEmpty()) {
            System.out.println(" Images: Aucune image dans cette catégorie.");
        } else {
            System.out.println(" Nombres d'images: " + images.size() + "\n");
            for (Image image : images) {
                image.afficher_propriete();
            }
        }
    }

    // Chargement du dernier ID depuis un fichier
    public static void chargercateDernierID() {
        try (BufferedReader reader = new BufferedReader(new FileReader("idcat.txt"))) {
            String lastId = reader.readLine();
            if (lastId != null) {
                idcompteur = Integer.parseInt(lastId.trim());
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement de l'ID : " + e.getMessage());
        }
    }

    // Sauvegarde du dernier ID dans un fichier
    public static void sauvegardercateDernierID() {
        try (FileWriter writer = new FileWriter("idcat.txt", false)) {
            writer.write(String.valueOf(idcompteur));
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde de l'ID : " + e.getMessage());
        }
    }
}
