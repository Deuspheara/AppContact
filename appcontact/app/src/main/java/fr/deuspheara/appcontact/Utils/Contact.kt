package fr.deuspheara.appcontact.Utils

class Contact {
    var id: Int = 0
    var name: String = ""
    var surname: String = ""
    var fullname: String = ""
    var age: Int = 0
    var email: String = ""
    var picture: String = ""

    constructor(
        id: Int,
        name: String,
        surname: String,
        fullname: String,
        age: Int,
        email: String,
        picture: String
    ) {
        this.id = id
        this.name = name
        this.surname = surname
        this.fullname = fullname
        this.age = age
        this.email = email
        this.picture = picture
    }
}

