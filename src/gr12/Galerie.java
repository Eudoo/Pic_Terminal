package gr12;

import java.util.ArrayList;

public class Galerie {

	private int id_galerie;
	private String nom_galerie;
	private Utilisateur usergaleris;
	private ArrayList<Image> images;
	
	// Constructeur 
	public Galerie(String nom_galerie, Utilisateur usergaleris) {
		this.nom_galerie = nom_galerie;
		this.usergaleris = usergaleris;
		this.images = new ArrayList<>();
	}
	
	
	// Methodes
	public void ajouter_image(Image image) {
        if (!images.contains(image)) {
            images.add(image);
            System.out.println("Image ajoutée à la galerie: " + nom_galerie);
        } else {
            System.out.println("L'image est déjà présente dans cette galerie.");
        }
    }
	
	public void supprimerImage(Image image) {
        if (images.contains(image)) {
            images.remove(image);
            System.out.println("Image " + image.get_titre() + " supprimée de la galerie " + nom_galerie);
        } else {
            System.out.println("Image non trouvée dans cette galerie.");
        }
    }
	
	public void afficherImages() {
        if (images.isEmpty()) {
            System.out.println("Aucune image dans la galerie.");
        } else {
            System.out.println("Images dans la galerie " + nom_galerie + ":");
            for (Image image : images) {
                image.afficher_propriete();
            }
        }
    }
	
	// Getters
    public String getNomGalerie() {
        return nom_galerie;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

}
