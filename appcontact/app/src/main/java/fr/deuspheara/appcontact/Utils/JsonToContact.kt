package fr.deuspheara.appcontact.Utils

import android.content.Context
import android.content.res.AssetManager
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

public class JsonToContact {
    private val mContext: Context? = null

    fun getContext(): Context? {
        return mContext
    }

    public fun getContactList(jsonFile: String, context : Context) : ArrayList<Contact> {
        var contacts: ArrayList<Contact> = ArrayList()
        val assetManager: AssetManager = context.getAssets()
        val json: String?

        //parse json file
        try {
            val inputStream = assetManager.open(jsonFile)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)

        } catch (ex: IOException) {
            ex.printStackTrace()
            return ArrayList()
        }

        //  Add json data to ArrayList of contact
        try {
            val obj = JSONObject(json)
            val userArray = obj.getJSONArray("items")
            //looping through All Contacts
            for (i in 0 until userArray.length()) {
                val userObj = userArray.getJSONObject(i)
                val contact = Contact(
                    i,
                    userObj.getString("name"),
                    userObj.getString("surname"),
                    userObj.getString("fullname"),
                    userObj.getInt("age"),
                    userObj.getString("email"),
                    userObj.getString("photo"),

                    )
                contacts.add(contact)
            }
            return contacts
        }
        catch (e: JSONException) {
            e.printStackTrace()
        }
        return ArrayList()
    }
}