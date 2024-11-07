package gr12;

import java.io.Serializable;
import java.util.ArrayList;

public class Galerie implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nom_galerie;
	private ArrayList<Image> images;
	
	// Constructeur 
	public Galerie(String nom_galerie) {
		this.nom_galerie = nom_galerie;
		this.images = new ArrayList<>();
	}
	
	
	// Methodes
	public void ajouter_image(Image image) {
        if (!images.contains(image)) {
            images.add(image);
            System.out.println("\nImage ajoutée à la galerie: " + nom_galerie);
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
            System.out.println("\n  Aucune image dans la galerie.");
        } else {
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
