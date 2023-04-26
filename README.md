

# customs-update-eori-admin-acceptance-tests
UI test suite for the `<digital service name>` using WebDriver and `<scalatest/cucumber>`.  

## Running the tests in local

Prior to executing the tests in local,ensure you have:

Make sure you have a Postman.
Make sure you have docker up and running
Make sure you have mongoDb up and running (with docker will be easy)
Make sure you have pulled the latest service-manager-config in local.

-Start the supporting services using this command:
  sm --start CUSTOMS_UPDATE_EORI_ADMIN_FRONTEND_ALL -r

-On how to create the data in Postman and then use in the code here, please follow this confluence page:
  https://confluence.tools.tax.service.gov.uk/pages/viewpage.action?pageId=653001067

Then execute the `run_tests.sh` script:

    ./run_tests.sh 

## Running the tests in QA

Prior to executing the tests in QA,ensure you have:

Make sure you have changed the url to QA environment: https://admin.qa.tax.service.gov.uk/manage-eori-number
EORI number should be created in QA. Confluence link on how to create data in QA:
  https://confluence.tools.tax.service.gov.uk/display/ET/Test+Execution+in+QA

Then execute the `run_tests.sh` script:

    ./run_tests.sh 




The `run_tests.sh` script defaults to using `chrome` in the `local` environment.  For a complete list of supported param values, see:
 - `src/test/resources/application.conf` for **environment** 
 - [webdriver-factory](https://github.com/hmrc/webdriver-factory#2-instantiating-a-browser-with-default-options) for **browser-driver**

## Running tests against a containerised browser - on a developer machine

The script `./run_browser_with_docker.sh` can be used to start a Chrome, Firefox or Edge container on a developer machine. 
The script requires `remote-chrome`, `remote-firefox` or `remote-edge` as an argument.

Read more about the script's functionality [here](run_browser_with_docker.sh).

To run against a containerised Chrome browser:

```bash
./run_browser_with_docker.sh remote-chrome 
./run_tests.sh remote-chrome local
```

`./run_browser_with_docker.sh` is **NOT** required when running in a CI environment. 

> :warning: **SM2 **: If you use [SM2](https://github.com/hmrc/sm2) rather than [service manager](https://github.com/hmrc/service-manager) please note that this is **NOT** currently supported in build Jenkins.

#### Running the tests against a test environment

To run the tests against an environment set the corresponding `host` environment property as specified under
 `<env>.host.services` in the [application.conf](/src/test/resources/application.conf). 

For example, to execute the `run_tests.sh` script using Chrome remote-webdriver against QA environment 

    ./run_tests.sh remote-chrome qa


### Running tests using BrowserStack
If you would like to run your tests via BrowserStack from your local development environment please refer to the [webdriver-factory](https://github.com/hmrc/webdriver-factory/blob/main/README.md/#user-content-running-tests-using-browser-stack) project.

## Installing local driver binaries

This project supports UI test execution using Firefox (Geckodriver) and Chrome (Chromedriver) browsers. 

See the `drivers/` directory for some helpful scripts to do the installation work for you.  They should work on both Mac and Linux by running the following command:

    ./installGeckodriver.sh <operating-system> <driver-version>
    or
    ./installChromedriver <operating-system> <driver-version>

- *<operating-system>* defaults to **linux64**, however it also supports **macos**
- *<driver-version>* defaults to **0.21.0** for Gecko/Firefox, and the latest release for Chrome.  You can, however, however pass any version available at the [Geckodriver](https://github.com/mozilla/geckodriver/tags) or [Chromedriver](http://chromedriver.storage.googleapis.com/) repositories.

**Note 1:** *You will need to ensure that you have a recent version of Chrome and/or Firefox installed for the later versions of the drivers to work reliably.*

**Note 2** *These scripts use sudo to set the right permissions on the drivers so you will likely be prompted to enter your password.*

## Scalafmt

Check all project files are formatted as expected as follows:

```bash
sbt scalafmtCheckAll scalafmtCheck
```

Format `*.sbt` and `project/*.scala` files as follows:

```bash
sbt scalafmtSbt
```

Format all project files as follows:

```bash
sbt scalafmtAll
```

## ZAP Tests

**There is no need for security testing as this is an internal service. Hence, ZAP tests are not applicable**

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").


**The chromedriver setup required on local machine:**

$1 download the chromedriver from https://chromedriver.chromium.org/downloads based on machine and local browser version


$2 copy the utils.driver to local bin using command: cp /chromedriverlocation /usr/local/bin/


$3 to view that utils.driver in local bin using command: ls -al /usr/local/bin/ | grep chrome

