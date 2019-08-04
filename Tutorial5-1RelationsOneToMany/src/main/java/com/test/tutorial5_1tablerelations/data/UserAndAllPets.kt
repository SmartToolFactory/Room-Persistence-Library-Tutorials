package com.test.tutorial5_1tablerelations.data

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

/**
 * @Embedded tag is for having nested fields.
 *
 *
 *
 * @Relation is for having relation with other model class.
 * Those two parameters say that parentColumn name from User class is id
 * and entityColumn name from Repo class is userId.
 */
class UserAndAllPets {

    @Embedded
    var user: User? = null

    @Relation(parentColumn = "id", entityColumn = "userId", entity = Pet::class)
    var pets: List<Pet>? = null
}
