package integration

import application.SpringKotlinApplication
import configuration.MongoConfiguration
import dal.UsersCommandRepository
import models.User
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.BasicQuery
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(SpringKotlinApplication::class))
@EnableMongoRepositories(basePackageClasses = arrayOf(UsersCommandRepository::class))
@ContextConfiguration(classes = arrayOf(MongoConfiguration::class))
class UsersCommandRepositoryIntegrationTest {
    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    @Autowired
    lateinit var sut: UsersCommandRepository

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
        mongoTemplate.dropCollection(UsersCommandRepository.USERS_COLLECTION)
    }

    @Test
    fun test_insert_calledWithValidUser_userCorrectlyStored() {
        val user = User(id = null, email = "test@mail.com", pwd = "testpwd")
        val count: Long = mongoTemplate.count(BasicQuery("{}"), UsersCommandRepository.USERS_COLLECTION)
        Assert.assertEquals(0, count)
        sut.insert(user)
        val result: List<User> = mongoTemplate.findAll(User::class.java, UsersCommandRepository.USERS_COLLECTION)
        var actual = ""
        result.forEach { it -> actual += it.email }
        val expected = "test@mail.com"
        Assert.assertEquals(expected, actual)
    }
}