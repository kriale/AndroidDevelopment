package edu.kriale.lab5.data.dao;

import androidx.room.RoomDatabase;
import edu.kriale.lab5.entities.Person;

public abstract class PersonDao extends AbstractDao<Person> {
    public PersonDao(RoomDatabase database) {
        super( database );
    }
}
