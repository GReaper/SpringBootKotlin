package dal.command

import dal.MongoRepositoryBase
import models.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersCommandRepository: MongoRepositoryBase, MongoRepository<User, String>