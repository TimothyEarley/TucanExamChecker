# TucanChecker

Gather the exam results from [Tucan](https://www.tucan.tu-darmstadt.de) using a webscraper and send
[IFTTT](https://ifttt.com) notifications if a new one is added.

## Configuration
The config file is in `src/dist/lib/conf/config.properties`, there you can set your Tucan
username and account as well as an IFTTT key.

//TODO add information about other settings

## Running

Either run directly with `./gradlew run` or install the app with
`./gradlew
installShadowApp` which will create everything needed in
`build/installShadow/TucanChecker` (`bin` has the executable).
