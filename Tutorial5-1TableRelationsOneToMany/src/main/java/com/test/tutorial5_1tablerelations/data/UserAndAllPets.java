package com.test.tutorial5_1tablerelations.data;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * @Embedded tag is for having nested fields.
 *
 * <p></p>
 * @Relation is for having relation with other model class.
 * Those two parameters say that parentColumn name from User class is id
 * and entityColumn name from Repo class is userId.
 */
public class UserAndAllPets {

    @Embedded
    public User user;

    @Relation(parentColumn = "id", entityColumn = "userId", entity = Pet.class)
    public List<Pet> pets;
}
