/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author hmarl
 */

public class User {
    private String username;
    private String petName;
    private int highScore;

    public User(String username, String petName, int highScore) {
        this.username = username;
        this.petName = petName;
        this.highScore = highScore;
    }

    public String getUsername() 
    { 
        return username;
    }
    public String getPetName()
    { 
        return petName;
    }
    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
    
}