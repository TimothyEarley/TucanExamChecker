package tucanChecker

import spock.lang.Specification

/**
 * Created by timmy on 15/10/16.
 */
class IFTTTMakerTest extends Specification {

	def "Send"() {
		when: "the event is send"
		new IFTTTMaker().send(new Exam("Test", "Test", "Test"))

		then: "no exception is thrown and send was done"
		noExceptionThrown()
	}
}
