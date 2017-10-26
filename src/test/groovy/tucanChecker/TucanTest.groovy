package tucanChecker

import spock.lang.Specification

class TucanTest extends Specification {

	def "exams work"() {
		given: "an active tucan API"
		def api = new TucanAPI()

		when: "we get the exams"
		def exams = api.getExams()

		then: "some exams were loaded"
		!exams.isEmpty()
	}

	def "logout works"() {
		given: "an active tucan API"
		def api = new TucanAPI()

		when: "the api is closed and then accessed"
		api.close()
		api.getExams()


		then: "we can no longer get the exams"
		thrown IOException
	}

}
