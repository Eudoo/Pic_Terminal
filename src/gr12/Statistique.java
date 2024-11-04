package gr12;
import java.lang.Integer;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;


public class Statistique {
	private int imagetotal;
	public static List<Categorie> categoriesst = new ArrayList<>();
	private int nbre_telechargement ;
	private int nbre_users;
	private List<List<Object>> imagepop = new ArrayList<>();
	private List<List<Object>>nbre_img_parcat = new ArrayList<>();
	
		
	//Constructeurs
		
		public Statistique() {
			this.imagetotal = Image.imagescreer.size();
			
		this.nbre_telechargement = nbre_telechargement();
			
			Statistique.categoriesst = Categorie.categories;
			this.setNbre_users(0);
			};
		
			
	//Autres méthodes
			public void afficher_statistique() {
				System.out.println("[[_Les Statistiques globals :_]]");
				System.out.println("Le nombre d'image total = " +imagetotal);
			    System.out.println("Les catégories présents :" +categoriesst);
				System.out.println("le nombre de téléchargement total :" +nbre_telechargement);
				System.out.println("||_||");
			};
			
			public int nbre_telechargement() {
			
				for (int i=0; i<Image.imagescreer.size();i++) {
					this.nbre_telechargement += Image.imagescreer.get(i).get_nbr_telechargement();
				}
				return nbre_telechargement;
			}	
			
		
		   public void image_populaire() {	
			   
			    // Remplit imagepop avec les titres et les likes sans doublons
			    for (int j = 0; j < Image.imagescreer.size(); j++) {
			        List<Object> row = new ArrayList<>();
			        row.add(Image.imagescreer.get(j).get_titre());
			        row.add(Image.imagescreer.get(j).get_like());

			        // Vérifie si une liste avec le même titre et likes existe déjà dans imagepop
			        boolean exists = false;
			        for (List<Object> existingRow : imagepop) {
			            String existingTitre = (String) existingRow.get(0);
			            Integer existingLikes = (Integer) existingRow.get(1);
			            String newTitre = (String) row.get(0);
			            Integer newLikes = (Integer) row.get(1);
			            if (existingTitre.equals(newTitre) && existingLikes.equals(newLikes)) {
			                exists = true;
			                break;
			            }}

			        // Ajoute la nouvelle ligne seulement s'il n'existe pas déjà dans imagepop
			        if (!exists) {
			            imagepop.add(row);
			        }}
			   
			
			 Collections.sort(imagepop,new Comparator<List<Object>>() {
					@Override public int compare(List<Object> row1 , List<Object> row2) {
						Integer likes1=(Integer) row1.get(1);
						Integer likes2 = (Integer) row2.get(1);
						return Integer.compare(likes2 , likes1);}
					
				});
			 System.out.println("||_Images populaires:_||");
			System.out.println(""+imagepop);
				// Affichage du résultat pour vérifier le tri	
				System.out.println("=========================================");
				System.out.println("| Titre                | Likes         |");
				System.out.println("=========================================");
				for (List<Object> roll : imagepop) {
					int i = 0;
				    String titre = (String) roll.get(0);
				    int likes = (int) roll.get(1);
				    i++;
				    if(i==5)
				    	break;
				    
				    // Affichage formaté avec padding pour aligner les colonnes
				    System.out.printf("| %-20s | %-12d \n", titre, likes);
				}
				System.out.println("=========================================");
				};
		
				
				public  void image_par_categorie() {
					
					  for (int j = 0; j < Categorie.categories.size(); j++) {
						  Categorie catp =Categorie.categories.get(j);
						  int number = 0;
						  for (int i = 0; i < catp.images.size(); i++) {
						  number =+ catp.images.get(i).get_nbr_telechargement();}
						  int recup = number;
						  List<Object> row = new ArrayList<>();
					        row.add(catp.get_nom_categorie());
					        row.add(recup);
					        nbre_img_parcat.add(row);
					        recup = 0;
						
					}
					  System.out.println("|_Affichage des images par catégorie:|");
					 
						// Affichage du résultat pour vérifier le tri	
						System.out.println("================================================================");
						System.out.println("| NomCategorie                | nbre de téléchargement         |");
						System.out.println("================================================================");
						for (List<Object> roll : nbre_img_parcat) {
						    String nom = (String) roll.get(0);
						    int tel = (int) roll.get(1);
						    
						    // Affichage formaté avec padding pour aligner les colonnes
						    System.out.printf("| %-20s | %-12d                  | \n", nom, tel);
						}
						System.out.println("===============================================================");
						}


				public int getNbre_users() {
					return nbre_users;
				}


				public void setNbre_users(int nbre_users) {
					this.nbre_users = nbre_users;
				}
}
