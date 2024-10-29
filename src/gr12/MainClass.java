package gr12;
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



