package com.test.tutorial5_1tablerelations.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "pets", foreignKeys = {@ForeignKey(
        entity = User.class,
        parentColumns = "id",
        childColumns = "userId",
        onDelete = CASCADE)
})
public class Pet {
    @PrimaryKey(autoGenerate = true)
    public long id;     // Pet id
    public long userId; // User id
    public String name;

    public long getId() {
        return id;
    }
}
