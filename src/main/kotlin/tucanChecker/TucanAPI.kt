package tucanChecker

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import tucanChecker.Parameters.loginArgs
import tucanChecker.Parameters.startArgs
import java.io.Closeable
import java.io.IOException

class TucanAPI(
		private val connection: Connection = Connection()
) : Closeable {


	private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

	private val session : String

	init {
		// sets the cookie
		connection.query(startArgs)
		logger.info("Connection established")
		val response = connection.query(loginArgs)
		session = response.headers()["REFRESH"].substringAfter("ARGUMENTS=").substringBefore(",")
		logger.info("Logged in")
	}

	fun getExams(): List<Exam> {

		val args = mapOf(
				Parameters.appName,
				"PRGNAME" to "EXAMRESULTS",
				"ARGUMENTS" to "$session,-N000325,"
		)

		val response = connection.query(args)

		val html = response.body().string()

		if (!response.isSuccessful || html.isEmpty()) {
			logger.error("Failed to receive exams ($response)")
			throw IOException()
		}

		if (html.contains("Zugang verweigert")) {
			throw IOException("Zugang verweigert! ($args)")
		}

		logger.info("Exams retrieved, attempting to parse")

		val result = ExamParser.parse(html, connection.host)

		logger.info("Exams parsed")

		return result
	}

	// No reuse!
	override fun close() {
		// log out
		connection.query( mapOf(
				Parameters.appName,
				"PRGNAME" to "LOGOUT",
				"ARGUMENTS" to "$session,-N001"
		))

		logger.info("Logged out")
	}

}
