package dal

import models.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersCommandRepository: MongoRepository<User, String> {
    fun getUserCollectionName(): String = "users"
}