package dal.query

import dal.MongoRepositoryBase
import models.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface UsersQueryRepository: MongoRepositoryBase, MongoRepository<User, String> {
    @Query("{ 'email' : ?0 }")
    fun findByEmail(email: String): List<User>
}