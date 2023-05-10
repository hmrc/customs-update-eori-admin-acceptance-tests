#!/bin/bash -e
DEFAULT_BROWSER=chrome
BROWSER_TYPE=$1
ENV=$2

if [ -z "$BROWSER_TYPE" ]; then
    echo "BROWSER_TYPE value not set, defaulting to $DEFAULT_BROWSER..."
    echo ""
fi

sbt -Dbrowser="${BROWSER_TYPE:=$DEFAULT_BROWSER}" -Denvironment="${ENV:=local}" -Daccessibility.test=true "testOnly uk.gov.hmrc.test.ui.specs.* -- -n a11y"
