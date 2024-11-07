package gr12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;
	private static int id = 1;
	private  int id_user;
	protected String nom;
	protected String email;
	protected String password;
	protected boolean suspendu;
	private ArrayList<Image> favoris = new ArrayList<>();
	private Galerie galerie;
	public static ArrayList<Utilisateur> liste_user = new ArrayList<>();
	private boolean like = false;
	
	// Constructeur
	public Utilisateur(String nom, String email, String password) {
		chargerDernierID(); 
		this.id_user = id++; 
		this.nom = nom;
		this.email = email;
		this.password = password; 
		this.suspendu = false;
		this.set_galerie(new Galerie(nom));
		liste_user.add(this);
		UserFileManager.saveUsers();
		sauvegarderDernierID();
		//if (id_user >= id_compteur) {
			//id_compteur = id_user + 1;
		//}
		
	}


	// Accesseurs
	
	public int get_id_user() {
		return id_user;
	}
	public String get_nom() {
		return this.nom;
	}
	public String get_email() {
		return this.email;
	}
	public String get_password() {
		return this.password;
	}
	public boolean get_suspendu() {
        return suspendu;
    }
	public ArrayList<Image> get_favoris() {
		return this.favoris;
	}
	public Galerie get_galerie() {
		return galerie;
	}

	// Mutateurs
	
	public void set_nom(String val) {
		this.nom = val;
		UserFileManager.saveUsers();
	}
	public void set_email(String val) {
		this.email = val;
		UserFileManager.saveUsers();
	}	
	public void set_password(String val) {
		this.password = val;
		UserFileManager.saveUsers();
	}
	public void set_suspendu(boolean suspendu) {
        this.suspendu = suspendu;
        UserFileManager.saveUsers();
    }
	
	public void set_favoris(ArrayList<Image> val) {
		this.favoris = val;
		UserFileManager.saveUsers();
	}
	public void set_galerie(Galerie galerie) {
		this.galerie = galerie;
		UserFileManager.saveUsers();
	}

	
	
	// Autres méthodes
	public static boolean  verifier_email(String emaill) {
		for (Utilisateur utilisateur : liste_user) {
            if (utilisateur.get_email().equals(emaill)) {
                return true; 
            }
        }
        return false;
	}
	
	public void modifierProfil() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Modifier le Profil ===");

        System.out.print("Nouveau nom (" + nom + ") : ");
        String nouveauNom = scanner.nextLine();
        if (!nouveauNom.isEmpty()) {
            this.nom = nouveauNom;
        }

        System.out.print("Nouvel email (" + email + ") : ");
        String nouvelEmail = scanner.nextLine();
        if (!nouvelEmail.isEmpty()) {
            this.email = nouvelEmail;
        }

        System.out.print("Nouveau mot de passe (laisser vide pour ne pas changer) : ");
        String nouveauMotDePasse = scanner.nextLine();
        if (!nouveauMotDePasse.isEmpty()) {
            this.password = nouveauMotDePasse;
        }

        System.out.println("Les informations de profil ont été mises à jour avec succès.");
        
        // Appel de la méthode pour sauvegarder les utilisateurs après modification
        UserFileManager.saveUsers();
    }
	
	/*
	public void ajouter_image(Image image, Galerie galerie) {
		galerie.ajouter_image(image);
		System.out.println("L'image " + image.titre + " a été ajoutée");
	}
	*/
	
	public void creerImage(List<Image> images) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom de l'image : ");
        String nom = scanner.nextLine();
        System.out.print("Entrez le titre de l'image : ");
        String titre = scanner.nextLine();
        System.out.print("Entrez une description de l'image : ");
        String description = scanner.nextLine();
        
        Image image = new Image(nom,titre,description);
       if (!images.contains(image)) {
        	images.add(image);
            System.out.println("\nNouvelle image créée :");
            UserFileManager.sauvegarderImages(images);
            UserFileManager.chargerImages();
            image.afficher_propriete();            
       	} else {
            System.out.println("Cette image existe déjà.");
        }
    }
	
	public void ajouter_favori(Image image) {
		favoris.add(image);
		UserFileManager.saveUsers();
		System.out.println("L'image " + image.get_titre() + " a été ajoutée à vos favoris ");
	}
	
	
	public void retirer_favori(Image image) {
		favoris.remove(image);
		UserFileManager.saveUsers();
		UserFileManager.loadUsers();
		System.out.println("L'image " + image.get_titre() + " a été retirée de vos favoris ");
	}
	
	
	public void telecharger(Image image) {
		image.set_nbr_telechargement(image.get_nbr_telechargement() +1);
		image.set_telecharger(true);
		galerie.ajouter_image(image);
		UserFileManager.sauvegarderImages(Image.imagescreer);
		UserFileManager.chargerImages();
		UserFileManager.saveUsers();
		System.out.println("L'image " + image.get_titre() + " a été téléchargée.");
		
	}
	
	
	public void liker(Image image) {
		if (!like) {
		image.set_like(image.get_like() +1);
		System.out.println("Vous avez liké cette image!!");
		like = true;
		UserFileManager.sauvegarderImages(Image.imagescreer);
		}
		else
			System.out.println(image.get_titre()+" déjà liké !");
			
	}
	
	public void afficher_infos() {
		//UserFileManager.loadUsers();
		System.out.println("\n -------------- Profil de "+ get_nom()+" --------------");
		System.out.println("  Id : "+ get_id_user());
		System.out.println("  Nom : "+ get_nom());
		System.out.println("  Email : "+ get_email());
		if(suspendu==false) {
			  System.out.println("  Statut : Actif");
		}
		else {
			System.out.println("  Statut : Suspendu");
		}
		
	}
	
	public void afficher_galerie() {
		System.out.println("\n  ------------- Galerie de "+get_nom()+" -------------");
		this.galerie.afficherImages();;
	}
	
	public static void sinscrire() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n┌──────── ֎ INSCRIPTION ֎ ────────");
        System.out.print("├Entrez votre nom : ");
        String nom = scanner.nextLine();
        
        System.out.print("├Entrez votre email : ");
        String email = scanner.nextLine();
        
        System.out.print("├Entrez votre mot de passe : ");
        String password = scanner.nextLine();

        if (verifier_email(email)) {
            System.out.println("└─── L'email existe déjà ──────────");
        } else {
        	
            Utilisateur new_user = new Utilisateur(nom, email, password);
            liste_user.add(new_user);
            UserFileManager.saveUsers();
            System.out.println("└───── Inscription réussie! ───────");
            
        }
        System.out.println("  ");
    }

    public static Utilisateur se_connecter() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n┌───── ֎ CONNEXION ֎ ────────────────────");
        System.out.print("├Entrez votre email : ");
        String email = scanner.nextLine();
        
        System.out.print("├Entrez votre mot de passe : ");
        String password = scanner.nextLine();

        UserFileManager.loadUsers();

        for (Utilisateur utilisateur : liste_user) {
            if (utilisateur.get_email().equals(email) && 
                utilisateur.get_password().equals(password)) {
            	System.out.println("└──── Connexion réussie!──────────────────\n");
                System.out.println("┌──────────┤ Bienvenue " + utilisateur.get_nom() + "! ├──────────");
                System.out.println("\n Infos de l'utilisateur : ");
                utilisateur.afficher_infos();
                return utilisateur;
            }
        }
        System.out.println("└── Email ou mot de passe incorrect ──────");
        return null;
    }
    
    public void setuser(boolean status) {
        this.suspendu = status;
        UserFileManager.saveUsers(); // Sauvegarde dans le fichier après la mise à jour
        System.out.println("Le statut de l'utilisateur " + nom + " a été mis à jour : " + (suspendu ? "Actif" : "Suspendu"));
        UserFileManager.loadUsers();
    }
    
    public static void chargerDernierID() {
        try (BufferedReader reader = new BufferedReader(new FileReader("iduser.txt"))) {
            String lastId = reader.readLine();
            if (lastId != null) {
                id = Integer.parseInt(lastId.trim());
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement de l'ID : " + e.getMessage());
        }
    }
    
    public static void sauvegarderDernierID() {
        try (FileWriter writer = new FileWriter("iduser.txt", false)) {
            writer.write(String.valueOf(id));
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde de l'ID : " + e.getMessage());
        }
    }
	
}
