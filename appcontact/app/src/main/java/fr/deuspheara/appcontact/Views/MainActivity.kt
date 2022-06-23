package fr.deuspheara.appcontact.Views

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import fr.deuspheara.appcontact.R
import fr.deuspheara.appcontact.Utils.Contact
import fr.deuspheara.appcontact.Utils.JsonToContact

class MainActivity : AppCompatActivity() {
    companion object {
        var railwayList : ArrayList<Contact> = ArrayList()
    }

    var recyclerView : RecyclerView? = null
    var searchView : SearchView? = null
    var adapter : ContactAdapter? = null
    var resetButton : Button? = null


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var jsonToContact = JsonToContact()

        //if preference contacts don't exist, we create them
        val sharedpreferences = getSharedPreferences("contacts", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedpreferences.edit()
        if(!sharedpreferences.contains("contacts")){
            //initialise railwayList with contact info from json file contacts.json
            railwayList = jsonToContact.getContactList("contacts.json", this)
            //if contacts don't exist, we create them
            editor.putString("contacts",Gson().toJson(railwayList))
        }else{
            //we get the contacts from the preference
            val json = sharedpreferences.getString("contacts", "")
            val contacts = json?.let {
                jsonToContact.getContactListFromPref(it, this)
                //Log.i("contacts",JSONObject().put("item",JSONArray(it)).toString())
            }
            if(contacts != null){
                railwayList.addAll(contacts)
            }
        }







        //initialise recyclerView
        recyclerView = findViewById<RecyclerView>(R.id.contactRecyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        //add adapter to recyclerView
       adapter = ContactAdapter()
        adapter!!.currentResults = railwayList
        recyclerView?.adapter = adapter

        //initialise searchView
        searchView = findViewById<SearchView>(R.id.searchContact)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //filter contact that match the query
                adapter!!.currentResults = railwayList.filter { it.name.lowercase().contains(query!!.lowercase()) || it.surname.lowercase().contains(query.lowercase()) } as ArrayList<Contact>
                adapter!!.notifyDataSetChanged()

                if(adapter!!.currentResults.size == 0) {
                    Log.d("MainActivity", "No results found")
                    adapter!!.currentResults = railwayList
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter!!.currentResults = railwayList.filter { it.name.lowercase().contains(newText!!.lowercase()) || it.surname.lowercase().contains(newText.lowercase()) } as ArrayList<Contact>
                adapter!!.notifyDataSetChanged()
                return false
            }
        })

        //initialise resetButton
        resetButton = findViewById<Button>(R.id.readFileButton)
        resetButton?.setOnClickListener {
            //reset the contact list with the contacts from the json file contacts.json
            val sharedpreferences = getSharedPreferences("contacts", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedpreferences.edit()
            editor.clear()
            railwayList = jsonToContact.getContactList("contacts.json", this)
            editor.putString("contacts",Gson().toJson(railwayList))

            adapter!!.currentResults = railwayList
            adapter!!.notifyDataSetChanged()
        }
    }


}