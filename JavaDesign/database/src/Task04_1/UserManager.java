/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Task04_1;
import java.io.*;
import java.util.HashMap;

/**
 *
 * @author 20281
 */
public class UserManager {
    private static final String FILE_PATH = "resources/T04_users.txt";
    private HashMap<String, User> users = new HashMap<>();

    public void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    User user = new User(parts[0].trim());
                    user.updateScore(Integer.parseInt(parts[1].trim()));
                    users.put(user.getUsername(), user);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading user file: " + e.getMessage());
        }
    }

    public void saveUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(user.getUsername() + "," + user.getScore());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public void updateUserScore(User user) {
        users.put(user.getUsername(), user);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User u : users.values()) {
                writer.write(u.getUsername() + "," + u.getScore());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating user score: " + e.getMessage());
        }
    }
}
