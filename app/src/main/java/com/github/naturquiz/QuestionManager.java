package com.github.naturquiz;
import java.util.Random;

import android.content.Context;

public class QuestionManager extends Object{
    Random rng = new Random();

    Context mContext;
    String[] family_names;

    
    public QuestionManager(Context context){
        mContext = context;
        family_names = mContext.getResources().getStringArray(R.array.fuglefamilier);
    }


    String random_family_name(){
        int n = rng.nextInt(family_names.length);
        return family_names[n];
    }
}