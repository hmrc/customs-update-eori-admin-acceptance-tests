/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.test.ui.specs

import uk.gov.hmrc.test.ui.pages.CheckYourVATHomePage.provideVATPeriod
import uk.gov.hmrc.test.ui.pages.CheckYourVATResult.{result, useSetVATFlatRate, useUniqueVATFlatRate}
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.tags.ZapTests
import uk.gov.hmrc.webdriver.SingletonDriver.closeInstance

class ExampleSpec extends BaseSpec {

  Feature("User trying to update the EORI") {

    Scenario("User is trying to replace the  EORI information", ZapTests) {
      //Remove ZapTests tag if not required

      Given("I am on EORI Login Page and enter valid information")
      LoginPage.loadPage
      LoginPage.loginInfo("12345", "update-enrolment-eori")
      When("I am selecting the service management type as Replace an existing EORI number")
      EoriServiceMgmt.eorimgmtserice("Replace")
      And("I am entering the valid information to replace the EORI number details")
      ReplaceEoriPage.eoriMgmtReplace("GB123456123456", "05", "01", "2023", "GB123456123789")
    }

  }
}
