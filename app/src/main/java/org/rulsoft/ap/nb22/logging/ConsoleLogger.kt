package org.rulsoft.ap.nb22.logging

import org.rulsoft.ap.nb22.domain.logger.Logger

class ConsoleLogger : Logger {
    override fun d(tag: String, message: String) {
        println("DEBUG: [$tag] $message")
    }

    override fun e(tag: String, message: String, throwable: Throwable?) {
        println("ERROR: [$tag] $message")
        throwable?.printStackTrace()
    }

    override fun i(tag: String, message: String) {
        println("INFO: [$tag] $message")
    }

    override fun w(tag: String, message: String) {
        println("WARN: [$tag] $message")
    }
}