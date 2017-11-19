package models

import dal.UsersCommandRepository
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = UsersCommandRepository.USERS_COLLECTION)
class User(@Id val id: String?, val email: String, val pwd: String) {
    override fun toString(): String {
        return "User(id='$id', email='$email', pwd='$pwd')"
    }
}