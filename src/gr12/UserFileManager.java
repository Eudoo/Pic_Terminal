package gr12;

import java.io.*;
import java.util.ArrayList;

public class UserFileManager {
    private static final String USERS_FILE = "users.dat";
    
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
}