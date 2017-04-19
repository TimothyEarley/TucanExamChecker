package tucanChecker


import okhttp3.ResponseBody
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Handles the notification of the IFTTT Event.
 *
 * Created by timmy on 15/10/16.
 */
class IFTTTMaker {

	interface IFTTTService {
		@POST("trigger/{eventName}/with/key/{key}")
		fun fireEvent(@Path("eventName") eventName: String, @Path("key") key: String, @Body msg: Message): Call<ResponseBody>
	}

	data class Message(val value1: String)

	val logger: Logger = LoggerFactory.getLogger(this.javaClass)


	private val service = Retrofit.Builder()
			.baseUrl(Config.IFTTT.baseUrl)
			.addConverterFactory(JacksonConverterFactory.create())
			.build()
			.create(IFTTTService::class.java)

	fun send(exam: Exam) {
		service.fireEvent(Config.IFTTT.eventName, Config.IFTTT.key, Message(exam.toString())).execute()
		logger.info("Send IFTTT Event for exam $exam")
	}
}