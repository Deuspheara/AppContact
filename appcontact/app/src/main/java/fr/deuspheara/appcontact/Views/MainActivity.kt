package fr.deuspheara.appcontact.Views

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.deuspheara.appcontact.R
import fr.deuspheara.appcontact.Utils.Contact
import fr.deuspheara.appcontact.Utils.JsonToContact

class MainActivity : AppCompatActivity() {
    var recyclerView : RecyclerView? = null
    var searchView : SearchView? = null

    var railwayList : ArrayList<Contact> = ArrayList()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initialise railwayList with contact info from json file contacts.json
        var jsonToContact = JsonToContact()
        railwayList = jsonToContact.getContactList("contacts.json", this)

        //initialise recyclerView
        recyclerView = findViewById<RecyclerView>(R.id.contactRecyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        //add adapter to recyclerView
        var adapter = ContactAdapter()
        adapter.currentResults = railwayList
        recyclerView?.adapter = adapter


    }

}