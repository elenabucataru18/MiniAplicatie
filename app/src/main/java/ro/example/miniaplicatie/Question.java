package ro.example.miniaplicatie;

import org.json.JSONArray;

public  class Question{
    String category;
    String type;
    String difficulty;
    String question;
    String correctAnswer;
    JSONArray wrongAnswers;

    public Question(String category, String type, String difficulty, String question, String correctAnswer, JSONArray wrongAnswers){
        this.category=category;
        this.type=type;
        this.difficulty=difficulty;
        this.question=question;
        this.correctAnswer=correctAnswer;
        this.wrongAnswers=wrongAnswers;
    }

};
