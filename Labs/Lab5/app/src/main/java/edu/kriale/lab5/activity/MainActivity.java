package edu.kriale.lab5.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

import edu.kriale.lab5.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }

    public void onClick_AddNewPerson(View view) {
        EditText firstNameEditText = findViewById( R.id.newPersonFirstNameEditText );
        EditText lastNameEditText = findViewById( R.id.newPersonLastNameEditText);
        EditText birthDateEditText = findViewById( R.id.newPersonBirthDatEditText);

        String firstNameValue = firstNameEditText.getText().toString();
        String lastNameValue = lastNameEditText.getText().toString();
        Date birthDateValue = birthDateEditText.getText().toString()
        if (!numberString.isEmpty()) {
            int number = Integer.parseInt( numberString );
            startFindingPrimesService( getBaseContext(), number );
            blockFindPrimesButton();
        } else {
            showErrorMessage();
        }
    }
}
