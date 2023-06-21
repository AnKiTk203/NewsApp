package com.codingblocks.notification

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val dbName = "MyDB"
val tableName = "Users"
val colName = "name"
val colAge = "age"
val colID = "id"

class DataBaseHandler(var context: Context):SQLiteOpenHelper(context, dbName,null,1)
{
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable= "CREATE TABLE " + tableName + " (" + colID +
                " INTEGER PRIMARY KEY AUTOINCREMENT," + colName + " VARCHAR(256)," +
                colAge + " INTEGER)"
        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
    fun insertData(user: User)
    {
        val db =  this.writableDatabase
        var cv = ContentValues()
        cv.put(colName,user.name)
        cv.put(colAge,user.age)
        var result = db.insert(tableName,null,cv)
        if(result == (-1).toLong())
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
    }
}
