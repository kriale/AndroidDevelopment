package edu.kriale.lab5.data.dao;

import androidx.room.Dao;
import androidx.room.RoomDatabase;
import edu.kriale.lab5.entity.AbstractEntity;

@Dao
abstract class AbstractDao<E extends AbstractEntity> {
    protected RoomDatabase database;

    AbstractDao(RoomDatabase database) {
        this.database = database;
    }
}
