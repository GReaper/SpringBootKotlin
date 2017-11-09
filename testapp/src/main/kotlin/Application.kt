import controllers.SampleController
import org.springframework.boot.*

fun main(args: Array<String>) {
    SpringApplication.run(SampleController::class.java, *args)
}