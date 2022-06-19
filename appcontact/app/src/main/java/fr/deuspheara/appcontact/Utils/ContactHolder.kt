package fr.deuspheara.appcontact.Utils

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.deuspheara.appcontact.R

class ContactHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var name = itemView.findViewById<TextView>(R.id.name);
    var surname = itemView.findViewById<TextView>(R.id.surname)
    var fullname = itemView.findViewById<TextView>(R.id.fullname)
    var email = itemView.findViewById<TextView>(R.id.email)
    var age = itemView.findViewById<TextView>(R.id.age)
    var picture = itemView.findViewById<ImageView>(R.id.profilePicture)
    var sendButton = itemView.findViewById<ImageButton>(R.id.send)

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
    }
    fun sendEmail(contact : Contact) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(contact.email))
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject")
        intent.putExtra(Intent.EXTRA_TEXT, "message")
        intent.setType("message/rfc822")
        itemView.context.startActivity(Intent.createChooser(intent, "Send Email using:"));
    }
}