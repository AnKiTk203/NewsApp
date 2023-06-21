package com.codingblocks.myprojecttodoapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ToDoDao {
    @Insert
    suspend fun insertTask(toDoModel: ToDoModel)

    @Query("Update ToDoModel Set isFinished = 1 where id=:uid")
    fun finishTask(uid: Long)

    @Query
        ("Delete From ToDoModel where id=:uid ")
    fun deleteTask(uid: Long)

    @Query("SELECT * FROM ToDoModel where isFinished == 0 ")
    fun getTask(): LiveData<List<ToDoModel>>

    @Query("SELECT * FROM ToDoModel WHERE id = :uid")
    fun getDataById(uid: Long): ToDoModel
}