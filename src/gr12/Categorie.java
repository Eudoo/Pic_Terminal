package gr12;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;



public class Categorie {

    //Les attribut
    private static int id_compteur = 0;
    private int id;
    private String nom_categorie;
    private String description;
    //Liste des images dans la catégorie
    public List<Image> images;
    public static List<Categorie> categories = new ArrayList<>();
    

    //Constructeur
        // Par defaut
        public Categorie() {
            this.id = ++id_compteur;
            this.nom_categorie = "Catégorie par défaut";
            this.description = "Description par défaut";
            this.images = new ArrayList<>(); // Liste vide
            Categorie.categories.add(this);
        }
        // Constructeur avec id et nom de la catégorie
        public Categorie(String nomCategorie) {
        	this.id = ++id_compteur;
            this.nom_categorie = nomCategorie;
            this.description = "Pas de description";
            this.images = new ArrayList<>();
            this.categories.add(this);
        }
        // Constructeur avec id, nom et description
        public Categorie(String nomCategorie, String Description) {
        	this.id = ++id_compteur;
            this.nom_categorie = nomCategorie;
            this.description = Description;
            this.images = new ArrayList<>();
            this.categories.add(this);
        }
        // Constructeur avec id, nom, description et images
        public Categorie(String nomCategorie, String Description, List<Image> Images) {
        	this.id = ++id_compteur;
            this.nom_categorie = nomCategorie;
            this.description = Description;
            this.images = Images; 
            this.categories.add(this);
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
        	return "Categorie: " +nom_categorie;
        }

    //Les Getters
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
    
    //Les Setters
        public void set_idCategorie(int idCategorie) {
            this.id = idCategorie;
        }
        public void set_nom_Categorie(String nomCategorie) {
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
                image.set_cat(this);
                System.out.println("- "+image.get_nomfichier()+" ajoutée à la catégorie : " + nom_categorie);
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
            System.out.println("\n*ID Catégorie: " + id);
            System.out.println(" Nom: " + nom_categorie);
            System.out.println(" Description: " + description);
            System.out.println(" Images dans cette catégorie:");
            if (images.isEmpty()) {
                System.out.println(" Images: Aucune image dans cette catégorie.");
            } else {
            	System.out.println(" Nombres d'images: " +images.size()+"\n");
                // Afficher chaque image dans la catégorie
                for (Image image : images) { 
                	image.afficher_propriete();
                }
            }
        }



}
