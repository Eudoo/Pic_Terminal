
package gr12;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Image implements Serializable {
	protected static final long serialVersionUID = 1L;
	private static int idcompt = 1;
	public static List<Image> imagescreer = new ArrayList<>();
    private int id_image;
	private String Nomfichier ;
	private String titre;
	private String description;
	private Categorie categorie = null;
	private boolean Estpublic;
	private boolean Statut;
	private int likes;
	private int nbre_Telechargement;
	private boolean telecharger;
	private List<String> utilisateursAyantLiker = new ArrayList<>();
	 

//constructeur par défaut
	public Image(){
		chargerimageDernierID();
		this.id_image = idcompt++;
		this.Nomfichier = "Unknown.Img";
		this.titre = "Unknown";
		this.description = "Pas de description";
		this.Estpublic = true;
		this.set_Statut(false);            
		this.likes = 0;
		this.nbre_Telechargement = 0;
		this.telecharger = false;
		this.utilisateursAyantLiker = new ArrayList<>();
		imagescreer.add(this);
		sauvegarderimageDernierID();
		UserFileManager.sauvegarderImages(imagescreer);
		
		
	}
	public Image(String Nf, String Ttr) {
		chargerimageDernierID();
		this.id_image = idcompt++;
		this.Nomfichier = Nf;
		this.titre = Ttr;
		this.description = "No describ";
		this.Estpublic = true;
		this.set_Statut(false);
		this.likes = 0;
		this.nbre_Telechargement = 0;
		this.telecharger = false;
		this.utilisateursAyantLiker = new ArrayList<>();
		imagescreer.add(this);
		sauvegarderimageDernierID();
		UserFileManager.sauvegarderImages(imagescreer);
	}
	
	public Image(String Nf, String Ttr, String Description) {
		chargerimageDernierID();
		this.id_image = idcompt++;
		this.Nomfichier = Nf;
		this.titre = Ttr;
		this.description = Description;
		this.Estpublic = true;
		this.set_Statut(false);
		this.likes = 0;
		this.nbre_Telechargement = 0;
		this.telecharger = false;
		this.utilisateursAyantLiker = new ArrayList<>();
		imagescreer.add(this);
		sauvegarderimageDernierID();
		UserFileManager.sauvegarderImages(imagescreer);
	}
	
//constructeur all
	public Image (String Nf, String Ttr,String descrip, boolean Estpublc,
		boolean Stat, int like, int nbre_te, boolean down) {
		chargerimageDernierID();
		this.id_image = idcompt++;
		this.Nomfichier = Nf;
		this.titre = Ttr;
		this.description = descrip;
		this.Estpublic = Estpublc;
		this.set_Statut(Stat);
		this.likes = like;
		this.nbre_Telechargement = nbre_te;
		this.telecharger = down;
		this.utilisateursAyantLiker = new ArrayList<>();
		imagescreer.add(this);
		sauvegarderimageDernierID();
		UserFileManager.sauvegarderImages(imagescreer);
		
	}

	@Override 
	public String toString() {
		return "Image: "+Nomfichier+" , Description: "+description+ (categorie!=null ? ", Categorie: "+categorie.get_nom_categorie():"");
	}
	
//Autres méthodes
//Afficher propriété

   public void afficher_propriete() {
	 System.out.println("-id image : " + id_image);   
	 System.out.println(" Nom du fichier : "+ Nomfichier);
	 System.out.println(" Titre : "+ titre);
	 System.out.println(" Description : "+ description);
	 if (categorie != null) {
	        System.out.println(" Catégorie : " + categorie.get_nom_categorie());
	    } else {
	        System.out.println(" Catégorie : Aucune");
	    }
	if(Estpublic==false) {
		  System.out.println(" Visibilité : Privé");
	}
	else {
		System.out.println(" Visibilité : Publique");
	}
	if(get_Statut()==false) {
		System.out.println(" Statut : Pas validée");
	}
	else {
		System.out.println(" Statut : validée");
	}
	System.out.println(" Nombres de likes:"+ likes);
	System.out.println(" nombre de téléchargement:"+nbre_Telechargement);
	System.out.println();
   }



   /**les modifications de propriété possibles sont: le nom du fichier , sa catégorie et le titre**/
//Modification du nom
   public void modifier_nom(String Newname) {
	   this.Nomfichier=Newname;
	   UserFileManager.sauvegarderImages(imagescreer);
	  }
//modification titre   
   
   public void modifier_titre(String Newtitle) {
	   this.titre= Newtitle;
	   UserFileManager.sauvegarderImages(imagescreer);
	   }
	
   
   //Modification de sa catégorie
   public void modifier_catégorie( List<Categorie> categories) {
   System.out.println("Faite un choix parmis les différentes catégories:");
   for(int i = 0;i<categories.size();i++){
	   System.out.println((i+1)+"."+categories.get(i).get_nom_categorie());
          };
   int choix =new Scanner(System.in).nextInt();
   if(choix > 0 && choix <= categories.size()) {
    Categorie newcatégorie = categories.get(choix - 1);
   this.categorie = newcatégorie;
   System.out.println("l'image:" + Nomfichier + "à changer de catégories avec succès");
   UserFileManager.sauvegarderImages(imagescreer);
   }
   else
	   System.out.println("catégorie introuvable");{}
   }
   
   public void modifierImage() {
       Scanner scanner = new Scanner(System.in);

       System.out.println("=== Modifier le Image ===");

       System.out.print("Nouveau nom (" + Nomfichier + ") : ");
       String nouveauNom = scanner.nextLine();
       if (!nouveauNom.isEmpty()) {
           this.Nomfichier = nouveauNom;
       }

       System.out.print("Nouvel tritre (" + titre + ") : ");
       String nouveltitre = scanner.nextLine();
       if (!nouveltitre.isEmpty()) {
           this.titre = nouveltitre;
       }

       System.out.print("Description (laisser vide pour ne pas changer) : ");
       String Description = scanner.nextLine();
       if (!Description.isEmpty()) {
           this.description = Description;
       }

       System.out.println("Les informations de l'image "+Nomfichier+" ont été mises à jour avec succès.");
       
       // Appel de la méthode pour sauvegarder les utilisateurs après modification
       UserFileManager.sauvegarderImages(imagescreer);;
   }
   
   public static void chargerimageDernierID() {
       try (BufferedReader reader = new BufferedReader(new FileReader("idimage.txt"))) {
           String lastId = reader.readLine();
           if (lastId != null) {
               idcompt = Integer.parseInt(lastId.trim());
           }
       } catch (IOException e) {
           System.out.println("Erreur lors du chargement de l'ID : " + e.getMessage());
       }
   }
   
   public static void sauvegarderimageDernierID() {
       try (FileWriter writer = new FileWriter("idimage.txt", false)) {
           writer.write(String.valueOf(idcompt));
       } catch (IOException e) {
           System.out.println("Erreur lors de la sauvegarde de l'ID : " + e.getMessage());
       }
   }
   
   public boolean liker(String emailUtilisateur) {
	    // Vérifie si la liste des utilisateurs ayant liké est initialisée
	    if (utilisateursAyantLiker == null) {
	        utilisateursAyantLiker = new ArrayList<>();
	    }

	    // Vérifie si l'utilisateur a déjà liké l'image
	    if (!utilisateursAyantLiker.contains(emailUtilisateur)) {
	        this.likes++;  // Incrémente le nombre de likes
	        utilisateursAyantLiker.add(emailUtilisateur);  // Ajoute l'email de l'utilisateur à la liste
	        UserFileManager.sauvegarderImages(imagescreer);
	        return true;
	    } else {
	        System.out.println("Vous avez déjà liké cette image.");
	        return false;  // Si l'utilisateur a déjà liké, on retourne false
	    }
	}

	
   
	 // les getters
	public String get_nomfichier(){
	 return Nomfichier;}
	
	public  int get_id(){
		 return id_image;}
	
	public String get_titre(){
		 return titre;}
	
	public String get_description() {
	    return description;
	}
	
	public Categorie get_categorie(){
		 return categorie;}
	
	public boolean get_visibilite(){
			return Estpublic;}
	

	public boolean get_Statut() {
		return Statut;
	}
	
	public int get_nbr_telechargement(){
		return nbre_Telechargement;}
	 
	public int get_like(){
		return likes;}
	
	public boolean get_télé(){
		return telecharger;}
	 



// les setters

    public void set_nomfichier(String val){
	   this.Nomfichier = val;
	   UserFileManager.sauvegarderImages(imagescreer);
    }
	
    public void set_Titre(String val){
		 this.titre = val;
		 UserFileManager.sauvegarderImages(imagescreer);
    }
    
    public void setDescription(String description) {
        this.description = description;
        UserFileManager.sauvegarderImages(imagescreer);
    }
	
    public void set_cat(Categorie val){
		 this.categorie = val ;
		 UserFileManager.sauvegarderImages(imagescreer);
    }
	
    public void set_visibilité(boolean val){
		  this.Estpublic = val;
		  UserFileManager.sauvegarderImages(imagescreer);
    }
	
    public void set_nbr_telechargement(int val){
		this.nbre_Telechargement= val;
		UserFileManager.sauvegarderImages(imagescreer);
    }
	 
	public void set_like(int val){
	     this.likes = val ;
	     UserFileManager.sauvegarderImages(imagescreer);
	}
	
	public void set_telecharger(boolean val){
		this.telecharger= val;
		UserFileManager.sauvegarderImages(imagescreer);
	}
	
	public void set_Statut(boolean statut) {
		Statut = statut;
		UserFileManager.sauvegarderImages(imagescreer);
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    Image image = (Image) obj;
	    return id_image == image.id_image; // Comparaison par ID unique
	}

	@Override
	public int hashCode() {
	    return Integer.hashCode(id_image); // Utilise l'ID unique pour le hashage
	}
	


}
