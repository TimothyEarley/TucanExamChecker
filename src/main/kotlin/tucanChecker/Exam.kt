package tucanChecker

data class Exam(val name: String, val date: String, val mark: String) {
	override fun toString(): String = "$name ($date): $mark"
}