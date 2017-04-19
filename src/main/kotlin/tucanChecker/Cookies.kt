package tucanChecker

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import java.util.*

/**
 * Simple class for storing cookies in a set
 */
class Cookies(private val cookieStore: MutableSet<Cookie> = HashSet()) : CookieJar {
	override fun loadForRequest(url: HttpUrl?): List<Cookie> = cookieStore.toList()
	override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
		cookieStore.addAll(cookies)
	}

}