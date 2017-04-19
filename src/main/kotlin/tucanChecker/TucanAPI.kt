package tucanChecker

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import tucanChecker.Parameters.loginArgs
import tucanChecker.Parameters.startArgs

class TucanAPI(private val connection: Connection) {


	private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

	var session = ""

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

		logger.info("Exams retrieved, attempting to parse")

		val result = ExamParser.parse(response.body().string(), connection.host)

		logger.info("Exams parsed")

		return result
	}

}
