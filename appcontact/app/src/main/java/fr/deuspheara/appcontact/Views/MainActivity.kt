package fr.deuspheara.appcontact.Views

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.deuspheara.appcontact.R
import fr.deuspheara.appcontact.Utils.Contact
import fr.deuspheara.appcontact.Utils.JsonToContact

class MainActivity : AppCompatActivity() {
    var recyclerView : RecyclerView? = null
    var railwayList : ArrayList<Contact> = ArrayList()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var jsonToContact = JsonToContact()
        railwayList = jsonToContact.getContactList("contacts.json", this)

        recyclerView = findViewById(R.id.contactRecyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        var adapter = ContactAdapter()
        adapter.currentResults = railwayList
        recyclerView?.adapter = adapter

    }
}