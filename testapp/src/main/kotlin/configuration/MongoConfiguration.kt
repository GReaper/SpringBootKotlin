package configuration

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate


@Configuration
open class MongoConfiguration {
    @Bean
    @Throws(Exception::class)
    fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(MongoClient(MongoClientURI( "mongodb://test:test1234@192.168.99.1:5600")), "test")
    }
}