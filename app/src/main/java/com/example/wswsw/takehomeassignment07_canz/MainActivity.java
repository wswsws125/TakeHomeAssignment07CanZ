package com.example.wswsw.takehomeassignment07_canz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private int QuestionIndex = 0;
    private boolean mIsCheater;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_asia, true),
            new Question(R.string.question_egypt, false),
            new Question(R.string.question_america, true),
            new Question(R.string.question_dry, false),
            new Question(R.string.question_canada, true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("New Geo Quiz");

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mQuestionTextView = (TextView)findViewById(R.id.question);
        mNextButton = (Button)findViewById(R.id.nextButton);
        mCheatButton = (Button)findViewById(R.id.cheatButton);

        //get the first question in the bank
        int question = mQuestionBank[QuestionIndex].getmTextResId();
        mQuestionTextView.setText(question);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Get the next question index. It will be from 0 to 4.
                QuestionIndex = (QuestionIndex + 1) % mQuestionBank.length;
                //reset the mIsCheater
                mIsCheater = false;
                //get the question content, show it in the TextView
                int question = mQuestionBank[QuestionIndex].getmTextResId();
                mQuestionTextView.setText(question);
            }
        });

        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //get the question answer
                boolean questionAnswer = mQuestionBank[QuestionIndex].getQuestionAnswer();
                //create a new intent to open the cheatActivity, pass the question answer
                Intent intent = newIntent(MainActivity.this, questionAnswer);
                startActivityForResult(intent, RequestCodes.REQUEST_CODE_CHEAT);
            }
        });

    }

    public static Intent newIntent(Context packageContext, boolean answerIsTrue){
        //create a new intent to open the cheatActivity,
        // pass the question answer value to the cheat activity
        Intent intent = new Intent (packageContext, cheatActivity.class);
        intent.putExtra(Keys.EXTRA_QUESTION_ANSWER,answerIsTrue);
        return intent;
    }

    //accept the result data
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        // Make sure the request was successful
        if (requestCode == RequestCodes.REQUEST_CODE_CHEAT){
            if (data == null){
                return;
            }
            //receive if the user cheat boolean
            mIsCheater = data.getBooleanExtra(Keys.EXTRA_ANSWER_SHOWN, false);
        }
    }


    private void checkAnswer(boolean userPressedTrue){
        boolean questionAnswer = mQuestionBank[QuestionIndex].getQuestionAnswer();
        int messageResId = 0;
        if(mIsCheater){
            messageResId = R.string.judgment_toast;
        }else{
            if(userPressedTrue == questionAnswer){
                messageResId = R.string.correct_toast;
            }else{
                messageResId = R.string.incorrect_toast;
            }
        }

        Toast.makeText(this, messageResId,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
