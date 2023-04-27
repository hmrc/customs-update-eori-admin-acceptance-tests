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
import uk.gov.hmrc.test.ui.stubs.{Enrolment, EnrolmentStoreProxyStub}

class EORIAutomation extends BaseSpec {

  //---------Auth Login Details----------//
  val pid = "12345"
  val givenName = "Automation"
  val surName = "Test"
  val email = "abcdef@hmrc.com"
  val roles = "update-enrolment-eori"

  //----------EORI Establishment Details-----------//
  val establishmentDay = "03"
  val establishmentMonth = "12"
  val establishmentYear = "2000"

  override def beforeAll() {
    CommonClass.loadPage
    StripeIDPLoginPage.loginStub(pid, givenName, surName, email, roles)
    CommonClass.clickContinueBtn
  }

  override def afterAll() {
    EnrolmentStoreProxyStub.cleanStubData()
  }

  Feature("EORI Automation Scenarios") {

    Scenario("Replace and Cancel EORI number- GVMS") {

      Given("User logs into EORI Toolkit homepage")
      CommonClass.loadPage
      CommonClass.onPage("Do you want to replace an existing EORI number or cancel subscriptions to HMRC services?")

      And("User has a EORI number which needs to be updated")
      val currentEoriNumber = "GB223311111006"
      val newEoriNumber = "GB223311111007"
      val enrolment = Enrolment("HMRC-GVMS-ORG", currentEoriNumber)
      EnrolmentStoreProxyStub.createEnrolments("90ccf333-65d2-4bf2-a008-01dfca70281", "00000123481", List(enrolment))

      When("User select Replace radio option and click on continue")
      CommonClass.selectRadioOption("Replace")
      CommonClass.clickContinueBtn
      CommonClass.onPage("Replace an existing EORI number")
      CommonClass.onPageLabelValidation(" What is the trader’s current EORI number?")
      CommonClass.onPage("What date was the trader established?")
      CommonClass.onPageLabelValidation("What is the trader’s new EORI number?")

      And("User Enter current and new EORI details and continue")
      ReplaceExistingEORINumber.replaceEORI(currentEoriNumber, establishmentDay, establishmentMonth, establishmentYear, newEoriNumber)
      CommonClass.clickContinueBtn
      CommonClass.onPage("Review changes before replacing existing EORI number " + currentEoriNumber + " with " + newEoriNumber)
      CommonClass.onPageObjectValidation("button", "Confirm Changes")
      CommonClass.onPageObjectValidation("a", "Cancel changes and start again")

      And("User click on confirm")
      CommonClass.clickContinueBtn

      Then("Success Message should display")
      CommonClass.successMessageValidation(currentEoriNumber, newEoriNumber)

      And("User launches EORI toolkit homepage")
      CommonClass.loadPage
      CommonClass.onPage("Do you want to replace an existing EORI number or cancel subscriptions to HMRC services?")

      And("User has a EORI number which needs to be cancelled")
      val eoriNumber = "GB111111111001"
      val enrolment1 = Enrolment("HMRC-GVMS-ORG", eoriNumber)
      EnrolmentStoreProxyStub.createEnrolments("90ccf333-65d2-4bf2-a008-01dfca70282", "00000123482", List(enrolment1))

      When("User selects Cancel journey and click Continue")
      CommonClass.selectRadioOption("Cancel")
      CommonClass.clickContinueBtn

      And("User Enter EORI details and continue")
      CommonClass.onPage("Cancel a trader’s subscriptions to HMRC services")
      CommonClass.onPageLabelValidation("What is the trader’s current EORI number?")
      CommonClass.onPage("What date was the trader established?")
      ReplaceExistingEORINumber.cancelEORI(eoriNumber, establishmentDay, establishmentMonth, establishmentYear)
      CommonClass.clickContinueBtn
      CommonClass.onPage("Review changes before cancelling subscriptions for EORI number " + eoriNumber)
      CommonClass.onPageObjectValidation("button", "Confirm Changes")
      CommonClass.onPageObjectValidation("a", "Cancel changes and start again")

      And("User click on confirm")
      CommonClass.clickContinueBtn

      Then("Success Message should display")
      CommonClass.cancelSucessMessageValidation(eoriNumber)
    }

    Scenario("Replace and Cancel EORI number- GB S&S") {

      Given("User logs into EORI Toolkit homepage")
      CommonClass.loadPage
      CommonClass.onPage("Do you want to replace an existing EORI number or cancel subscriptions to HMRC services?")

      And("User has a EORI number which needs to be updated")
      val currentEoriNumber = "GB223322222006"
      val newEoriNumber = "GB223322222007"
      val enrolment = Enrolment("HMRC-SS-ORG", currentEoriNumber)
      EnrolmentStoreProxyStub.createEnrolments("90ccf333-65d2-4bf2-a008-01dfca70283", "00000123483", List(enrolment))

      When("User select Replace radio option and click on continue")
      CommonClass.selectRadioOption("Replace")
      CommonClass.clickContinueBtn
      CommonClass.onPage("Replace an existing EORI number")
      CommonClass.onPageLabelValidation(" What is the trader’s current EORI number?")
      CommonClass.onPage("What date was the trader established?")
      CommonClass.onPageLabelValidation("What is the trader’s new EORI number?")

      And("User Enter current and new EORI details and continue")
      ReplaceExistingEORINumber.replaceEORI(currentEoriNumber, establishmentDay, establishmentMonth, establishmentYear, newEoriNumber)
      CommonClass.clickContinueBtn
      CommonClass.onPage("Review changes before replacing existing EORI number " + currentEoriNumber + " with " + newEoriNumber)
      CommonClass.onPageObjectValidation("button", "Confirm Changes")
      CommonClass.onPageObjectValidation("a", "Cancel changes and start again")

      And("User click on confirm")
      CommonClass.clickContinueBtn

      Then("Success Message should display")
      CommonClass.successMessageValidation(currentEoriNumber, newEoriNumber)

      And("User launches EORI toolkit homepage")
      CommonClass.loadPage
      CommonClass.onPage("Do you want to replace an existing EORI number or cancel subscriptions to HMRC services?")

      And("User has a EORI number which needs to be cancelled")
      val eoriNumber = "GB111111111002"
      val enrolment1 = Enrolment("HMRC-SS-ORG", eoriNumber)
      EnrolmentStoreProxyStub.createEnrolments("90ccf333-65d2-4bf2-a008-01dfca70284", "00000123484", List(enrolment1))

      When("User selects Cancel journey and click Continue")
      CommonClass.selectRadioOption("Cancel")
      CommonClass.clickContinueBtn

      And("User Enter EORI details and continue")
      CommonClass.onPage("Cancel a trader’s subscriptions to HMRC services")
      CommonClass.onPageLabelValidation("What is the trader’s current EORI number?")
      CommonClass.onPage("What date was the trader established?")
      ReplaceExistingEORINumber.cancelEORI(eoriNumber, establishmentDay, establishmentMonth, establishmentYear)
      CommonClass.clickContinueBtn
      CommonClass.onPage("Review changes before cancelling subscriptions for EORI number " + eoriNumber)
      CommonClass.onPageObjectValidation("button", "Confirm Changes")
      CommonClass.onPageObjectValidation("a", "Cancel changes and start again")

      And("User click on confirm")
      CommonClass.clickContinueBtn

      Then("Success Message should display")
      CommonClass.cancelSucessMessageValidation(eoriNumber)
    }
  }
}