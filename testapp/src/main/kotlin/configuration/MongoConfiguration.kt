package configuration

import com.mongodb.Mongo
import com.mongodb.MongoClient
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import dal.command.UsersCommandRepository
import dal.query.UsersQueryRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoDbFactory
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


@Configuration
@EnableMongoRepositories(basePackageClasses = arrayOf(UsersCommandRepository::class, UsersQueryRepository::class))
open class MongoConfiguration : AbstractMongoConfiguration() {
    private val mongoDBName: String = "test"

    private val mongoDbLogin: String = "test"

    private val mongoDbPassword: String = "test1234"

    private val mongoDbServer : String = "192.168.99.1:5600"

    override fun getDatabaseName(): String? {
        return mongoDBName
    }

    @Bean
    @Throws(Exception::class)
    override fun mongo(): Mongo {
        return MongoClient(ServerAddress(mongoDbServer), listOf(MongoCredential.createCredential(mongoDbLogin, mongoDBName, mongoDbPassword.toCharArray())))
    }

    @Bean
    @Throws(Exception::class)
    override fun mongoTemplate(): MongoTemplate {
        val mongoDbFactory = SimpleMongoDbFactory(mongo(), mongoDBName)
        return MongoTemplate(mongoDbFactory, mappingMongoConverter())
    }
}