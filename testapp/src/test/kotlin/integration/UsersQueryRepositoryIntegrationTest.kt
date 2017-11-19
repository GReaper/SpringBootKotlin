package integration

import application.SpringKotlinApplication
import configuration.MongoConfiguration
import dal.MongoRepositoryBase
import dal.query.ResourceNotFoundQueryError
import dal.query.UsersQueryRepository
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
@EnableMongoRepositories(basePackageClasses = arrayOf(UsersQueryRepository::class))
@ContextConfiguration(classes = arrayOf(MongoConfiguration::class))
class UsersQueryRepositoryIntegrationTest {
    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    @Autowired
    lateinit var sut: UsersQueryRepository

    @Before
    fun setUp() {
        val fixtures = arrayListOf<User>(User(id = null, email = "testquery@mail.com", pwd = "testpwd"))
        fixtures.forEach { mongoTemplate.insert(it) }
    }

    @After
    fun tearDown() {
        mongoTemplate.dropCollection(MongoRepositoryBase.USERS_COLLECTION)
    }

    @Test
    fun test_findByEmail_calledWithExistentUser_rightUserRetrieved() {
        sut.findByEmail("testquery@mail.com")
        val result: List<User> = mongoTemplate.findAll(User::class.java, MongoRepositoryBase.USERS_COLLECTION)
        var actual = ""
        result.forEach { it -> actual += it.email }
        val expected = "testquery@mail.com"
        Assert.assertEquals(expected, actual)
    }

    @Test(expected = ResourceNotFoundQueryError::class)
    fun test_findByEmail_calledWithNonExistentUser_raiseResourceNotFoundQueryError() {
        sut.findByEmail("fake@mail.com")
    }
}