package com.example.kotlinmvvm.database

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kotlinmvvm.dao.NoteDao
import com.example.kotlinmvvm.entities.Note

@Database(entities = [Note::class],exportSchema = false,version = 3)
abstract class NoteDatabase : RoomDatabase() {

  abstract val noteDao:NoteDao

  companion object {

    @Volatile
    private var INSTANCE:NoteDatabase? = null

    fun getInstance(context: Context):NoteDatabase{
      synchronized(this){
        var instance = INSTANCE

      if (instance == null){
        instance = databaseBuilder(context.applicationContext,
          NoteDatabase::class.java,"note_database")
          .fallbackToDestructiveMigration()
          .addCallback(object : RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
              super.onCreate(db)
              AddInitialData(instance).execute()
            }
          })
          .build()

        INSTANCE = instance
      }
      return instance
    }
  }
}

  class AddInitialData(private val db:NoteDatabase?) : AsyncTask<Void, Void, Void>() {

    override fun doInBackground(vararg params: Void?): Void? {
    db!!.noteDao.insert(Note(title = "Title 1",description = "Description 1",priority = 1))
    db.noteDao.insert(Note(title = "Tittle 2",description = "Description 3",priority = 2))
    db.noteDao.insert(Note(title = "Tittle 3",description = "Description 3",priority = 4))
      Log.e("TAG","Is working")

      return null
    }

  }

}



