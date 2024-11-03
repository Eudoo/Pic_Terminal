package gr12;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserFileManager {
    private static final String USERS_FILE = "users.dat";
    private static final String FICHIER_IMAGES = "images.dat";
    private static final String FICHIER_CATEGORIES = "categories.dat";
    
    public static void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(USERS_FILE))) {
            oos.writeObject(Utilisateur.liste_user);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des utilisateurs: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public static void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(USERS_FILE))) {
            Utilisateur.liste_user = (ArrayList<Utilisateur>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Utilisateur.liste_user = new ArrayList<>();
        }
    }
    
 // Méthode pour sauvegarder les images
    public static void sauvegarderImages(List<Image> images) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHIER_IMAGES))) {
            oos.writeObject(images);
            System.out.println("Les images ont été sauvegardées avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des images : " + e.getMessage());
        }
    }
    
 // Méthode pour charger les images
    @SuppressWarnings("unchecked")
    public static List<Image> chargerImages() {
        List<Image> images = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHIER_IMAGES))) {
            images = (List<Image>) ois.readObject();
            System.out.println("Les images ont été chargées avec succès.");
        } catch (FileNotFoundException e) {
            System.out.println("Aucun fichier d'images trouvé, un nouveau sera créé.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement des images : " + e.getMessage());
        }
        return images;
    }

    
    
 // Méthode pour sauvegarder les catégories dans un fichier .dat
    public static void sauvegarderCategories(List<Categorie> categories) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHIER_CATEGORIES))) {
            oos.writeObject(categories);
            System.out.println("Les catégories ont été sauvegardées avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des catégories : " + e.getMessage());
        }
    }

    // Méthode pour charger les catégories depuis un fichier .dat
    @SuppressWarnings("unchecked")
	public static List<Categorie> chargerCategories() {
        List<Categorie> categories = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHIER_CATEGORIES))) {
            categories = (List<Categorie>)ois.readObject();
            System.out.println("Les catégories ont été chargées avec succès.");
        } catch (FileNotFoundException e) {
            System.out.println("Aucun fichier de catégories trouvé, un nouveau sera créé.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement des catégories : " + e.getMessage());
        }
        return categories;
    }
}