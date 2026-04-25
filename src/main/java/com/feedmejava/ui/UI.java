/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.feedmejava.ui;

/**
 *
 * @author hmarl
 */

public interface UI {
    void displayText(String text);
    void displayError(String text);
    String getUserInput(String prompt);
}
