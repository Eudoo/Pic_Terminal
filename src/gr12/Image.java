
package gr12;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

public class Image {
	
    private  int id_image;
	private  String Nomfichier ;
	private String Titre;
	private Categorie catégorie;
	private  boolean Estpublic;
	private boolean Statut;
	private int likes;
	private  int nbre_Téléchargement;
	private boolean télécharger;
	

//constructeur par défaut
	public Image(){
		this.id_image = 0;
		 this.Nomfichier = "Unknown.Img";
		this.Titre = "Unknown";
		this.Estpublic = false;
		this.Statut = 0;
		this.likes = 0;
		this.nbre_Téléchargement = 0;
		this.télécharger = false;
		
		
	}
	
//constructeur avec  certaines valeurs
	public Image( String Nf, String Ttr, Categorie cat) {
		this.id_image = ;
		 this.Nomfichier = Nf;
		this.Titre = Ttr;
		this.Estpublic = false;
		this.Statut = 0;
		this.likes = 0;
		this.nbre_Téléchargement = 0;
		this.télécharger = false;
		this.catégorie = cat;
	}

	
//constructeur all
public Image (int idimage, String Nf, String Ttr, Categorie cat, boolean Estpublc,
		boolean Stat, int like, int nbre_te, boolean down) 
{
	this.id_image = idimage;
	 this.Nomfichier = Nf;
	this.Titre = Ttr;
	this.catégorie = cat;
	this.Estpublic = Estpublc;
	this.Statut = Stat;
	this.likes = like;
	this.nbre_Téléchargement = nbre_te;
	this.télécharger = down;
	
	
}

//Autres méthodes
//Afficher propriété

   public void afficher_propriété() {
	 System.out.println("id" +id_image);   
	 System.out.println("Nom du fichier:"+ Nomfichier );
	 System.out.println("Titre:"+ Titre);
	   System.out.println("Catégorie:"+ catégorie);
	if(Estpublic=false) {
		  System.out.println("Visibilité:Privé");
	}
	else {
		  System.out.println("Visibilité:Publique");}
	   //System.out.println("Statut:"+ Statut);
	   System.out.println("Nombres de likes:"+ likes);
	   System.out.println("nombre de téléchargement:"+nbre_Téléchargement);
  
   }



   /**les modifications de propriété possibles sont: le nom du fichier , sa catégorie et le titre**/
//Modification du nom
   public void modifier_nom(String Newname) {
	   Scanner scanner = new Scanner(System.in);
	   System.out.println("Veuillez entrer le nouveau nom:");
	  String Newname = scanner.nextLine();
	 this.Nomfichier=Newname;
	 scanner.close();
	  }
//modification titre   
   
   public void modifier_titre(String Newtitle) {
	   Scanner scanner = new Scanner(System.in);
	   System.out.println("Veuillez entrer le nouveau title:");
	  String Newtitle = scanner.nextLine();
	 this.Titre=Newtitle;
	 scanner.close();}
	
   
   //Modification de sa catégorie
   public void modifier_catégorie( List<Categorie> catégories) {
   System.out.println("Faite un choix parmis les différentes catégories:");
   for(int i = 0;i<catégories.size();i++){
	   System.out.println((i+1)+"."+categories.get(i).get_nom_categorie());
          };
   int choix =new Scanner(System.in).nextInt();
   if(choix > 0 && choix <= catégories.size()) {
    Categorie newcatégorie = catégories.get(choix - 1);
   this.catégorie = newcatégorie;
   System.out.println("l'image:" ,+ Nomfichier, "à changer de catégories avec succès");
   }
   else
	   System.out.println("catégorie introuvable");{
	     }
}

  // les getters
public String getnomfichier(){
 return Nomfichier;}

public  int getid(){
	 return id_image;}

public String getTitre(){
	 return Titre;}

public Categorie getcat(){
	 return catégorie;}

public boolean getvisibilité(){
		return Estpublic;}

public boolean getstat(){
	 return  Statut;}

public int getnbr_télé(){
	return nbre_Téléchargement;}
 
public int getlike(){
	return likes;}
public boolean get_télé1(){
	return télécharger;}
 



// les setters

    public void set_nomfichier(String val){
	   this.Nomfichier = val;}
	
    public void  set_id(int val){
	   this.id_image = val ;}
	
    public void set_Titre(String val){
		 this.Titre = val;}
	
    public void set_cat(Categorie val){
		 this.catégorie = val ;}
	
    public void set_visibilité(boolean val){
		  this.Estpublic = val;}
	
    public void set_stat(boolean val){
		  this.Statut = val;}
	
    public void set_nbr_télé(int val){
		this.nbre_Téléchargement= val;}
	 
	public void set_like(int val){
	     this.likes = val ;}
	
	public void get_télé(boolean val){
		this.télécharger= val;}
}