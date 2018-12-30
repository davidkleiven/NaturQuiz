package com.github.naturquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

import com.github.naturquiz.QuestionManager;

public class MainActivity extends AppCompatActivity {
    QuestionManager q_manager;
    Question activeQuestion = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        q_manager = new QuestionManager(this);
        newQuestion();
    }

    public void newQuestion(){
        Question question = q_manager.getQuestion();

        List<String> alternatives = question.altInRandomOrder();

        Button button = (Button)findViewById(R.id.alt1);
        button.setText(alternatives.get(0));

        button = (Button)findViewById(R.id.alt2);
        button.setText(alternatives.get(1));

        button = (Button)findViewById(R.id.alt3);
        button.setText(alternatives.get(2));

        button = (Button)findViewById(R.id.alt4);
        button.setText(alternatives.get(3));

        button = (Button)findViewById(R.id.alt5);
        button.setText(alternatives.get(4));

        // Set image
        ImageView img = (ImageView) findViewById(R.id.bird_picture);
        String pkgName = getPackageName();
        int resId = getResources().getIdentifier(question.imageName(), "drawable", pkgName);

        if (resId > 0){
            img.setImageResource(resId);
        }
        else{
            img.setImageResource(R.drawable.default_bird);
        }
    }
}
