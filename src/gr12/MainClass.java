package gr12;

import java.util.ArrayList;
import java.util.List;

public class MainClass {

	public static void main(String[] args) {
		
		Utilisateur user = new Utilisateur("Eudes", "eud@gmail.com", "salsa");
		Administrateur admin = new Administrateur("Eudes", "eud@gmail.com", "salsa");
		
		//Affichage des infos
		System.out.println("Nom Utilisateur: " + user.get_nom());
        System.out.println("Email Utilisateur: " + user.get_email());
        
        // Création de catégories
        Categorie categorie1 = new Categorie(1, "Nature");
        Categorie categorie2 = new Categorie(2, "Technologie", "Images sur la technologie");

        // Ajout des catégories dans une liste pour gestion
        List<Categorie> categories = new ArrayList<>();
        admin.creer_Categorie(categorie1, categories);
        admin.creer_Categorie(categorie2, categories);

        // Création de quelques images
        Image image1 = new Image("Montagne", "Photo d'une montagne", categorie1);
        Image image2 = new Image("Ordinateur", "Image d'un ordinateur", categorie2);
        Image image3 = new Image("Voiture", "Image d'une Mercedes Benz C400", categorie2);

        // Affichage des propriétés de l'image
        System.out.println("Propriétés de l'image 1:");
        image1.afficher_propriete();

        System.out.println("\nPropriétés de l'image 2:");
        image2.afficher_propriete();
        
        System.out.println("\nPropriétés de l'image 2:");
        image3.afficher_propriete();

        // Ajout d'images dans les catégories
        categorie1.ajouter_image(image1);
        categorie2.ajouter_image(image2);
        categorie2.ajouter_image(image3);

        // Création d'une galerie
        Galerie galerie = new Galerie("Ma Galerie", user);
        galerie.ajouter_image(image1);
        galerie.ajouter_image(image2);
        galerie.ajouter_image(image3);

        // Affichage des images dans la galerie
        galerie.afficherImages();

        // Test de recherche d'image par mot-clé
        List<Image> images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        List<Image> imagesTrouvees = admin.rechercher("Montagne", images);
        
        System.out.println("\nImages trouvées avec le mot-clé 'Montagne':");
        for (Image img : imagesTrouvees) {
            img.afficher_propriete();
        }

        // Test de filtrage des images par catégorie
        List<Image> imagesFiltrees = admin.filtrage(categorie1, images);
        System.out.println("\nImages filtrées dans la catégorie 'Nature':");
        for (Image img : imagesFiltrees) {
            img.afficher_propriete();
        }
        
        // Modification de la catégorie
        admin.modifier_Categorie(categorie1, "Nature et Paysages", "Photos de paysages naturels");
        categorie1.afficher_categorie();

	}

}
