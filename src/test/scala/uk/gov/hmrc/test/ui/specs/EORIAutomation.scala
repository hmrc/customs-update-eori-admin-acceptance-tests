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

      When("User selects Cancel journey and click Continue")
      CommonClass.selectRadioOption("Cancel")
      CommonClass.clickContinueBtn

      And("User Enter EORI details and continue")
      CommonClass.onPage("Cancel a trader’s subscriptions to HMRC services")
      CommonClass.onPageLabelValidation("What is the trader’s current EORI number?")
      CommonClass.onPage("What date was the trader established?")
      ReplaceExistingEORINumber.cancelEORI(currentEoriNumber, establishmentDay, establishmentMonth, establishmentYear)
      CommonClass.clickContinueBtn
      CommonClass.onPage("Review changes before cancelling subscriptions for EORI number " + currentEoriNumber)
      CommonClass.onPageObjectValidation("button", "Confirm Changes")
      CommonClass.onPageObjectValidation("a", "Cancel changes and start again")

      And("User click on confirm")
      CommonClass.clickContinueBtn

      Then("Success Message should display")
      CommonClass.cancelSucessMessageValidation(currentEoriNumber)
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

      When("User selects Cancel journey and click Continue")
      CommonClass.selectRadioOption("Cancel")
      CommonClass.clickContinueBtn

      And("User Enter EORI details and continue")
      CommonClass.onPage("Cancel a trader’s subscriptions to HMRC services")
      CommonClass.onPageLabelValidation("What is the trader’s current EORI number?")
      CommonClass.onPage("What date was the trader established?")
      ReplaceExistingEORINumber.cancelEORI(currentEoriNumber, establishmentDay, establishmentMonth, establishmentYear)
      CommonClass.clickContinueBtn
      CommonClass.onPage("Review changes before cancelling subscriptions for EORI number " + currentEoriNumber)
      CommonClass.onPageObjectValidation("button", "Confirm Changes")
      CommonClass.onPageObjectValidation("a", "Cancel changes and start again")

      And("User click on confirm")
      CommonClass.clickContinueBtn

      Then("Success Message should display")
      CommonClass.cancelSucessMessageValidation(currentEoriNumber)
    }

    Scenario("Replace and Cancel EORI number- ATaR") {

      Given("User logs into EORI Toolkit homepage")
      CommonClass.loadPage
      CommonClass.onPage("Do you want to replace an existing EORI number or cancel subscriptions to HMRC services?")

      And("User has a EORI number which needs to be updated")
      val currentEoriNumber = "GB223333333006"
      val newEoriNumber = "GB223333333007"
      val enrolment = Enrolment("HMRC-ATAR-ORG", currentEoriNumber)
      EnrolmentStoreProxyStub.createEnrolments("90ccf333-65d2-4bf2-a008-01dfca70285", "00000123485", List(enrolment))

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

      When("User selects Cancel journey and click Continue")
      CommonClass.selectRadioOption("Cancel")
      CommonClass.clickContinueBtn

      And("User Enter EORI details and continue")
      CommonClass.onPage("Cancel a trader’s subscriptions to HMRC services")
      CommonClass.onPageLabelValidation("What is the trader’s current EORI number?")
      CommonClass.onPage("What date was the trader established?")
      ReplaceExistingEORINumber.cancelEORI(currentEoriNumber, establishmentDay, establishmentMonth, establishmentYear)
      CommonClass.clickContinueBtn
      CommonClass.onPage("Review changes before cancelling subscriptions for EORI number " + currentEoriNumber)
      CommonClass.onPageObjectValidation("button", "Confirm Changes")
      CommonClass.onPageObjectValidation("a", "Cancel changes and start again")

      And("User click on confirm")
      CommonClass.clickContinueBtn

      Then("Success Message should display")
      CommonClass.cancelSucessMessageValidation(currentEoriNumber)
    }

    Scenario("Replace and Cancel EORI number- EU Subs") {

      Given("User logs into EORI Toolkit homepage")
      CommonClass.loadPage
      CommonClass.onPage("Do you want to replace an existing EORI number or cancel subscriptions to HMRC services?")

      And("User has a EORI number which needs to be updated")
      val currentEoriNumber = "GB223344444006"
      val newEoriNumber = "GB223344444007"
      val enrolment = Enrolment("HMRC-ESC-ORG", currentEoriNumber)
      EnrolmentStoreProxyStub.createEnrolments("90ccf333-65d2-4bf2-a008-01dfca70287", "00000123487", List(enrolment))

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

      When("User selects Cancel journey and click Continue")
      CommonClass.selectRadioOption("Cancel")
      CommonClass.clickContinueBtn

      And("User Enter EORI details and continue")
      CommonClass.onPage("Cancel a trader’s subscriptions to HMRC services")
      CommonClass.onPageLabelValidation("What is the trader’s current EORI number?")
      CommonClass.onPage("What date was the trader established?")
      ReplaceExistingEORINumber.cancelEORI(currentEoriNumber, establishmentDay, establishmentMonth, establishmentYear)
      CommonClass.clickContinueBtn

      Then("Error Message should display")
      CommonClass.onPage("Cancel subscriptions for " +currentEoriNumber)
      CommonClass.cancelErrorMessageValidation(currentEoriNumber)
      CommonClass.onPageObjectValidation("a", "replace an existing EORI number or cancel subscriptions to HMRC services")
    }

    Scenario("Replace and Cancel EORI number- CDS") {

      Given("User logs into EORI Toolkit homepage")
      CommonClass.loadPage
      CommonClass.onPage("Do you want to replace an existing EORI number or cancel subscriptions to HMRC services?")

      And("User has a EORI number which needs to be updated")
      val currentEoriNumber = "GB223355555006"
      val newEoriNumber = "GB223355555007"
      val enrolment = Enrolment("HMRC-CUS-ORG", currentEoriNumber)
      EnrolmentStoreProxyStub.createEnrolments("90ccf333-65d2-4bf2-a008-01dfca70289", "00000123489", List(enrolment))

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

      When("User selects Cancel journey and click Continue")
      CommonClass.selectRadioOption("Cancel")
      CommonClass.clickContinueBtn

      And("User Enter EORI details and continue")
      CommonClass.onPage("Cancel a trader’s subscriptions to HMRC services")
      CommonClass.onPageLabelValidation("What is the trader’s current EORI number?")
      CommonClass.onPage("What date was the trader established?")
      ReplaceExistingEORINumber.cancelEORI(currentEoriNumber, establishmentDay, establishmentMonth, establishmentYear)
      CommonClass.clickContinueBtn

      Then("Error Message should display")
      CommonClass.onPage("Cancel subscriptions for " + currentEoriNumber)
      CommonClass.cancelErrorMessageValidation(currentEoriNumber)
      CommonClass.onPageObjectValidation("a", "replace an existing EORI number or cancel subscriptions to HMRC services")
    }

    Scenario("Replace and Cancel EORI number - CTS( NDRC, Route1 and C18)") {

      Given("User logs into EORI Toolkit homepage")
      CommonClass.loadPage
      CommonClass.onPage("Do you want to replace an existing EORI number or cancel subscriptions to HMRC services?")

      And("User has a EORI number which needs to be updated")
      val currentEoriNumber = "GB223366666006"
      val newEoriNumber = "GB223366666007"
      val enrolment = Enrolment("HMRC-CTS-ORG", currentEoriNumber)
      EnrolmentStoreProxyStub.createEnrolments("90ccf333-65d2-4bf2-a008-01dfca70291", "00000123491", List(enrolment))

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

      When("User selects Cancel journey and click Continue")
      CommonClass.selectRadioOption("Cancel")
      CommonClass.clickContinueBtn

      And("User Enter EORI details and continue")
      CommonClass.onPage("Cancel a trader’s subscriptions to HMRC services")
      CommonClass.onPageLabelValidation("What is the trader’s current EORI number?")
      CommonClass.onPage("What date was the trader established?")
      ReplaceExistingEORINumber.cancelEORI(currentEoriNumber, establishmentDay, establishmentMonth, establishmentYear)
      CommonClass.clickContinueBtn

      Then("Error Message should display")
      CommonClass.onPage("Cancel subscriptions for " + currentEoriNumber)
      CommonClass.cancelErrorMessageValidation(currentEoriNumber)
      CommonClass.onPageObjectValidation("a", "replace an existing EORI number or cancel subscriptions to HMRC services")
    }


    Scenario("Replace and Cancel EORI number- All Services") {

      Given("User logs into EORI Toolkit homepage")
      CommonClass.loadPage
      CommonClass.onPage("Do you want to replace an existing EORI number or cancel subscriptions to HMRC services?")

      And("User has a EORI number which needs to be updated")
      val currentEoriNumber = "GB223388888006"
      val newEoriNumber = "GB223388888007"
      val enrolment1 = Enrolment("HMRC-GVMS-ORG", currentEoriNumber)
      val enrolment2 = Enrolment("HMRC-SS-ORG", currentEoriNumber)
      val enrolment3 = Enrolment("HMRC-ATAR-ORG", currentEoriNumber)
      val enrolment4 = Enrolment("HMRC-CUS-ORG", currentEoriNumber)
      val enrolment5 = Enrolment("HMRC-CTS-ORG", currentEoriNumber)
      val enrolment6 = Enrolment("HMRC-ESC-ORG", currentEoriNumber)
      val enrolment7 = Enrolment("HMRC-CTC-ORG", currentEoriNumber)
      EnrolmentStoreProxyStub.createEnrolments("90ccf333-65d2-4bf2-a008-01dfca70295", "00000123495", List(enrolment1, enrolment2, enrolment3,enrolment4, enrolment5, enrolment6, enrolment7))

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

      When("User selects Cancel journey and click Continue")
      CommonClass.selectRadioOption("Cancel")
      CommonClass.clickContinueBtn

      And("User Enter EORI details and continue")
      CommonClass.onPage("Cancel a trader’s subscriptions to HMRC services")
      CommonClass.onPageLabelValidation("What is the trader’s current EORI number?")
      CommonClass.onPage("What date was the trader established?")
      ReplaceExistingEORINumber.cancelEORI(currentEoriNumber, establishmentDay, establishmentMonth, establishmentYear)
      CommonClass.clickContinueBtn
      CommonClass.onPage("Review changes before cancelling subscriptions for EORI number " + currentEoriNumber)
      CommonClass.onPageObjectValidation("button", "Confirm Changes")
      CommonClass.onPageObjectValidation("a", "Cancel changes and start again")

      And("User click on confirm")
      CommonClass.clickContinueBtn

      Then("Success Message should display")
      CommonClass.cancelSucessMessageValidation(currentEoriNumber)
    }

    Scenario("Replace and Cancel EORI number - CTC") {

      Given("User logs into EORI Toolkit homepage")
      CommonClass.loadPage
      CommonClass.onPage("Do you want to replace an existing EORI number or cancel subscriptions to HMRC services?")

      And("User has a EORI number which needs to be updated")
      val currentEoriNumber = "GB223377777006"
      val newEoriNumber = "GB223377777007"
      val enrolment = Enrolment("HMRC-CTC-ORG", currentEoriNumber)
      EnrolmentStoreProxyStub.createEnrolments("90ccf333-65d2-4bf2-a008-01dfca70293", "00000123493", List(enrolment))

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

      Then("Error Message should display")
      CommonClass.onPage("Replacing existing EORI number " + currentEoriNumber)
      CommonClass.replaceErrorMessageValidation(currentEoriNumber)
      CommonClass.onPageObjectValidation("a", "replace an existing EORI number or cancel subscriptions to HMRC services")

      And("User launches EORI toolkit homepage")
      CommonClass.loadPage
      CommonClass.onPage("Do you want to replace an existing EORI number or cancel subscriptions to HMRC services?")

      When("User selects Cancel journey and click Continue")
      CommonClass.selectRadioOption("Cancel")
      CommonClass.clickContinueBtn

      And("User Enter EORI details and continue")
      CommonClass.onPage("Cancel a trader’s subscriptions to HMRC services")
      CommonClass.onPageLabelValidation("What is the trader’s current EORI number?")
      CommonClass.onPage("What date was the trader established?")
      ReplaceExistingEORINumber.cancelEORI(currentEoriNumber, establishmentDay, establishmentMonth, establishmentYear)
      CommonClass.clickContinueBtn

      Then("Error Message should display")
      CommonClass.onPage("Cancel subscriptions for " + currentEoriNumber)
      CommonClass.cancelErrorMessageValidation(currentEoriNumber)
      CommonClass.onPageObjectValidation("a", "replace an existing EORI number or cancel subscriptions to HMRC services")
    }

  }
}

