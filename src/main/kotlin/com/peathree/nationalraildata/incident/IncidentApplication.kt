package com.peathree.nationalraildata.incident

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jms.annotation.EnableJms

@SpringBootApplication
@EnableJms
class IncidentApplication

fun main(args: Array<String>) {
    runApplication<IncidentApplication>(*args)
}
