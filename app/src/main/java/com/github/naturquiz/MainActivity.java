package com.github.naturquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import java.util.List;

import com.github.naturquiz.QuestionManager;

public class MainActivity extends AppCompatActivity {
    QuestionManager q_manager;
    Score score = new Score();
    Question activeQuestion = null;
    Button answerButtons[] = null;
    private boolean processing_answer = false;
    private int num_questions_remaining = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        q_manager = new QuestionManager(this);

        answerButtons = new Button[5];
        answerButtons[0] = (Button)findViewById(R.id.alt1);
        answerButtons[1] = (Button)findViewById(R.id.alt2);
        answerButtons[2] = (Button)findViewById(R.id.alt3);
        answerButtons[3] = (Button)findViewById(R.id.alt4);
        answerButtons[4] = (Button)findViewById(R.id.alt5);
        newQuestion();
    }

    public void newQuestion(){
        Question question = q_manager.getQuestion();

        List<String> alternatives = question.altInRandomOrder();

        for (int i=0;i<alternatives.size();i++){
            answerButtons[i].setText(alternatives.get(i));
        }
        resetButtonColor();

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
        processing_answer = false;
        num_questions_remaining -= 1;
        process_game_finished();
    }

    private void resetButtonColor(){
        for (Button button : answerButtons){
            button.setBackgroundColor(getResources().getColor(R.color.neutralButton));
        }
    }


    private Button getCorrectButton(){
        for (Button button : answerButtons){
            String text = button.getText().toString();
            if (q_manager.getActive().isCorrect(text)){
                return button;
            }
        }
        return null;
    }

    private void process_game_finished(){
        if (num_questions_remaining > 0){
            return;
        }
        // TODO: Ask user if new game should be started
        // TODO: Save result in DB and give position 
        // of current round
        reset_game();
    }

    private void reset_game(){
        num_questions_remaining = 100;
        score.reset();
    }

    public void onClick(View v){
        if (processing_answer){
            return;
        }
        processing_answer = true;
        Button button = (Button) v;
        String text = button.getText().toString();

        boolean correct = q_manager.getActive().isCorrect(text);
        if (correct){
            button.setBackgroundColor(getResources().getColor(R.color.correctAnswer));
        }
        else{
            button.setBackgroundColor(getResources().getColor(R.color.wrongAnswer));
            getCorrectButton().setBackgroundColor(getResources().getColor(R.color.correctAnswer));
        }
        score.update(correct);
        updateScoreString();
        postNewQuestion();
    }

    public void updateScoreString(){
        TextView tv = (TextView)findViewById(R.id.score);
        tv.setText(score.get() + " Spm. igjen: " + num_questions_remaining);
    }

    public void postNewQuestion(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                newQuestion();
            }
        }, 1000);
    }
}
