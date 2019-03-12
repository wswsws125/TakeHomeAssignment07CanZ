package com.example.wswsw.takehomeassignment07_canz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class cheatActivity extends AppCompatActivity {

    private boolean theQuestionAnswer;

    private TextView mAnswerTextView;
    private Button mShowAnswerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Cheat Page");

        //get the question answer passed by the main activity
        theQuestionAnswer = getIntent().getBooleanExtra(Keys.EXTRA_QUESTION_ANSWER, false);

        mAnswerTextView = (TextView)findViewById(R.id.answer_text_view);
        mShowAnswerButton = (Button)findViewById(R.id.show_answer_button);

        //show the question answer in the TextView
        mShowAnswerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (theQuestionAnswer){
                    mAnswerTextView.setText(R.string.true_button);
                }else{
                    mAnswerTextView.setText(R.string.false_button);
                }
        // if the user clicked the show answer button,
                // set the EXTRA_ANSWER_SHOWN as true
                setAnswerShownResult(true);
            }
        });
    }

    private void setAnswerShownResult(boolean isAnswerShown){
        //start a new intent to send whether the user have cheated
        Intent data = new Intent();
        data.putExtra(Keys.EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK,data);
    }

}
