package fr.deuspheara.appcontact.Utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import fr.deuspheara.appcontact.R
import fr.deuspheara.appcontact.Views.ContactActivity
import fr.deuspheara.appcontact.Views.MainActivity
import fr.deuspheara.appcontact.Views.MainActivity.Companion.railwayList

@SuppressLint("ClickableViewAccessibility")
class ContactHolder(itemView : View, parent: Context) : RecyclerView.ViewHolder(itemView) {
    private val context = parent
    var name = itemView.findViewById<TextView>(R.id.name);
    var surname = itemView.findViewById<TextView>(R.id.surname)
    var fullname = itemView.findViewById<TextView>(R.id.fullname)
    var email = itemView.findViewById<TextView>(R.id.email)
    var age = itemView.findViewById<TextView>(R.id.age)
    var picture = itemView.findViewById<ImageView>(R.id.profilePicture)
    var sendButton = itemView.findViewById<ImageButton>(R.id.send)



    //bind the contact data to the view
    @SuppressLint("ClickableViewAccessibility")
    fun bind(contact : Contact) {
            name.text = contact.name
            surname.text = contact.surname
            fullname.text = contact.fullname
            email.text = contact.email
            age.text = contact.age.toString()
            DownloadImage(picture).execute(contact.picture)
            sendButton.setOnClickListener {
                sendEmail(contact)
            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, ContactActivity::class.java)
                intent.putExtra("id", contact.id)
                intent.putExtra("name", contact.name)
                intent.putExtra("surname", contact.surname)
                intent.putExtra("email", contact.email)
                intent.putExtra("age", contact.age)
                intent.putExtra("picture", contact.picture)
                intent.putExtra("fullname", contact.fullname)


                itemView.context.startActivity(intent)
                true
            }
        itemView.setOnLongClickListener {
            deleteContact(contact)
            true
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deleteContact(contact: Contact) {
        //delete in preferences
        val preferences = itemView.context.getSharedPreferences("contacts", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        Log.i("ContactHolder", editor.toString());
        railwayList.remove(contact)
        editor.putString("contacts", Gson().toJson(railwayList))
        editor.apply()
        //notify the adapter
        (itemView.context as MainActivity).adapter?.notifyDataSetChanged()

    }

    //open app contact to send an email
    fun sendEmail(contact : Contact) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(contact.email))
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject")
        intent.putExtra(Intent.EXTRA_TEXT, "message")
        intent.setType("message/rfc822")
        itemView.context.startActivity(Intent.createChooser(intent, "Send Email using:"));
    }
}