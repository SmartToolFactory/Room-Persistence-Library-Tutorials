package com.test.tutorial5_1tablerelations.data


import androidx.room.Embedded
import androidx.room.Relation

/**
 * @Embedded tag is for having nested fields.
 *
 *
 *
 * @Relation is for having relation with other model class.
 * Those two parameters say that parentColumn name from User class is petId
 * and entityColumn name from Repo class is userId.
 */
class UserAndAllPets {

    @Embedded
    var user: User? = null

    // 🔥 'id' comes from User, 'userId' comes from Pet. Both are the same ids
    @Relation(parentColumn = "id", entityColumn = "userId", entity = Pet::class)
    var pets: List<Pet>? = null
}
