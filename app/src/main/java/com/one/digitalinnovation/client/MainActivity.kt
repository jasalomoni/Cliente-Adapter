package com.one.digitalinnovation.client

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var noteRecycler: RecyclerView
    lateinit var noteBtnRefresh: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteBtnRefresh = findViewById(R.id.client_btn_refresh)
        noteRecycler = findViewById(R.id.client_list)
        getContentProvider()

        noteBtnRefresh.setOnClickListener { getContentProvider() }
    }

    private fun getContentProvider() {
        try {
            val url = "content://com.one.digitalinnovation.applicationcontentprovider.provider/notes"
            val data = Uri.parse(url)
            val cursor: Cursor? =
                contentResolver.query(data, null, null, null, "title")
            noteRecycler.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = ClientAdapter(cursor as Cursor)
            }
        }catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}