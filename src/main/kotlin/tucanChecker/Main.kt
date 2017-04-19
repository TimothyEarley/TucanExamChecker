package tucanChecker

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import org.slf4j.LoggerFactory

fun main(args: Array<String>) {

	setLevel(Config.logLevel)

	val connection = Connection(Config.Tucan.baseUrl, Config.Tucan.path)
	val tucan = TucanAPI(connection)

	val examStore = ExamStore()
	val ifttt = IFTTTMaker()

	val exams = tucan.getExams()
	examStore.update(exams).forEach {
		ifttt.send(it)
	}

	examStore.save()

}

fun setLevel(level: Level) {
	val root: Logger = LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME) as Logger
	root.level = level
}