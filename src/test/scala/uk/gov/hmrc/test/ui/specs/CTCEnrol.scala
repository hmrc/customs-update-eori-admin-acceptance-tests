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

class CTCEnrol extends BaseSpec {
  //----------EORI Establishment Details-----------//
  val establishmentDay = "03"
  val establishmentMonth = "12"
  val establishmentYear = "2000"

  Feature("EORI Automation Scenarios-CTC") {

    Scenario("Replace and Cancel EORI number - CTC") {

      Given("User logs into EORI Toolkit homepage")
      CommonClass.loadPage
      CommonClass.onPage("Do you want to replace an existing EORI number or cancel subscriptions to HMRC services?")
      Thread.sleep(5000)

      And("User has a EORI number which needs to be updated")
      val currentEoriNumber = "GB223377777006"
      val newEoriNumber = "GB223377777007"
      val enrolment = Enrolment("HMRC-CTC-ORG", currentEoriNumber)
      EnrolmentStoreProxyStub.createEnrolments("90ccf333-65d2-4bf2-a008-01dfca70293", "00000123493", List(enrolment))
      Thread.sleep(5000)

      When("User select Replace radio option and click on continue")
      CommonClass.selectRadioOption("Replace")
      CommonClass.clickContinueBtn
      CommonClass.onPage("Replace an existing EORI number")

      And("User Enter current and new EORI details and continue")
      ReplaceExistingEORINumber.replaceEORI(currentEoriNumber, establishmentDay, establishmentMonth, establishmentYear, newEoriNumber)
      CommonClass.clickContinueBtn

      Then("Error Message should display")
      CommonClass.onPage("Replacing existing EORI number " + currentEoriNumber)
      CommonClass.replaceErrorMessageValidation(currentEoriNumber)

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

      Then("Error Message should display")
      CommonClass.onPage("Cancel subscriptions for " + currentEoriNumber)
      CommonClass.cancelErrorMessageValidation(currentEoriNumber)
    }

  }
}

