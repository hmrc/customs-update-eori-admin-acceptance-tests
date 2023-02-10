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

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object ProblemReplacingEORINumber extends BasePage {

  val PblmreplacingEori                = "/h1[contains(text(),There was a problem replacing the EORI number')]"
  val CurrentlyNotSubscribed           = "//p[text()[contains(.,'has not been subscribed to:')]]"
  val ATAR                             = "//ul/li[contains(text(),'Advance Tariff Registration (ATAR)')]"
  val GVMS                             = "//ul/li[contains(text(),'Goods Vehicle Movement System (GVMS)')]"
  val ReplaceorCancelsubscribtionllink = "//a[contains(text(), 'create a live service ticket on ServiceNow')]"
  val CreateLiveServiceTicket          = "//a[contains(text(), 'Replace another EORI number or cancel subscriptions to HMRC services.')]"

  def verifyEoriNumberNotSubscribed: this.type = {

    if (driver.findElement(By.xpath(PblmreplacingEori)).isDisplayed)
      println("The text is verified")
    else
      println("The text is wrong and can't be verified")

    if (driver.findElement(By.xpath(CurrentlyNotSubscribed)).isDisplayed)
      println("The text is verified")
    else
      println("The text is wrong and can't be verified")

    if (driver.findElement(By.xpath(ATAR)).isDisplayed)
      println("The text is verified")
    else
      println("The text is wrong and can't be verified")

    if (driver.findElement(By.xpath(GVMS)).isDisplayed)
      println("The text is verified")
    else
      println("The text is wrong and can't be verified")

    if (driver.findElement(By.linkText(ReplaceorCancelsubscribtionllink)).isDisplayed)
      println("The link is verified")
    else
      println("The link is not verified")

    if (driver.findElement(By.linkText(CreateLiveServiceTicket)).isDisplayed)
      println("The link is verified")
    else
      println("The link is not verified")
    this
  }

}
