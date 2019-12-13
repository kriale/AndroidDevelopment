package edu.kriale.lab5.data.dao;

import java.util.Collection;
import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RoomDatabase;
import androidx.room.Update;
import edu.kriale.lab5.entity.Person;

public abstract class PersonDao extends AbstractDao<Person> {
    public PersonDao(RoomDatabase database) {
        super( database );
    }

    @Query( "SELECT * FROM person" )
    public abstract Collection<Person> getAllPersons();

    @Query( "SELECT * FROM person WHERE rowid = :id" )
    public abstract Person getPersonById(int id);

    @Query( "SELECT * FROM person WHERE first_name LIKE :firstName " +
            "AND firstName LIKE :lastName" )
    public abstract Collection<Person> searchPersonsByFirstAndSecondNames(String firstName, String lastName);

    @Query( "SELECT * FROM person WHERE first_name LIKE :firstName" )
    public abstract Collection<Person> searchPersonsByFirstName(String firstName);

    @Query( "SELECT * FROM person WHERE last_name LIKE :lastName" )
    public abstract Collection<Person> searchPersonsByLastName(String lastName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long savePerson(Person person);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract List<Long> savePersonCollection(Collection<Person> persons);

    @Update
    public abstract void updatePerson(Person person);

    @Update
    public abstract void updatePersonCollection(Collection<Person> persons);

    @Delete
    public abstract void deletePerson(Person person);
}
