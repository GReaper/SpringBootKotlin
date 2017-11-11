package integration

import application.SpringKotlinApplication
import dal.UsersCommandRepository
import models.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.testng.Assert
import org.testng.annotations.Test
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.BasicQuery
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod

@SpringBootTest(classes = arrayOf(SpringKotlinApplication::class))
@DirtiesContext
@EnableMongoRepositories
@ContextConfiguration(classes = arrayOf(UsersCommandRepository::class))
class UsersCommandRepositoryIntegrationTest: AbstractTestNGSpringContextTests() {
    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    @Autowired
    lateinit var sut: UsersCommandRepository

    @BeforeMethod
    fun setUp() {

    }

    @AfterMethod
    fun tearDown() {
        mongoTemplate.dropCollection(sut.getUserCollectionName())
    }

    @Test
    fun test_insert_calledWithValidUser_userCorrectlyStored() {
        val user = User(id = null, email = "test@mail.com", pwd = "testpwd")
        val count: Long = mongoTemplate.count(BasicQuery("{}"), sut.getUserCollectionName())
        Assert.assertEquals(count, 0, "guard -- users collection must be empty")
        sut.insert(user)
        val result: List<User> = mongoTemplate.findAll(User::class.java, sut.getUserCollectionName())
        var actual = ""
        result.forEach { it -> actual += it.email }
        val expected = "testmail.com"
        Assert.assertEquals(actual, expected)
    }
}