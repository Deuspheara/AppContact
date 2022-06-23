package fr.deuspheara.appcontact.Views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.deuspheara.appcontact.R
import fr.deuspheara.appcontact.Utils.Contact
import fr.deuspheara.appcontact.Utils.ContactHolder

class ContactAdapter : RecyclerView.Adapter<ContactHolder>(){
    var currentResults: ArrayList<Contact> = ArrayList()
    var contactItem: View? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        // Create a new view
        contactItem = LayoutInflater.from(parent.context).inflate(R.layout.contact_adapter_view, parent, false)
        return ContactHolder( contactItem!! , parent.context)
    }

    override fun getItemCount(): Int {
        // Return the number of items
        return currentResults.size
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        // Bind the data to the view
        holder.bind(currentResults[position])
    }


}