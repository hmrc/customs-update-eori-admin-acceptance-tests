#!/bin/bash -e

BROWSER=${1:-chrome}
ENVIRONMENT=${2:-local}

sbt clean -Dbrowser="${BROWSER}" -Denvironment="${ENVIRONMENT}" -Dbrowser.option.headless=false "testOnly uk.gov.hmrc.test.ui.specs.*" testReport
