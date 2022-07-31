package com.example.android_aplikacia_2022

import androidx.room.*


@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getAll(): List<User>

    @Query("SELECT * FROM user_table WHERE roll_no LIKE :roll LIMIT 1")
    fun findByRoll(roll: Int): User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)

    @Query("UPDATE user_table SET first_name=:firstName,last_name=:lastName WHERE roll_no LIKE :roll")
    fun update(firstName: String, lastName : String, roll: Int)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM user_table")
    fun deleteAll()
}