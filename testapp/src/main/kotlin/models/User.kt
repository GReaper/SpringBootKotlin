package models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

class User(@Id val id: String?, val email: String, val pwd: String) {
    override fun toString(): String {
        return "User(id='$id', email='$email', pwd='$pwd')"
    }
}