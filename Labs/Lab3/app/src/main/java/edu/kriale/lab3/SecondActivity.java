package edu.kriale.lab3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends Activity {
    private static final String QUESTION_EXTRA_KEY = "question";
    private static final String ANSWER_EXTRA_KEY = "answer";
    private static final String EMPTY_TEXT = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_second );
    }

    @Override
    protected void onStart() {
        super.onStart();

        TextView questionTextView = findViewById( R.id.receivedQuestionText );
        Intent receivedIntent = getIntent();
        String receivedQuestion = receivedIntent.getStringExtra( QUESTION_EXTRA_KEY );
        if (receivedQuestion  != null) {
            questionTextView.setText( receivedQuestion );
        } else {
            questionTextView.setText( EMPTY_TEXT ); // stub
        }
    }

    public void onClick_SendAnswer(View view) {
        EditText AnswerEditText = findViewById( R.id.answerText );
        String answer = AnswerEditText.getText().toString();

        Intent answerIntent = new Intent();
        answerIntent.putExtra( ANSWER_EXTRA_KEY, answer );

        setResult(Activity.RESULT_OK, answerIntent);
        finish();
    }

}
