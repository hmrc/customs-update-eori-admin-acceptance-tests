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

class MultiEnrol extends BaseSpec {

  //----------EORI Establishment Details-----------//
  val establishmentDay = "03"
  val establishmentMonth = "12"
  val establishmentYear = "2000"

  Feature("EORI Automation Scenarios-Multi") {

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

      And("User Enter current and new EORI details and continue")
      ReplaceExistingEORINumber.replaceEORI(currentEoriNumber, establishmentDay, establishmentMonth, establishmentYear, newEoriNumber)
      CommonClass.clickContinueBtn
      CommonClass.onPage("Review changes before replacing existing EORI number " + currentEoriNumber + " with " + newEoriNumber)


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
      ReplaceExistingEORINumber.cancelEORI(currentEoriNumber, establishmentDay, establishmentMonth, establishmentYear)
      CommonClass.clickContinueBtn
      CommonClass.onPage("Review changes before cancelling subscriptions for EORI number " + currentEoriNumber)

      And("User click on confirm")
      CommonClass.clickContinueBtn

      Then("Success Message should display")
      CommonClass.cancelSucessMessageValidation(currentEoriNumber)
    }

  }
}

