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
		images.add(image);
		System.out.println("Image " + image.get_titre() + " ajoutée a la galerie " + nom_galerie );
	}
	
	public void supprimer_image(Image image) {
		images.remove(image);
		System.out.println("Image " + image.get_titre() + " supprimer de la galerie " + nom_galerie );
	}
	
	public void afficher_galerie() {
		System.out.println("Galerie : " + nom_galerie);
		for (Image img : images) {
			img.afficher_propriete();
		}
	}

}
