package edu.kriale.lab5.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import edu.kriale.lab5.data.dao.PersonDao;
import edu.kriale.lab5.entity.Person;

@Database(entities = Person.class, version = 1)
public abstract class PersonsRoomDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
}
