package gr12;

import java.util.ArrayList;

public class Utilisateur {
	private static int id_precedent = 0;
	private int id_user;
	private String nom;
	private String email;
	private String password;
	private boolean suspendu;
	private ArrayList<Image> favoris = new ArrayList<>();

	
	
	// Constructeur
	public Utilisateur(String nom, String email, String password) {
		this.id_user = ++id_precedent; ;
		this.nom = nom;
		this.email = email;
		this.password = password; 
		this.suspendu = false;
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
	
	public boolean estSuspendu() {
        return suspendu;
    }

	
	
	public ArrayList<Image> get_favoris() {
		return this.favoris;
	}

	
	
	// Mutateurs
	public void set_id_user(int val) {
		this.id_user = val;
	}
	
	
	public void set_nom(String val) {
		this.nom = val;
	}
	
	
	public void set_email(String val) {
		this.email = val;
	}
	
		
	public void set_password(String val) {
		this.password = val;
	}
	
	public void setSuspendu(boolean suspendu) {
        this.suspendu = suspendu;
    }
	
	public void set_favoris(ArrayList<Image> val) {
		this.favoris = val;
	}
	
	
	/*
	// Autres méthodes
	public static boolean  verifier_email(String emaill) {
		for (Utilisateur utilisateur : Administrateur.utilisateurs) {
            if (utilisateur.get_email().equals(emaill)) {
                return true; 
            }
        }
        return false;
	}
	
	
	public static void sinscrire(String nom, String email, String password) {
		if (verifier_email(email)) {
			System.out.println("L'email existe déjà");
		}
		else {
			Utilisateur new_user = new Utilisateur(nom, email, password);
			Administrateur.utilisateurs.add(new_user);
			System.out.println("Inscription reussie");
		}
	}
	
	
	public static void se_connecter(String email, String password) {
		for (Utilisateur utilisateur : Administrateur.utilisateurs) {
            if (utilisateur.get_email().equals(email) && utilisateur.get_password().equals(password)) {
                System.out.println("Vous êtes connectés");
            }  
            
            System.out.println("Mot de passe ou email incorrect ");
        }
	}*/
	
	/*
	public void ajouter_image(Image image, Galerie galerie) {
		galerie.ajouter_image(image);
		System.out.println("L'image " + image.titre + " a été ajoutée");
	}
	*/
	
	public void ajouter_favori(Image image) {
		favoris.add(image);
		System.out.println("L'image " + image.get_titre() + " a été ajoutée à vos favoris ");
	}
	
	
	public void retirer_favori(Image image) {
		favoris.remove(image);
		System.out.println("L'image " + image.get_titre() + " a été retirée de vos favoris ");
	}
	
	
	public void telecharger(Image image, Galerie galerie) {
		image.set_nbr_telechargement(image.get_nbr_telechargement() +1);
		image.set_télé(true);
		galerie.ajouter_image(image);
		System.out.println("L'image " + image.get_titre() + " a été téléchargée.");
		
	}
	
	
	public void liker(Image image) {
		image.set_like(image.get_like() +1);
		System.out.println("Vous avez liké cette image ^-^ ");
	}

}
