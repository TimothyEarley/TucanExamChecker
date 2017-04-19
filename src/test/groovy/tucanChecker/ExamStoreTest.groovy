package tucanChecker

import spock.lang.Specification

class ExamStoreTest extends Specification {

	def "Save and Load"() {
		given: "Some exams and IO"
		def exams = [
				new Exam("nameA", "dateA", "markA"),
				new Exam("nameB", "dateB", "markB"),
				new Exam("nameC", "dateC", "markC"),
		]
		def io = new ExamStore("test.json")
		io.update(exams)

		when: "the exams are saved and loaded"
		io.save()
		def result = io.load()

		then: "the loaded one is the same"
		result == exams
	}

	def "update"() {

		given: "some exams stored"
		def exams = [
				new Exam("nameA", "dateA", "markA"),
				new Exam("nameB", "dateB", "markB"),
				new Exam("nameC", "dateC", "markC"),
		]
		def newExams = [
		        new Exam("a", "b", "c"),
				new Exam("nameC", "dateC", "markC"),
		]
		def store = new ExamStore("test.json")
		store.update(exams)
		store.save()

		when: "they are loaded and compared"
		def updated = new ExamStore("test.json").update(newExams)

		then: "the updated are correct"
		updated == [
			new Exam("a", "b", "c"),
		]
	}

}
