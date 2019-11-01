package edu.kriale.lab3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String QUESTION_EXTRA_KEY = "question";
    private static final String ANSWER_EXTRA_KEY = "answer";
    private static final int SEND_QUESTION_ACTION = 10;
    private static final String EMPTY_TEXT = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }

    public void onClick_SendQuestion(View view) {
        EditText QuestionEditText = findViewById( R.id.questionText );
        String question = QuestionEditText.getText().toString();

        Intent questionIntent = new Intent( this, SecondActivity.class );
        questionIntent.putExtra( QUESTION_EXTRA_KEY, question );
        startActivityForResult( questionIntent, SEND_QUESTION_ACTION );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == SEND_QUESTION_ACTION) {
            String answer = data.getStringExtra( ANSWER_EXTRA_KEY );
            TextView receivedAnswerText = findViewById( R.id.receivedAnswerText );
            if (answer != null) {
                receivedAnswerText.setText( answer );
            } else {
                receivedAnswerText.setText( EMPTY_TEXT ); // stub
            }
        }
    }
}
