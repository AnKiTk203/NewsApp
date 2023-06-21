package com.codingblocks.myprojecttodoapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ToDoDao {
    @Insert
   suspend fun insertTask(toDoModel: ToDoModel):Long
   @Query("Select * from ToDoModel where isFinished !=  (-1)")
   fun getTask():LiveData<List<ToDoModel>>

   @Query
       ("Update ToDoModel Set isFinished = 1 where id=:uid")
   fun finishTask(uid:Long)

    @Query
        ("Delete from ToDoModel where id=:uid")
    fun deleteTask(uid:Long)
}