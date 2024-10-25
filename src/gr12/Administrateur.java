package gr12;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Administrateur extends Utilisateur {
	
	public Administrateur( String nom, String email, String password ) {
		super( nom, email, password );
	}
	
	public void creer_Categorie(Categorie categorie, List<Categorie> categories ) {
		categories.add(categorie);
		System.out.println("Vous avez créé une nouvelle catégorie\nNom : " + categorie.get_nom_categorie()+"\nDescription: "+categorie.get_description());
		
	}
	
	public void modifier_Categorie(Categorie categorie, String nNomCategorie, String nDescriptionCategorie ) {
		categorie.set_nom_Categorie(nNomCategorie);
		categorie.set_description(nDescriptionCategorie);
		System.out.println("Une catégorie a été modifié en\nNom : " +nNomCategorie+ "\nDescription : "  +nDescriptionCategorie);
		
	}
	
	public boolean supprimerCategorie(Categorie categorie, List<Categorie> categories) {
	    if (categories.contains(categorie)) {
	        categories.remove(categorie);
	        System.out.println("Vous avez supprimé la catégorie " + categorie.get_nom_categorie());
	        return true;  
	    } else {
	        System.out.println("Catégorie '" + categorie.get_nom_categorie() + " introuvable.");
	        return false;  
	    }
	}

	public void validerImage(Image image, List<Image> images) {
		
            image.set_stat(true); 
            images.add(image);
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
	
	public void supprimerImage(Image image, List<Image> images) {
        images.remove(image);
        System.out.println("Vous avez supprimé l'image '" + image.get_titre() );
    }
	
	public class GererUtilisateur {
	    private List<Utilisateur> utilisateurs;

	    public GererUtilisateur() {
	        this.utilisateurs = new ArrayList<>();
	    }

	    public void consulterUtilisateurs() {
	        if (utilisateurs.isEmpty()) {
	            System.out.println("Aucun utilisateur enregistré.");
	        } else {
	            System.out.println("Liste des utilisateurs :");
	            for (Utilisateur utilisateur : utilisateurs) {
	                System.out.println("- " + utilisateur.get_nom() + " | Statut : "  + (utilisateur.estSuspendu() ? "Suspendu" : "Actif"));
	            }
	        }
	    }

	    public void ajouterUtilisateur(String nom,String email, String password) {
	        Utilisateur utilisateur = new Utilisateur( nom , email,  password);
	        if (!utilisateurs.contains(utilisateur)) {
	            utilisateurs.add(utilisateur);
	            System.out.println("L'utilisateur '" + utilisateur.get_nom() + "' a été ajouté.");
	        } else {
	            System.out.println("Utilisateur déjà présent.");
	        }
	    }

	    public void modifierUtilisateur(Utilisateur utilisateur, String newNom, String newPassword) {
	        if (utilisateurs.contains(utilisateur)) {
	            utilisateur.set_nom(newNom);
	            utilisateur.set_password(newPassword);
	            System.out.println("Les informations de l'utilisateur '" + utilisateur.get_nom() + "' ont été mises à jour.");
	        } else {
	            System.out.println("Utilisateur non trouvé.");
	        }
	    }

	    public void suspendreUtilisateur(Utilisateur utilisateur) {
	        if (!utilisateur.estSuspendu()) {
	            utilisateur.setSuspendu(true);
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
	
	public List<Image> rechercher(String motCle, List<Image> images) {
	    List<Image> imagesRecherchees = new ArrayList<>();

	    for (Image image : images) {
	        if (image.get_titre().toLowerCase().contains(motCle.toLowerCase()) || 
	            image.get_description().toLowerCase().contains(motCle.toLowerCase())) {       
	        	imagesRecherchees.add(image);
	        }
	    }
	    System.out.println(imagesRecherchees.size() + " images trouvées avec le mot-clé '" + motCle + "'.");
	    return imagesRecherchees;
	}
	
	public List<Image> filtrage(Categorie categorie, List<Image> images) {
	    List<Image> imagesFiltrees = new ArrayList<>();

	    for (Image image : images) {
	        
	        if (image.get_categorie().get_nom_categorie().equalsIgnoreCase(categorie.get_nom_categorie())) {
	        	imagesFiltrees.add(image);
	        }
	    }
	    System.out.println(imagesFiltrees.size() + " images trouvées dans la catégorie '" + categorie.get_nom_categorie() + "'.");
	    return imagesFiltrees;
	}
}



