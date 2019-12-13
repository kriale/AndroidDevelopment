package edu.kriale.lab5.data;

import androidx.room.Room;

public class PersonsDatabaseSingleton {
    private static final PersonsDatabaseSingleton ourInstance = new PersonsDatabaseSingleton();

    private PersonsRoomDatabase database;

    public static PersonsDatabaseSingleton getInstance() {
        return ourInstance;
    }

    private PersonsDatabaseSingleton() {
        this.database = Room.
    }
}
