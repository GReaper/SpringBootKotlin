package unit

import controllers.SampleController
import org.testng.Assert
import org.testng.annotations.Test

class SampleControllerUnitTest {

    @Test
    fun test_home_called_returnCorrectResponse() {
        val sut = SampleController()
        val actual = sut.home()
        val expected = "Hello world Kotlin!"
        Assert.assertEquals(actual, expected)
    }
}