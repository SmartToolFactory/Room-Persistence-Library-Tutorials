package com.test.tutorial5_1tablerelations.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "pets")
public class Pet {
    @PrimaryKey(autoGenerate = true)
    public long id;     // Pet id
    public long userId; // User id
    public String name;

    public long getId() {
        return id;
    }
}
