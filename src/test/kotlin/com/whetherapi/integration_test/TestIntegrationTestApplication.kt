package com.whetherapi.integration_test

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<IntegrationTestApplication>().with(TestcontainersConfiguration::class).run(*args)
}
