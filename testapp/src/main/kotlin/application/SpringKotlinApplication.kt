package application

import controllers.SampleController
import org.springframework.boot.*
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class SpringKotlinApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(SampleController::class.java, *args)
        }
    }
}
