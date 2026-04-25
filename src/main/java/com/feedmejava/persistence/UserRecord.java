/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.feedmejava.persistence;

/**
 *
 * @author hmarl
 */

import com.feedmejava.model.User;
import com.feedmejava.model.QuizSession;

public interface UserRecord {
    void saveRecord(User user);
    User loadRecord(String username);

    void saveGame(QuizSession session);
    QuizSession loadGame(String username);
}
