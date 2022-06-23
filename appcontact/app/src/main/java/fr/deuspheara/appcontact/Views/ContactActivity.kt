package fr.deuspheara.appcontact.Views

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import fr.deuspheara.appcontact.R
import fr.deuspheara.appcontact.Utils.Contact
import fr.deuspheara.appcontact.Utils.DownloadImage

class ContactActivity : AppCompatActivity() {
    var contact : Contact? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_contact)

        contact = Contact(
            0,
            intent.getStringExtra("name")!!,
            intent.getStringExtra("surname")!!,
            intent.getStringExtra("fullname")!!,
            intent.getIntExtra("age",0),
            intent.getStringExtra("email")!!,
            intent.getStringExtra("picture")!!
        )

        //set the info of the contact
        findViewById<TextView>(R.id.cname).text = contact!!.name
        findViewById<TextView>(R.id.csurname).text = contact!!.surname
        findViewById<TextView>(R.id.cfullname).text = contact!!.fullname
        findViewById<TextView>(R.id.cage).text = contact!!.age.toString()
        findViewById<TextView>(R.id.cemail).text = contact!!.email
        DownloadImage(findViewById<ImageView>(R.id.cprofilePicture)).execute(contact!!.picture)



    }

}