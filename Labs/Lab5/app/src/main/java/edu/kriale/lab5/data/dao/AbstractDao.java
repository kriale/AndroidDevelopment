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
import edu.kriale.lab5.entity.AbstractEntity;
import edu.kriale.lab5.entity.Person;

@Dao
abstract class AbstractDao<E extends AbstractEntity> {
    protected RoomDatabase database;

    AbstractDao(RoomDatabase database) {
        this.database = database;
    }

    @Query( "SELECT * FROM person" )
    public abstract List<E> getAllEntities();

    @Query( "SELECT * FROM person WHERE rowid = :id" )
    public abstract Person getEntityById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long saveEntity(E entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract List<Long> saveEntityCollection(Collection<E> entities);

    @Update
    public abstract void updateEntity(E entity);

    @Update
    public abstract void updateEntityCollection(Collection<E> entities);

    @Delete
    public abstract void deletePerson(Person person);
}
