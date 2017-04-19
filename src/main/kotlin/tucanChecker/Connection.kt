package tucanChecker

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class Connection(
		val host: String,
		private val path: String
) {

	private val client = OkHttpClient.Builder()
			//TODO remove
//			.authenticator { route, response ->
//				println("Authentication required for $route ($response)")
//				response.request()
//			}
			.cookieJar(Cookies())
			.build()

	fun query(params: Map<String, String>): Response {

		val url = HttpUrl.Builder()
				.scheme("https")
				.host(host)
				.addPathSegments(path)
				.apply {
					params.forEach { k, v -> addQueryParameter(k, v) }
				}
				.build()

		val request = Request.Builder().url(url).build()

		return client.newCall(request).execute()
	}

}