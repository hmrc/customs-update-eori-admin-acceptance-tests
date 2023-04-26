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

import org.openqa.selenium.remote.RemoteWebDriver
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.tags.Wip

class EORIAutomation extends BaseSpec {
//---------------------------------Test Data----------------------------------//

  //---------Auth Login Details----------//
  val pid="12345"
  val givenName="Automation"
  val surName="Test"
  val email="abcdef@hmrc.com"
  val roles="update-enrolment-eori"

  //----------Replace EORI details-----------//

  val currentEORI = "GB111111111006"
  val replaceEORI_day = "03"
  val replaceEORI_month = "12"
  val replaceEORI_year = "2000"
  val newEORI = "GB111111111007"

  //------------Cancel EORI------------------//
  val cancelEORI = "GB111111111006"
  val cancelEORI_day = "03"
  val cancelEORI_month = "12"
  val cancelEORI_year = "2000"

  Feature("EORI Automation Scenarios") {

    Scenario("Replace EORI number") {

      Given("User logs into EORI Toolkit homepage")
      CommonClass.loadPage
      StripeIDPLoginPage.loginStub(pid, givenName, surName, email, roles)
      CommonClass.clickContinueBtn
      driver.navigate().to("http://localhost:11120/manage-eori-number")
      CommonClass.onPage("Do you want to replace an existing EORI number or cancel subscriptions to HMRC services?")


      When("User select Replace radio option and click on continue")
      CommonClass.selectRadioOption("Replace")
      CommonClass.clickContinueBtn
      CommonClass.onPage("Replace an existing EORI number")
      CommonClass.onPageLabelValidation(" What is the trader’s current EORI number?")
      CommonClass.onPage("What date was the trader established?")
      CommonClass.onPageLabelValidation("What is the trader’s new EORI number?")

      And("User Enter current and new EORI details and continue")
      ReplaceExistingEORINumber.replaceEORI(currentEORI, replaceEORI_day, replaceEORI_month, replaceEORI_year, newEORI)
      CommonClass.clickContinueBtn
      CommonClass.onPage("Review changes before replacing existing EORI number "+currentEORI+" with "+newEORI)
      CommonClass.onPageObjectValidation("button","Confirm Changes")
      CommonClass.onPageObjectValidation("a","Cancel changes and start again")

      And("User click on confirm")
      CommonClass.clickContinueBtn

      Then("Success Message should display")
      CommonClass.successMessageValidation(currentEORI, newEORI)
    }


    Scenario("Cancel EORI number") {

      Given("User launches EORI toolkit homepage")
      CommonClass.clickContinueBtn
      CommonClass.onPage("Do you want to replace an existing EORI number or cancel subscriptions to HMRC services?")

      When("User selects Cancel journey and click Continue")
      CommonClass.selectRadioOption("Cancel")
      CommonClass.clickContinueBtn


      And("User Enter EORI details and continue")
      CommonClass.onPage("Cancel a trader’s subscriptions to HMRC services")
      CommonClass.onPageLabelValidation("What is the trader’s current EORI number?")
      CommonClass.onPage("What date was the trader established?")
      ReplaceExistingEORINumber.cancelEORI(cancelEORI, cancelEORI_day, cancelEORI_month, cancelEORI_year)
      CommonClass.clickContinueBtn
      CommonClass.onPage("Review changes before cancelling subscriptions for EORI number "+cancelEORI)
      CommonClass.onPageObjectValidation("button","Confirm Changes")
      CommonClass.onPageObjectValidation("a","Cancel changes and start again")

      And("User click on confirm")
      CommonClass.clickContinueBtn

      Then("Success Message should display")
      CommonClass.cancelSucessMessageValidation(cancelEORI)
    }

  }
}
