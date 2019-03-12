package com.example.wswsw.takehomeassignment07_canz;

public class Question {
        private int mTextResId;
        private boolean mQuestionAnswer;

        public Question(int textResId, boolean QuestionAnswer){
            mTextResId = textResId;
            mQuestionAnswer = QuestionAnswer;
        }

        public Question() {
        }

        public int getmTextResId() {
            return mTextResId;
        }

        public void setmTextResId(int mTextResId) {
            this.mTextResId = mTextResId;
        }

        public boolean getQuestionAnswer() {
            return mQuestionAnswer;
        }

        public void setQuestionAnswer(boolean mAnswerTrue) {
            this.mQuestionAnswer = mAnswerTrue;
        }
    }

