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

public class UserRecordFileIO implements UserRecord {

    @Override
    public void saveRecord(User user) {
        // TODO: write user to file
    }

    @Override
    public User loadRecord(String username) {
        // TODO: read from file
        return null;
    }

    @Override
    public void saveGame(QuizSession session) {
        // TODO: save session (question IDs, progress, etc.)
    }

    @Override
    public QuizSession loadGame(String username) {
        // TODO: reconstruct session from file
        return null;
    }
}