package com.example.kotlinmvvm.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinmvvm.R
import com.example.kotlinmvvm.utils.showToast
import kotlinx.android.synthetic.main.activity_add_note.*


class AddNoteActivity : AppCompatActivity() {

    companion object{
       const val EXTRA_TITLE:String = "com.codinginflow.architectureexample.EXTRA_TITLE"
       const val EXTRA_DESCRIPTION:String = "com.codinginflow.architectureexample.EXTRA_DESCRIPTION"
       const val EXTRA_PRIORITY:String = "com.codinginflow.architectureexample.EXTRA_PRIORITY"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        title = "Add Note"
    }

    private fun saveNote(){
        val title = edit_text_title!!.text.toString()
        val description = edit_text_description!!.text.toString()
        val priority = number_picker_priority!!.value

        if (title.trim { it <= ' ' }.isEmpty() || description.trim { it <= ' ' }.isEmpty()) {
           showToast("Please insert a title and description")
        }

        val data = Intent()
        data.putExtra(EXTRA_TITLE, title)
        data.putExtra(EXTRA_DESCRIPTION, description)
        data.putExtra(EXTRA_PRIORITY, priority)

        setResult(Activity.RESULT_OK, data)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
