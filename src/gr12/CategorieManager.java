package gr12;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieManager {
    // Attribut pour le nom du fichier de sauvegarde
    private static final String FICHIER_CATEGORIES = "categories.dat";

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
    public static List<Categorie> chargerCategories() {
        List<Categorie> categories = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHIER_CATEGORIES))) {
            categories = (List<Categorie>) ois.readObject();
            System.out.println("Les catégories ont été chargées avec succès.");
        } catch (FileNotFoundException e) {
            System.out.println("Aucun fichier de catégories trouvé, un nouveau sera créé.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement des catégories : " + e.getMessage());
        }
        return categories;
    }
}
