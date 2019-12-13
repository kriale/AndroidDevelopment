package edu.kriale.lab5.data.dao;

import java.util.Collection;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RoomDatabase;
import androidx.room.Update;
import edu.kriale.lab5.entity.Person;

@Dao
public abstract class PersonDao extends AbstractDao<Person> {
    public PersonDao(RoomDatabase database) {
        super( database );
    }

    @Query( "SELECT * FROM person WHERE first_name LIKE '%' || :firstName || '%' " +
            "AND last_name LIKE '%' || :lastName || '%'" )
    public abstract List<Person> searchPersonsByFirstAndSecondNames(String firstName, String lastName);
}
