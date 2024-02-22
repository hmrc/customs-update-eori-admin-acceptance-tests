#!/bin/bash -e

DRIVER="-Dwebdriver.chrome.driver=/usr/local/bin/chromedriver"

# Scalafmt checks have been separated from the test command to avoid OutOfMemoryError in Jenkins
#sbt scalafmtCheckAll scalafmtSbtCheck

sbt -Dbrowser="remote-chrome" -Denvironment="staging" $DRIVER "testOnly uk.gov.hmrc.test.ui.specs.*"
