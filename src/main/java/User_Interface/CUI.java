/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User_Interface;

/**
 *
 * @author hmarl
 */

import java.util.Scanner;

public class CUI implements UI {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void displayText(String text) {
        System.out.println(text);
    }

    @Override
    public void displayError(String text) {
        System.err.println(text);
    }

    @Override
    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}