package gr12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utilisateur {
	private static int id_compteur = 0;
	protected  int id_user;
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
		this.id_user = id_compteur; 
		this.nom = nom;
		this.email = email;
		this.password = password; 
		this.suspendu = false;
		this.set_galerie(new Galerie(nom));
		liste_user.add(this);
		sauvegarderDansFichier();
		if (id_user >= id_compteur) {
			id_compteur = id_user + 1;
        }
	}

	public Utilisateur(int id, String nom2, String email2, String motdepasse, boolean suspendu2) {
		this.id_user = id_compteur; 
		this.nom = nom2;
		this.email = email2;
		this.password = motdepasse; 
		this.suspendu = suspendu2;
		this.set_galerie(new Galerie(nom2));
		if (id_user >= id_compteur) {
			id_compteur = id_user + 1;
        }
	}

	@Override
	public String toString() {
        return "ID: " + id_user + ", Nom: " + nom + ", Email: " + email + ", Statut: " + (suspendu ? "Suspendu" : "Actif");
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
	public void set_suspendu(boolean suspendu) {
        this.suspendu = suspendu;
    }
	
	public void set_favoris(ArrayList<Image> val) {
		this.favoris = val;
	}
	public void set_galerie(Galerie galerie) {
		this.galerie = galerie;
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
	
	
	public void telecharger(Image image) {
		image.set_nbr_telechargement(image.get_nbr_telechargement() +1);
		image.set_telecharger(true);
		galerie.ajouter_image(image);
		System.out.println("L'image " + image.get_titre() + " a été téléchargée.");
		
	}
	
	
	public void liker(Image image) {
		if (!like) {
		image.set_like(image.get_like() +1);
		System.out.println("Vous avez liké cette image!!");
		like = true;
		}
		else
			System.out.println(image.get_titre()+" déjà liké !");
			
	}
	
	public void afficher_infos() {
		System.out.println("\nId : "+ get_id_user());
		System.out.println("Nom : "+ get_nom());
		System.out.println("Email : "+ get_email());
		System.out.println("Suspendu : "+ get_suspendu());
	}
	
	 // Méthode pour vérifier si l'email existe déjà
    public static boolean emailExistant(String email) {
    	try (BufferedReader reader = new BufferedReader(new FileReader("utilisateurs.txt"))) {
	        String ligne;
	        while ((ligne = reader.readLine()) != null) {
	            String[] donnees = ligne.split(",");
	            if (donnees.length > 2 && donnees[2].trim().equalsIgnoreCase(email)) {
	                return true;
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
	    }
	    return false;
    }
    
    public static void inscription() {
        Scanner scanner = new Scanner(System.in);
        String nom, email, motDePasse;
        int id = id_compteur; // Utilisation de l'ID du compteur

        for (int i = 0; i < 3; i++) {
            System.out.println("Entrez le nom :");
            nom = scanner.nextLine().trim();
            System.out.println("Entrez l'email :");
            email = scanner.nextLine().trim();
            System.out.println("Entrez le mot de passe :");
            motDePasse = scanner.nextLine().trim();

            // Vérification des champs vides
            if (nom.isEmpty() || email.isEmpty() || motDePasse.isEmpty()) {
                System.out.println("Tous les champs sont obligatoires. Veuillez réessayer.");
                continue;
            }

            // Vérification de l'existence de l'email
            if (emailExistant(email)) {
                System.out.println("Cet email est déjà utilisé. Veuillez en choisir un autre.");
            } else {
                // Création d'un nouvel utilisateur
                Utilisateur nouvelUtilisateur = new Utilisateur(id, nom, email, motDePasse, false); // false par défaut pour 'suspendu'
                liste_user.add(nouvelUtilisateur);
                nouvelUtilisateur.sauvegarderDansFichier();

                // Incrémentation du compteur d'ID pour le prochain utilisateur
                id_compteur++;

                System.out.println("Inscription réussie pour : " + nom);
                return; // Fin de la méthode après une inscription réussie
            }
        }
        System.out.println("Nombre de tentatives dépassé. Inscription annulée.");
    }


    // Sauvegarder les informations dans un fichier texte
    public void sauvegarderDansFichier() {
        try (FileWriter writer = new FileWriter("utilisateurs.txt", true)) {
            writer.write(id_user + "," + nom + "," + email + "," + password + "," + (suspendu ? "Suspendu" : "Actif") + "\n");
            System.out.println("Utilisateur enregistré dans le fichier.");
        } catch (IOException e) {
            System.out.println("Erreur lors de l'enregistrement : " + e.getMessage());
        }
    }
    
 public static void chargerUtilisateursDepuisFichier() {
	    liste_user.clear(); // Effacer la liste existante
	    id_compteur = 1; // Réinitialiser l'idCounter

	    try (BufferedReader reader = new BufferedReader(new FileReader("utilisateurs.txt"))) {
	        String ligne;
	        while ((ligne = reader.readLine()) != null) {
	            String[] donnees = ligne.split(",");

	            // Vérifiez que la ligne contient suffisamment de données
	            if (donnees.length < 5) {
	                System.out.println("Ligne invalide dans le fichier : " + ligne);
	                continue; // Passe à la ligne suivante
	            }

	            try {
	                int id = Integer.parseInt(donnees[0].trim());
	                String nom = donnees[1].trim();
	                String email = donnees[2].trim();
	                String motDePasse = donnees[3].trim();
	                boolean suspendu = donnees[4].trim().equalsIgnoreCase("Suspendu");

	                Utilisateur utilisateur = new Utilisateur(id, nom, email, motDePasse, suspendu);
	                liste_user.add(utilisateur);
	                
	                // Mettez à jour le compteur si nécessaire
	                if (id >= id_compteur) {
	                    id_compteur = id + 1; // Assure que le compteur est toujours supérieur au dernier ID
	                }
	            } catch (NumberFormatException e) {
	                System.out.println("Erreur de format pour l'ID dans la ligne : " + ligne);
	            }
	        }
	        System.out.println("Utilisateurs chargés depuis le fichier.");
	    } catch (IOException e) {
	        System.out.println("Erreur lors du chargement des utilisateurs : " + e.getMessage());
	    }
	}

    // Afficher tous les utilisateurs
    public void afficherUtilisateurs() {
        if (liste_user.isEmpty()) {
            System.out.println("Aucun utilisateur enregistré.");
        } else {
            System.out.println("Liste des utilisateurs :");
            for (Utilisateur utilisateur : liste_user) {
                System.out.println("- ID: " + utilisateur.id_user + " | Nom: " + utilisateur.nom + " | Email: " + utilisateur.email + " | Statut : " + (utilisateur.suspendu ? "Suspendu" : "Actif"));
            }
        }
    }




	


	
}
