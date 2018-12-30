package com.github.naturquiz;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class Question extends Object{
    List<String> alternatives;
    String correct;

    // Assume that the first alternative is the correct one
    public Question(List<String> alt){
        alternatives = alt;
        correct = new String(alternatives.get(0));
    }

    public boolean isCorrect(String answer){
        return answer == correct;
    }

    public List<String> altInRandomOrder(){
        Collections.shuffle(alternatives);
        return alternatives;
    }

    public String imageName(){
        String imgName = new String(correct);
        imgName = imgName.replace("æ", "ae");
        imgName = imgName.replace("ø", "oe");
        imgName = imgName.replace("å", "aa");
        return imgName;
    }
}