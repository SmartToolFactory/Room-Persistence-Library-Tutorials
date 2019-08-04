package com.test.tutorial5_1tablerelations.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

import android.arch.persistence.room.ForeignKey.CASCADE

@Entity(tableName = "pets", foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"], onDelete = CASCADE)])
class Pet {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0     // Pet id
    var userId: Long = 0 // User id
    var name: String? = null
}
