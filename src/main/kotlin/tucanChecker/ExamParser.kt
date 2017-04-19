package tucanChecker

import org.jsoup.Jsoup

object ExamParser {

	fun parse(html: String, base: String): List<Exam> = Jsoup.parse(html, base)
			.getElementById("contentSpacer_IE")
			.getElementsByTag("table")
			.first()
			.getElementsByTag("tbody")
			.first()
			.getElementsByTag("tr")
			.map { it.getElementsByTag("td") }
			.map {
				val name = it[0].text()
				val date = it[1].text()
				val mark = it[2].text()
				Exam(name, date, mark)
			}

}
