

# customs-update-eori-admin-acceptance-tests
UI test suite for the `EORI Toolkit` using UITestRunner and `<scalatest>`.  

**'Jenkins Pipeline'** : 
 https://build.tax.service.gov.uk/job/EORI/job/customs-update-eori-admin-frontend-acceptance-tests/


## Pre-requisites

Prior to executing the tests ensure you have:
- Installed [MongoDB](https://docs.mongodb.com/manual/installation/)
- Installed/configured [service manager](https://github.com/hmrc/sm2).

### Services

Start Mongo as follows:

```bash
sudo mongod
```

Start `CUSTOMS_UPDATE_EORI_ADMIN_FRONTEND_ALL` services locally as follows:

```bash
sm2 --start CUSTOMS_UPDATE_EORI_ADMIN_FRONTEND_ALL
```

### Selenium Grid

Confirm that [docker-selenium-grid](https://github.com/hmrc/docker-selenium-grid) is up-to-date and follow the provided [instructions](https://github.com/hmrc/docker-selenium-grid/blob/main/README.md).

or use [local-selenium-grid](https://github.com/hmrc/local-selenium-grid) and follow the provided [instructions](https://github.com/hmrc/local-selenium-grid/blob/main/README.md).

## Tests

Run tests as follows:

* Argument `<browser>` must be `chrome`, `edge` or `firefox`.
* Argument `<environment>` must be `local`, `qa` or `staging`.

```bash
./run-tests.sh <browser> <environment>
```

-how to create the data in Postman and then use in the code here, please follow this confluence page:
https://confluence.tools.tax.service.gov.uk/pages/viewpage.action?pageId=653001067


## Running the tests in QA

Prior to executing the tests in QA,ensure you have:

Make sure you have changed the url to QA environment: https://admin.qa.tax.service.gov.uk/manage-eori-number
EORI number should be created in QA. Confluence link on how to create data in QA:
  https://confluence.tools.tax.service.gov.uk/display/ET/Test+Execution+in+QA

Then execute the `run-tests.sh`

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
