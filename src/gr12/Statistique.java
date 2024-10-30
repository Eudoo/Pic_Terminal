package gr12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Statistique {
	private int nbre_total_image;
	private ArrayList<Image> image_populaire;
	//private ArrayList<Categorie> categories;
	private ArrayList<ArrayList<Object>> image_par_cat = new ArrayList<>();
	private int nbre_telechargement;
	
	
	// Constructeur
	public Statistique() {
		this.nbre_total_image = Image.idcompt;
		this.image_populaire = images_populaires(Image.imagescreer);
		this.nbre_telechargement = nbre_telechargement();
		this.image_par_cat = telechargements_categorie();	
	}
	
	
	public  ArrayList<ArrayList<Object>> telechargements_categorie() {
		ArrayList<ArrayList<Object>> img_par_cat = new ArrayList<>();
		for (Categorie categorie : Categorie.categories) {
			ArrayList<Object> tab = new ArrayList<>(); // Pour stocker le name de la categorie et le nbre de telechargement.
			int nbr = 0;
			tab.add(categorie.get_nom_categorie());
			for (Image image : categorie.images) {
				nbr += image.get_nbr_telechargement();
			}
			tab.add(nbr);
			img_par_cat.add(tab);
		}
		return img_par_cat;
	}
	
	
	
	public int nbre_telechargement() {
		int nbre_tlgmt = 0;
		for (Image image : Image.imagescreer) {
			nbre_tlgmt += image.get_nbr_telechargement();
		}
		return nbre_tlgmt;
	}
	
	
	public ArrayList<Image> images_populaires(List<Image> imagecreer) {
		Collections.sort(imagecreer, new Comparator<Image>() {
            @Override
            public int compare(Image image1, Image image2) {
                return Integer.compare(image2.get_like(), image1.get_like());
            }
        });

        ArrayList<Image> image_popu = new ArrayList<>();

        for (int i = 0; i < Math.min(5, imagecreer.size()); i++) { // Histoire de retourner les 5 premiers populaires
        	image_popu.add(imagecreer.get(i));
        }

        return image_popu;
	}
	
	

}

		// Hyacinthe doit ajouter à la méthode toString de Image, like.

// nbre_telechargement, images_populaires, telechargement_categorie