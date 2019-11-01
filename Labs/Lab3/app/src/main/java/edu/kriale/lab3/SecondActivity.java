package edu.kriale.lab3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends MenuFragmentActivity {
    private static final String QUESTION_EXTRA_KEY = "question";
    private static final String ANSWER_EXTRA_KEY = "answer";
    private static final String FINISH_EXTRA_KEY = "finish";
    private static final String EMPTY_TEXT = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_second );

        final Button button = findViewById(R.id.sendMessageButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendAnswer();
            }
        });
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
        sendAnswer();
    }

    private void sendAnswer() {
        EditText AnswerEditText = findViewById( R.id.messageText );
        String answer = AnswerEditText.getText().toString();

        Intent answerIntent = new Intent();
        answerIntent.putExtra( ANSWER_EXTRA_KEY, answer );

        setResult(Activity.RESULT_OK, answerIntent);
        finish();
    }

    @Override
    protected void closeActivity() {
        EditText AnswerEditText = findViewById( R.id.messageText );
        String answer = AnswerEditText.getText().toString();

        Intent answerIntent = new Intent();
        answerIntent.putExtra( ANSWER_EXTRA_KEY, answer );
        answerIntent.putExtra( FINISH_EXTRA_KEY, true );

        setResult(Activity.RESULT_OK, answerIntent);
        finish();
    }
}
