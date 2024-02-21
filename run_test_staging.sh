#!/bin/bash -e

DRIVER="-Dwebdriver.chrome.driver=$(which chromedriver)"

# Scalafmt checks have been separated from the test command to avoid OutOfMemoryError in Jenkins
#sbt scalafmtCheckAll scalafmtSbtCheck

sbt -Dbrowser="remote-chrome" -Denvironment="${ENV:=staging}" $DRIVER "testOnly uk.gov.hmrc.test.ui.specs.*"
