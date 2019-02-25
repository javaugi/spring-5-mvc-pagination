
import ch.qos.logback.classic.AsyncAppender
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy
import ch.qos.logback.core.status.OnConsoleStatusListener

import ch.qos.logback.classic.PatternLayout
import ch.qos.logback.core.encoder.LayoutWrappingEncoder

import static ch.qos.logback.classic.Level.*
import org.springframework.boot.logging.logback.ColorConverter
import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter
import org.springframework.boot.logging.logback.ExtendedWhitespaceThrowableProxyConverter

scan()
def LOG_FILE_NAME = "spring5"
def LOG_DIRECTORY_DEFAULT = "/tmp"
def byDay = timestamp("yyyyMMdd")
def LOG_FILE_FORMAT = "%highlight(%-25(%date) %-5level) %cyan(%logger{5}) %msg%n"

def APPENDERS_SYSTEM = System.getProperty("appserver.private.appenders")
def APPENDERS_DEFAULT = "async-con"
def APPENDERS = APPENDERS_SYSTEM == null ? [APPENDERS_DEFAULT] : APPENDERS_SYSTEM.tokenize( ',' ).toList()

statusListener(OnConsoleStatusListener)
if(APPENDERS.any { it =~ /(con|console)/ }){
    appender("console", ConsoleAppender) {
        encoder(PatternLayoutEncoder) {
            pattern = LOG_FILE_FORMAT
        }
    }
}
//if async-file or file is wanted add this appender
if(APPENDERS.any { it =~ '.*file.*' }){
    appender("file", RollingFileAppender) {
        file = "${LOG_DIRECTORY}/${LOG_FILE_NAME}.log"
        rollingPolicy(TimeBasedRollingPolicy) {
            fileNamePattern = "${LOG_DIRECTORY}/${LOG_FILE_NAME}.%d{yyyy-MM-dd}.log"
            maxHistory = 30
        }
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = LOG_FILE_FORMAT
        }
    }
}
if(APPENDERS.any { it =~ 'async-file' }){
    appender("async-file", AsyncAppender) {
        queueSize = 5000
        appenderRef("file")
    }
}

if(APPENDERS.any { it =~ 'async-con' }){
    appender("async-con", AsyncAppender) {
        queueSize = 5000
        appenderRef("console")
    }
}

if(APPENDERS.any { it =~ 'async-gelf-udp' }){
    appender("async-gelf-udp", AsyncAppender) {
        queueSize = 5000
        appenderRef("gelf-udp")
    }
}
logger("com.spring5", DEBUG)
logger("org.hibernate", ERROR)
logger("org.springframework", ERROR)

println "appenders = " + APPENDERS
root(INFO, APPENDERS)
