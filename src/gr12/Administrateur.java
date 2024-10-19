package gr12;

import java.util.List;
import java.util.ArrayList;

public class Administrateur extends Utilisateur {
	
	public Administrateur(int idUser, String nom, String email, String password ) {
		super(idUser, nom, email, password );
	}
	public void creerCategorie(Categorie categorie, List<Categorie> categories ) {
		categories.add(categorie);
		System.out.println("Vous avez créé une nouvelle catégorie\nNom : " + categorie.getNomCategorie()+"\nDescription: "+categorie.getDescription());
		
	}
	
	public void modifierCategorie(Categorie categorie, String nNomCategorie, String nDescriptionCategorie ) {
		categorie.setNomCategorie(nNomCategorie);
		categorie.setDescription(nDescriptionCategorie);
		System.out.println("Une catégorie a été modifié en\nNom : " +nNomCategorie+ "\nDescription : "  +nDescriptionCategorie);
		
	}
	
	public boolean supprimerCategorie(Categorie categorie, List<Categorie> categories) {
	    if (categories.contains(categorie)) {
	        categories.remove(categorie);
	        System.out.println("Vous avez supprimé la catégorie " + categorie.getNomCategorie());
	        return true;  
	    } else {
	        System.out.println("Catégorie '" + categorie.getNomCategorie() + " introuvable.");
	        return false;  
	    }
	}

	public void validerImage(Image image, List<Image> images) {
		
            image.setStatut(true); 
            images.add(image);
            System.out.println("Image '" + image.getTitre() + "' validée.");
    }
	
	 public void rejeterImage(Image image) {
		 if (image.estValide()) {
	            image.setStatut(false); 
	            System.out.println("L'image '" + image.getTitre() + "' a été rejetée.");
	        } else {
	            System.out.println("Image '" + image.getTitre() + "' est déjà rejetée.");
	        }
	    }
	
	public void supprimerImage(Image image, List<Image> images) {
        images.remove(image);
        System.out.println("Vous avez supprimé l'image '" + image.getTitre() );
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
	                System.out.println("- " + utilisateur.getNom() + " | Statut : " 
	                                    + (utilisateur.estSuspendu() ? "Suspendu" : "Actif"));
	            }
	        }
	    }

	    public void ajouterUtilisateur(String nom, String password) {
	        Utilisateur utilisateur = new Utilisateur(nom, password);
	        if (!utilisateurs.contains(utilisateur)) {
	            utilisateurs.add(utilisateur);
	            System.out.println("L'utilisateur '" + utilisateur.getNom() + "' a été ajouté.");
	        } else {
	            System.out.println("Utilisateur déjà présent.");
	        }
	    }

	    public void modifierUtilisateur(Utilisateur utilisateur, String newNom, String newPassword) {
	        if (utilisateurs.contains(utilisateur)) {
	            utilisateur.setNom(newNom);
	            utilisateur.setPassword(newPassword);
	            System.out.println("Les informations de l'utilisateur '" + utilisateur.getNom() + "' ont été mises à jour.");
	        } else {
	            System.out.println("Utilisateur non trouvé.");
	        }
	    }

	    public void suspendreUtilisateur(Utilisateur utilisateur) {
	        if (!utilisateur.estSuspendu()) {
	            utilisateur.setSuspendu(true);
	            System.out.println("L'utilisateur '" + utilisateur.getNom() + "' a été suspendu.");
	        } else {
	            System.out.println("L'utilisateur est déjà suspendu.");
	        }
	    }

	    public void supprimerUtilisateur(Utilisateur utilisateur) {
	        if (utilisateurs.contains(utilisateur)) {
	            utilisateurs.remove(utilisateur);
	            System.out.println("L'utilisateur '" + utilisateur.getNom() + "' a été supprimé.");
	        } else {
	            System.out.println("Utilisateur non trouvé.");
	        }
	    }
	    
	    public Utilisateur rechercherUtilisateur(String nom) {
	        for (Utilisateur utilisateur : utilisateurs) {
	            if (utilisateur.getNom().equals(nom)) {
	                return utilisateur;
	            }
	        }
	        return null;  
	    }
	
	public void voirStatistiques(Statistique stats) {
		System.out.println("Nombre total d'images : " + stats.getNbreTotalImage());
        System.out.println("Nombre total de téléchargements : " + stats.getNbreTelechargement());
        
        System.out.println("Images populaires : ");
        for (Image img : stats.getImagesPopulaires()) {
            System.out.println(img.getTitre() + " - Likes : " + img.getLikes());
        }
        
        System.out.println("Images par catégorie : ");
        for (Map.Entry<Categorie, Integer> entry : stats.getImageParCategorie().entrySet()) {
            System.out.println(entry.getKey().getNomCategorie() + " : " + entry.getValue() + " images.");
        }
		
	}
	
	public List<Image> rechercher(String motCle, List<Image> images) {
	    List<Image> imagesRecherchees = new ArrayList<>();

	    for (Image image : images) {
	        if (image.getTitre().toLowerCase().contains(motCle.toLowerCase()) || 
	            image.getDescription().toLowerCase().contains(motCle.toLowerCase())) {
	        	imagesRecherchees.add(image);
	        }
	    }
	    System.out.println(imagesRecherchées.size() + " images trouvées avec le mot-clé '" + motCle + "'.");
	    return imagesRecherchées;
	}
	
	public List<Image> filtrage(Categorie categorie, List<Image> images) {
	    List<Image> imagesFiltrees = new ArrayList<>();

	    for (Image image : images) {
	        
	        if (image.getCategorie().getNomCategorie().equalsIgnoreCase(categorie.getNomCategorie())) {
	        	imagesFiltrees.add(image);
	        }
	    }
	    System.out.println(imagesFiltrées.size() + " images trouvées dans la catégorie '" + categorie.getNomCategorie() + "'.");
	    return imagesFiltrées;
	}
}}

