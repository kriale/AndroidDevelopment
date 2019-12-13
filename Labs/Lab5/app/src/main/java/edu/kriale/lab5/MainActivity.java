package edu.kriale.lab5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import androidx.room.Room;
import edu.kriale.lab5.R;
import edu.kriale.lab5.data.PersonsRoomDatabase;
import edu.kriale.lab5.entity.Person;

public class MainActivity extends AppCompatActivity {
    private static final String EMPTY_STRING = "";
    private static final SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

    private PersonsRoomDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        this.database = Room.databaseBuilder(getApplicationContext(),
                PersonsRoomDatabase.class, "persons-db")
                .allowMainThreadQueries().build();
    }

    public void onClick_AddNewPerson(View view) {
        EditText firstNameEditText = findViewById( R.id.newPersonFirstNameEditText );
        EditText lastNameEditText = findViewById( R.id.newPersonLastNameEditText);
        EditText birthDateEditText = findViewById( R.id.newPersonBirthDatEditText);

        String firstNameValue = firstNameEditText.getText().toString();
        String lastNameValue = lastNameEditText.getText().toString();
        try {
            Date birthDateValue = DATE_FORMAT.parse( birthDateEditText.getText().toString() );
            Person person = new Person();
            person.setFirstName( firstNameValue );
            person.setLastName( lastNameValue );
            person.setBirthDate( birthDateValue );

            Toast.makeText(getApplicationContext(),
                    "Saved with id " + database.personDao().saveEntity( person ),
                    Toast.LENGTH_LONG).show();

            firstNameEditText.setText( EMPTY_STRING );
            lastNameEditText.setText( EMPTY_STRING );
            birthDateEditText.setText( EMPTY_STRING );
        } catch (ParseException e) {
            Toast.makeText(getApplicationContext(), R.string.invalid_birth_date_message,
                    Toast.LENGTH_SHORT ).show();
        }
    }

    public void onClick_SearchPerson(View view) {
        TextView result = findViewById( R.id.resultTextView );
        EditText firstNameEditText = findViewById( R.id.personFirstNameEditText );
        EditText lastNameEditText = findViewById( R.id.personLastNameEditText );
        String firstNameValue = firstNameEditText.getText().toString();
        String lastNameValue = lastNameEditText.getText().toString();

        Collection<Person> persons = database.personDao()
                .searchPersonsByFirstAndSecondNames( firstNameValue, lastNameValue );

        result.setText( persons.toString() );
    }
}
