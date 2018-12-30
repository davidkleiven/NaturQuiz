package com.github.naturquiz;

import java.util.Random;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;

import android.content.Context;

public class QuestionManager extends Object{
    Random rng = new Random();

    Context mContext;
    String[] family_names;
    int numAlternatives = 5;
    Question activeQuestion = null;

    
    public QuestionManager(Context context){
        mContext = context;
        family_names = mContext.getResources().getStringArray(R.array.fuglefamilier);
    }


    private String randomFamilyName(){
        int n = rng.nextInt(family_names.length);
        return family_names[n];
    }


    private String[] membersInGroup(String group_name){
        String pkgName = mContext.getPackageName();
        int resId = mContext.getResources().getIdentifier(group_name, "array", pkgName);
        return mContext.getResources().getStringArray(resId);
    }


    public Question getQuestion(){
        String famName = randomFamilyName();
        String[] members = membersInGroup(famName);
        List<String> strList = Arrays.asList(members);
        Collections.shuffle(strList);
        activeQuestion = new Question(strList.subList(0, numAlternatives));
        return activeQuestion;
    }


    public Question getActive(){
        return activeQuestion;
    }
}