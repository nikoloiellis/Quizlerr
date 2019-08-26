package com.example.instar.quizlerr;

/**
 * Created by Nikoloi Ellis on 7/27/2017.
 */

public class TrueFalse {

    // TODO: Create true false constructor

    private  int mQuestionID; // resource folder ID most be int
    private  boolean mAnswer; //boolean question
    public TrueFalse(int questionResouceID, boolean trueorfalse){ //contructor

        mQuestionID = questionResouceID; //equal the parameter to the variables
        //to link it up
        mAnswer = trueorfalse;

    }
    //TODO: Retrieves the mQuestion value alone
    public int getQuestionID() {
        return mQuestionID;
    }

    public void setQuestionID(int questionID) {
        mQuestionID = questionID;
    }
    //TODO: Retrives the mAnswer Boolean
    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}
