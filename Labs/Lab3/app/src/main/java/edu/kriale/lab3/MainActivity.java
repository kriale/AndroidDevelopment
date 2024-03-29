package edu.kriale.lab3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends MenuFragmentActivity {
    private static final String QUESTION_EXTRA_KEY = "question";
    private static final String ANSWER_EXTRA_KEY = "answer";
    private static final String FINISH_EXTRA_KEY = "finish";
    private static final int SEND_QUESTION_ACTION = 10;
    private static final String EMPTY_TEXT = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_main );

        final Button button = findViewById(R.id.sendMessageButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendQuestion();
            }
        });
    }

    private void sendQuestion() {
        EditText QuestionEditText = findViewById( R.id.messageText );
        String question = QuestionEditText.getText().toString();

        Intent questionIntent = new Intent( this, SecondActivity.class );
        questionIntent.putExtra( QUESTION_EXTRA_KEY, question );
        startActivityForResult( questionIntent, SEND_QUESTION_ACTION );
    }

    public void onClick_SendQuestion(View view) {
        sendQuestion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == SEND_QUESTION_ACTION) {
            if (!data.hasExtra( FINISH_EXTRA_KEY )) {
                String answer = data.getStringExtra( ANSWER_EXTRA_KEY );
                TextView receivedAnswerText = findViewById( R.id.receivedAnswerText );
                if (answer != null) {
                    receivedAnswerText.setText( answer );
                } else {
                    receivedAnswerText.setText( EMPTY_TEXT ); // stub
                }
            } else {
                closeActivity();
            }
        }
    }

    @Override
    public void closeActivity() {
        finish();
    }
}
