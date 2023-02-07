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

package uk.gov.hmrc.test.ui.cucumber.stepdefs

import uk.gov.hmrc.test.ui.pages.Turnover.submitPage
import uk.gov.hmrc.test.ui.pages._
//import uk.gov.hmrc.test.ui.pages.RequiredInformationPage.{onPage, submitPage}

class StepDefinitions extends BaseStepDef with BasePage {
  Given("I am EORI Toolkit user"){()=>
    println("Execution started....")
   invokeURL()
  }
  When("I enter (.*),(.*),(.*),(.*),(.*) and click on Continue") {
    (EORINumber:String,GivenName:String,Surname:String,Email:String,Roles:String) =>
      StripeIDPLoginPage.loginStub(EORINumber,GivenName,Surname,Email,Roles )
      submitPage()
  }
  Then(
    "I should move to the next page"
  ) {
    () =>
      println("Execution completed...")
  }

}
