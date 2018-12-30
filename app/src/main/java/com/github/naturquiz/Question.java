package com.github.naturquiz;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class Question extends Object{
    String[] alternatives;
    String correct;

    // Assume that the first alternative is the correct one
    public Question(String[] alt){
        alternatives = alt;
        correct = new String(alternatives[0]);
    }

    public boolean isCorrect(String answer){
        return answer == correct;
    }

    public String[] altInRandomOrder(){
        List<String> strList = Arrays.asList(alternatives);
        Collections.shuffle(strList);
        return alternatives;
    }

    public String imageName(){
        return correct;
    }
}