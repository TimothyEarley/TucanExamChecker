package tucanChecker

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File
import java.util.*

class ExamStore(private val saveLocation: File = ExamStore.FileHelper.toFile(Config.saveFile)) {

	constructor(filename: String): this(ExamStore.FileHelper.toFile(filename))

	object FileHelper {
		fun toFile(filename: String): File {
			val jarLocation = File(javaClass.protectionDomain.codeSource.location.toURI().path)
			return File(jarLocation.parent, filename)
		}
	}

	val logger: Logger = LoggerFactory.getLogger(this.javaClass)


	private val exams: MutableList<Exam> by lazy {
		logger.info("Loading Exams from $saveLocation")
		load().apply {
			logger.debug("Loaded: $this")
		}
	}

	private val objectMapper: ObjectMapper = ObjectMapper().registerModule(KotlinModule())

	/**
	 * returns new exams
	 */
	fun update(newExams: List<Exam>): List<Exam> = newExams
			.filter {
				logger.debug("Filtering $it")
				! exams.contains(it)
			}
			.onEach {
				logger.debug("Found new exam $it")
				exams.add(it)
			}

	fun save() {
		logger.info("Saving exams to $saveLocation")
		objectMapper.writerWithDefaultPrettyPrinter().writeValue(saveLocation, exams)
	}

	fun load(): MutableList<Exam> =
			if (!saveLocation.exists()) ArrayList()
			else objectMapper.readValue<List<Exam>>(saveLocation).toMutableList()

}
