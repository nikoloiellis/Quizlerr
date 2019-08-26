package com.example.instar.quizlerr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity {

    // TODO: Declare constants here


    // TODO: Declare member variables here:
    Button mTrueButton;
    Button mFalseButton;
    TextView mQuestionTextView;
    TextView mScoreTextView;
    ProgressBar mProgressBar;
    int mIndex; //variable for indexing array
    int mQuestion; //variable for finding the question ID
    int mScore; //increments everytime the user gets it right

    // TODO: Uncomment to create question bank
    private TrueFalse mQuestionBank[]= {
            new TrueFalse(R.string.question_1, true), //instead of storing whole numbers its storing
            //the true false constructor
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13, true)
    };

    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil( 100.0 / mQuestionBank.length); /*increments the progress bar
    everytime the user gets it right*/


    //main method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //TODO: All Views go Here
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        //set the textview for the questions
        mScoreTextView = (TextView) findViewById(R.id.score);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

       //TODO: Bundle Code for saving state during rotate
        if(savedInstanceState != null){
            mScore = savedInstanceState.getInt("ScoreKey"); //retrieve the bundle value
            mIndex = savedInstanceState.getInt("IndexKey");
            mScoreTextView.setText("Score " + mScore + "/" + mQuestionBank.length);
        }
        else {
            mScore = 0;
            mIndex = 0;
        }

        //TODO: Set the text element to the constructor array
        /*accesses the array elements by the int variable mIndex
        * and since setText can only take string, int, and resouces
        * use the getter that returns the ID of the resource specifically
        * .getQuestionID();
        *
        * */
        int mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);

        //TODO: Listener Goes here
        //true button
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateQuestion();
                checkAnswer(true);


            }
        });
        //false button
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //everytime the buttons are pressed the listener does update question
                updateQuestion();

                checkAnswer(false);



            }
        });


    }

    //TODO: Method to change questions
    private void updateQuestion() {


        mIndex = (mIndex + 1) % mQuestionBank.length;//increments the mIndex value by so
        /*it can cycle through it however modulus(%) is meant to stop
        the array from going out of bounds or adding an extra element
        that doesn't exist it caps the ramaider which is 1 (since the array starts at 0) then cycles
        back to 0) .lenght cylces through the whole thing*/

        if(mIndex == 0){ //if the index reaches 0 an alert box pops up
            AlertDialog.Builder alert = new AlertDialog.Builder(this); //instatiate the alertbox
            alert.setTitle("Game Over"); //alertbox title
            alert.setCancelable(false); //guess if you can stop it or not
            alert.setMessage("Your Score is " + mScore + " Not bad"); // the message
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override //an onclick listener for the close button
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();//just closes the app
                }
            });
            alert.show(); //shows the dialogue box
        }
        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);
        //everytime the user does a question it goes up
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        //Shows the increased mScore
        mScoreTextView.setText("Score " + mScore + "/" + mQuestionBank.length);

        Log.d("Quizzler-Android-master", String.valueOf(mScore));

    }

    //TODO: Checks the answer
    private void checkAnswer(boolean userSelection) {
        boolean correctAnswer = mQuestionBank[mIndex].isAnswer();
        /*this boolean cycles through the isAnswer getter method
        and retrieves the true or false value
         */

        //if userSelection is the same as the isAnswer(Correct answer) then it runes the condition

        if (userSelection == correctAnswer) {

            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
            mScore = mScore + 1;
        } else {

            Toast.makeText(getApplicationContext(), R.string.incorrect_toast,
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putInt("ScoreKey", mScore); //store them in a bundle
        outState.putInt("IndexKey", mIndex);
    }
}