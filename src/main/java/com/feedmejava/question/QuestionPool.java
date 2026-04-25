/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.feedmejava.question;

/**
 *
 * @author hmarl
 */
import java.util.*;

public class QuestionPool {
    private Map<Integer, Question> questionPool = new HashMap<>();
    private Random random = new Random();

    public QuestionPool() {
        // TODO: Initialize with questions
    }

    public Map<Integer, Question> getQuestionPool() {
        return questionPool;
    }

    /**
     * Returns N random unique questions
     */
    public List<Question> getRandomQuestions(int numQuestions) {
        List<Question> list = new ArrayList<>(questionPool.values());
        Collections.shuffle(list);
        return list.subList(0, Math.min(numQuestions, list.size()));
    }

    public Question getQuestionByID(int id) {
        return questionPool.get(id);
    }
}