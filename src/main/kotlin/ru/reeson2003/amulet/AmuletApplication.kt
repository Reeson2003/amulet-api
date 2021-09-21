package ru.reeson2003.amulet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.task.TaskSchedulerBuilder
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class AmuletApplication {

    @Bean
    fun taskScheduler(): TaskScheduler = TaskSchedulerBuilder()
        .poolSize(1)
        .threadNamePrefix("refresh-loop-")
        .build()
}

fun main(args: Array<String>) {
    runApplication<AmuletApplication>(*args)
}
