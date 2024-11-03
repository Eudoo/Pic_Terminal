
package gr12;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

public class Image {
	
	public static int idcompt = 0;
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
	 

//constructeur par défaut
	public Image(){
		this.id_image = ++idcompt;
		this.Nomfichier = "Unknown.Img";
		this.titre = "Unknown";
		this.description = "No describ";
		this.Estpublic = true;
		this.Statut = false;            
		this.likes = 0;
		this.nbre_Telechargement = 0;
		this.telecharger = false;
		imagescreer.add(this);
		
	}
	public Image(String Nf, String Ttr) {
		this.id_image = ++idcompt;
		this.Nomfichier = Nf;
		this.titre = Ttr;
		this.description = "No describ";
		this.Estpublic = true;
		this.Statut = false;
		this.likes = 0;
		this.nbre_Telechargement = 0;
		this.telecharger = false;
		imagescreer.add(this);
	}
	
//constructeur all
	public Image (String Nf, String Ttr,String descrip, boolean Estpublc,
		boolean Stat, int like, int nbre_te, boolean down) {
		this.id_image = ++idcompt;
		this.Nomfichier = Nf;
		this.titre = Ttr;
		this.description = descrip;
		this.Estpublic = Estpublc;
		this.Statut = Stat;
		this.likes = like;
		this.nbre_Telechargement = nbre_te;
		this.telecharger = down;
		imagescreer.add(this);
		
	}

	@Override 
	public String toString() {
		return "Image: "+Nomfichier+" , Description: "+description+ (categorie!=null ? ", Categorie: "+categorie.get_nom_categorie():"");
	}
	
//Autres méthodes
//Afficher propriété

   public void afficher_propriete() {
	 System.out.println("-id image : " + id_image);   
	 System.out.println(" Nom du fichier : "+ Nomfichier+".img" );
	 System.out.println(" Titre : "+ titre);
	 System.out.println(" Catégorie : "+ categorie);
	if(Estpublic==false) {
		  System.out.println(" Visibilité : Privé");
	}
	else {
		System.out.println(" Visibilité : Publique");
	}
	if(Statut==false) {
		System.out.println(" Statut : Pas validée");
	}
	else {
		System.out.println(" Statut : validée");
	}
	    System.out.println(" Nombres de likes:"+ likes);
	    System.out.println(" nombre de téléchargement:"+nbre_Telechargement);
   }



   /**les modifications de propriété possibles sont: le nom du fichier , sa catégorie et le titre**/
//Modification du nom
   public void modifier_nom(String Newname) {
	   Scanner scanner = new Scanner(System.in);
	   System.out.println("Veuillez entrer le nouveau nom:");
	   Newname = scanner.nextLine();
	   this.Nomfichier=Newname;
	   scanner.close();
	  }
//modification titre   
   
   public void modifier_titre(String Newtitle) {
	   Scanner scanner = new Scanner(System.in);
	   System.out.println("Veuillez entrer le nouveau title:");
	   Newtitle = scanner.nextLine();
	   this.titre=Newtitle;
	   scanner.close();
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
   }
   else
	   System.out.println("catégorie introuvable");{}
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
	
	public boolean get_stat(){
		 return  Statut;}
	
	public int get_nbr_telechargement(){
		return nbre_Telechargement;}
	 
	public int get_like(){
		return likes;}
	
	public boolean get_télé(){
		return telecharger;}
	 



// les setters

    public void set_nomfichier(String val){
	   this.Nomfichier = val;}
	
    public void  set_id(int val){
	   this.id_image = val ;}
	
    public void set_Titre(String val){
		 this.titre = val;}
    
    public void setDescription(String description) {
        this.description = description;
    }
	
    public void set_cat(Categorie val){
		 this.categorie = val ;}
	
    public void set_visibilité(boolean val){
		  this.Estpublic = val;}
	
    public void set_stat(boolean val){
		  this.Statut = val;}
	
    public void set_nbr_telechargement(int val){
		this.nbre_Telechargement= val;}
	 
	public void set_like(int val){
	     this.likes = val ;}
	
	public void set_telecharger(boolean val){
		this.telecharger= val;}
}