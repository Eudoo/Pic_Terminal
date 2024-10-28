package gr12;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;

public class Administrateur extends Utilisateur {
	
	
	
	public Administrateur( String nom, String email, String password ) {
		super( nom, email, password );
		liste_user.remove(this);
	}
	
	public void creer_Categorie(String nom, String Description) {
		Categorie categorie = new Categorie(nom,Description);
		if (!Categorie.categories.contains(categorie))
			Categorie.categories.add(categorie);
		System.out.println("\nVous avez créé une nouvelle catégorie");
		categorie.afficher_categorie();
		
	}
	
	public void ajouter_image(Image image, Categorie categorie) {
        categorie.ajouter_image(image);
    }
	
	public void afficher_toutes_categorie() {
		System.out.println("\n\t♂ Voici présenté toutes les categories ♂");
		System.out.println("Nombres de categories: " +Categorie.categories.size());
		if (Categorie.categories.isEmpty()) {
            System.out.println("\nAucune Catégorie n'a été crée !");
        } else {
        	for(Categorie categorie : Categorie.categories) {
        		categorie.afficher_categorie();
        	}
		}
	}
	
	public void modifier_Categorie(Categorie categorie, String nNomCategorie, String nDescriptionCategorie ) {
		categorie.set_nom_Categorie(nNomCategorie);
		categorie.set_description(nDescriptionCategorie);
		System.out.println("\nLa catégorie a été modifié en\nNom : " +nNomCategorie+ "\nDescription : "  +nDescriptionCategorie);
		
	}
	
	public boolean supprimerCategorie(Categorie categorie) {
	    if (Categorie.categories.contains(categorie)) {
	    	Categorie.categories.remove(categorie);
	        System.out.println("Vous avez supprimé la catégorie " + categorie.get_nom_categorie());
	        return true;  
	    } else {
	        System.out.println("Catégorie '" + categorie.get_nom_categorie() + " introuvable.");
	        return false;  
	    }
	}

	public void validerImage(Image image) {
            image.set_stat(true); 
            System.out.println("Image '" + image.get_titre() + "' validée.");
    }
	
	 public void rejeterImage(Image image) {
		 if (image.get_stat()) {
	            image.set_stat(false); 
	            System.out.println("L'image '" + image.get_titre() + "' a été rejetée.");
	        } else {
	            System.out.println("Image '" + image.get_titre() + "' est déjà rejetée.");
	        }
	    }
	
	public void supprimerImage(Image image) {
        Image.imagescreer.remove(image);
        System.out.println("Vous avez supprimé l'image '" + image.get_titre() );
    }

	public void consulterUtilisateurs() {
	        if (Utilisateur.liste_user.isEmpty()) {
	            System.out.println("Aucun utilisateur enregistré.");
	        } else {
	            System.out.println("\n ♂ Liste des utilisateurs :");
	            for (Utilisateur utilisateur : liste_user) {
	                System.out.println("- " + utilisateur.get_nom() + " | Statut : "  + (suspendu ? "Suspendu" : "Actif"));
	            }
	        }
	    }

	    public void ajouterUtilisateur(String nom,String email, String password) {
	        Utilisateur utilisateur = new Utilisateur( nom , email,  password);
	        if (!liste_user.contains(utilisateur)) {
	        	liste_user.add(utilisateur);
	            System.out.println("L'utilisateur '" + utilisateur.get_nom() + "' a été ajouté.");
	        } else {
	            System.out.println("Utilisateur déjà présent.");
	        }
	    }

	    public void modifierUtilisateur(Utilisateur utilisateur, String newNom, String newPassword) {
	        if (liste_user.contains(utilisateur)) {
	            utilisateur.set_nom(newNom);
	            utilisateur.set_password(newPassword);
	            System.out.println("Les informations de l'utilisateur '" + utilisateur.get_nom() + "' ont été mises à jour.");
	        } else {
	            System.out.println("Utilisateur non trouvé.");
	        }
	    }

	    public void suspendreUtilisateur(Utilisateur utilisateur) {
	        if (utilisateur.suspendu == false) {
	            utilisateur.set_suspendu(true);
	            System.out.println("L'utilisateur '" + utilisateur.get_nom() + "' a été suspendu.");
	        } else {
	            System.out.println("L'utilisateur est déjà suspendu.");
	        }
	    }

	    public void supprimerUtilisateur(Utilisateur utilisateur) {
	        if (utilisateurs.contains(utilisateur)) {
	            utilisateurs.remove(utilisateur);
	            System.out.println("L'utilisateur '" + utilisateur.get_nom() + "' a été supprimé.");
	        } else {
	            System.out.println("Utilisateur non trouvé.");
	        }
	    }
	    
	    public Utilisateur rechercherUtilisateur(String nom) {
	        for (Utilisateur utilisateur : utilisateurs) {
	            if (utilisateur.get_nom().equals(nom)) {
	                return utilisateur;
	            }
	        }
	        return null;  
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
	
	public void rechercher(String motCle) {
	    List<Image> imagesRecherchees = new ArrayList<>();

	    for (Image image : Image.imagescreer) {
	        if (image.get_titre().toLowerCase().contains(motCle.toLowerCase()) || 
	            image.get_description().toLowerCase().contains(motCle.toLowerCase())) {       
	        	imagesRecherchees.add(image);
	        }
	    }
	    System.out.println("\n"+imagesRecherchees.size() + " images trouvées avec le mot-clé '" + motCle + "'.");
        for (Image image : imagesRecherchees) {
            image.afficher_propriete();
        }
	}
	
	public void filtrage(String cate) {
	    List<Image> imagesFiltrees = new ArrayList<>();

	    for (Image image : Image.imagescreer) {
	        
	        if (image.get_categorie()!= null && image.get_categorie().get_nom_categorie().contains(cate)) {
	        	imagesFiltrees.add(image);
	        }
	    }
	    System.out.println("\n♂ "+imagesFiltrees.size() + " images trouvées dans la catégorie '" + cate+ "'. ♂");
	    for (Image img : imagesFiltrees) {
            img.afficher_propriete();
        }
	}
}




