package gr12;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import gr12.Image;


public class Categorie {

    //Les attribut
    private int id_categorie;
    private String nom_categorie;
    private String description;
    //Liste des images dans la catégorie
    private List<Image> images;

    //Constructeur
        // Par defaut
        public Categorie() {
            this.id_categorie = 0;
            this.nom_categorie = "Catégorie par défaut";
            this.description = "Description par défaut";
            this.images = new ArrayList<>(); // Liste vide
        }
        // Constructeur avec id et nom de la catégorie
        public Categorie(int idCategorie, String nomCategorie) {
            this.id_categorie = idCategorie;
            this.nom_categorie = nomCategorie;
            this.description = "Pas de description";
            this.images = new ArrayList<>();
        }
        // Constructeur avec id, nom et description
        public Categorie(int idCategorie, String nomCategorie, String Description) {
            this.id_categorie = idCategorie;
            this.nom_categorie = nomCategorie;
            this.description = Description;
            this.images = new ArrayList<>();
        }
        // Constructeur avec id, nom, description et images
        public Categorie(int idCategorie, String nomCategorie, String Description, List<Image> Images) {
            this.id_categorie = idCategorie;
            this.nom_categorie = nomCategorie;
            this.description = Description;
            this.images = Images; 
        }
        // Constructeur de copie
        public Categorie(Categorie original) {
            this.id_categorie = original.id_categorie;
            this.nom_categorie = original.nom_categorie;
            this.description = original.description;
            this.images = new ArrayList<>(original.images); 
        }

    //Les Getters
        public int get_idcategorie() {
            return id_categorie;
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
    
    //Les Setters
        public void set_idCategorie(int idCategorie) {
            this.id_categorie = idCategorie;
        }
        public void set_nomCategorie(String nomCategorie) {
            this.nom_categorie = nomCategorie;
        }
        public void set_description(String Description) {
            this.description = Description;
        }
        public void set_images(List<Image> Images) {
            this.images = Images;
        }
    
    //Les Méthodes

        //Ajout d'image à une catégorie
        public void ajouter_image(Image image) {
            if (!images.contains(image)) {
                this.images.add(image);
                System.out.println("Image ajoutée à la catégorie : " + nom_categorie);
            } else {
                System.out.println("L'image est déjà présente dans cette catégorie.");
            }
        }
        //Suppression d'image d'une catégorie
        public void supprimer_image(Image image) {
            if (images.contains(image)) {
                images.remove(image);
                System.out.println("Image supprimée de la catégorie : " + nom_categorie);
            } else {
                System.out.println("L'image n'existe pas dans cette catégorie.");
            }
        }
        //Modifier la catégorie
        public void modifier_categorie(String nouveauNom, String nouvelleDescription) {
            this.nom_categorie = nouveauNom;
            this.description = nouvelleDescription;
            System.out.println("Catégorie modifiée : " + nom_categorie);
        }
        //Afficher la composition d'une catégorie
        public void afficher_categorie() {
            System.out.println("ID Catégorie: " + id_categorie);
            System.out.println("Nom: " + nom_categorie);
            System.out.println("Description: " + description);
            System.out.println("Images dans cette catégorie:");
            if (images.isEmpty()) {
                System.out.println("Aucune image dans cette catégorie.");
            } else {
                // Afficher chaque image dans la catégorie
                for (Image image : images) {
                    image.afficher_propriete();
                }
            }
        }



}
