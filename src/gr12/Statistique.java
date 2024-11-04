package gr12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Statistique {

	private int nbre_total_image;
	private List<List<Object>> image_populaire;
	private List<List<Object>> image_par_cat = new ArrayList<>();
	private int nbre_telechargement;
	
	
	// Constructeur
	public Statistique() {
		this.nbre_total_image = Image.imagescreer.size();
		this.image_populaire = images_populaires(Image.imagescreer);
		this.nbre_telechargement = nbre_telechargement();
		this.image_par_cat = telechargements_categorie();
	}
	
	
	// Accesseurs
	public int get_nbre_total_image() {
		return this.nbre_total_image;
	}
	
	
	public int get_nbre_telechargement() {
		return this.nbre_telechargement;
	}
	
	
	public List<List<Object>> get_image_par_cat() {
		return this.image_par_cat;
	}
	
	
	public List<List<Object>> get_image_populaire() {
		return this.image_populaire;
	}
	
	
	
	// Mutateurs
	public void set_nbre_total_image(int val) {
		this.nbre_total_image = val;
	}
	
	
	public void set_nbre_telechargement(int val) {
		this.nbre_telechargement = val;
	}
	
	
	public void set_image_par_cat(List<List<Object>> val) {
		this.image_par_cat = val;
	}
	
	
	public void set_image_populaire(List<List<Object>> val) {
		this.image_populaire = val;
	}
	
	
	public  List<List<Object>> telechargements_categorie() {
		List<List<Object>> img_par_cat = new ArrayList<>();
		for (Categorie categorie : Categorie.categories) {
			List<Object> tab = new ArrayList<>(); // Pour stocker le name de la categorie et le nbre de telechargement.
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
	
	
	
	public int nbre_telechargement() { // Nombre total de téléchargement sur la plateforme
		int nbre_tlgmt = 0;
		for (Image image : Image.imagescreer) {
			nbre_tlgmt += image.get_nbr_telechargement();
		}
		return nbre_tlgmt;
	}
	
	
	public List<List<Object>> images_populaires(List<Image> imagecreer) {
		List<List<Object>> imagepop = new ArrayList<>();
		for (Image image : Image.imagescreer) {
			List<Object> row = new ArrayList<>();
	        row.add(image.get_titre());
	        row.add(image.get_like());
	        
	        boolean exists = false;
	        for (List<Object> existingRow : imagepop) {
	            String existingTitre = (String) existingRow.get(0);
	            Integer existingLikes = (Integer) existingRow.get(1);
	            String newTitre = (String) row.get(0);
	            Integer newLikes = (Integer) row.get(1);
	            if (existingTitre.equals(newTitre) && existingLikes.equals(newLikes)) {
	                exists = true;
	                break;
	            }
	       }
	       
	     // Ajoute la nouvelle ligne seulement s'il n'existe pas déjà dans imagepop
	       if (!exists) {
	           imagepop.add(row);
	       }
		}
		
		Collections.sort(imagepop, new Comparator<List<Object>>() {
            @Override
            public int compare(List<Object> image1, List<Object> image2) {
            	Integer likes1=(Integer) image1.get(1);
				Integer likes2 = (Integer) image2.get(1);
                return Integer.compare(likes2, likes1);
            }
        });

		List<List<Object>> image_popu = new ArrayList<>();

        for (int i = 0; i < Math.min(5, imagepop.size()); i++) { // Histoire de retourner les 5 premiers populaires
        	image_popu.add(imagepop.get(i));
        }

        return image_popu;
	}
	
	
	
	public void afficher_statistique() {
		System.out.println("[[_____Les Statistiques globals :_____]]");
		System.out.println("Le nombre d'image total = " + nbre_total_image);
		System.out.println("le nombre de téléchargement total :" + nbre_telechargement);
		System.out.println("|_____Affichage des images par catégorie:____|");
		 
		
		System.out.println("================================================================");
		System.out.println("| NomCategorie                | nbre de téléchargement         |");
		System.out.println("================================================================");
		for (List<Object> row : image_par_cat) {
		    String nom = (String) row.get(0);
		    int nbr_telech = (int) row.get(1);
		    
		    System.out.printf("| %-20s | %-12d                  | \n", nom, nbr_telech);
		}
		System.out.println("===============================================================");
		
		
		System.out.println("||_____Images populaires:_____||");
		System.out.println("=========================================");
		System.out.println("| Titre                | Likes         |");
		System.out.println("=========================================");
		
		for (List<Object> row : image_populaire) {
			String titre = (String) row.get(0);
			int likes = (int) row.get(1);
			
			System.out.printf("| %-20s | %-12d \n", titre, likes);
		}
		System.out.println("=========================================");
		System.out.println("||___________________||");
			
	}
	
}
