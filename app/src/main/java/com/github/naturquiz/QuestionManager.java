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
        int resId = mContext.getResources().getIdentifier(group_name, "fugler", pkgName);
        return mContext.getResources().getStringArray(resId);
    }

    public Question getQuestion(){
        String famName = randomFamilyName();
        String[] members = membersInGroup(famName);
        List<String> strList = Arrays.asList(members);
        Collections.shuffle(strList);
        return new Question(Arrays.copyOfRange(members, 0, numAlternatives));
    }
}