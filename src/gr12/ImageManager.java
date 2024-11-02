package gr12;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ImageManager {

    // Sauvegarde la liste des images dans un fichier .dat
    public static void sauvegarderImages(List<Image> images, String nomFichier) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichier))) {
            oos.writeObject(images);
            System.out.println("Images sauvegardées dans le fichier " + nomFichier);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des images : " + e.getMessage());
        }
    }

    // Charge la liste des images à partir d'un fichier .dat
    @SuppressWarnings("unchecked")
    public static List<Image> chargerImages(String nomFichier) {
        List<Image> images = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier))) {
            images = (List<Image>) ois.readObject();
            System.out.println("Images chargées depuis le fichier " + nomFichier);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement des images : " + e.getMessage());
        }
        return images;
    }

    // Ajoute une image à la liste et sauvegarde
    public static void ajouterImage(Image image, String nomFichier) {
        List<Image> images = chargerImages(nomFichier);
        if (!images.contains(image)) {
            images.add(image);
            sauvegarderImages(images, nomFichier);
            System.out.println("Image ajoutée et sauvegardée.");
        } else {
            System.out.println("L'image existe déjà dans le fichier.");
        }
    }

    // Supprime une image de la liste et sauvegarde
    public static void supprimerImage(Image image, String nomFichier) {
        List<Image> images = chargerImages(nomFichier);
        if (images.remove(image)) {
            sauvegarderImages(images, nomFichier);
            System.out.println("Image supprimée et fichier mis à jour.");
        } else {
            System.out.println("Image non trouvée dans le fichier.");
        }
    }
}
