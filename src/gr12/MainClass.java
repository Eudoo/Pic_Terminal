package gr12;

 ///  ########     1er MAIN    ########
/*
import java.util.List;

public class MainClass {

	public static void main(String[] args) {
		
		Utilisateur Paul = new Utilisateur("Eudes", "eud@gmail.com", "salsa");
		Administrateur admin = new Administrateur("Eudes", "eud@gmail.com", "salsa");
		admin.suspendreUtilisateur(Paul);
		admin.consulterUtilisateurs();
		Paul.afficher_infos();
		
        // Création de catégories
        Categorie categorie1 = new Categorie("Nature");
        Categorie categorie2 = new Categorie("Technologie", "Images sur la technologie");
        
        
        // Ajout des catégories dans une liste pour gestion
        
        admin.creer_Categorie("Fruits", "les fruits des arbres");
        admin.creer_Categorie("Aliments", "les choses mangeables");
        admin.afficher_toutes_categorie();

        // Création de quelques images
        Image image1 = new Image("Montagne", "Photo d'une montagne");
        Image image2 = new Image("Ordinateur", "Image d'un ordinateur");
        Image image3 = new Image("Voiture", "Image d'une Mercedes Benz C400");
		
        //Affichage des propriétés de l'image
        System.out.println("Propriétés de l'image 1:");
        image1.afficher_propriete();

        System.out.println("\nPropriétés de l'image 2:");
        image2.afficher_propriete();
        
        System.out.println("\nPropriétés de l'image 2:");
        image3.afficher_propriete();

        // Ajout d'images dans les catégories
        admin.ajouter_image(image1,categorie1);
        admin.ajouter_image(image2,categorie1);
        //categorie2.ajouter_image(image2);
        //categorie2.ajouter_image(image3);
        admin.validerImage(image1);
        categorie1.afficher_categorie();
        categorie2.afficher_categorie();

        // Création d'une galerie
        
        Paul.get_galerie().ajouter_image(image1);
     // Affichage des images dans la galerie
        
        Paul.telecharger(image1);
        Paul.liker(image1);
        image1.afficher_propriete();
        Paul.get_galerie().afficherImages();

        // Test de recherche d'image par mot-clé
        admin.rechercher("ord");
        
        //Test de filtrage des images par catégorie
        admin.filtrage("Natu");
       
        // Modification de la catégorie
        admin.modifier_Categorie(categorie1, "Nature et Paysages", "Photos de paysages naturels");
        categorie1.afficher_categorie();
        admin.afficher_toutes_categorie();
        admin.ajouterUtilisateur("Olade","Olade@gmail.com","Ok" );
        admin.consulterUtilisateurs();
        image1.modifier_catégorie(Categorie.categories);
	}
}
*/


///  ########     2eme MAIN    ########
/*
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        // Charger les utilisateurs au démarrage
        UserFileManager.loadUsers();
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Inscription\n2. Connexion");
        System.out.print("Veuillez entrer un chiffre pour faire un choix: ");
        int choix = scanner.nextInt();
        scanner.nextLine(); // Consommer la ligne restante

        if (choix == 1) {
            Utilisateur.sinscrire();
        } else if (choix == 2) {
            Utilisateur utilisateur = Utilisateur.se_connecter();
            if (utilisateur != null) {
                // Actions possibles après connexion réussie
                utilisateur.afficher_infos();
            }
        }

        // Sauvegarder les utilisateurs avant de fermer
        UserFileManager.saveUsers();
    }
}

*/


///  ########     3eme MAIN    ########
/*
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        // Charger les catégories existantes au démarrage (si elles existent)
        List<Categorie> categories = CategorieManager.chargerCategories();
        System.out.println("Catégories chargées au démarrage:");
        for (Categorie cat : categories) {
            cat.afficher_categorie();
        }

        // Création de nouvelles catégories
        System.out.println("\nCréation de nouvelles catégories:");
        Categorie cat1 = new Categorie("Nature", "Photos de paysages et de la nature");
        Categorie cat2 = new Categorie("Technologie", "Images de gadgets et d'innovations");
        Categorie cat3 = new Categorie("Art", "Illustrations et œuvres d'art");

        // Sauvegarde des nouvelles catégories après création
        CategorieManager.sauvegarderCategories(Categorie.categories);

        // Affichage des catégories après ajout
        System.out.println("\nCatégories après ajout:");
        for (Categorie cat : Categorie.categories) {
            cat.afficher_categorie();
        }

        // Suppression d'une catégorie
        System.out.println("\nSuppression de la catégorie 'Technologie':");
        cat2.supprimer_image(new Image()); // Exemple de suppression d'une image (à remplacer avec une image existante dans la catégorie)

        // Sauvegarde des catégories après suppression
        CategorieManager.sauvegarderCategories(Categorie.categories);

        // Affichage des catégories après suppression
        System.out.println("\nCatégories après suppression:");
        for (Categorie cat : Categorie.categories) {
            cat.afficher_categorie();
        }

        // Vérification du chargement des catégories depuis le fichier
        System.out.println("\nRechargement des catégories depuis le fichier:");
        List<Categorie> categoriesRecharges = CategorieManager.chargerCategories();
        for (Categorie cat : categoriesRecharges) {
            cat.afficher_categorie();
        }
    }
}
*/



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Création de quelques catégories pour les tests
        List<Categorie> categories = new ArrayList<>();
        categories.add(new Categorie("Nature"));
        categories.add(new Categorie("Technologie"));
        categories.add(new Categorie("Art"));

        // Création d'images avec différents constructeurs
        Image img1 = new Image();
        Image img2 = new Image("beach.jpg", "Plage");
        Image img3 = new Image("mountain.jpg", "Montagne", "Image d'une montagne", true, true, 25, 10, true);

        // Affichage des propriétés initiales
        System.out.println("Affichage des images créées :");
        img1.afficher_propriete();
        img2.afficher_propriete();
        img3.afficher_propriete();

        // Modification des propriétés de l'image 1
        System.out.println("\nModification des propriétés de l'image 1 :");
        System.out.print("Entrez un nouveau nom de fichier pour l'image 1 : ");
        String newName = scanner.nextLine();
        img1.modifier_nom(newName);

        System.out.print("Entrez un nouveau titre pour l'image 1 : ");
        String newTitle = scanner.nextLine();
        img1.modifier_titre(newTitle);

        // Modification de la catégorie de l'image 2
        System.out.println("\nModification de la catégorie de l'image 2 :");
        img2.modifier_catégorie(categories);

        // Affichage des propriétés après modification
        System.out.println("\nPropriétés après modification :");
        img1.afficher_propriete();
        img2.afficher_propriete();
        img3.afficher_propriete();

        // Ajout de likes et téléchargements
        System.out.println("\nMise à jour des statistiques de l'image 3 :");
        img3.set_like(img3.get_like() + 5);
        img3.set_nbr_telechargement(img3.get_nbr_telechargement() + 3);
        img3.afficher_propriete();

        scanner.close();
    }
}


