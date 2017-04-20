package tucanChecker

import ch.qos.logback.classic.Level
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.net.URLClassLoader
import java.util.*


object Config {

	private val logger: Logger = LoggerFactory.getLogger(this.javaClass)


	private val configfile = "config.properties"

	private val props = Properties().apply {
		ClassLoader.getSystemClassLoader().getResourceAsStream(configfile).use {
			if (it == null) throw FileNotFoundException("Could not load $configfile")
			load(it)
		}
		logger.info("Config loaded")
	}

	object IFTTT {
		val baseUrl: String = props.getProperty("ifttt.baseUrl") ?: "https://maker.ifttt.com/"
		val eventName: String = props.getProperty("ifttt.eventName") ?: "TucanKlausur"
		val key: String = props.getProperty("ifttt.key") ?: throw NoSuchElementException("Please specify a IFTTT API key in $configfile under 'ifttt.key'")
	}

	object Tucan {
		val baseUrl: String = props.getProperty("tucan.baseURL") ?: "www.tucan.tu-darmstadt.de"
		val path: String = props.getProperty("tucan.path") ?: "scripts/mgrqcgi"
		val username: String = props.getProperty("tucan.username") ?: throw NoSuchElementException("Please specify your Tucan username in $configfile under 'tucan.username'")
		val password: String = props.getProperty("tucan.password") ?:throw NoSuchElementException("Please specify your Tucan password in $configfile under 'tucan.password'")
	}

	val logLevel: Level = Level.toLevel(props.getProperty("logLevel") ?: Level.INFO.levelStr)
	val saveFile: String = props.getProperty("saveFile") ?: "save.json"

}
