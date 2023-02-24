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

import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.tags.Wip

class EORIAutomation extends BaseSpec {

  Feature("EORI Automation Scenarios") {
val pid="1234"
    val givenName="Automation"
    val surName="Test"
    val email="abcdef@hmrc.com"
    val roles="update-enrolment-eori"

    //update EORI details

    val currentEORI = "GB130002023001"
    val day = "03"
    val month = "12"
    val year = "2000"
    val newEORI = "GB130002023002"

    Scenario("Replace EORI number", Wip) {
      //Remove ZapTests tag if not required

      Given("User launches Stride Identity Provider Login page")
      CommonClass.loadPage

      When("User enters values and logs in to the EORI Toolkit homepage")
      StripeIDPLoginPage.loginStub(pid,givenName,surName,email,roles)
     CommonClass.clickContinueBtn

      And("User click on EORI number management service link")
      CommonClass.clickEORINumberMgntLink
      CommonClass.onPage("Do you want to replace an existing EORI number or cancel subscriptions to HMRC services?")

      And("User select Replace radio option and click on continue")
      CommonClass.selectRadioOption("Replace")
      CommonClass.clickContinueBtn
      CommonClass.onPage("Replace an existing EORI number")

      And("User Enter current and new EORI details and continue")
      ReplaceExistingEORINumber.replaceEORI(currentEORI, day, month, year, newEORI)
      CommonClass.clickContinueBtn
      CommonClass.onPage("Are you sure you want to replace the current EORI number with " + newEORI + "?")

      And("User click on confirm")
      CommonClass.clickContinueBtn

      Then("Success Message should display")
      CommonClass.sucessMessageValidation(currentEORI, newEORI)
    }
  }
}
