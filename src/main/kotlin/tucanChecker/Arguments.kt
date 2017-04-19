package tucanChecker

object Parameters {

	val appName = "APPNAME" to "CampusNet"

	val loginArgs = mapOf(
			appName,
			"PRGNAME" to "LOGINCHECK",
			"usrname" to Config.Tucan.username,
			"pass" to Config.Tucan.password,
			"clino" to "000000000000001",
			"menuno" to "000344",
			"menu_type" to "classic",
			"browser" to "",
			"platform" to "",
			"ARGUMENTS" to "clino,usrname,pass,menuno,menu_type,browser,platform"
	)

	val startArgs = mapOf(
			appName,
			"PRGNAME" to "STARTPAGE_DISPATCH",
			"ARGUMENTS" to "-N000000000000001"
	)

}