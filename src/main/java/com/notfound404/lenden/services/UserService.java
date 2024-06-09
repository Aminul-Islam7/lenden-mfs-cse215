package com.notfound404.lenden.services;

import com.notfound404.lenden.models.User;

import java.io.*;
import java.util.ArrayList;

public class UserService {
  private ArrayList<User> users;
  private User currentUser;
  private final String USER_FILE_PATH = "src/main/resources/data/users.dat";
  private final String SESSION_FILE_PATH = "src/main/resources/data/session.dat";

  public UserService() {
    users = loadUsers();
    currentUser = loadSession();
  }

  public boolean registerUser(String name, String phone, String nid, int age, int pin, double balance) {
    if (getUserByPhone(phone) != null) {
      return false; // User with this phone number already exists
    }
    User newUser = new User(name, phone, nid, age, pin, balance);
    users.add(newUser);
    saveUsers();
    return true;
  }

  public User loginUser(String phone, int pin) {
    User user = getUserByPhone(phone);
    if (user != null && user.getPin() == pin) {
      currentUser = user;
      saveSession();
      return user;
    }
    return null;
  }

  public void logoutUser() {
    currentUser = null;
    clearSession();
  }

  public User getCurrentUser() {
    loadUsers();
    for (User user : users) {
      if (user.equals(currentUser)) {
        return user;
      }
    }
    return currentUser;
  }

  private User getUserByPhone(String phone) {
    for (User user : users) {
      if (user.getPhone().equals(phone)) {
        return user;
      }
    }
    return null;
  }

  private ArrayList<User> loadUsers() {
    ArrayList<User> users = new ArrayList<>();
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE_PATH))) {
      users = (ArrayList<User>) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      System.out.println("No users found or failed to load users: " + e.getMessage());
    }
    return users;
  }

  private void saveUsers() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE_PATH))) {
      oos.writeObject(users);
    } catch (IOException e) {
      System.out.println("Failed to save users: " + e.getMessage());
    }
  }

  private void saveSession() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SESSION_FILE_PATH))) {
      oos.writeObject(currentUser);
    } catch (IOException e) {
      System.out.println("Failed to save session: " + e.getMessage());
    }
  }

  private User loadSession() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SESSION_FILE_PATH))) {
      return (User) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      System.out.println("No session found or failed to load session: " + e.getMessage());
      return null;
    }
  }

  private void clearSession() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SESSION_FILE_PATH))) {
      oos.writeObject(null);
    } catch (IOException e) {
      System.out.println("Failed to clear session: " + e.getMessage());
    }
  }

  public void createFiles() {
    try {
      new File(USER_FILE_PATH).createNewFile();
      new File(SESSION_FILE_PATH).createNewFile();
    } catch (IOException e) {
      System.out.println("Failed to create files: " + e.getMessage());
    }
  }

  public void updateUser(User user) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).equals(user)) {
        users.set(i, user);
        saveUsers();
        break;
      }
    }
  }

  public void addBalance(User user, double amount) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).equals(user)) {
        users.get(i).setBalance(users.get(i).getBalance() + amount);
        saveUsers();
        break;
      }
    }
  }

  public void deductBalance(User user, double amount) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).equals(user)) {
        users.get(i).setBalance(users.get(i).getBalance() - amount);
        saveUsers();
        break;
      }
    }
  }
}
